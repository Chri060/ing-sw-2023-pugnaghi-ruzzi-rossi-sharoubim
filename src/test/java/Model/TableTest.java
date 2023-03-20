package Model;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

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


    @Test
    void listTest() {
        Table[] tabelle = new Table[1];
        Bag bag = new Bag();

        tabelle[0] = new Table(2);
        tabelle[0].refill(bag);
        tabelle[0].printCards();
        System.out.println();


        List<Integer> exampList = new ArrayList<>();
        List<Cards> givenList;

        exampList.add(0);
        exampList.add(0);
        exampList.add(5);
        exampList.add(1);

        givenList = tabelle[0].withdraw(exampList);


        System.out.println(givenList);
        System.out.println();


        tabelle[0].printCards();
        System.out.println();
    }


    @Test
    void refillTest () {
        Table[] tabelle = new Table[1];
        tabelle[0] = new Table(2);
        Bag bag = new Bag();


        tabelle[0].refill(bag);

        tabelle[0].printCards();
        System.out.println();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if ((i != 5 || j != 6)) {tabelle[0].getCard(i, j); }
            }
        }

        tabelle[0].printCards();
        System.out.println();


        System.out.println(tabelle[0].needsRefill());

    }

    @Test
    void jsonTest() {
        Table t;
        Bag b = new Bag();
        for (int i = 2; i < 5; i++) {
            System.out.println("For " + i + " players");
            t = new Table(i);
            t.printTaps();
            t.refill(b);
            System.out.println();
            t.printCards();
            System.out.println();
            System.out.println("---------------------------------------------------------------------");
        }
    }

}