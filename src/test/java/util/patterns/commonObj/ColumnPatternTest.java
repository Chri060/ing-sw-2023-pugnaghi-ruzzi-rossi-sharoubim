package util.patterns.commonObj;

import org.junit.jupiter.api.Test;
import util.Config;
import util.PlanarCoordinate;

class ColumnPatternTest {

    @Test
    void constructorTest() {
        Config.initialise(2);
        PlanarCoordinate planarCoordinate;
        CommonObjectivePattern columnPattern = new ColumnPattern();

        assert (columnPattern.getColumnLength() == 1);
        assert (columnPattern.getRowLength() == Config.getShelfRows());

        columnPattern.restart();
        assert (columnPattern.hasNext());

        planarCoordinate = columnPattern.get();
        assert (planarCoordinate.getColumn() == 0);
        assert (planarCoordinate.getRow() == 0);

        columnPattern.next();
        assert (columnPattern.hasNext());
        planarCoordinate = columnPattern.get();
        assert (planarCoordinate.getColumn() == 0);
        assert (planarCoordinate.getRow() == 1);

        columnPattern.next();
        assert (columnPattern.hasNext());
        planarCoordinate = columnPattern.get();
        assert (planarCoordinate.getColumn() == 0);
        assert (planarCoordinate.getRow() == 2);

        columnPattern.next();
        assert (columnPattern.hasNext());
        planarCoordinate = columnPattern.get();
        assert (planarCoordinate.getColumn() == 0);
        assert (planarCoordinate.getRow() == 3);

        columnPattern.next();
        assert (columnPattern.hasNext());
        planarCoordinate = columnPattern.get();
        assert (planarCoordinate.getColumn() == 0);
        assert (planarCoordinate.getRow() == 4);

        columnPattern.next();
        assert (!columnPattern.hasNext());
        planarCoordinate = columnPattern.get();
        assert (planarCoordinate.getColumn() == 0);
        assert (planarCoordinate.getRow() == 5);
    }

}