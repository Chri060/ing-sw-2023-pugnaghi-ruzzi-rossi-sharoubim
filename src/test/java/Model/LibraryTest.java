package Model;

import Exceptions.ColumFullException;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.awt.font.LineBreakMeasurer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LibraryTest extends TestCase {

    @Test
    void printLibrary(Library l) {
        Cards[][] c = l.getAsMatrix();
        for (int i = l.LIBRARYROWS - 1; i >= 0; i--) {
            for (int j = 0; j < l.LIBRARYCOLUMNS; j++) {
                if (c[i][j] != null) {
                    System.out.print(c[i][j].getType() + "\t");
                }
                else {
                    System.out.print("null" + "\t");
                }
            }
            System.out.println();
        }
    }

    @Test
    void insertTest() {
        Library l = new Library();
        List<Cards> cards = new ArrayList<>();
        Random rand = new Random();

        int x;
        for (int i = 0; i < 20; i++) {
            x = rand.nextInt(l.LIBRARYCOLUMNS);
            System.out.print("Inserting in " +  "column " + x + ": ");
            cards = new ArrayList<>();
            for (int j = 0; j < 1 + rand.nextInt(3); j++) {
                cards.add(new Cards(CardsType.values()[rand.nextInt(6)], rand.nextInt(22)));
                System.out.print(cards.get(j).getType() + " ");
            }
            System.out.println();
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
        List<Cards> cards = new ArrayList<Cards>();
        Random rand = new Random();
        int x;

        while (!l.isFull()) {
            x = rand.nextInt(l.LIBRARYCOLUMNS);
            System.out.print("Inserting in " +  "column " + x + ": ");
            cards.clear();
            for (int j = 0; j < 1 + rand.nextInt(3); j++) {
                cards.add(new Cards(CardsType.values()[rand.nextInt(6)], rand.nextInt(22)));
                System.out.print(cards.get(j).getType() + " ");
            }
            System.out.println();
            try {
                l.insert(cards, x);
                printLibrary(l);
            }
            catch (ColumFullException e) {
            }

            System.out.println(l.isFull());
                System.out.println();
        }
    }


    @Test
    void maxcolumn() throws ColumFullException {
        Library l = new Library();
        List<Cards> cards = new ArrayList<>();
        Random rand = new Random();
        int x;

        while (!l.isFull()) {
            x = rand.nextInt(l.LIBRARYCOLUMNS);
            System.out.print("Inserting in " +  "column " + x + ": ");
            cards.clear();
            for (int j = 0; j < 1 + rand.nextInt(3); j++) {
                cards.add(new Cards(CardsType.values()[rand.nextInt(6)], rand.nextInt(22)));
                System.out.print(cards.get(j).getType() + " ");
            }
            System.out.println();
            try {
                l.insert(cards, x);
                printLibrary(l);
                System.out.println(l.maxFreeSpace());
                System.out.println();
            }
            catch (ColumFullException e) {
            }
        }

    }







}