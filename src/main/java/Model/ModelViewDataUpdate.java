package Model;

import Model.entities.Card;
import Model.entities.Point;
import Model.viewEntities.CommonObjectiveView;
import Model.viewEntities.PlayerView;

import java.io.Serializable;
import java.util.List;

public class ModelViewDataUpdate implements Serializable{

    private Card[][] dashboard;
    private List<PlayerView> playerViewList;
    private List<CommonObjectiveView> commonObjectiveViews;
    private String currentPlayer;
    private Model.TurnStatus turnState;
    private ModelView.State state;
    private List<Card> withdrawnCards;

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


    /**
     * @return the list df withdrawn Cards
     */
    public List<Card> getWithdrawnCards() {
        return withdrawnCards;
    }

    /**
     * Sets the cards list that is withdrawn of the turn
     *
     * @param withdrawnCards is the new list of cards to set
     */

    public void setWithdrawnCards(List<Card> withdrawnCards) {
        this.withdrawnCards = withdrawnCards;
    }
}