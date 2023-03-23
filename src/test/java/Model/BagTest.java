package Model;

import Exceptions.BagEmptyException;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

public class BagTest extends TestCase {
    @Test
    void pesca() throws BagEmptyException {
        Bag bag = new Bag();
        Cards c;

        for (int i = 0; i < bag.getNumOfCards() * CardsType.values().length + 5; i++) {
            try {
                c = bag.getCard();
                System.out.println("Pesco il tipo " + c.getType() + " con ID: " + c.getCardID());
            }
            catch (BagEmptyException e) {
            }
        }


    }

}