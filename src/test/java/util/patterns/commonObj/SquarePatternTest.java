package util.patterns.commonObj;

import org.junit.jupiter.api.Test;
import util.PlanarCoordinate;

class SquarePatternTest {

    @Test
    void constructorTest() {
        CommonObjectivePattern squarePattern = new SquarePattern(2);

        assert (squarePattern.getColumnLength() == 2);
        assert (squarePattern.getRowLength() == 2);

        squarePattern.restart();

        assert (squarePattern.hasNext());

        PlanarCoordinate helper = squarePattern.get();
        assert (helper.getRow() == 0);
        assert (helper.getColumn() == 0);

        assert (squarePattern.hasNext());
        squarePattern.next();
        helper = squarePattern.get();
        assert (helper.getRow() == 0);
        assert (helper.getColumn() == 1);

        assert (squarePattern.hasNext());
        squarePattern.next();
        helper = squarePattern.get();
        assert (helper.getRow() == 1);
        assert (helper.getColumn() == 0);

        assert (squarePattern.hasNext());
        squarePattern.next();
        helper = squarePattern.get();
        assert (helper.getRow() == 1);
        assert (helper.getColumn() == 1);

        assert (!squarePattern.hasNext());



    }

}