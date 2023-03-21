package Model;

import Exceptions.BagEmptyException;
import Exceptions.ColumFullException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

class PrivateObjectiveTest {

    @Test
    void objectiveCheck () {

        for (int i = 0; i < 12; i++) {
            PrivateObjective obiettivo = new PrivateObjective(i);
            obiettivo.getPattern();
        }
        
    }

    @Test
    void printLibrary(Library l) {
        Cards[][] c = l.getAsMatrix();
        System.out.print("\t");
        for (int j = 0; j < l.LIBRARYCOLUMNS; j++) {
            System.out.print(j + "\t\t");
        }
        System.out.println();
        for (int i = 0; i < l.LIBRARYROWS; i++) {
            System.out.print(i + "\t");
            for (int j = 0; j < l.LIBRARYCOLUMNS; j++) {
                System.out.print(c[i][j].getType() + "\t");
            }
            System.out.println();
        }
    }


    @Test
    void toVerify() throws ColumFullException, BagEmptyException {
        Random random = new Random();
        PrivateObjective obiettivo = new PrivateObjective(random.nextInt(12));
        Library libreria = new Library();
        Bag bag = new Bag();
        ArrayList<Cards> carte = new ArrayList<>();


        for(int i = 0; i < libreria.LIBRARYROWS; i++) {
            for (int j = 0; j < libreria.LIBRARYCOLUMNS; j++) {
                carte.add(bag.getCard());
                libreria.insert(carte, j);
                carte.clear();
            }
        }

        printLibrary(libreria);

        obiettivo.getPattern();
        System.out.println(obiettivo.verify(libreria));

    }

    @Test
    void toVerify50() throws ColumFullException, BagEmptyException{
        for(int i = 0; i < 50; i++) {
            toVerify();
        }
    }


}

