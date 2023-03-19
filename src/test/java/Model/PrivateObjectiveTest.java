package Model;

import Exceptions.ColumFullException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PrivateObjectiveTest {

    @Test
    void objectiveCheck () {
        PrivateObjective obiettivo = new PrivateObjective(1);
        obiettivo.getPattern();
    }


    @Test
    void toVerify() throws ColumFullException {
        PrivateObjective obiettivo = new PrivateObjective(1);
        LibraryTest test = new LibraryTest();
        Library libreria = new Library();
        ArrayList<Cards> carte = new ArrayList<>();
        carte.add(Cards.TROP);




        libreria.insert(carte, 2);

        test.printLibrary(libreria);

        obiettivo.getPattern();
        System.out.println(obiettivo.verify(libreria));

    }
}