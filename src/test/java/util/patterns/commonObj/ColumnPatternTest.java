package util.patterns.commonObj;

import org.junit.jupiter.api.Test;
import util.Config;
import util.Iterators.Iterator;
import util.PlanarCoordinate;

class ColumnPatternTest {

    @Test
    void constructorTest() {
        Config.initialise(2);
        PlanarCoordinate planarCoordinate;
        CommonObjectivePattern columnPattern = new ColumnPattern();

        assert (columnPattern.getColumnLength() == 1);
        assert (columnPattern.getRowLength() == Config.getShelfRows());

        Iterator patternIterator = columnPattern.getIterator();
        assert (!patternIterator.iterationCompleted());

        planarCoordinate = patternIterator.getActual();
        assert (planarCoordinate.getColumn() == 0);
        assert (planarCoordinate.getRow() == 0);

        patternIterator.next();
        assert (!patternIterator.iterationCompleted());
        planarCoordinate = patternIterator.getActual();
        assert (planarCoordinate.getColumn() == 0);
        assert (planarCoordinate.getRow() == 1);

        patternIterator.next();
        assert (!patternIterator.iterationCompleted());
        planarCoordinate = patternIterator.getActual();
        assert (planarCoordinate.getColumn() == 0);
        assert (planarCoordinate.getRow() == 2);

        patternIterator.next();
        assert (!patternIterator.iterationCompleted());
        planarCoordinate = patternIterator.getActual();
        assert (planarCoordinate.getColumn() == 0);
        assert (planarCoordinate.getRow() == 3);

        patternIterator.next();
        assert (!patternIterator.iterationCompleted());
        planarCoordinate = patternIterator.getActual();
        assert (planarCoordinate.getColumn() == 0);
        assert (planarCoordinate.getRow() == 4);

        patternIterator.next();
        assert (!patternIterator.iterationCompleted());
        planarCoordinate = patternIterator.getActual();
        assert (planarCoordinate.getColumn() == 0);
        assert (planarCoordinate.getRow() == 5);

        patternIterator.next();
        assert (patternIterator.iterationCompleted());
    }

}