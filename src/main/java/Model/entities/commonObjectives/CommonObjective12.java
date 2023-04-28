package Model.entities.commonObjectives;

import Model.entities.Card;
import Model.entities.Shelf;
import Model.entities.commonObjectives.patternObjectives.PatternCommonObjective;
import util.patterns.commonObj.StairPattern;

public class CommonObjective12 extends PatternCommonObjective {

    public CommonObjective12(int ID) {
        super(ID);
        this.pattern = new StairPattern(5);
    }
    @Override
    public boolean verify(Shelf shelf) {
        //If there's at least a stair with any combination of cards (maxDifferentTypes is set to the number of available types)
        if (this.pattern.verifyWithSameType(shelf.asMatrix(), Card.Type.values().length) > 0) {
            //checks if the negative pattern (the remaining inverted stairs) are empty
            if (this.pattern.verifyEmptyPattern(shelf.asMatrix())) {
                return true;
            }
        }
        //Repeat with mirrored pattern
        if (this.pattern.verifyWithSameTypeMirrored(shelf.asMatrix(), Card.Type.values().length) > 0) {
            if (this.pattern.verifyEmptyPatternMirrored(shelf.asMatrix())) {
                return true;
            }
        }
        //If none of the previous return was executed returns false
        return false;
    }
}
