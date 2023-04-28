package Model.entities.commonObjectives;

import Model.entities.Shelf;
import Model.entities.commonObjectives.patternObjectives.PatternCommonObjective;
import util.patterns.commonObj.CornerPattern;

public class CommonObjective3 extends PatternCommonObjective {

    public CommonObjective3(int ID) {
        super(ID);
        this.pattern = new CornerPattern();
    }
    @Override
    public boolean verify(Shelf shelf) {
        return 1 == pattern.verifyWithSameType(shelf.asMatrix(), 1);
    }
}
