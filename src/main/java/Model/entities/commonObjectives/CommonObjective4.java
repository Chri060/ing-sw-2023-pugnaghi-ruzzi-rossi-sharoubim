package Model.entities.commonObjectives;

import Model.entities.Shelf;
import Model.entities.commonObjectives.patternObjectives.PatternCommonObjective;
import util.patterns.commonObj.RowPattern;

public class CommonObjective4 extends PatternCommonObjective {

    public CommonObjective4(int ID) {
        super(ID);
        this.pattern = new RowPattern();
    }
    @Override
    public boolean verify(Shelf shelf) {
        return 3 < pattern.verifyWithSameType(shelf.asMatrix(), 3);
    }
}
