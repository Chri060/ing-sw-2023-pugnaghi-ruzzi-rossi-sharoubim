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

    /**
     * Constructor of the model
     */
    public Model() {
        this.gameStatus = GameStatus.PREMATCH;
        this.playerNames = new ArrayList<>();
        this.playerList = new ArrayList<>();
        this.roomSize = - 1;
    }

    /**
     * Set the number of the players that will play the match
     *
     * @param roomSize is the dimension of the lobby to set
     */
    public void setTargetRoomSize(int roomSize) {
        if (this.roomSize != - 1) {
            setChangedAndNotifyObservers(new TestMessage("Room size already set"));
            return;
        }
        this.roomSize = roomSize;
        setChangedAndNotifyObservers(new TestMessage("Room size set to " + roomSize));
        setChangedAndNotifyObservers(new RoomSizeSettedMessage(roomSize));
    }

    /**
     * @return the dimension of the lobby
     */
    public int getTargetRoomSize() {
        return roomSize;
    }

    /**
     * @return the actual dimension of the lobby
     */
    public int getRoomSize() {
        return this.playerNames.size();
    }

    /**
     * @return the name of the room leader
     */
    public String getRoomLeader() {
        return roomLeader;
    }

    /**
     * Checks if a player is in the game using his name
     *
     * @param name is the name of the player
     *
     * @return true if the name is in the lobby, false otherwise
     */
    public boolean isInGame(String name) {
        return playerNames.contains(name);
    }

    /**
     * Adds a player to the lobby
     *
     * @param playerName is the name of the player to add
     *
     * @throws InvalidActionException on invalid action
     */
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

    /**
     * Check is possible for a player to join the lobby
     *
     * @param name is the name of the player
     *
     * @return true if the player can join, false otherwise
     */
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

    /**
     * Removes a player from the lobby
     *
     * @param playerName is the player that is going to be removed from the lobby
     */
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
            System.out.println(playerName + " left the lobby");
        }
    }

    /**
     * @return the value of gameStatus enumeration
     */
    public GameStatus getGameStatus() {
        return gameStatus;
    }

    /**
     * Set a specified value for gameStatus
     *
     * @param gameStatus is the new value of the variable
     */
    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    /**
     * @return the value of turnStatus enumeration
     */
    public TurnStatus getTurnStatus() {
        return turnStatus;
    }
    /**
     * Set a specified value for gameStatus
     *
     * @param turnStatus is the new value of the variable
     */
    public void setTurnStatus(TurnStatus turnStatus) {
        this.turnStatus = turnStatus;
        setChangedAndNotifyObservers(new ModelViewUpdateMessage(getModelViewDataUpdate()));
        System.out.println("Update Sent");
    }

    /**
     * @return the player that is playing
     */
    public String getCurrentPlayer() { return currentPlayer; }

    /**
     * @return the player that is goint to play after the actual one
     */
    public String getNextPlayer() {
        int i = playerNames.indexOf(getCurrentPlayer());
        String nextPlayer;
        do {
            i++;
            nextPlayer = playerNames.get((i) % playerNames.size());
        } while (!getPlayer(nextPlayer).isConnected());
        return nextPlayer;
    }

    /**
     * Set a player to offline status
     *
     * @param playerName is the name of the player to set offline
     */
    public synchronized void setPlayerOffline(String playerName) {
        Player player = getPlayer(playerName);
        if (player != null) {
            player.setOffline();
            System.out.println(playerName + " disconnected");
            setChangedAndNotifyObservers(new TestMessage(playerName + " disconnected"));
            setChangedAndNotifyObservers(new TestMessage("Connected players " + getOnlinePlayersCount()));
            if (playerName.equals(currentPlayer) && getOnlinePlayersCount() > 0) {
                currentPlayer = getNextPlayer();
            }
            if (getOnlinePlayersCount() == 1) {
                Timer timer = new Timer();
                setGameStatus(GameStatus.PAUSED);
                System.out.println("Game Paused");
                setChangedAndNotifyObservers(new GamePausedMessage());
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        forfeit();
                    }
                }, 10000);
            }
        }
    }

    /**
     * Set the given player status as online
     *
     * @param playerName is the name of the player to set as online
     */
    public synchronized void setPlayerOnline(String playerName) {
        Player player = getPlayer(playerName);
        if (player != null) {
            player.setOnline();
            System.out.println(playerName + " connected");
            setChangedAndNotifyObservers(new TestMessage(playerName + " connected"));
            setChangedAndNotifyObservers(new TestMessage("Connected players " + getOnlinePlayersCount()));
            ModelViewData modelViewData = getModelviewData(playerName);
            setChangedAndNotifyObservers(new ModelViewMessage(modelViewData, playerName));
        }
    }

    /**
     * @return the number of the players that are online in a given moment
     */
    public synchronized int getOnlinePlayersCount() {
        return (int) playerList.stream().filter(x -> x.isConnected()).count();
    }

    /**
     * Checks if there are enough player
     */
    public void forfeit() {
        int onlinePlayers = getOnlinePlayersCount();
        if (onlinePlayers > 1) {
            gameStatus = GameStatus.RUNNING;
            setChangedAndNotifyObservers(new GameResumedMessage());
            return;
        }
        if (onlinePlayers == 0) {
            return;
        }
        //TODO notifies macth ended
    }

    /**
     * Set the player that is going to play
     *
     * @param playerName is the name of the player
     */
    public void setCurrentPlayer(String playerName) {
        this.currentPlayer = playerName;
        System.out.println("It's " + currentPlayer + "'s turn");
    }

    /**
     * @return the name of the player who started the match
     */
    public String getChairPlayer() { return chairPlayer; }

    /**
     * Initializes the actual game
     */
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
            commonObjectiveList = Generator.getCommonObjectives();
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
        System.out.println("Game started: it's " + currentPlayer + "'s turn");
        sendModelViewData();
    }

    /**
     * Gives the player object liked to a certain player name
     *
     * @param playerName is the name of the player to check
     *
     * @return the Player object of the player given
     */
    private Player getPlayer(String playerName) {
        Optional<Player> player = playerList.stream().filter(x -> x.equals(playerName)).findAny();
        if (player.isPresent()) {
           return player.get();
        }
        else {
            return null;
        }
    }

    /**
     * Checks if it is possible to insert cards in the shelf of a certain player
     *
     * @param playerName is the name of the player
     * @param column is the column where you want to insert the cards
     *
     * @return true if it is possible, false otherwise
     *
     * @throws InvalidArgumentException on invalid argument
     */
    public boolean canInsert(String playerName, int column) throws InvalidArgumentException {
        Player player = getPlayer(playerName);
        if (player != null) {
            return player.getShelf().canInsert(withdrawnCards, column);
        }
        else {
            throw new InvalidArgumentException(playerName + " not found in this lobby");
        }
    }

    /**
     * Insert a card in a selected shelf of a certain player
     *
     * @param playerName is the name of the player
     * @param column is the column selected
     *
     * @throws InvalidArgumentException on invalid argument
     */
    public void insert(String playerName, int column) throws InvalidArgumentException {
        Player player = getPlayer(playerName);
        if (player != null) {
            System.out.println(playerName + " wants to insert " + withdrawnCards + "in the " + column + "° column");
            if (!player.getShelf().canInsert(withdrawnCards, column)) {
                System.out.println("Action Denied");
                return;
            }
            player.getShelf().insert(withdrawnCards, column);
            withdrawnCards.clear();
            System.out.println("Done!");
        }
        else {
            throw new InvalidArgumentException(playerName + " not found in this lobby");
        }
    }

    /**
     * Checks the columns with the max space for a given player
     *
     * @param playerName is the name of the player
     *
     * @return the value of the space of the least occupied  column of the shelf
     *
     * @throws InvalidArgumentException on invalid argument
     */
    public int getPlayerMaxFreeSpace(String playerName) throws InvalidArgumentException {
        Player player = getPlayer(playerName);
        if (player != null) {
            return player.getShelf().maxFreeSpace();
        }
        else {
            throw new InvalidArgumentException(playerName + " not found in this lobby");
        }
    }

    /**
     * Checks if it is the last turn of the game
     *
     * @return true if it is the last turn, false otherwise
     */
    public boolean isLastTurn() {
        boolean isLastTurn = playerList.stream().anyMatch(x -> x.getShelf().isFull());
        if (isLastTurn) {
            System.out.println("Last turn!");
            System.out.println(currentPlayer + " will get an extra point");
            givePoint(currentPlayer, new Point(1, "First to fill the shelf"));
        }
        return isLastTurn;
    }

    /**
     * @return true if the game is ended, false otherwise
     */
    public boolean endGame() {
        boolean endGame = isLastTurn() && playerList.stream().reduce((a, b) -> b).get().equals(currentPlayer);
        if (endGame) {
            System.out.println("This was the last turn");
        }
        return endGame;
    }

    /**
     * @return a list of IDs of the common objectives
     */
    public List<Integer> getCommonObjectivesID() {
        return commonObjectiveList.stream().map(x -> x.getID()).toList();
    }

    /**
     * Checks if the common objective is achieved or not
     *
     * @param playerName is the name of the player that is trying to do the common objective
     * @param ID is the ID of the common objective to check
     *
     * @return true if the common objective is verified, false otherwise
     *
     * @throws InvalidArgumentException on invalid argument
     */
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

    /**
     * Gives the point for a private objective completion
     */
    public void givePrivatePoints() {
        System.out.println("Giving private objective Points");
        playerList.stream().forEach(x -> {
            Point point = x.getTotalPrivatePoints();
            x.givePoint(point);
        });
    }

    /**
     * Give points for the groups found in the shelf
     */
    public void giveShelfPoints() {
        System.out.println("Giving Shelf Points");
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

    /**
     * Verifies all the common objective in the game for a specified player
     *
     * @param playerName is the name of the player to check
     *
     * @return a list of boolean that indicates what objectives are fulfilled and what are not
     *
     * @throws InvalidArgumentException on invalid argument
     */
    public List<Boolean> verifyCommonObj(String playerName) throws InvalidArgumentException {
        List<Boolean> result = new ArrayList<>();
        List<Integer> IDList = this.getCommonObjectivesID();
        for(int ID : IDList) {
            result.add(verifyCommonObj(playerName, ID));
        }
        return result;
    }

    /**
     * @param ID is the ID of the common objective to check
     *
     * @return the max point available in the given object
     *
     * @throws InvalidArgumentException on invalid argument
     */
    public Point getCommonObjMaxPoints(int ID) throws InvalidArgumentException {
        Optional<CommonObjective> commonObjective = commonObjectiveList.stream().filter(x -> x.getID() == ID).findAny();
        if (commonObjective.isPresent()) {
            return commonObjective.get().getMaxPoints();
        }
        else {
            throw new InvalidArgumentException("None Common Objective whit ID = " + ID + " found");
        }
    }

    /**
     * Give points to a certain player
     *
     * @param playerName is the name of the player
     * @param point are the point to give to him
     */
    public void givePoint(String playerName, Point point) {
        getPlayer(playerName).givePoint(point);
        System.out.println("Gave " + playerName + " " + point.getValue() + " points: " + point.getOrigin());
    }

    /**
     * @param playerName is the name of the player
     *
     * @return the list of the point given to the selected player
     */
    public List<Point> getPlayerPoints(String playerName) {
        return this.getPlayer(playerName).getPoints();
    }

    /**
     * Checks if the dashboard needs a refill
     *
     * @return true if it needs to be refilled, false otherwise
     */
    public boolean needsRefill() {
        return dashboard.needsRefill();
    }

    /**
     * Refills the dashboard
     *
     * @throws InvalidActionException on invalid action
     */
    public void refill() throws InvalidActionException {
        dashboard.refill(bag);
        System.out.println("Dashboard refilled");
    }

    /**
     *  Check if it is possible to withdraw from a certain coordinate
     *
     * @param coordinateList is a list of coordinates
     *
     * @return true if it is possible to withdraw from the coordinates given
     */
    public boolean canWithdraw(List<PlanarCoordinate> coordinateList) {
        return dashboard.canWithdraw(coordinateList);
    }

    /**
     * Withdraws the cards from the selected coordinates
     *
     * @param coordinateList is a list of coordinate
     */
    public void withdraw(List<PlanarCoordinate> coordinateList) {
        System.out.println(currentPlayer + " wants to withdraw: " + coordinateList);
        if (!canWithdraw(coordinateList)) {
            System.out.println("Action denied");
            return;
        }
        withdrawnCards = dashboard.withdraw(coordinateList);
        System.out.println("Done!");
    }

    /**
     * Allows to sort the card that has been drawn
     *
     * @param orderList is the list of integer that represent the new order
     */
    public void sortWithdrawnCards(List<Integer> orderList) {
        if (orderList == null) {
            return;
        }
        if (orderList.size() != withdrawnCards.size()) {
            return;
        }
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

    /**
     * Send a chat message to a list of receivers
     *
     * @param playerName is the name of the sender
     * @param receivers is the list of names of the receivers
     * @param message is the message that is going to be delivered
     */
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

    /**
     * Sort the winners in ascending order
     */
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
        Collections.reverse(playerList);
    }


    /**
     * Send the modelViewData
     */
    public synchronized void sendModelViewData() {
        playerList.stream().filter(p -> p.isConnected()).
                forEach(p -> setChangedAndNotifyObservers(new ModelViewMessage(getModelviewData(p.getName()), p.getName())));
    }

    /**
     * Gets the modelViewData
     *
     * @param playerName is the name that is going to be checked
     *
     * @return a modelViewData object
     */
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

    /**
     * Creates and returns modelViewDataUpdate based on the current state of the game: this object doesn't contain private info
     * so it's the same for all players
     *
     * @return  modelViewDataUpdate describing the model
     */

    public synchronized ModelViewDataUpdate getModelViewDataUpdate() {
        ModelViewDataUpdate modelViewDataUpdate = new ModelViewDataUpdate();
        modelViewDataUpdate.setDashboard(dashboard.asMatrix());
        List<CommonObjectiveView> commonObjectiveViewList = new ArrayList<>();
        for (CommonObjective commonObjective : commonObjectiveList) {
            CommonObjectiveView commonObjectiveView = new CommonObjectiveView();
            commonObjectiveView.setID(commonObjective.getID());
            commonObjectiveView.setMaxPoint(new Point(commonObjective.checkMaxPoints().getValue(), commonObjective.checkMaxPoints().getOrigin()));
            commonObjectiveViewList.add(commonObjectiveView);
        }
        modelViewDataUpdate.setCommonObjectiveViews(commonObjectiveViewList);
        modelViewDataUpdate.setCurrentPlayer(new String(currentPlayer));
        List<PlayerView> playerViewList = new ArrayList<>();
        for (Player p : playerList) {
            PlayerView playerView = new PlayerView();
            playerView.setName(new String(p.getName()));
            playerView.setShelf(p.getShelf().asMatrix());
            playerView.setPoint(new ArrayList<>(p.getPoints()));
            playerViewList.add(playerView);
        }
        modelViewDataUpdate.setPlayerViewList(playerViewList);
        modelViewDataUpdate.setWithdrawnCards(withdrawnCards);
        modelViewDataUpdate.setTurnState(getTurnStatus());
        modelViewDataUpdate.setState(getModelViewState(getGameStatus()));
        return modelViewDataUpdate;
    }


    /**
     * Returns the corresponding ModelView state from the actual state of the model
     *
     * @param status the actual state of the model
     *
     * @return corresponding state of the modelView
     */

    private ModelView.State getModelViewState(GameStatus status) {
        switch (status) {
            case RUNNING : return ModelView.State.RUNNING;
            case PAUSED : return ModelView.State.PAUSED;
            case ENDED : return ModelView.State.ENDED;
        }
        return null;
    }

}