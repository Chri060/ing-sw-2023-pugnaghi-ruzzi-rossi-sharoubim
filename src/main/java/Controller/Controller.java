package Controller;

import Distributed.Messages.serverMessages.ServerMessage;
import Exceptions.InvalidActionException;
import Exceptions.InvalidArgumentException;
import Model.Model;
import Model.entities.Player;
import Model.entities.Point;
import util.Observable;
import util.Observer;
import util.PlanarCoordinate;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {

    private Model model;


    public Controller(Model model) {
        this.model = model;
    }


    //Prematch stuff

    public void join(String playerName) {
        synchronized (model) {
            if (model.getGameStatus() != Model.GameStatus.PREMATCH) {
                //TODO notifies the match has already started
                return;

            }
            if (playerName == null) {
                //TODO notifies the name is invalid
                return;
            }
            try {
                model.joinPlayer(playerName);
                if (model.getRoomSize() == model.getTargetRoomSize() && model.getRoomSize() > 1) {
                    model.start();
                    updateStates(Model.TurnStatus.DRAWING, Model.GameStatus.RUNNING);
                }
                //TODO Sends JoinAck
            } catch (InvalidActionException e) {
                //TODO notifies the name is already taken
            }
        }
    }

    public void setRoomSize(int size, String playerName) {
        if (!playerName.equals(model.getRoomLeader())) {
            return;
        }
        if (size < 2) {
            return;
        }
        model.setTargetRoomSize(size);

    }
    public void leave(String playerName) {
        synchronized (model) {
            if (model.getGameStatus() != Model.GameStatus.PREMATCH) {
                //TODO notifies the match has already started
                return;
            }
            if (playerName == null) {
                //TODO notifies the name is invalid
                return;
            }
            model.removePlayer(playerName);
            //TODO sends leave ack
        }
    }


    private boolean isYourTurn(String playerName) {
        synchronized (model) {
            return (model.getCurrentPlayer().equals(playerName));
        }
    }
    private void updateStates(Model.TurnStatus turnStatus, Model.GameStatus gameStatus) {
        synchronized (model) {
            model.setTurnStatus(turnStatus);
            model.setGameStatus(gameStatus);
        }
    }


    //Operation from clients
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
            model.withdraw(planarCoordinateList);
            updateStates(Model.TurnStatus.ORDERING, Model.GameStatus.RUNNING);
        }
    }

    public void changeOrderOfCards(List<Integer> orderList) {
        if (model.getTurnStatus() != Model.TurnStatus.ORDERING) {
            //TOOD
            return;
        }
        model.sortWithdrawnCards(orderList);
        updateStates(Model.TurnStatus.INSERTING, Model.GameStatus.RUNNING);
    }

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
                updateStates(Model.TurnStatus.ENDED, Model.GameStatus.RUNNING);
                endTurn(playerName);
            } catch (InvalidArgumentException e) {/*Never thrown if it's player turn*/}
        }
    }

    //End of turn stuff
    private void endTurn(String playerName) throws InvalidArgumentException {
        //Checks for common objectives
        List<Integer> IDs = model.getCommonObjectivesID();
        List<Boolean> verified = model.verifyCommonObj(playerName);
        for (int i = 0; i < IDs.size(); i++) {
            if (verified.get(i)) {
                Point point = model.getCommonObjMaxPoints(IDs.get(i));
                model.givePoint(playerName, point);
            }
        }

        if (model.endGame()) {
            updateStates(Model.TurnStatus.ENDED, Model.GameStatus.ENDED);
            endgame();
        } else {
            updateStates(Model.TurnStatus.DRAWING, Model.GameStatus.RUNNING);
            model.setCurrentPlayer(model.getNextPlayer());
            if (model.needsRefill()) {
                try {
                    model.refill();
                } catch (InvalidActionException e) {}
            }
        }
    }
    public void endgame() {
        //TODO foreach player gives shelfPoints
        model.giveShelfPoints();
        //TODO foreach player gives privateObjectivePoints: fatto
        model.givePrivatePoints();
        model.sortWinners(new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                if (p1.getTotalPoints().getValue() < p2.getTotalPoints().getValue()) {
                    return - 1;
                }
                if (p1.getTotalPoints().getValue() > p2.getTotalPoints().getValue()) {
                    return 1;
                }
                return 0;
            }
        });
    }

    public void print(String print) {
        System.out.println(print);
    }
}