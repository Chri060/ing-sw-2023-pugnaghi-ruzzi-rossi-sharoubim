package Model;

import Exceptions.ColumFullException;
import junit.framework.TestCase;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LibraryTest extends TestCase {

    @Test
    void printLibrary(Library l) {
        Cards[][] c = l.getAsMatrix();
        for (int i = l.LIBRARYROWS - 1; i >= 0; i--) {
            for (int j = 0; j < l.LIBRARYCOLUMNS; j++) {
                System.out.print(c[i][j] + "\t");
            }
            System.out.println();

        }
    }

    @Test
    void libraryTest() {
        Library l = new Library();
        List<Cards> cards = new ArrayList<>();
        Random rand = new Random();



        int x;
        for (int i = 0; i < 20; i++) {
            x = rand.nextInt(l.LIBRARYCOLUMNS);
            System.out.print("Inserting in " + (x+1) + "°column : ");
            cards = new ArrayList<>();
            for (int j = 0; j < 1 + rand.nextInt(3); j++) {
                cards.add(Cards.values()[rand.nextInt(6)]);
            }
            System.out.println(cards);
            try {
                l.insert(cards, x);
                printLibrary(l);
            }
            catch (ColumFullException e) {
            }

            System.out.println();
        }
    }



    @Test
    void is_Full() throws ColumFullException {
        Library l = new Library();
        List<Cards> cards = new ArrayList<>();
        Random rand = new Random();
        int x;




        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                cards.clear();
                cards.add(Cards.values()[rand.nextInt(6)]);
                l.insert(cards, i);
                printLibrary(l);
                System.out.println(l.isFull());
                System.out.println();
            }
        }

    }


    @Test
    void maxcolumn() throws ColumFullException {
        Library l = new Library();
        List<Cards> cards = new ArrayList<>();
        Random rand = new Random();
        int x;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                cards.clear();
                cards.add(Cards.values()[rand.nextInt(6)]);
                l.insert(cards, i);
                printLibrary(l);
                System.out.println(l.maxFreeSpace());
                System.out.println();
            }
        }

    }
}