package Model;

import Model.entities.Card;
import Model.viewEntities.CommonObjectiveView;
import Model.viewEntities.PlayerView;

import java.io.Serializable;
import java.util.List;

public class ModelViewData implements Serializable {

    private Card[][] dashboard;
    private List<PlayerView> playerViews;
    private List<CommonObjectiveView> commonObjectiveViews;
    private String chairPlayer;

    public Card[][] getDashboard() {
        return dashboard;
    }

    public void setDashboard(Card[][] dashboard) {
        this.dashboard = dashboard;
    }

    public List<PlayerView> getPlayerViews() {
        return playerViews;
    }

    public void setPlayerViews(List<PlayerView> playerViews) {
        this.playerViews = playerViews;
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
}
