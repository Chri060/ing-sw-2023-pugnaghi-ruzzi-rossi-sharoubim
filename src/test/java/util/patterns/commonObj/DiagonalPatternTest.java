package util.patterns.commonObj;

import org.junit.jupiter.api.Test;
import util.Config;
import util.Iterators.Iterator;

class DiagonalPatternTest {

    @Test
    void constructorTest() {
        Config.initialise(2);
        int size = Math.min(Config.getShelfColumns(), Config.getShelfRows());
        CommonObjectivePattern diagonalPattern = new DiagonalPattern(size);

        assert (diagonalPattern.getRowLength() == diagonalPattern.getColumnLength());
        assert (diagonalPattern.getColumnLength() == size);
        assert (diagonalPattern.patternLength == size);

        Iterator patternIterator = diagonalPattern.getIterator();

        for (int i = 0; i < size; i++) {
            assert(patternIterator.getActual().getColumn() == i);
            assert(patternIterator.getActual().getRow() == i);
            patternIterator.next();
        }

        assert(patternIterator.iterationCompleted());




    }



}