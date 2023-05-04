package Model.entities;

import Exceptions.InvalidActionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import util.Config;
class BagTest {

    Bag bag;
    @BeforeEach
    void setup()
    {
        Config.initialise(2);
        bag = new Bag();

    }

    @Test
    void constructorTest() throws InvalidActionException
    {

        assert (bag.getNumberOfCardsLeft() == 132);
        assert (bag.getNumberOfCardsOfEachType() == 22);

    }

    @Test
    void getCardTest() throws InvalidActionException
    {

        assert (bag.getNumberOfCardsLeft() == 132);

        for(int i=132; i>0; i--)
       {
           assert (bag.getNumberOfCardsLeft()==i);
           bag.getCard();
       }

        assert (bag.getNumberOfCardsLeft()==0);
        assert (bag.getNumberOfCardsOfEachType()==22);
    }

}