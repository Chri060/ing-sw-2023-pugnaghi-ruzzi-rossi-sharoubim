package Model;

import Model.entities.Card;
import Model.entities.Point;
import Model.viewEntities.CommonObjectiveView;
import Model.viewEntities.DashBoardView;
import Model.viewEntities.Events.*;
import Model.viewEntities.PlayerView;
import Model.viewEntities.ShelfView;
import util.Observable;

import java.util.List;
import java.util.Optional;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDashboard(DashBoardView dashboard) {
        this.dashboard = dashboard;
    }

    public ShelfView getMyShelf() {
        return myShelf;
    }

    public void setMyShelf(ShelfView myShelf) {
        this.myShelf = myShelf;
    }

    public List<Point> getMyPoints() {
        return myPoints;
    }

    public void setMyPoints(List<Point> myPoints) {
        this.myPoints = myPoints;
    }

    public List<Card.Type[][]> getMyPrivateObjectives() {
        return myPrivateObjectives;
    }

    public void setMyPrivateObjectives(List<Card.Type[][]> myPrivateObjectives) {
        this.myPrivateObjectives = myPrivateObjectives;
    }

    private List<Point> myPoints;
    private List<Card.Type[][]> myPrivateObjectives;
    private List<PlayerView> playerViews;
    private List<CommonObjectiveView> commonObjectiveViews;
    private List<Card> withdrawnCards;
    private String currentPlayer;
    private String chairPlayer;
    private List<String> playerNames;
    private int targetRoomSize;
    public ModelView() {
        this.state = State.SELECTNAME;
        this.requestStatus = RequestStatus.RECIVED;
    }

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
        setChangedAndNotifyObservers(new GameStartedEvent());
    }

    public void setRoomLeader(String roomLeader) {
        if (!roomLeader.equals(this.roomLeader)) {
            setChanged();
            this.roomLeader = roomLeader;
        }
        notifyObservers(new ResponseReceivedEvent());

    }

    public void setDashboard(Card[][] dashboard) {
        this.dashboard = new DashBoardView(dashboard);
    }
    public void setPlayerViews(List<PlayerView> playerViews) {
        this.playerViews = playerViews;
    }
    public void setCommonObjectiveViews(List<CommonObjectiveView> commonObjectiveViews) {
        this.commonObjectiveViews = commonObjectiveViews;
    }
    public void setWithdrawnCards(List<Card> withdrawnCards) {
        this.withdrawnCards = withdrawnCards;
    }
    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    public void setChairPlayer(String chairPlayer) {
        this.chairPlayer = chairPlayer;
    }
    public void setPlayerNames(List<String> playerName) {
        playerNames = playerName;
        setChangedAndNotifyObservers(new JoinLeaveLobbyEvent());
    }
    public synchronized void setState(State state) {
        this.state = state;
    }
    public synchronized void setTurnState(Model.TurnStatus turnState) {
        this.turnState = turnState;
    }

    public void setTargetRoomSize(int roomSize) {
        this.targetRoomSize = roomSize;
    }

    public String getRoomLeader() {
        return roomLeader;
    }

    public DashBoardView getDashboard() {
        return dashboard;
    }
    public List<PlayerView> getPlayerViews() {
        return playerViews;
    }
    public List<CommonObjectiveView> getCommonObjectiveViews() {
        return commonObjectiveViews;
    }
    public List<Card> getWithdrawnCards() {
        return withdrawnCards;
    }
    public String getCurrentPlayer() {
        return currentPlayer;
    }
    public List<String> getPlayerNames() {
        return playerNames;
    }
    public String getChairPlayer() {
        return chairPlayer;
    }
    public synchronized State getState() {
        return state;
    }
    public synchronized Model.TurnStatus getTurnState() {
        return turnState;
    }
    public synchronized RequestStatus getRequestStatus() {
        return requestStatus;
    }
    public int getTargetRoomSize() {
        return targetRoomSize;
    }

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

    public synchronized void requestSent() {
        this.requestStatus = RequestStatus.SENT;
        setChangedAndNotifyObservers(new WaitResponseEvent());
    }
    public synchronized void setReceived() {
        this.requestStatus = RequestStatus.RECIVED;
        setChangedAndNotifyObservers(new ResponseReceivedEvent());
    }

    public synchronized void waitEvent() {
        this.requestStatus = RequestStatus.WAITINGEVENT;
        setChangedAndNotifyObservers(new WaitEventEvent());
    }

}
