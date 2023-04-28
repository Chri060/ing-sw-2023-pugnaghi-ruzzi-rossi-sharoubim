package Model.entities.commonObjectives;

import Model.entities.Shelf;
import Model.entities.commonObjectives.patternObjectives.PatternCommonObjective;
import util.patterns.commonObj.DiagonalPattern;

public class CommonObjective2 extends PatternCommonObjective {

    public CommonObjective2(int ID) {
        super(ID);
        this.pattern = new DiagonalPattern();
        /*Alternativa fissa*/
        //this.pattern = new DiagonalPattern(5);
    }
    @Override
    public boolean verify(Shelf shelf) {
        return (pattern.verifyWithSameType(shelf.asMatrix(), 1) > 0
        || pattern.verifyWithSameTypeMirrored(shelf.asMatrix(), 1) > 0);

    }
}
