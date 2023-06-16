package Model;

import Model.entities.Card;
import Model.entities.Point;
import Model.viewEntities.CommonObjectiveView;
import Model.viewEntities.PlayerView;

import java.io.Serializable;
import java.util.List;

/**
 * Class that contains the useful values for the client
 */
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

    /**
     * @return the dashboard as an array of cards
     */
    public Card[][] getDashboard() {
        return dashboard;
    }

    /**
     * Sets this dashboard as the matrix of card given
     *
     * @param dashboard is the array of cards that represents the new dashboard
     */
    public void setDashboard(Card[][] dashboard) {
        this.dashboard = dashboard;
    }

    /**
     * @return the list of PlayerViews
     */
    public List<PlayerView> getPlayerViewList() {
        return playerViewList;
    }

    /**
     * Sets the new list of playerViews or updates the old one
     *
     * @param playerViewList is the list of PlayerViews to set
     */
    public void setPlayerViewList(List<PlayerView> playerViewList) {
        this.playerViewList = playerViewList;
    }

    /**
     * @return an array of card that represent the shelf
     */
    public Card[][] getMyShelf() {
        return myShelf;
    }

    /**
     * Sets the updated shelf
     *
     * @param myShelf is the new shelf as an array of cards
     */
    public void setMyShelf(Card[][] myShelf) {
        this.myShelf = myShelf;
    }

    /**
     * @return a list of Points
     */
    public List<Point> getMyPoints() {
        return myPoints;
    }

    /**
     * Sets the new list of points
     *
     * @param myPoints is the list of points to set
     */
    public void setMyPoints(List<Point> myPoints) {
        this.myPoints = myPoints;
    }

    /**
     * @return a list of private objective patterns
     */
    public List<Card.Type[][]> getMyPrivateObjectivePatterns() {
        return myPrivateObjectivePatterns;
    }

    /**
     * Sets the list of private objective patterns
     *
     * @param myPrivateObjectivePatterns is the list of private objectives to set
     */
    public void setMyPrivateObjectivePatterns(List<Card.Type[][]> myPrivateObjectivePatterns) {
        this.myPrivateObjectivePatterns = myPrivateObjectivePatterns;
    }

    /**
     * @return a list of common objective views
     */
    public List<CommonObjectiveView> getCommonObjectiveViews() {
        return commonObjectiveViews;
    }

    /**
     * Sets the list of common objective views
     *
     * @param commonObjectiveViews is the updated list of common objective views
     */
    public void setCommonObjectiveViews(List<CommonObjectiveView> commonObjectiveViews) {
        this.commonObjectiveViews = commonObjectiveViews;
    }

    /**
     * @return the first player that is going to start the game or started it
     */
    public String getChairPlayer() {
        return chairPlayer;
    }

    /**
     * Sets the first player that is going to start the game
     *
     * @param chairPlayer is the name of the player
     */
    public void setChairPlayer(String chairPlayer) {
        this.chairPlayer = chairPlayer;
    }

    /**
     * @return the name of the player that is going to do the next action
     */
    public String getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Sets the name of the player that is going to do the next action
     *
     * @param currentPlayer is the name of the player
     */
    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * @return the state of the turn
     */
    public Model.TurnStatus getTurnState() {
        return turnState;
    }

    /**
     * Sets the state of the turn
     *
     * @param turnState is the new turn status to set
     */
    public void setTurnState(Model.TurnStatus turnState) {
        this.turnState = turnState;
    }

    /**
     * @return the state of the game
     */
    public ModelView.State getState() {
        return state;
    }

    /**
     * Sets the state of the turn
     *
     * @param state is the new status of the game to set
     */
    public void setState(ModelView.State state) {
        this.state = state;
    }
}