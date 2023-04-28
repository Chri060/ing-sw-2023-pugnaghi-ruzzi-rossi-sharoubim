package Model.entities.commonObjectives;

import Model.entities.Shelf;
import Model.entities.commonObjectives.patternObjectives.PatternCommonObjective;
import util.patterns.commonObj.RowPattern;

public class CommonObjective8 extends PatternCommonObjective {

    public CommonObjective8(int ID) {
        super(ID);
        this.pattern = new RowPattern();
    }

    @Override
    public boolean verify(Shelf shelf) {
        return 1 < pattern.verifyWithDifferentType(shelf.asMatrix(), 5);

    }
}
