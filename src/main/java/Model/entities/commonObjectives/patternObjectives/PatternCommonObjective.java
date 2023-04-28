package Model.entities.commonObjectives.patternObjectives;

import Model.entities.Shelf;
import Model.entities.commonObjectives.CommonObjective;
import util.patterns.commonObj.CommonObjectivePattern;

abstract public class PatternCommonObjective extends CommonObjective {

    public PatternCommonObjective(int ID) {
        super(ID);
    }

    protected CommonObjectivePattern pattern;
    abstract public boolean verify(Shelf shelf);
}
