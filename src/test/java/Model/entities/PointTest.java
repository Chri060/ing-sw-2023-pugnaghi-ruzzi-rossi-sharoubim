package Model.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Config;

import java.util.Objects;

class PointTest {

    /**
     * Initializes the config for the game
     */
    @BeforeEach
    void setup() {
        Config.initialise(2);

    }

    /**
     * Creates various points with their origin and check if all is correct
     */
    @Test
    void contructorTest() {
        Point pointOne = new Point(8, "common objective");
        Point pointTwo = new Point(4, "common objective");
        Point pointThree = new Point(12, "private objective");
        Point pointFour = new Point(1, "final point");

        assert (pointOne.getValue() == 8);
        assert (Objects.equals(pointOne.getOrigin(), "common objective"));
        assert (pointTwo.getValue() == 4);
        assert (Objects.equals(pointTwo.getOrigin(), "common objective"));
        assert (pointThree.getValue() == 12);
        assert (Objects.equals(pointThree.getOrigin(), "private objective"));
        assert (pointFour.getValue() == 1);
        assert (Objects.equals(pointFour.getOrigin(), "final point"));
    }
}