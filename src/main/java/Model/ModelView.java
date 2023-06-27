package Model;

import Distributed.Events.*;
import Model.entities.Card;
import Model.entities.Point;
import Model.viewEntities.CommonObjectiveView;
import Model.viewEntities.DashBoardView;
import Model.viewEntities.PlayerView;
import Model.viewEntities.ShelfView;
import util.Observable;

import java.util.List;
import java.util.Optional;

/**
 * Class that is used to send the data to the clients
 */
public class ModelView extends Observable<Event> {

    public enum RequestStatus {
        SENT,
        WAITINGEVENT,
        RECIVED,
    }

    public enum State{
        SELECTNAME,
        SETTINGSIZE,
        WAITINGLEADER,
        NOTJOINABLE,
        INLOBBY,
        RUNNING,
        STARTED,
        PAUSED,
        ENDED
    }

    private State state;
    private RequestStatus requestStatus;
    private Model.TurnStatus turnState;
    private String name;
    private DashBoardView dashboard;
    private String roomLeader;
    private ShelfView myShelf;
    private List<Point> myPoints;
    private List<Card.Type[][]> myPrivateObjectives;
    private List<PlayerView> playerViews;
    private List<CommonObjectiveView> commonObjectiveViews;
    private List<Card> withdrawnCards;
    private String currentPlayer;
    private String chairPlayer;
    private List<String> playerNames;
    private int targetRoomSize;

    /**
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name variable in the model view
     *
     * @param name is the name of the player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the dashboard in the model view
     *
     * @param dashboard is the dashboard to save
     */
    public void setDashboard(DashBoardView dashboard) {
        this.dashboard = dashboard;
    }

    /**
     * @return the shelf
     */
    public ShelfView getMyShelf() {
        return myShelf;
    }

    /**
     * Sets the shelf in the model view
     *
     * @param myShelf is the shelf to save
     */
    public void setMyShelf(ShelfView myShelf) {
        this.myShelf = myShelf;
    }

    /**
     * @return the list of points
     */
    public List<Point> getMyPoints() {
        return myPoints;
    }

    /**
     * Sets the points in the model view
     *
     * @param myPoints is the list of points to save
     */
    public void setMyPoints(List<Point> myPoints) {
        this.myPoints = myPoints;
    }

    /**
     * @return the list of private objectives
     */
    public List<Card.Type[][]> getMyPrivateObjectives() {
        return myPrivateObjectives;
    }

    /**
     * Sets the list of private objectives
     *
     * @param myPrivateObjectives is the list of private objectives
     */
    public void setMyPrivateObjectives(List<Card.Type[][]> myPrivateObjectives) {
        this.myPrivateObjectives = myPrivateObjectives;
    }

    /**
     * Construct the ModelView object
     */
    public ModelView() {
        this.state = State.SELECTNAME;
        this.requestStatus = RequestStatus.RECIVED;
    }

    /**
     * Initializes the values of the ModelView
     *
     * @param modelViewData is the modelViewData object
     */
    public void initialise(ModelViewData modelViewData) {
        this.state = State.STARTED;
        this.dashboard = new DashBoardView(modelViewData.getDashboard());
        this.chairPlayer = modelViewData.getChairPlayer();
        this.currentPlayer = modelViewData.getCurrentPlayer();
        this.commonObjectiveViews = modelViewData.getCommonObjectiveViews();
        this.myPrivateObjectives = modelViewData.getMyPrivateObjectivePatterns();
        this.playerViews = modelViewData.getPlayerViewList();
        this.myShelf = new ShelfView(modelViewData.getMyShelf());
        this.myPoints = modelViewData.getMyPoints();
        this.turnState = Model.TurnStatus.DRAWING;
        setChangedAndNotifyObservers(new GameStartedEvent());
    }

    public void update(ModelViewDataUpdate modelViewDataUpdate) {
        this.dashboard = new DashBoardView(modelViewDataUpdate.getDashboard());
        this.currentPlayer = modelViewDataUpdate.getCurrentPlayer();
        this.commonObjectiveViews = modelViewDataUpdate.getCommonObjectiveViews();
        this.playerViews = modelViewDataUpdate.getPlayerViewList();
        Optional<PlayerView> me = modelViewDataUpdate.getPlayerViewList().stream().filter(x -> x.getName().equals(this.name)).findFirst();
        this.myShelf = new ShelfView(me.get().getShelf());
        this.myPoints = me.get().getPoint();
        this.withdrawnCards = modelViewDataUpdate.getWithdrawnCards();
        this.turnState = modelViewDataUpdate.getTurnState();
        this.state = modelViewDataUpdate.getState();
        setChangedAndNotifyObservers(new ModelViewUpdateEvent());
    }

    /**
     * Sets the leader of the room
     *
     * @param roomLeader is the name of the new leader to set
     */
    public void setRoomLeader(String roomLeader) {
        if (!roomLeader.equals(this.roomLeader)) {
            setChanged();
            this.roomLeader = roomLeader;
        }
        notifyObservers(new ResponseReceivedEvent());
    }

    /**
     * Sets the dashboard of the model view
     *
     * @param dashboard is the dashboard to save
     */
    public void setDashboard(Card[][] dashboard) {
        this.dashboard = new DashBoardView(dashboard);
    }

    /**
     * Sets the PlayerViews to link to this model view
     *
     * @param playerViews is the list of PlayerView
     */
    public void setPlayerViews(List<PlayerView> playerViews) {
        this.playerViews = playerViews;
    }

    /**
     * Set the CommonObjectiveViews to link to this model view
     *
     * @param commonObjectiveViews is the list of CommonObjectiveViews
     */
    public void setCommonObjectiveViews(List<CommonObjectiveView> commonObjectiveViews) {
        this.commonObjectiveViews = commonObjectiveViews;
        setChangedAndNotifyObservers(new ModelViewUpdateEvent());
    }

    /**
     * Sets the cards that has been withdrawn
     *
     * @param withdrawnCards is the list of card withdrawn from the dashboard
     */
    public void setWithdrawnCards(List<Card> withdrawnCards) {
        this.withdrawnCards = withdrawnCards;
    }

    /**
     * Sets the current player
     *
     * @param currentPlayer is the name of the player that is going to play
     */
    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
        setChangedAndNotifyObservers(new ModelViewUpdateEvent());
    }

    /**
     * Sets the chair player, that is the first player to start the match
     *
     * @param chairPlayer is the name of the player to set as chair player
     */
    public void setChairPlayer(String chairPlayer) {
        this.chairPlayer = chairPlayer;
    }

    /**
     * Sets the list of player in the game
     *
     * @param playerName is the list of players
     */
    public void setPlayerNames(List<String> playerName) {
        playerNames = playerName;
        setChangedAndNotifyObservers(new JoinLeaveLobbyEvent());
    }

    /**
     * Sets the state
     *
     * @param state is the new state
     */
    public synchronized void setState(State state) {
        this.state = state;
    }

    /**
     * Sets the TurnState
     *
     * @param turnState is the new tun state
     */
    public synchronized void setTurnState(Model.TurnStatus turnState) {
        this.turnState = turnState;
    }

    /**
     * Sets the dimension of the size of the room
     *
     * @param roomSize is the exact number of player required to start the game
     */
    public void setTargetRoomSize(int roomSize) {
        this.targetRoomSize = roomSize;
    }

    /**
     * @return the room leader
     */
    public String getRoomLeader() {
        return roomLeader;
    }

    /**
     * @return the dashboard
     */
    public DashBoardView getDashboard() {
        return dashboard;
    }

    /**
     * @return the list of player views
     */
    public List<PlayerView> getPlayerViews() {
        return playerViews;
    }

    /**
     * @return the list of common objectives
     */
    public List<CommonObjectiveView> getCommonObjectiveViews() {
        return commonObjectiveViews;
    }

    /**
     * @return the list of cards that had been withdrawn
     */
    public List<Card> getWithdrawnCards() {
        return withdrawnCards;
    }

    /**
     * @return the current player
     */
    public String getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * @return the list of the names of the players
     */
    public List<String> getPlayerNames() {
        return playerNames;
    }

    /**
     * @return the name of the chair player
     */
    public String getChairPlayer() {
        return chairPlayer;
    }

    /**
     * @return the actual game state
     */
    public synchronized State getState() {
        return state;
    }

    /**
     * @return the state of the turn
     */
    public synchronized Model.TurnStatus getTurnState() {
        return turnState;
    }

    /**
     * @return the status of the request
     */
    public synchronized RequestStatus getRequestStatus() {
        return requestStatus;
    }

    /**
     * @return the target size of the room
     */
    public int getTargetRoomSize() {
        return targetRoomSize;
    }

    /**
     * Updates the player shelf
     *
     * @param playerName is the name of the owner of the shelf to update
     * @param shelf is the new shelf
     */
    public void updatePlayerShelf(String playerName, Card[][] shelf) {
        Optional<PlayerView> p = playerViews.stream().filter(x -> x.getName().equals(playerName)).findFirst();
        if (p.isPresent()) {
            PlayerView player = p.get();
            player.setShelf(shelf);
        }
        else {
            myShelf = new ShelfView(shelf);
        }
        setChangedAndNotifyObservers(new ModelViewUpdateEvent());
    }

    /**
     * Sets the request status as SENT
     */
    public synchronized void requestSent() {
        this.requestStatus = RequestStatus.SENT;
        setChangedAndNotifyObservers(new WaitResponseEvent());
    }

    /**
     * Sets the request status as RECEIVED
     */
    public synchronized void setReceived() {
        this.requestStatus = RequestStatus.RECIVED;
        setChangedAndNotifyObservers(new ResponseReceivedEvent());
    }

    /**
     * Sets the request status as WAIT
     */
    public synchronized void waitEvent() {
        this.requestStatus = RequestStatus.WAITINGEVENT;
        setChangedAndNotifyObservers(new WaitEventEvent());
    }
}