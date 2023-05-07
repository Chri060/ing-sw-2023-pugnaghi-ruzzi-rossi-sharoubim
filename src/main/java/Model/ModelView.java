package Model;

import Model.entities.Card;
import Model.entities.Point;
import Model.viewEntities.CommonObjectiveView;
import Model.viewEntities.PlayerView;

import java.io.Serializable;
import java.util.List;

public class ModelView implements Serializable {

    private Card[][] dashboard;
    private List<PlayerView> playerViews;
    private List<CommonObjectiveView> commonObjectiveViews;
    private List<Card> withdrawnCards;



    public void setCommonObjectiveViews(List<CommonObjectiveView> commonObjectiveViews) {
        this.commonObjectiveViews = commonObjectiveViews;
    }

    public void setDashboard(Card[][] dashboard) {
        this.dashboard = dashboard;
    }

    public void setPlayerViews(List<PlayerView> playerViews) {
        this.playerViews = playerViews;
    }

    public void setWithdrawnCards(List<Card> withdrawnCards) {
        this.withdrawnCards = withdrawnCards;
    }

    public Card[][] getDashboard() {
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
}
