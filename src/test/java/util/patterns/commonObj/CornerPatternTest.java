package util.patterns.commonObj;

import org.junit.jupiter.api.Test;
import util.Config;
import util.Iterators.Iterator;

class CornerPatternTest {

    @Test
    void constructorTest() {
        Config.initialise(2);

        CommonObjectivePattern cornerPattern = new CornerPattern();

        Iterator patternIterator = cornerPattern.getIterator();

        assert (!patternIterator.iterationCompleted());

        assert (cornerPattern.getRowLength() == Config.getShelfRows());
        assert (cornerPattern.getColumnLength() == Config.getShelfColumns());


        assert (patternIterator.getActual().getRow() == 0);
        assert (patternIterator.getActual().getColumn() == 0);

        patternIterator.next();


        assert (patternIterator.getActual().getRow() == 0);
        assert (patternIterator.getActual().getColumn() == Config.getShelfColumns() - 1);

        patternIterator.next();


        assert (patternIterator.getActual().getRow() == Config.getShelfRows() - 1);
        assert (patternIterator.getActual().getColumn() == 0);

        assert (!patternIterator.iterationCompleted());


        patternIterator.next();


        assert (patternIterator.getActual().getRow() == Config.getShelfRows() - 1);
        assert (patternIterator.getActual().getColumn() == Config.getShelfColumns() - 1);

        patternIterator.next();
        assert (patternIterator.iterationCompleted());


    }

}