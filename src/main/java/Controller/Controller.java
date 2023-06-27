package Controller;

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
     * Let a player join the room after checking if it is possible
     *
     * @param playerName is the name of the player to add
     */
    public void join(String playerName) {
        synchronized (model) {
            if (playerName == null) {
                //TODO notifies the name is invalid
                return;
            }
            if (model.getGameStatus() != Model.GameStatus.PREMATCH) {
                //TODO notifies the match has already started
                model.setPlayerOnline(playerName);
                return;
            }
            try {
                model.joinPlayer(playerName);
                if (model.getRoomSize() == model.getTargetRoomSize() && model.getRoomSize() > 1) {
                    model.start();
                }
                //TODO Sends JoinAck
            } catch (InvalidActionException e) {
                //TODO notifies the name is already taken
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
                //TODO notifies the name is invalid
                return;
            }
            if (model.getGameStatus() == Model.GameStatus.PREMATCH) {
                model.removePlayer(playerName);
            }
            else {
                model.setPlayerOffline(playerName);
            }

            /*if (model.getGameStatus() != Model.GameStatus.PREMATCH) {
                model.setPlayerOffline(playerName);
                if (playerName.equals(model.getCurrentPlayer())) {
                    model.setCurrentPlayer(model.getNextPlayer());
                    model.setTurnStatus(Model.TurnStatus.DRAWING);
                }
                if (model.getOnlinePlayersCount() == 1) {
                    model.setGameStatus(Model.GameStatus.PAUSED);
                    model.setChangedAndNotifyObservers(new TestMessage("Only you left, automatic win in:", model.getCurrentPlayer()));
                    new Thread(() -> {
                        synchronized (model) {
                            for (int i = 10; i > 0; i--) {
                                model.setChangedAndNotifyObservers(new TestMessage("" + i, model.getCurrentPlayer()));
                                if (model.getOnlinePlayersCount() != 1) {
                                    model.notifyObservers(new TestMessage("New connection game restarting", model.getCurrentPlayer()));
                                    model.setGameStatus(Model.GameStatus.RUNNING);
                                    return;
                                }
                                try {
                                    model.wait(1000);
                                } catch (InterruptedException e) {
                                }
                            }
                            model.setChangedAndNotifyObservers(new TestMessage("Only you left, congrats stinky butt you won!"));
                            model.setGameStatus(Model.GameStatus.ENDED);
                        }
                    }).start();
                    //TODO timer decreta vittoria
                }
                return;
            }
            model.removePlayer(playerName);
            //TODO sends leave ack*/
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
                //TODO sends a message error -> Game ended
                return;
            }
            if (model.getTurnStatus() != Model.TurnStatus.DRAWING) {
                //TODO sends a message error -> InvalidAction: current action is drawing
                return;
            }
            if (!isYourTurn(playerName)) {
                //TODO sends a message error -> InvalidAction
                return;
            }
            if (planarCoordinateList == null || planarCoordinateList.size() > 3) {
                //TODO sends a message error
                //Null list or too many cads to withdraw: NOTE the model doesn't check for the list's size
                return;
            }
            try {
                if (model.getPlayerMaxFreeSpace(playerName) < planarCoordinateList.size()) {
                    //TODO sends a message error -> Can't withdraw more cards than the space in the shelf
                    return;
                }
            } catch (InvalidArgumentException e) {/*Never thrown if it's player turn*/}
            if (!model.canWithdraw(planarCoordinateList)) {
                //TODO sends a message error -> InvalidAction: can't withdraw those cards
                return;
            }
            System.out.println("Withdrawing");
            model.withdraw(planarCoordinateList);
            updateStates(Model.TurnStatus.INSERTING, Model.GameStatus.RUNNING);
        }
    }

    /**
     * Let the player change the order of cards if it's his turn
     *
     * @param orderList is a list of integer that represent the new order of the withdrawn cards
     */
    public void changeOrderOfCards(List<Integer> orderList) {
        synchronized (model) {
            if (model.getTurnStatus() != Model.TurnStatus.INSERTING) {
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
                //TODO sends a message error -> Game ended
                return;
            }
            if (model.getTurnStatus() != Model.TurnStatus.INSERTING) {
                //TODO sends a message error -> InvalidAction: current action is not inserting
                return;
            }
            if (!isYourTurn(playerName)) {
                //TODO sends a message error -> InvalidAction: Not Your Turn
                return;
            }
            try {
                if (!model.canInsert(playerName, column)) {
                    //TODO sends a message error -> InvalidAction: Column can't contain or is out of bounds
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
     * @throws InvalidArgumentException on invalid argument
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