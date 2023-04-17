package Model;

import Exceptions.BagEmptyException;
import Exceptions.NotEnoughSpaceInColumnException;
import Exceptions.InvalidPickException;
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
    void printLibrary(Shelf l) {
        Cards[][] c = l.getAsMatrix();
        System.out.print("\t");
        for (int j = 0; j < l.getLibraryCols(); j++) {
            System.out.print(j + "\t\t");
        }
        System.out.println();
        for (int i = 0; i < l.getLibraryRows(); i++) {
            System.out.print(i + "\t");
            for (int j = 0; j < l.getLibraryCols(); j++) {
                System.out.print(c[i][j].getType() + "\t");
            }
            System.out.println();
        }
    }


    @Test
    void toVerify() throws NotEnoughSpaceInColumnException, BagEmptyException, InvalidPickException {
        Random random = new Random();
        PrivateObjective objective = new PrivateObjective(random.nextInt(12));
        Shelf shelf = new Shelf();
        Bag bag = new Bag();
        ArrayList<Cards> carte = new ArrayList<>();


        for(int i = 0; i < shelf.getLibraryRows(); i++) {
            for (int j = 0; j < shelf.getLibraryCols(); j++) {
                carte.add(bag.getCard());
                shelf.insert(carte, j);
                carte.clear();
            }
        }

        printLibrary(shelf);

        objective.getPattern();
        System.out.println(objective.verify(shelf));

    }

    @Test
    void toVerify50() throws NotEnoughSpaceInColumnException, BagEmptyException, InvalidPickException {
        for(int i = 0; i < 50; i++) {
            toVerify();
        }
    }


    @Test
    void pattern() {

        PrivateObjective p = new PrivateObjective(1);


        Shelf l = new Shelf();
        Cards[][] c = p.getPattern();
        for (int i = 0; i < l.getLibraryRows(); i++) {
            for (int j = 0; j < l.getLibraryCols(); j++) {
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


}

