package Model.viewentities;

import Model.entities.Point;
import Model.viewEntities.CommonObjectiveView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Config;


class CommonObjectiveViewTest {

    CommonObjectiveView commonObjectiveView;

    /**
     * Creates the CommonObjectiveView
     */
    @BeforeEach
    void setup() {
        Config.initialise(2);
        commonObjectiveView = new CommonObjectiveView();
    }

    @Test
    void general () {
        commonObjectiveView.setID(1);
        assert (commonObjectiveView.getID() == 1);

        commonObjectiveView.setMaxPoint(new Point(1, "test"));
        assert (commonObjectiveView.getMaxPoint().getValue() == 1);
    }
}