package Model.entities.commonObjectives.patternObjectives;

import Model.entities.Shelf;
import Model.entities.commonObjectives.CommonObjective;
import util.patterns.commonObj.CommonObjectivePattern;

/**
 * Abstract class for a generic common objective pattern
 */
abstract public class PatternCommonObjective extends CommonObjective {

    protected CommonObjectivePattern pattern;

    /**
     * Construct a common objective card
     *
     * @param ID is the ID of the selected common objective
     */
    public PatternCommonObjective(int ID) {
        super(ID);
    }

    /**
     * Verifies if the common objective is achieved or not in a given shelf
     *
     * @param shelf is the shelf to check
     *
     * @return true if the objective has been achieved, false otherwise
     */
    abstract public boolean verify(Shelf shelf);
}