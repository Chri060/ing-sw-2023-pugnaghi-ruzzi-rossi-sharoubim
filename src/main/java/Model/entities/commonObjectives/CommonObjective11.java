package Model.entities.commonObjectives;

import Model.entities.Shelf;
import Model.entities.commonObjectives.patternObjectives.PatternCommonObjective;
import util.patterns.commonObj.SingleCellPattern;

public class CommonObjective11 extends PatternCommonObjective {
    public CommonObjective11(int ID) {
        super(ID);
        this.pattern = new SingleCellPattern();
    }

    @Override
    public boolean verify(Shelf shelf) {
        return 7 < pattern.verifyPatternWithOneCard(shelf.asMatrix());
    }


}
