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
        return this.pattern.verifyPatternAndAntiPattern(shelf.asMatrix());
       }
}
