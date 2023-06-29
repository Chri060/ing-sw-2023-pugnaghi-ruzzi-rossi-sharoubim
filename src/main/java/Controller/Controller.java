package Controller;

import Distributed.Messages.serverMessages.GameEndedMessage;
import Exceptions.InvalidActionException;
import Exceptions.InvalidArgumentException;
import Model.Model;
import Model.entities.Point;
import util.PlanarCoordinate;

import java.util.List;

/**
 * Class that implements the controller for the actions of the players
 */
public class Controller {

    private Model model;

    /**
     * Constructs a Controller object to operate on the given model
     *
     * @param model is the Model controlled by the Controller
     */
    public Controller(Model model) {
        this.model = model;
    }

    /**
     * Sets the room size only if the player that is trying to change it is the room leader
     *
     * @param size is the size to set
     * @param playerName is the name of the player that is trying to change the room size
     */
    public void setRoomSize(int size, String playerName) {
        synchronized (model) {
            if (!playerName.equals(model.getRoomLeader())) {
                return;
            }
            if (size < 2 || size > 4) {
                return;
            }
            model.setTargetRoomSize(size);
        }
    }

    /**
     * Let a player join the room after checking if it is possible.
     * Starts the match if the number of player in the room equals the number set by the room leader
     *
     * @param playerName is the name of the player to add
     */
    public void join(String playerName) {
        synchronized (model) {
            if (playerName == null) {
                return;
            }
            if (model.getGameStatus() != Model.GameStatus.PREMATCH) {
                model.setPlayerOnline(playerName);
                return;
            }
            try {
                model.joinPlayer(playerName);
                if (model.getRoomSize() == model.getTargetRoomSize() && model.getRoomSize() > 1) {
                    model.start();
                }
            } catch (InvalidActionException e) {
            }
        }
    }

    /**
     * Let a player leave the mach
     *
     * @param playerName is the name of the player who requested to leave
     */
    public void leave(String playerName) {
        synchronized (model) {
            if (playerName == null) {
                return;
            }
            if (model.getGameStatus() == Model.GameStatus.PREMATCH) {
                model.removePlayer(playerName);
            }
            else {
                model.setPlayerOffline(playerName);
            }
        }
    }

    /**
     * Sends a chat message to the player selected in the list
     *
     * @param playerName is the name of the player who is sending the message
     * @param receivers is the list of receivers
     * @param message is the message that has been sent
     */
    public void chatMessage(String playerName, List<String> receivers, String message) {
        synchronized (model) {
            if (playerName == null || receivers == null || message == null) {
                return;
            }
            model.sendChatMessage(playerName, receivers, message);
        }
    }

    /**
     * Check if the player who is doing the action is the right one or not
     *
     * @param playerName is the name of the player who is trying to do the action
     *
     * @return true if the player is correct, false otherwise
     */
    private boolean isYourTurn(String playerName) {
        synchronized (model) {
            return (model.getCurrentPlayer().equals(playerName));
        }
    }

    /**
     * Updates the state of the game and of the turn
     *
     * @param turnStatus is the new state of the turn
     * @param gameStatus is the new state of the game
     */
    private void updateStates(Model.TurnStatus turnStatus, Model.GameStatus gameStatus) {
        synchronized (model) {
            model.setGameStatus(gameStatus);
            model.setTurnStatus(turnStatus);
        }
    }

    /**
     * Checks all that is needed to withdraw and then does the action if it is possible
     *
     * @param playerName is the name of the player that is trying to withdraw
     * @param planarCoordinateList is a list of coordinates selected by the player
     */
    public void withdraw(String playerName, List<PlanarCoordinate> planarCoordinateList) {
        synchronized (model) {
            if (model.getGameStatus() != Model.GameStatus.RUNNING) {
                return;
            }
            if (model.getTurnStatus() != Model.TurnStatus.DRAWING) {
                return;
            }
            if (!isYourTurn(playerName)) {
                return;
            }
            if (planarCoordinateList == null || planarCoordinateList.size() > 3) {
                //Null list or too many cads to withdraw: NOTE the model doesn't check for the list's size
                return;
            }
            try {
                if (model.getPlayerMaxFreeSpace(playerName) < planarCoordinateList.size()) {
                    return;
                }
            } catch (InvalidArgumentException e) {/*Never thrown if it's player turn*/}
            if (!model.canWithdraw(planarCoordinateList)) {
                return;
            }
             model.withdraw(planarCoordinateList);
            updateStates(Model.TurnStatus.INSERTING, Model.GameStatus.RUNNING);
        }
    }

    /**
     * Let the player change the order of cards if it's his turn
     *
     * @param orderList is a list of integer that represent the new order of the withdrawn cards
     */
    public void changeOrderOfCards(String playerName, List<Integer> orderList) {
        synchronized (model) {
            if (model.getTurnStatus() != Model.TurnStatus.INSERTING) {
                return;
            }
            if (!isYourTurn(playerName)) {
                return;
            }
            model.sortWithdrawnCards(orderList);
        }
    }

    /**
     * Let the player insert the cards in the shelf if it's his turn
     *
     * @param playerName is the name of the player that is trying to insert the cards
     * @param column is the column selected to insert the cards
     */
    public void insert(String playerName, int column) {
        synchronized (model) {
            if (model.getGameStatus() != Model.GameStatus.RUNNING) {
                return;
            }
            if (model.getTurnStatus() != Model.TurnStatus.INSERTING) {
                return;
            }
            if (!isYourTurn(playerName)) {
                return;
            }
            try {
                if (!model.canInsert(playerName, column)) {
                    return;
                }
                model.insert(playerName, column);
                endTurn(playerName);
            } catch (InvalidArgumentException e) {/*Never thrown if it's player turn*/}
        }
    }

    /**
     * Do all the needed checks for the end of the turn of one player like the common objective completion
     *
     * @param playerName is the name of the player that is ending his turn
     *
     * @throws InvalidArgumentException if playerName is not found in the lobby
     */
    private void endTurn(String playerName) throws InvalidArgumentException {
        synchronized (model) {
            List<Integer> IDs = model.getCommonObjectivesID();
            List<Boolean> verified = model.verifyCommonObj(playerName);
            for (int i = 0; i < IDs.size(); i++) {
                if (verified.get(i)) {
                    Point point = model.getCommonObjMaxPoints(IDs.get(i));
                    model.givePoint(playerName, point);
                }
            }
            if (model.endGame()) {
                endgame();
                updateStates(Model.TurnStatus.ENDED, Model.GameStatus.ENDED);
                model.setChangedAndNotifyObservers(new GameEndedMessage(false));
            } else {
                model.setCurrentPlayer(model.getNextPlayer());
                if (model.needsRefill()) {
                    try {
                        model.refill();
                    } catch (InvalidActionException e) {
                    }
                }
                updateStates(Model.TurnStatus.DRAWING, Model.GameStatus.RUNNING);
            }
        }
    }

    /**
     * Does all the action needed in the final phase of the match
     */
    public void endgame() {
        synchronized (model) {
            model.giveShelfPoints();
            model.givePrivatePoints();
            model.sortWinners();
        }
    }
}