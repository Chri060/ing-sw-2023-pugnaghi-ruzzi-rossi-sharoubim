package Model;

import Model.entities.Card;
import Model.entities.Point;
import Model.viewEntities.CommonObjectiveView;
import Model.viewEntities.DashBoardView;
import Model.viewEntities.PlayerView;
import Model.viewEntities.ShelfView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Config;

import java.util.ArrayList;
import java.util.List;

class ModelViewTest {

    ModelView modelView;

    /**
     * Creates the model view
     */
    @BeforeEach
    void setup() {
        Config.initialise(2);
        modelView = new ModelView();
    }

    @Test
    void test() {
        modelView.setName("playerOne");
        assert (modelView.getName().equals("playerOne"));

        modelView.setState(ModelView.State.PAUSED);
        assert (modelView.getState() == ModelView.State.PAUSED);

        modelView.setTurnState(Model.TurnStatus.INSERTING);
        assert (modelView.getTurnState() == Model.TurnStatus.INSERTING);

        modelView.setTargetRoomSize(2);
        assert (modelView.getTargetRoomSize() == 2);

        Card[][] dashboard = new Card[9][9];
        modelView.setDashboard(new DashBoardView(dashboard));
        assert (modelView.getDashboard() != null);
        modelView.setDashboard(dashboard);
        assert (modelView.getDashboard() != null);

        modelView.setMyShelf(new ShelfView(dashboard));
        assert (modelView.getMyShelf() != null);

        List<Point> points = new ArrayList<>();
        points.add(new Point(1, "origin"));
        modelView.setMyPoints(points);
        assert (modelView.getMyShelf() != null);

        modelView.initialise(new ModelViewData());

        modelView.setRoomLeader("player");
        assert (modelView.getRoomLeader().equals("player"));

        List<PlayerView> playerViewList = new ArrayList<>();
        modelView.setPlayerViews(playerViewList);
        assert (modelView.getPlayerViews() != null);

        List<CommonObjectiveView> commonObjectiveViews = new ArrayList<>();
        modelView.setCommonObjectiveViews(commonObjectiveViews);
        assert (modelView.getCommonObjectiveViews() != null);

        List<Card> cards = new ArrayList<>();
        modelView.setWithdrawnCards(cards);
        assert (modelView.getWithdrawnCards() != null);

        modelView.setCurrentPlayer("playerOne");
        assert (modelView.getCurrentPlayer().equals("playerOne"));

        modelView.setChairPlayer("playerOne");
        assert (modelView.getChairPlayer().equals("playerOne"));

        List<String> names = new ArrayList<>();
        names.add("playerOne");
        names.add("playerTwo");
        names.add("playerThree");
        modelView.setPlayerNames(names);
        assert (modelView.getPlayerNames().get(2).equals("playerThree"));

        assert (modelView.getRequestStatus() != null);
        Card[][] card = new Card[3][3];
        modelView.updatePlayerShelf("player", card);

        modelView.requestSent();
        modelView.waitEvent();

        modelView.setMyPrivateObjectives(null);
        assert (modelView.getMyPrivateObjectives() == null);

        List<Card.Type[][]> cardList = new ArrayList<>();
        modelView.setMyPrivateObjectives(cardList);
        assert (modelView.getMyPrivateObjectives() != null);
    }
}