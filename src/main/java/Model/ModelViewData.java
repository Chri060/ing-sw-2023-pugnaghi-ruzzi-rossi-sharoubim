package Model;

import Model.entities.Card;
import Model.entities.Point;
import Model.viewEntities.CommonObjectiveView;
import Model.viewEntities.PlayerView;

import java.io.Serializable;
import java.util.List;

public class ModelViewData implements Serializable {

    private Card[][] dashboard;
    private List<PlayerView> playerViewList;
    private Card[][] myShelf;
    private List<Point> myPoints;
    private List<Card.Type[][]> myPrivateObjectivePatterns;
    private List<CommonObjectiveView> commonObjectiveViews;
    private String chairPlayer;
    private String currentPlayer;
    private Model.TurnStatus turnState;
    private ModelView.State state;

    public Card[][] getDashboard() {
        return dashboard;
    }

    public void setDashboard(Card[][] dashboard) {
        this.dashboard = dashboard;
    }

    public List<PlayerView> getPlayerViewList() {
        return playerViewList;
    }

    public void setPlayerViewList(List<PlayerView> playerViewList) {
        this.playerViewList = playerViewList;
    }

    public Card[][] getMyShelf() {
        return myShelf;
    }

    public void setMyShelf(Card[][] myShelf) {
        this.myShelf = myShelf;
    }

    public List<Point> getMyPoints() {
        return myPoints;
    }

    public void setMyPoints(List<Point> myPoints) {
        this.myPoints = myPoints;
    }

    public List<Card.Type[][]> getMyPrivateObjectivePatterns() {
        return myPrivateObjectivePatterns;
    }

    public void setMyPrivateObjectivePatterns(List<Card.Type[][]> myPrivateObjectivePatterns) {
        this.myPrivateObjectivePatterns = myPrivateObjectivePatterns;
    }

    public List<CommonObjectiveView> getCommonObjectiveViews() {
        return commonObjectiveViews;
    }

    public void setCommonObjectiveViews(List<CommonObjectiveView> commonObjectiveViews) {
        this.commonObjectiveViews = commonObjectiveViews;
    }

    public String getChairPlayer() {
        return chairPlayer;
    }

    public void setChairPlayer(String chairPlayer) {
        this.chairPlayer = chairPlayer;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Model.TurnStatus getTurnState() {
        return turnState;
    }

    public void setTurnState(Model.TurnStatus turnState) {
        this.turnState = turnState;
    }

    public ModelView.State getState() {
        return state;
    }

    public void setState(ModelView.State state) {
        this.state = state;
    }
}
