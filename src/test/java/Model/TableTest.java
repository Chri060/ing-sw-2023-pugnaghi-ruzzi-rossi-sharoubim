package Model;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

public class TableTest extends TestCase {

    @Test
    void test() {
        Bag bag = new Bag();
        Table[] tabelle = new Table[3];
        for (int i = 0; i < 3; i++) {
            tabelle[i] = new Table(i + 2);
            System.out.println("For " + (i+2) + " players");
            /*tabelle[i].printCards();
            System.out.println();*/
            tabelle[i].refill(bag);
            tabelle[i].printCards();
            System.out.println();

        }

    }
}