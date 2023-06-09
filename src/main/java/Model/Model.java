package Model;

import Distributed.Messages.serverMessages.*;
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
        PAUSED,
        ENDED
    }

    public enum TurnStatus {
        DRAWING,
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
        this.playerList = new ArrayList<>();
        this.roomSize = - 1;
    }

    public void setTargetRoomSize(int roomSize) {
        if (this.roomSize != - 1) {
            setChangedAndNotifyObservers(new TestMessage("Room size already set"));
            return;
        }
        this.roomSize = roomSize;
        setChangedAndNotifyObservers(new TestMessage("Room size set to " + roomSize));
        setChangedAndNotifyObservers(new RoomSizeSettedMessage(roomSize));
    }
    public int getTargetRoomSize() {
        return roomSize;
    }
    public int getRoomSize() {
        return this.playerNames.size();
    }
    public String getRoomLeader() {
        return roomLeader;
    }
    public boolean isInGame(String name) {
        return playerNames.contains(name);
    }
    public void joinPlayer(String playerName) throws InvalidActionException {
        this.playerNames.add(playerName);
        System.out.println(playerName + " joined the match");
        if (playerNames.size() == 1) {
            setChangedAndNotifyObservers(new JoinRequestAcceptedMessage(new ArrayList<>(playerNames), ModelView.State.SETTINGSIZE));
            roomLeader = playerName;
            System.out.println(roomLeader + " is the new roomLeader");
            setChangedAndNotifyObservers(new SetRoomSizeMessage(roomLeader));
        }
        else {
            setChangedAndNotifyObservers(new JoinRequestAcceptedMessage(new ArrayList<>(playerNames), ModelView.State.INLOBBY));
        }
    }
    public boolean canJoin(String name) {
        if (roomLeader == null) {
            return true;
        }
        if (roomSize != - 1) {
            if (gameStatus == GameStatus.PREMATCH) {
                return true;
            }
            if (isInGame(name)) {
                return true;
            }
        }
        return false;
    }
    public void removePlayer(String playerName) {
        boolean changed = this.playerNames.remove(playerName);
        if (playerNames.size() > 0) {
            roomLeader = playerNames.get(0);
        }
        else {
            roomLeader = null;
            roomSize = - 1;
        }
        if (changed) {
            setChangedAndNotifyObservers(new LeaveLobbyMessage(new ArrayList<>(playerNames)));
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
    public String getNextPlayer() {
        int i = playerNames.indexOf(getCurrentPlayer());
        String nextPlayer;
        do {
            i++;
            nextPlayer = playerNames.get((i) % playerNames.size());
        } while (!getPlayer(nextPlayer).isConnected());
        return nextPlayer;
    }
    public synchronized void setPlayerOffline(String playerName) {
        Player player = getPlayer(playerName);
        if (player != null) {
            player.setOffline();
            setChangedAndNotifyObservers(new TestMessage(playerName + " disconnected"));
            setChangedAndNotifyObservers(new TestMessage("Connected players " + getOnlinePlayersCount()));

            if (playerName.equals(currentPlayer) && getOnlinePlayersCount() > 0) {
                currentPlayer = getNextPlayer();
                setChangedAndNotifyObservers(new TurnUpdateMessage(currentPlayer, turnStatus));
            }

            if (getOnlinePlayersCount() == 1) {
                Timer timer = new Timer();
                setGameStatus(GameStatus.PAUSED);
                setChangedAndNotifyObservers(new GamePausedMessage());
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        forfeit();
                    }
                }, 20000);
            }
        }
    }
    public synchronized void setPlayerOnline(String playerName) {
        Player player = getPlayer(playerName);
        if (player != null) {
            player.setOnline();
            setChangedAndNotifyObservers(new TestMessage(playerName + " connected"));
            setChangedAndNotifyObservers(new TestMessage("Connected players " + getOnlinePlayersCount()));
            ModelViewData modelViewData = getModelviewData(playerName);
            setChangedAndNotifyObservers(new ModelViewMessage(modelViewData, playerName));
        }
    }
    public synchronized int getOnlinePlayersCount() {
        return (int) playerList.stream().filter(x -> x.isConnected()).count();
    }
    public void forfeit() {
        int onlinePlayers = getOnlinePlayersCount();
        if (onlinePlayers > 1) {
            gameStatus = GameStatus.RUNNING;
            return;
        }
        if (onlinePlayers == 0) {
            return;
        }

        //TODO notifies macth ended
    }
    public void setCurrentPlayer(String playerName) {
        this.currentPlayer = playerName;
        setChangedAndNotifyObservers(new TurnUpdateMessage(currentPlayer, turnStatus));
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
            } catch (InvalidActionException e) {/*Never Thrown*/}
            chairPlayer = playerNames.get(0);
            currentPlayer = chairPlayer;
            gameStatus = GameStatus.RUNNING;
            turnStatus = TurnStatus.DRAWING;
        }
        sendModelViewData();
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
            setChangedAndNotifyObservers(new ShelfUpdateMessage(new String(player.getName()), player.getShelf().asMatrix()));
            withdrawnCards.clear();
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
    public void giveShelfPoints() {
        int[] points = Config.getCustomShelfPoints();
        playerList.stream().forEach(x -> {
        List<Integer> groupsSizes = x.getShelfGroups();
        groupsSizes.stream().forEach(y -> {
            if (y >= points.length) {
                x.givePoint(new Point(points[points.length - 1], "Shelf Point"));
            }
            else {
                x.givePoint(new Point(points[y - 1], "Shelf Point"));
            }
        });
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
        setChangedAndNotifyObservers(new WithdrawUpdateMessage(dashboard.asMatrix(), withdrawnCards));
    }
    public void sortWithdrawnCards(List<Integer> orderList) {
        if (orderList == null) {
            return;
        }
        if (orderList.size() != withdrawnCards.size()) {
            return;
        }//se si lascia = sul maggiore l'ordine va da 0 se lo si mette sul minore va da 1
        if (orderList.stream().anyMatch(x -> x >= withdrawnCards.size() || x < 0)) {
            return;
        }
        if (orderList.stream().distinct().count() < withdrawnCards.size()) {
            return;
        }
        List<Card> newList = new ArrayList<>();
        for (Integer i : orderList) {
            newList.add(withdrawnCards.get(i));
        }
        withdrawnCards = newList;

    }
    public void sendChatMessage(String playerName, List<String> receivers, String message) {
        ChatMessage chatMessage;
        if (receivers.contains("")) {
            chatMessage = new ChatMessage(playerName, message);
        }
        else {
            chatMessage = new ChatMessage(playerName, receivers, message);
        }
        setChangedAndNotifyObservers(chatMessage);

    }
    public void sortWinners() {
        playerList.sort((p1, p2) -> {
            if (p1.getTotalPoints().getValue() < p2.getTotalPoints().getValue()) {
                return -1;
            }
            if (p1.getTotalPoints().getValue() > p2.getTotalPoints().getValue()) {
                return 1;
            }
            return 0;
        });
        sendRank();
    }
    public void sendRank() {
        List<PlayerView> ranking = new ArrayList<>();
        for (int i = playerList.size() - 1; i >= 0; i--) {
            Player player = playerList.get(i);
            PlayerView p = new PlayerView();
            p.setName(player.getName());
            p.setPoint(player.getPoints());
            ranking.add(p);
        }
        setChangedAndNotifyObservers(new RankingMessage(ranking));
    }
    public synchronized void sendModelViewData() {
        playerList.stream().filter(p -> p.isConnected()).
                forEach(p -> setChangedAndNotifyObservers(new ModelViewMessage(getModelviewData(p.getName()), p.getName())));
    }
    public synchronized ModelViewData getModelviewData(String playerName) {
        ModelViewData modelViewData = new ModelViewData();
        modelViewData.setDashboard(dashboard.asMatrix());

        List<CommonObjectiveView> commonObjectiveViewList = new ArrayList<>();
        for (CommonObjective commonObjective : commonObjectiveList) {
            CommonObjectiveView commonObjectiveView = new CommonObjectiveView();
            commonObjectiveView.setID(commonObjective.getID());
            commonObjectiveView.setMaxPoint(new Point(commonObjective.checkMaxPoints().getValue(), commonObjective.checkMaxPoints().getOrigin()));
            commonObjectiveViewList.add(commonObjectiveView);
        }
        modelViewData.setCommonObjectiveViews(commonObjectiveViewList);
        modelViewData.setCurrentPlayer(new String(currentPlayer));
        modelViewData.setChairPlayer(new String(chairPlayer));

        List<PlayerView> playerViewList = new ArrayList<>();

        for (Player p : playerList.stream().filter(p -> !p.equals(playerName)).toList()) {
            PlayerView playerView = new PlayerView();
            playerView.setName(new String(p.getName()));
            playerView.setShelf(p.getShelf().asMatrix());
            playerView.setPoint(new ArrayList<>(p.getPoints()));
            playerViewList.add(playerView);
        }

        modelViewData.setPlayerViewList(playerViewList);

        Player player = getPlayer(new String(playerName));
        modelViewData.setMyShelf(player.getShelf().asMatrix());
        modelViewData.setMyPoints(new ArrayList<>(player.getPoints()));
        modelViewData.setMyPrivateObjectivePatterns(new ArrayList<>(player.getPrivateObjectivePattern()));

        return modelViewData;
    }
}
