package Model.entities.commonObjectives;

import Model.entities.Shelf;
import Model.entities.commonObjectives.patternObjectives.PatternCommonObjective;
import util.patterns.commonObj.StairPattern;

/**
 * Class that implements the common objective twelve
 */
public class CommonObjective12 extends PatternCommonObjective {

    /**
     * Construct the common objective twelve
     *
     * @param ID is the ID of the common objective created
     */
    public CommonObjective12(int ID) {
        super(ID);
        this.pattern = new StairPattern(5);
    }

    /**
     * Verifies if the common objective is achieved or not
     *
     * @param shelf is the shelf that is going to be checked
     *
     * @return true if the common objective is verified, false otherwise
     */
    @Override
    public boolean verify(Shelf shelf) {
        return this.pattern.verifyPatternAndAntiPattern(shelf.asMatrix());
       }
}