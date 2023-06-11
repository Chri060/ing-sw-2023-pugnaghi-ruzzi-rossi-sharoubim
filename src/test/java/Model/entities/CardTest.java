package Model.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Config;

import static Model.entities.Card.Type.*;

class CardTest {

    /**
     * Initializes the config for the game
     */
    @BeforeEach
    void setup() {
        Config.initialise(2);
    }

    /**
     * Checks if the equality method works all correctly
     */
    @Test
    void equalityTest () {
        Card cardOne = new Card(PLANT,0);
        Card cardTwo = new Card(PLANT,0);
        Card cardThree = new Card(TROPHY,0);
        Card cardFour = new Card(PLANT,10);

        assert (cardOne.getType() != CAT);
        assert (cardOne.getType() != BOOK);
        assert (cardOne.getType() != GAME);
        assert (cardOne.getType() != FRAME);
        assert (cardOne.getType() != TROPHY);
        assert (cardOne.getType() == PLANT);
        assert (cardOne.getId() == 0);

        assert (cardTwo.getType() != CAT);
        assert (cardTwo.getType() != BOOK);
        assert (cardTwo.getType() != GAME);
        assert (cardTwo.getType() != FRAME);
        assert (cardTwo.getType() != TROPHY);
        assert (cardTwo.getType() == PLANT);
        assert (cardTwo.getId() == 0);

        assert (cardThree.getType() != CAT);
        assert (cardThree.getType() != BOOK);
        assert (cardThree.getType() != GAME);
        assert (cardThree.getType() != FRAME);
        assert (cardThree.getType() == TROPHY);
        assert (cardThree.getType() != PLANT);
        assert (cardThree.getId() == 0);

        assert (cardFour.getType() != CAT);
        assert (cardFour.getType() != BOOK);
        assert (cardFour.getType() != GAME);
        assert (cardFour.getType() != FRAME);
        assert (cardFour.getType() != TROPHY);
        assert (cardFour.getType() == PLANT);
        assert (cardFour.getId() == 10);

        assert (cardOne.equals(cardTwo));
        assert (!cardOne.equals(cardThree));
        assert (!cardOne.equals(cardFour));
        assert (!cardTwo.equals(cardThree));
        assert (!cardTwo.equals(cardFour));
        assert (!cardThree.equals(cardFour));

        assert (cardOne.equalsType(cardTwo));
        assert (!cardOne.equalsType(cardThree));
        assert (cardOne.equalsType(cardFour));
        assert (!cardTwo.equalsType(cardThree));
        assert (cardTwo.equalsType(cardFour));
        assert (!cardThree.equalsType(cardFour));

        assert (!cardOne.equalsType(CAT));
        assert (!cardOne.equalsType(BOOK));
        assert (!cardOne.equalsType(GAME));
        assert (!cardOne.equalsType(FRAME));
        assert (!cardOne.equalsType(TROPHY));
        assert (cardOne.equalsType(PLANT));

        assert (!cardTwo.equalsType(CAT));
        assert (!cardTwo.equalsType(BOOK));
        assert (!cardTwo.equalsType(GAME));
        assert (!cardTwo.equalsType(FRAME));
        assert (!cardTwo.equalsType(TROPHY));
        assert (cardTwo.equalsType(PLANT));

        assert (!cardThree.equalsType(CAT));
        assert (!cardThree.equalsType(BOOK));
        assert (!cardThree.equalsType(GAME));
        assert (!cardThree.equalsType(FRAME));
        assert (cardThree.equalsType(TROPHY));
        assert (!cardThree.equalsType(PLANT));

        assert (!cardFour.equalsType(CAT));
        assert (!cardFour.equalsType(BOOK));
        assert (!cardFour.equalsType(GAME));
        assert (!cardFour.equalsType(FRAME));
        assert (!cardFour.equalsType(TROPHY));
        assert (cardFour.equalsType(PLANT));
    }
}