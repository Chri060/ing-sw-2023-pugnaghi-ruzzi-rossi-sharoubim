package Model;

import Model.entities.Card;
import Model.viewEntities.CommonObjectiveView;
import Model.viewEntities.DashBoardView;
import Model.viewEntities.PlayerView;

import java.util.List;

public class ModelView {

    private DashBoardView dashboard;
    private List<PlayerView> playerViews;
    private List<CommonObjectiveView> commonObjectiveViews;
    private List<Card> withdrawnCards;
    private String currentPlayer;
    private String chairPlayer;
    private List<String> playerNames;

    public ModelView() {}

    public ModelView(ModelViewData modelViewData) {
        this.dashboard = new DashBoardView(modelViewData.getDashboard());
        this.playerViews = modelViewData.getPlayerViews();
        this.chairPlayer = modelViewData.getChairPlayer();
        this.currentPlayer = chairPlayer;
        this.commonObjectiveViews = modelViewData.getCommonObjectiveViews();
    }


    public void updateDashboard(Card[][] dashboard) {
        this.dashboard = new DashBoardView(dashboard);
    }

    public void updatePlayerViews(List<PlayerView> playerViews) {
        this.playerViews = playerViews;
    }

    public void updateCommonObjectiveViews(List<CommonObjectiveView> commonObjectiveViews) {
        this.commonObjectiveViews = commonObjectiveViews;
    }

    public void updateWithdrawnCards(List<Card> withdrawnCards) {
        this.withdrawnCards = withdrawnCards;
    }

    public void updateCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
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

    public void addPlayerName(String playerName) {
        playerNames.add(playerName);
    }

    public void removePlayerName(String playerName) {
        playerNames.remove(playerName);
    }
}
