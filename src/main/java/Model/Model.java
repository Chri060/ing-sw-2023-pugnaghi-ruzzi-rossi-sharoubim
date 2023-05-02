package Model;

import Distributed.Messages.NetworkMessage;
import Exceptions.InvalidActionException;
import Exceptions.InvalidArgumentException;
import Model.entities.*;
import Model.entities.commonObjectives.CommonObjective;
import Model.entities.commonObjectives.CommonObjective7;
import util.Config;
import util.Generator;
import util.Observable;
import util.PlanarCoordinate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Model extends Observable<NetworkMessage> {

    public enum GameStatus {
        PREMATCH,
        STARTING,
        RUNNING,
        ENDED
    }

    public enum TurnStatus {
        DRAWING,
        INSERTING,
        ENDED
    }

    private GameStatus gameStatus;
    private TurnStatus turnStatus;

    private Dashboard dashboard;
    private List<Player> playerList;
    private List<String> playerNames;
    private List<CommonObjective> commonObjectiveList;
    private Bag bag;

    private String chairPlayer;
    private String currentPlayer;

    private List<Card> withdrawnCards;

    public Model() {
        this.gameStatus = GameStatus.PREMATCH;
        this.playerNames = new ArrayList<>();
    }

    public void joinPlayer(String playerName) throws InvalidActionException {
        synchronized (playerNames) {
            if (this.playerNames.contains(playerName)) {
                throw new InvalidActionException(playerName + " joining the room", "somebody with the same name being already in lobby");
            }
            this.playerNames.add(playerName);
            setChanged();
        }
    }
    public void removePlayer(String playerName) {
        synchronized (playerNames) {
            this.playerNames.remove(playerName);
            setChanged();
        }
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }
    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public TurnStatus getTurnStatus() {
        return turnStatus;
    }
    public void setTurnStatus(TurnStatus turnStatus) {
        this.turnStatus = turnStatus;
    }

    public String getCurrentPlayer() {return currentPlayer;}
    public void setCurrentPlayer(String playerName) {
        this.currentPlayer = playerName;
    }
    public String getChairPlayer() {return chairPlayer;}

    public int getRoomSize() {
        synchronized (playerNames) {
            return this.playerNames.size();
        }
    }

    //Initialises the game
    public void start() throws InvalidActionException {
        synchronized (Config.class) {
            Config.initialise(this.playerNames.size());
            this.gameStatus = GameStatus.STARTING;
            int numberOfCommonObjectives = Config.getNumberOfCommonObjectives();
            int availableCommonObjectives = CommonObjective.getNumberOfAvailableObjectives();
            int numberOfPrivateObjectives = Config.getNumberOfPrivateObjectives();
            int availablePrivateObjectives = Config.getAvailablePrivateObjectives();
            playerList = new ArrayList<>();
            Collections.shuffle(playerNames);
            for (String name : playerNames) {
                List<PrivateObjective> privateObjectiveList = Generator.
                        getUniqueInstances(numberOfPrivateObjectives, availablePrivateObjectives, PrivateObjective.class);
                playerList.add(new Player(name, privateObjectiveList));
            }
            Generator.clearCommonList();
            commonObjectiveList = Generator.getCommonObjectives(numberOfCommonObjectives, availableCommonObjectives);
            dashboard = new Dashboard();
            bag = new Bag();
            dashboard.refill(bag);
            chairPlayer = playerNames.get(0);
            currentPlayer = chairPlayer;
            gameStatus = GameStatus.RUNNING;
            turnStatus = TurnStatus.DRAWING;
        }
    }


    private Player getPlayer(String playerName) {
        Optional<Player> player = playerList.stream().filter(x -> x.equals(playerName)).findAny();
        if (player.isPresent()) {
           return player.get();
        }
        else {
            return null;
        }
    }
    public void insert(String playerName, int column) throws InvalidArgumentException {
        Player player = getPlayer(playerName);
        if (player != null) {
            player.getShelf().insert(withdrawnCards, column);
        }
        else {
            throw new InvalidArgumentException(playerName + " not found in this lobby");
        }
    }
    public boolean isLastTurn() {
        return playerList.stream().anyMatch(x -> x.getShelf().isFull());
    }
    public boolean endGame() {
        return isLastTurn() && playerList.stream().reduce((a, b) -> b).get().equals(currentPlayer);
    }
    public List<Integer> getCommonObjectivesID() {
        return commonObjectiveList.stream().map(x -> x.getID()).toList();
    }

    public boolean verifyCommonObj(String playerName, int ID) throws InvalidArgumentException {
        Player player = getPlayer(playerName);
        if (player != null) {
            if (!player.hasAlreadyGotCommonPoints(ID)) {
                Shelf shelf = player.getShelf();
                Optional<CommonObjective> commonObjective = commonObjectiveList.stream().filter(x -> x.getID() == ID).findFirst();
                if (commonObjective.isPresent()) {
                    return commonObjective.get().verify(shelf);
                }
            }
            return false;
        } else {
                throw new InvalidArgumentException(playerName + " not found in this lobby");
        }
    }

    public List<Boolean> verifyCommonObj(String playerName) throws InvalidArgumentException {
        List<Boolean> result = new ArrayList<>();
        List<Integer> IDList = this.getCommonObjectivesID();
        for(int ID : IDList) {
            result.add(verifyCommonObj(playerName, ID));
        }
        return result;
    }

    public Point getCommonObjMaxPoints(int ID) throws InvalidArgumentException {
        Optional<CommonObjective> commonObjective = commonObjectiveList.stream().filter(x -> x.getID() == ID).findAny();
        if (commonObjective.isPresent()) {
            return commonObjective.get().getMaxPoints();
        }
        else {
            throw new InvalidArgumentException("None Common Objective whit ID = " + ID + " found");
        }
    }

    public void givePoints(String playerName, Point point) {
        getPlayer(playerName).givePoints(point);
    }
    public List<Point> getPlayerPoints(String playerName) {
        return this.getPlayer(playerName).getPoints();
    }

    public boolean needsRefill() {
        return dashboard.needsRefill();
    }
    public void refill() throws InvalidActionException {
        dashboard.refill(bag);
    }
    public boolean canWithdraw(List<PlanarCoordinate> coordinateList) {
        return dashboard.canWithdraw(coordinateList);
    }
    public void withdraw(List<PlanarCoordinate> coordinateList) {
        withdrawnCards = dashboard.withdraw(coordinateList);
    }



}
