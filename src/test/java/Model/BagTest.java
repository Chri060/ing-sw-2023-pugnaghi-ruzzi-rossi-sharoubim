package Model;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class BagTest extends TestCase {
    @Test
    void pesca() {
        Bag bag = new Bag();

        for (int i = 0; i < bag.NUMBEROFCARDS * Cards.values().length; i++) {
            System.out.println("Pesco il tipo " + bag.getCard());
        }

        for (Cards c : Cards.values()) {
            System.out.println("Rimanenti per il tipo " + c + ": "+ bag.getNumberOf(c));
        }

    }

}