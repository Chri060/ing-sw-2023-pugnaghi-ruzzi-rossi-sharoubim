package Model;

import Model.entities.Card;
import Model.entities.Point;
import Model.viewEntities.CommonObjectiveView;
import Model.viewEntities.PlayerView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Config;

import java.util.ArrayList;
import java.util.List;

class ModelViewDataTest {

    ModelViewData modelViewData;

    /**
     * Creates the model view data
     */
    @BeforeEach
    void setup() {
        Config.initialise(2);
        modelViewData = new ModelViewData();
    }

    @Test
    void general() {

        Card[][] card = new Card[9][9];
        modelViewData.setDashboard(card);
        assert (modelViewData.getDashboard() != null);

        List<PlayerView> playerViewList = new ArrayList<>();
        modelViewData.setPlayerViewList(playerViewList);
        assert (modelViewData.getPlayerViewList() != null);

        modelViewData.setMyShelf(card);
        assert (modelViewData.getMyShelf() != null);

        List<Point> points = new ArrayList<>();
        modelViewData.setMyPoints(points);
        assert (modelViewData.getMyShelf() != null);

        List<Card.Type[][]> cardList = new ArrayList<>();
        modelViewData.setMyPrivateObjectivePatterns(cardList);
        assert (modelViewData.getMyPrivateObjectivePatterns() != null);

        List<CommonObjectiveView> commonObjectiveViewsList = new ArrayList<>();
        modelViewData.setCommonObjectiveViews(commonObjectiveViewsList);
        assert (modelViewData.getCommonObjectiveViews() != null);

        modelViewData.setChairPlayer("player");
        assert (modelViewData.getChairPlayer().equals("player"));

        modelViewData.setCurrentPlayer("player");
        assert (modelViewData.getCurrentPlayer().equals("player"));

        modelViewData.setTurnState(Model.TurnStatus.INSERTING);
        assert (modelViewData.getTurnState() == Model.TurnStatus.INSERTING);

        modelViewData.setState(ModelView.State.INLOBBY);
        assert (modelViewData.getState() == ModelView.State.INLOBBY);
    }
}