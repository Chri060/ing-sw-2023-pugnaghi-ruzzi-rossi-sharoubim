package util.patterns.commonObj;

import org.junit.jupiter.api.Test;
import util.Config;

class XPatternTest {


    @Test
    void constructorTest() {
        Config.initialise(2);

        CommonObjectivePattern xPattern = new XPattern(3);

        xPattern.restart();

        assert (xPattern.hasNext());

        assert (xPattern.getRowLength() == 3);
        assert (xPattern.getColumnLength() == 3);


        assert (xPattern.get().getRow() == 0);
        assert (xPattern.get().getColumn() == 0);

        xPattern.next();


        assert (xPattern.get().getRow() == 0);
        assert (xPattern.get().getColumn() == 2);


        xPattern.next();


        assert (xPattern.get().getRow() == 1);
        assert (xPattern.get().getColumn() == 1);

        assert (xPattern.hasNext());


        xPattern.next();


        assert (xPattern.get().getRow() == 2);
        assert (xPattern.get().getColumn() == 0);

        assert (xPattern.hasNext());


        xPattern.next();

        assert (!xPattern.hasNext());

        assert (xPattern.get().getRow() == 2);
        assert (xPattern.get().getColumn() == 2);

        assert (!xPattern.hasNext());

    }
}