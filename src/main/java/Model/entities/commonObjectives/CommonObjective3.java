package Model.entities.commonObjectives;

import Model.entities.Shelf;
import Model.entities.commonObjectives.patternObjectives.PatternCommonObjective;
import util.patterns.commonObj.CornerPattern;

/**
 * Class that implements the common objective three
 */
public class CommonObjective3 extends PatternCommonObjective {

    /**
     * Construct the common objective three
     *
     * @param ID is the ID of the common objective created
     */
    public CommonObjective3(int ID) {
        super(ID);
        this.pattern = new CornerPattern();
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
        return 1 == pattern.verifyPatternWithOneCardCore(shelf.asMatrix());
    }
}