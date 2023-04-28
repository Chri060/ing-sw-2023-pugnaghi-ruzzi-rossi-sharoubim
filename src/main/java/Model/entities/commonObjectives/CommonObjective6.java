package Model.entities.commonObjectives;

import Model.entities.Shelf;
import Model.entities.commonObjectives.patternObjectives.PatternCommonObjective;
import util.patterns.commonObj.ColumnPattern;

public class CommonObjective6 extends PatternCommonObjective {

    public CommonObjective6(int ID) {
        super(ID);
        this.pattern = new ColumnPattern();
    }
    @Override
    public boolean verify(Shelf shelf) {
        return 1 < pattern.verifyWithDifferentType(shelf.asMatrix(), 6);

    }
}
