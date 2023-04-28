package util.Iterators;

import org.junit.jupiter.api.Test;
import util.PlanarCoordinate;

import static org.junit.jupiter.api.Assertions.*;

class MatrixIteratorTest {

    @Test
    void test() {
        Iterator iterator = new MatrixIterator(3,3);
        PlanarCoordinate coordinate;

        assert (!iterator.iterationCompleted());


        coordinate = iterator.getActual();
        assert(coordinate.getRow() == 0 && coordinate.getColumn() == 0);
        iterator.next();
        assert (!iterator.iterationCompleted());

        coordinate = iterator.getActual();
        assert(coordinate.getRow() == 0 && coordinate.getColumn() == 1);
        iterator.next();
        assert (!iterator.iterationCompleted());

        coordinate = iterator.getActual();
        assert(coordinate.getRow() == 0 && coordinate.getColumn() == 2);
        iterator.next();
        assert (!iterator.iterationCompleted());

        coordinate = iterator.getActual();
        assert(coordinate.getRow() == 1 && coordinate.getColumn() == 0);
        iterator.next();
        assert (!iterator.iterationCompleted());

        coordinate = iterator.getActual();
        assert(coordinate.getRow() == 1 && coordinate.getColumn() == 1);
        iterator.next();
        assert (!iterator.iterationCompleted());

        coordinate = iterator.getActual();
        assert(coordinate.getRow() == 1 && coordinate.getColumn() == 2);
        iterator.next();
        assert (!iterator.iterationCompleted());

        coordinate = iterator.getActual();
        assert(coordinate.getRow() == 2 && coordinate.getColumn() == 0);
        iterator.next();
        assert (!iterator.iterationCompleted());

        coordinate = iterator.getActual();
        assert(coordinate.getRow() == 2 && coordinate.getColumn() == 1);
        iterator.next();
        assert (!iterator.iterationCompleted());

        coordinate = iterator.getActual();
        assert(coordinate.getRow() == 2 && coordinate.getColumn() == 2);
        iterator.next();
        assert (iterator.iterationCompleted());




        iterator = new MatrixIterator(1, 4);

        assert (!iterator.iterationCompleted());
        coordinate = iterator.getActual();
        assert(coordinate.getRow() == 0 && coordinate.getColumn() == 0);
        iterator.next();
        assert (!iterator.iterationCompleted());


        coordinate = iterator.getActual();
        assert(coordinate.getRow() == 0 && coordinate.getColumn() == 1);
        iterator.next();
        assert (!iterator.iterationCompleted());


        coordinate = iterator.getActual();
        assert(coordinate.getRow() == 0 && coordinate.getColumn() == 2);
        iterator.next();
        assert (!iterator.iterationCompleted());


        coordinate = iterator.getActual();
        assert(coordinate.getRow() == 0 && coordinate.getColumn() == 3);
        iterator.next();
        assert (iterator.iterationCompleted());





        iterator = new MatrixIterator(4, 1);

        assert (!iterator.iterationCompleted());
        coordinate = iterator.getActual();
        assert(coordinate.getRow() == 0 && coordinate.getColumn() == 0);
        iterator.next();
        assert (!iterator.iterationCompleted());


        coordinate = iterator.getActual();
        assert(coordinate.getRow() == 1 && coordinate.getColumn() == 0);
        iterator.next();
        assert (!iterator.iterationCompleted());


        coordinate = iterator.getActual();
        assert(coordinate.getRow() == 2 && coordinate.getColumn() == 0);
        iterator.next();
        assert (!iterator.iterationCompleted());


        coordinate = iterator.getActual();
        assert(coordinate.getRow() == 3 && coordinate.getColumn() == 0);
        iterator.next();
        assert (iterator.iterationCompleted());





        iterator = new CrossIterator(new PlanarCoordinate(0, 0));

        assert (!iterator.iterationCompleted());
        coordinate = iterator.getActual();
        assert(coordinate.getRow() == 1 && coordinate.getColumn() == 0);
        iterator.next();
        assert (!iterator.iterationCompleted());


        coordinate = iterator.getActual();
        assert(coordinate.getRow() == 0 && coordinate.getColumn() == 1);
        iterator.next();
        assert (!iterator.iterationCompleted());


        coordinate = iterator.getActual();
        assert(coordinate.getRow() == -1 && coordinate.getColumn() == 0);
        iterator.next();
        assert (!iterator.iterationCompleted());


        coordinate = iterator.getActual();
        assert(coordinate.getRow() == 0 && coordinate.getColumn() == - 1);
        iterator.next();
        assert (iterator.iterationCompleted());
    }

}


