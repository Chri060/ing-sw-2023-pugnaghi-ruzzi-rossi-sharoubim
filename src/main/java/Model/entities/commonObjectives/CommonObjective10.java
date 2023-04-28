package Model.entities.commonObjectives;

import Model.entities.Shelf;
import Model.entities.commonObjectives.patternObjectives.PatternCommonObjective;
import util.patterns.commonObj.XPattern;

public class CommonObjective10 extends PatternCommonObjective {

    public CommonObjective10(int ID) {
        super(ID);
        this.pattern = new XPattern(3);
    }

    @Override
    public boolean verify(Shelf shelf) {
        return 0 < this.pattern.verifyWithSameType(shelf.asMatrix(), 1);
    }
}
