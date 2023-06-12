package Model.entities.commonObjectives;

import Model.entities.Shelf;
import Model.entities.commonObjectives.patternObjectives.PatternCommonObjective;
import util.patterns.commonObj.SingleCellPattern;

/**
 * Class that implements the common objective eleven
 */
public class CommonObjective11 extends PatternCommonObjective {

    /**
     * Construct the common objective eleven
     *
     * @param ID is the ID of the common objective created
     */
    public CommonObjective11(int ID) {
        super(ID);
        this.pattern = new SingleCellPattern();
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
        return 7 < pattern.verifyPatternWithOneCardCore(shelf.asMatrix());
    }
}