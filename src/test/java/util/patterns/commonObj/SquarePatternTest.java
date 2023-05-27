package util.patterns.commonObj;

import org.junit.jupiter.api.Test;
import util.Iterators.Iterator;
import util.PlanarCoordinate;

class SquarePatternTest {

    @Test
    void constructorTest() {
        CommonObjectivePattern squarePattern = new SquarePattern(2);

        assert (squarePattern.getColumnLength() == 2);
        assert (squarePattern.getRowLength() == 2);

        Iterator patternIterator = squarePattern.getIterator();


        PlanarCoordinate helper = patternIterator.getActual();
        assert (helper.getRow() == 0);
        assert (helper.getColumn() == 0);

        assert (!patternIterator.iterationCompleted());
        patternIterator.next();
        helper = patternIterator.getActual();
        assert (helper.getRow() == 0);
        assert (helper.getColumn() == 1);

        assert (!patternIterator.iterationCompleted());
        patternIterator.next();
        helper = patternIterator.getActual();
        assert (helper.getRow() == 1);
        assert (helper.getColumn() == 0);

        assert (!patternIterator.iterationCompleted());
        patternIterator.next();
        helper = patternIterator.getActual();
        assert (helper.getRow() == 1);
        assert (helper.getColumn() == 1);

        patternIterator.next();
        assert (patternIterator.iterationCompleted());



    }

}