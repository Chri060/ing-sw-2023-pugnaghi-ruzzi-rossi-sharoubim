package Model;

import Exceptions.BagEmptyException;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

public class BagTest extends TestCase {
    @Test
    void pesca() throws BagEmptyException {
        Bag bag = new Bag();

        assertEquals(132, bag.cardsLeft());

        Cards card = bag.getCard();

        assert (card.getCardID() < bag.getNumOfCards() && card.getCardID() >= 0);

        assert (card.getType().equals(CardsType.CATS) || card.getType().equals(CardsType.FRAM)
        || card.getType().equals(CardsType.TROP) || card.getType().equals(CardsType.PLAN) ||
                card.getType().equals(CardsType.BOOK) || card.getType().equals(CardsType.GAME) );

        assertEquals(131, bag.cardsLeft());



    }

    @Test
    void checkAllCards() {
        Bag bag = new Bag();

        int[] corrispondences = new int[CardsType.values().length];

        while (bag.cardsLeft() != 0) {
            try {
                Cards card = bag.getCard();
                switch (card.getType()) {
                case CATS -> corrispondences[0]++;
                case TROP -> corrispondences[1]++;
                case PLAN -> corrispondences[2]++;
                case GAME -> corrispondences[3]++;
                case BOOK -> corrispondences[4]++;
                case FRAM -> corrispondences[5]++;
            }
            }
            catch (BagEmptyException w) {}
        }
        for (int i = 0; i < CardsType.values().length; i++) {
            assertEquals(bag.getNumOfCards(), corrispondences[i]);
        }
    }

}