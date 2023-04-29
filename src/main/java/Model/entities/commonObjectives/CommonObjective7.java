package Model.entities.commonObjectives;

import Model.entities.Shelf;
import Model.entities.commonObjectives.patternObjectives.PatternCommonObjective;
import util.patterns.commonObj.SquarePattern;

public class CommonObjective7 extends PatternCommonObjective {

    public CommonObjective7(int ID) {
        super(ID);
        this.pattern = new SquarePattern(2);
    }
    @Override
    public boolean verify(Shelf shelf) {
            return 1 < pattern.verifyPatternWithOneCard(shelf.asMatrix());
    }
}
