package Model.entities;

import Exceptions.InvalidActionException;
import org.junit.jupiter.api.Test;
import util.Config;

class BagTest {

    @Test
    void constructorTest() throws InvalidActionException {
        Config.initialise(2);
        Bag bag = new Bag();

        //assert (bag.getNumberOfCardsLeft() == 132);
        //assert (bag.getNumberOfCardsOfEachType() == 22);

        try {
            bag.getCard();
        }
        catch (InvalidActionException e) {
            System.out.println(e.getMessage());
        }
        //assert (bag.getNumberOfCardsLeft() == 131);

    }

}