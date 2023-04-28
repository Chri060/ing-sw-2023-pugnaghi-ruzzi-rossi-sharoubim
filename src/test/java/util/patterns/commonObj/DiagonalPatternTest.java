package util.patterns.commonObj;

import org.junit.jupiter.api.Test;
import util.Config;

class DiagonalPatternTest {

    @Test
    void constructorTest() {
        Config.initialise(2);
        int size = Math.min(Config.getShelfColumns(), Config.getShelfRows());
        CommonObjectivePattern diagonalPattern = new DiagonalPattern(size);

        assert (diagonalPattern.getRowLength() == diagonalPattern.getColumnLength());
        assert (diagonalPattern.getColumnLength() == size);
        assert (diagonalPattern.patternLength == size);

        diagonalPattern.restart();

        for (int i = 0; i < size; i++) {
            assert(diagonalPattern.get().getColumn() == i);
            assert(diagonalPattern.get().getRow() == i);
            diagonalPattern.next();
        }

        assert(!diagonalPattern.hasNext());




    }



}