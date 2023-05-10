package Model;

import Distributed.Messages.serverMessages.ModelViewMessage;
import Distributed.Messages.serverMessages.ServerMessage;
import Distributed.Messages.serverMessages.TestMessage;
import Exceptions.InvalidActionException;
import Exceptions.InvalidArgumentException;
import Model.entities.*;
import Model.entities.commonObjectives.CommonObjective;
import Model.viewEntities.CommonObjectiveView;
import Model.viewEntities.PlayerView;
import util.Config;
import util.Generator;
import util.Observable;
import util.PlanarCoordinate;

import java.util.*;

public class Model extends Observable<ServerMessage> {

    public enum GameStatus {
        PREMATCH,
        STARTING,
        RUNNING,
        ENDED
    }

    public enum TurnStatus {
        DRAWING,
        ORDERING,
        INSERTING,
        ENDED
    }

    private int roomSize;
    private String roomLeader;

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

    public void setTargetRoomSize(int roomSize) {
        if (this.roomSize != 0) {
            setChangedAndNotifyObservers(new TestMessage("Room size already set"));
            return;
        }
        this.roomSize = roomSize;
        setChangedAndNotifyObservers(new TestMessage("Room size set to " + roomSize));
        if (roomSize == getRoomSize()) {
            start();
        }
    }
    public int getTargetRoomSize() {
        return roomSize;
    }
    public int getRoomSize() {
        return this.playerNames.size();
    }
    public void setRoomLeader(String playerName) {
        roomLeader = playerName;
    }
    public String getRoomLeader() {
        return roomLeader;
    }

    public void joinPlayer(String playerName) throws InvalidActionException {
        this.playerNames.add(playerName);
        System.out.println(playerName + " joined the match");
        setChangedAndNotifyObservers(new TestMessage(playerName + " joined the room "));
        if (playerNames.size() == 1) {
            roomLeader = playerName;
            //TODO notifyObservers -> send set room size message
            setChangedAndNotifyObservers(new TestMessage( "You're the first player, use size command to set room size "));
        }
        //TODO notifyObservers
    }
    public void removePlayer(String playerName) {
        this.playerNames.remove(playerName);
            if (playerNames.size() != 0) {
                roomLeader = playerNames.get(0);
            }
            else {
                roomLeader = null;
                setTargetRoomSize(0);
            }
            setChangedAndNotifyObservers(new TestMessage(playerName + " left the room"));
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
    public String getNextPlayer() {
        int i = playerNames.indexOf(getCurrentPlayer());
        return playerNames.get((i + 1) % playerNames.size());
    }



    public void setCurrentPlayer(String playerName) {
        this.currentPlayer = playerName;
    }
    public String getChairPlayer() {return chairPlayer;}

    //Initialises the game
    public void start() {
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
            try {
                dashboard.refill(bag);
            } catch (InvalidActionException e) {/**/}
            chairPlayer = playerNames.get(0);
            currentPlayer = chairPlayer;
            gameStatus = GameStatus.RUNNING;
            turnStatus = TurnStatus.DRAWING;
            setChangedAndNotifyObservers(new TestMessage("Match started"));
            setChangedAndNotifyObservers(new ModelViewMessage(getModelView()));
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
    public boolean canInsert(String playerName, int column) throws InvalidArgumentException {
        Player player = getPlayer(playerName);
        if (player != null) {
            return player.getShelf().canInsert(withdrawnCards, column);
        }
        else {
            throw new InvalidArgumentException(playerName + " not found in this lobby");
        }
    }
    public void insert(String playerName, int column) throws InvalidArgumentException {
        Player player = getPlayer(playerName);
        if (player != null) {
            if (!player.getShelf().canInsert(withdrawnCards, column)) {
                //TODO setchangedandnotify
                return;
            }
            player.getShelf().insert(withdrawnCards, column);
            withdrawnCards.clear();
            setChangedAndNotifyObservers(new ModelViewMessage(getModelView()));
        }
        else {
            throw new InvalidArgumentException(playerName + " not found in this lobby");
        }
    }
    public int getPlayerMaxFreeSpace(String playerName) throws InvalidArgumentException {
        Player player = getPlayer(playerName);
        if (player != null) {
            return player.getShelf().maxFreeSpace();
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

    public void givePrivatePoints() {
        playerList.stream().forEach(x -> {
            Point point = x.getTotalPrivatePoints();
            x.givePoint(point);
        });
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

    public void givePoint(String playerName, Point point) {
        getPlayer(playerName).givePoint(point);
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
        if (!canWithdraw(coordinateList)) {
            //TODO setchangedandnotify
            return;
        }
        withdrawnCards = dashboard.withdraw(coordinateList);
        setChangedAndNotifyObservers(new TestMessage( currentPlayer + " drew Cards " + withdrawnCards.stream().map(x -> x.getType()).toList()));
        //TODO setchangedandnotify
    }
    public void sortWithdrawnCards(List<Integer> orderList) {
        if (orderList == null) {
            //TODO
            return;
        }
        if (orderList.size() != withdrawnCards.size()) {
            //TODO ordine non valido
            return;
        }//se si lascia = sul maggiore l'ordine va da 0 se lo si mette sul minore va da 1
        if (orderList.stream().anyMatch(x -> x >= withdrawnCards.size() || x < 0)) {
            //TODO valori non validi
            return;
        }
        if (orderList.stream().distinct().count() < withdrawnCards.size()) {
            //TODO valori non validi
            return;
        }
        List<Card> newList = new ArrayList<>();
        for (Integer i : orderList) {
            newList.add(withdrawnCards.get(i));
        }
        withdrawnCards = newList;

    }
    public void sortWinners(Comparator comparator) {
        playerList.sort(comparator);
    }

    public ModelView getModelView() {
        ModelView modelView = new ModelView();

        modelView.setDashboard(dashboard.asMatrix());
        List<PlayerView> playerViewList = new ArrayList<>();
        for (Player player : playerList) {
            PlayerView playerView = new PlayerView();
            playerView.setName(player.getName());
            playerView.setShelf(player.getShelf().asMatrix());
            playerView.setPoint(player.getTotalPoints());
            playerView.setPrivateObjectivePattern(player.getPrivateObjectivePattern());
            playerViewList.add(playerView);
            //TODO aggiungere obiettivi privati
        }
        modelView.setPlayerViews(playerViewList);

        List<CommonObjectiveView> commonObjectiveViewList = new ArrayList<>();
        for (CommonObjective commonObjective : commonObjectiveList) {
            CommonObjectiveView commonObjectiveView = new CommonObjectiveView();
            commonObjectiveView.setID(commonObjective.getID());
            commonObjectiveView.setMaxPoint(commonObjective.checkMaxPoints());
            commonObjectiveViewList.add(commonObjectiveView);
        }
        modelView.setCommonObjectiveViews(commonObjectiveViewList);

        modelView.setWithdrawnCards(withdrawnCards);

        return modelView;
    }
}
