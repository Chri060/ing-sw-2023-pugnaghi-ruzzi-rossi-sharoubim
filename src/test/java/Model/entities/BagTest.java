package Model.entities;

import Exceptions.InvalidActionException;
import org.junit.jupiter.api.*;

import util.Config;

class BagTest {

    Bag bag;

    /**
     * Creates the bag
     */
    @BeforeEach
    void setup() {
        Config.initialise(2);
        bag = new Bag();
    }

    /**
     * Check if the created bag has the right number of cards and cards of each type
     */
    @Test
    void constructorTest() {
        assert (bag.getNumberOfCardsLeft() == 132);
        assert (bag.getNumberOfCardsOfEachType() == 22);
    }

    /**
     * Draws all cards from the bag and checks if the number of cards decreases every time
     *
     * @throws InvalidActionException on invalid action
     */
    @Test
    void getCardTest() throws InvalidActionException {

        assert (bag.getNumberOfCardsLeft() == 132);

        for (int i = 132; i > 0; i--) {
           assert (bag.getNumberOfCardsLeft() == i);
           bag.getCard();
        }

        assert (bag.getNumberOfCardsLeft() == 0);
        assert (bag.getNumberOfCardsOfEachType() == 22);
    }
}