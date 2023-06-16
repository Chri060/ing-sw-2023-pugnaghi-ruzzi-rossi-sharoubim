package Model.viewentities;

import Model.entities.Card;
import Model.viewEntities.DashBoardView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Config;
import util.PlanarCoordinate;

import java.util.ArrayList;
import java.util.List;

class DashBoardViewTest {

    DashBoardView dashBoardView;

    /**
     * Creates the CommonObjectiveView
     */
    @BeforeEach
    void setup() {
        Config.initialise(2);
        Card[][] dashboard = new Card[9][9];
        dashBoardView = new DashBoardView(dashboard);
    }

    @Test
    void general() {
        List<PlanarCoordinate> coordinates = new ArrayList<>();
        assert (!dashBoardView.canWithdraw(coordinates));

        coordinates.add(new PlanarCoordinate(0,0));
        coordinates.add(new PlanarCoordinate(0,1));
        coordinates.add(new PlanarCoordinate(0,2));
        assert (!dashBoardView.canWithdraw(coordinates));

        coordinates.clear();
        coordinates.add(new PlanarCoordinate(3,3));
        coordinates.add(new PlanarCoordinate(3,4));
        coordinates.add(new PlanarCoordinate(4,4));
        assert (!dashBoardView.canWithdraw(coordinates));

        coordinates.clear();
        coordinates.add(new PlanarCoordinate(-1,3));
        coordinates.add(new PlanarCoordinate(3,4));
        coordinates.add(new PlanarCoordinate(4,0));
        assert (!dashBoardView.canWithdraw(coordinates));
    }
}