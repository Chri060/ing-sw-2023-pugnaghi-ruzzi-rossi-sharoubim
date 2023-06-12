package Model.entities.commonObjectives;

import Model.entities.Card;
import Model.entities.Point;
import Model.entities.Shelf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Config;

import java.util.ArrayList;
import java.util.List;

class CommonObjectiveTest {
    Shelf shelf;
    List<Card> cards;
    CommonObjective1 commonObjective1;

    /**
     * Construct a shelf, a list of cards and a CommonObjectiveOne object
     */
    @BeforeEach
    void setup(){
        Config.initialise(2);
        shelf = new Shelf();
        cards = new ArrayList<>();
        commonObjective1 = new CommonObjective1(4);
    }

    /**
     * Tests all methods in the abstract common objective card
     */
    @Test
    void general() {
        assert (commonObjective1.getID() == 4);
        Point points = commonObjective1.checkMaxPoints();
        assert (commonObjective1.getMaxPoints().equals(points));
        points = commonObjective1.checkMaxPoints();
        assert (commonObjective1.getMaxPoints().equals(points));
        points = commonObjective1.checkMaxPoints();
        assert (commonObjective1.getMaxPoints().equals(points));
        assert (CommonObjective.getNumberOfAvailableObjectives() == 12);
    }
}