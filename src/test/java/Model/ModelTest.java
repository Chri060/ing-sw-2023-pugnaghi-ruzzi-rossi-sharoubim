package Model;

import Exceptions.InvalidActionException;
import Model.entities.PrivateObjective;
import org.junit.jupiter.api.Test;
import util.Generator;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    @Test
    void constructorTest() throws InvalidActionException {
        Model model = new Model();

        model.addObserver(null);

        model.joinPlayer("Carlo");
        model.joinPlayer("Christian");
        model.joinPlayer("Gianluca");

        model.start();



    }

}