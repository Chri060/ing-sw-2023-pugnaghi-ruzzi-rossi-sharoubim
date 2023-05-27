package util.patterns.commonObj;

import org.junit.jupiter.api.Test;
import util.Config;
import util.Iterators.Iterator;

class XPatternTest {


    @Test
    void constructorTest() {
        Config.initialise(2);

        CommonObjectivePattern xPattern = new XPattern(3);

        Iterator patternIterator = xPattern.getIterator();


        assert (xPattern.getRowLength() == 3);
        assert (xPattern.getColumnLength() == 3);


        assert (patternIterator.getActual().getRow() == 0);
        assert (patternIterator.getActual().getColumn() == 0);

        patternIterator.next();


        assert (patternIterator.getActual().getRow() == 0);
        assert (patternIterator.getActual().getColumn() == 2);


        patternIterator.next();


        assert (patternIterator.getActual().getRow() == 1);
        assert (patternIterator.getActual().getColumn() == 1);

        assert (!patternIterator.iterationCompleted());


        patternIterator.next();


        assert (patternIterator.getActual().getRow() == 2);
        assert (patternIterator.getActual().getColumn() == 0);

        patternIterator.next();

        assert (patternIterator.getActual().getRow() == 2);
        assert (patternIterator.getActual().getColumn() == 2);

        patternIterator.next();

        assert (patternIterator.iterationCompleted());

    }
}