package Model;

import Exceptions.ColumFullException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CommonObjOneTest {


    @Test
    void test () throws ColumFullException {


        LibraryTest test = new LibraryTest();
        Library library = new Library();


        test.printLibrary(library);
        List<Cards> cards = new ArrayList<>();
        Random rand = new Random();

        int row = library.LIBRARYROWS;
        int col = library.LIBRARYCOLUMNS;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                cards.add(new Cards(CardsType.values()[rand.nextInt(6)], rand.nextInt(22)));
                library.insert(cards, j);
                cards.clear();
            }
        }


        System.out.println();

        test.printLibrary(library);






        CommonObjOne obiettivo = new CommonObjOne();














        System.out.println(obiettivo.verify(library));



    }
}