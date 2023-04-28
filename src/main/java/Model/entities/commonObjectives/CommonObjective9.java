package Model.entities.commonObjectives;

import Model.entities.Shelf;
import Model.entities.commonObjectives.patternObjectives.PatternCommonObjective;
import util.patterns.commonObj.ColumnPattern;

public class CommonObjective9 extends PatternCommonObjective {

    public CommonObjective9(int ID) {
        super(ID);
        this.pattern = new ColumnPattern();
    }

    @Override
    public boolean verify(Shelf shelf) {
        return 2 < pattern.verifyWithSameType(shelf.asMatrix(), 3);
    }
}
