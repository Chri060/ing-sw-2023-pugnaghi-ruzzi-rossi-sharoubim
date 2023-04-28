package util.patterns.commonObj;

import org.junit.jupiter.api.Test;
import util.Config;

class CornerPatternTest {

    @Test
    void constructorTest() {
        Config.initialise(2);

        CommonObjectivePattern cornerPattern = new CornerPattern();

        cornerPattern.restart();

        assert (cornerPattern.hasNext());

        assert (cornerPattern.getRowLength() == Config.getShelfRows());
        assert (cornerPattern.getColumnLength() == Config.getShelfColumns());


        assert (cornerPattern.get().getRow() == 0);
        assert (cornerPattern.get().getColumn() == 0);

        cornerPattern.next();


        assert (cornerPattern.get().getRow() == 0);
        assert (cornerPattern.get().getColumn() == Config.getShelfColumns() - 1);

        cornerPattern.next();


        assert (cornerPattern.get().getRow() == Config.getShelfRows() - 1);
        assert (cornerPattern.get().getColumn() == 0);

        assert (cornerPattern.hasNext());


        cornerPattern.next();


        assert (cornerPattern.get().getRow() == Config.getShelfRows() - 1);
        assert (cornerPattern.get().getColumn() == Config.getShelfColumns() - 1);

        assert (!cornerPattern.hasNext());


    }

}