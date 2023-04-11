package Model;

import Exceptions.BagEmptyException;
import Exceptions.CannotWithdrawCardException;
import Exceptions.InvalidPickException;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TableTest extends TestCase {

    @Test
    void withdrawTest() {


        Table table = new Table(3);
        List<Integer> coordinates = new ArrayList<>();
        List<Cards> cardsList;

        coordinates.add(-1);
        coordinates.add(5);

        try {
            table.withdraw(coordinates);
            assert (false);
        } catch (InvalidPickException e) {
        }
        catch (CannotWithdrawCardException e) {
            assert (false);
        }

        coordinates.clear();


        for (int i = 0; i < table.dashDim; i++) {
            for (int j = 0; j < table.dashDim; j++) {
                if (table.taps[i][j] && table.dashboard[i][j] == null) {
                    Cards card = new Cards(CardsType.values()[i % 6], 0);
                    table.dashboard[i][j] = card;
                }
            }
        }

        coordinates.add(3);
        coordinates.add(2);
        coordinates.add(3);
        coordinates.add(4);


        try {
            table.withdraw(coordinates);
            assert (false);
        } catch (InvalidPickException e) {
            assert (false);
        }
        catch (CannotWithdrawCardException e) {
            assert(table.dashboard[3][2] != null);
            assert(table.dashboard[3][3] != null);
        }


        coordinates.clear();


        coordinates.add(1);
        coordinates.add(3);
        coordinates.add(1);
        coordinates.add(4);
        coordinates.add(2);
        coordinates.add(3);


        try {
            table.withdraw(coordinates);
            assert (false);
        } catch (InvalidPickException e) {
            assert (false);
        }
        catch (CannotWithdrawCardException e) {
            assert(table.dashboard[1][3] != null);
            assert(table.dashboard[1][4] != null);
            assert(table.dashboard[2][3] != null);
        }

        coordinates.clear();


        coordinates.add(1);
        coordinates.add(3);
        coordinates.add(1);
        coordinates.add(4);

        try {
            cardsList = table.withdraw(coordinates);
            assert (cardsList.get(0).getType() == CardsType.BOOK);
            assert (cardsList.get(1).getType() == CardsType.BOOK);
            assert (table.dashboard[1][3] == null);
            assert (table.dashboard[1][4] == null);
        } catch (InvalidPickException | CannotWithdrawCardException e) {
            assert (false);
        }

    }



































    @Test
    void test() throws BagEmptyException {
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
    void listTest() throws BagEmptyException {
        Table tabelle;
        Bag bag = new Bag();

        tabelle = new Table(2);
        tabelle.refill(bag);
        tabelle.printCards();
        System.out.println();


        List<Integer> exampList = new ArrayList<>();
        List<Cards> givenList;

        exampList.add(4);
        exampList.add(1);
        exampList.add(5);
        exampList.add(1);

        try {
            givenList = tabelle.withdraw(exampList);
            for (int i = 0; i < givenList.size(); i++) {
                System.out.println(givenList.get(i).getType());
            }
            System.out.println();


            tabelle.printCards();
            System.out.println();
        }
        catch (CannotWithdrawCardException | InvalidPickException e) {}


    }


    @Test
    void refillTest() throws BagEmptyException, InvalidPickException{
        Table dash;
        dash = new Table(2);
        Bag bag = new Bag();
        List <Integer> cards = new ArrayList<Integer>();
        List <Cards> withdrown = new ArrayList<Cards>();
        Random rand = new Random();

        dash.refill(bag);

        dash.printCards();
        System.out.println();

        for (int i = 1; i < 8; i++) {
            for (int j = 1; j < 8; j++) {
                cards.add(rand.nextInt(8));
                cards.add(rand.nextInt(8));
                try {
                    withdrown = dash.withdraw(cards);
                    System.out.println("Withdrawn " + withdrown.get(0).getType() + " from [" + cards.get(0) + ", " + cards.get(1) + "]");
                    dash.printCards();
                }
                catch (CannotWithdrawCardException e) {
                }
                cards.clear();
            }
        }

        dash.printCards();
        System.out.println();


        System.out.println(dash.needsRefill());

    }

    @Test
    void jsonTest() throws BagEmptyException {
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