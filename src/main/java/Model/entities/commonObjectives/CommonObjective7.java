package Model.entities.commonObjectives;

import Model.entities.Shelf;
import Model.entities.commonObjectives.patternObjectives.PatternCommonObjective;
import util.patterns.commonObj.SquarePattern;

/**
 * Class that implements the common objective seven
 */
public class CommonObjective7 extends PatternCommonObjective {

    /**
     * Construct the common objective seven
     *
     * @param ID is the ID of the common objective created
     */
    public CommonObjective7(int ID) {
        super(ID);
        this.pattern = new SquarePattern(2);
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
            return 1 < pattern.verifyPatternWithOneCard(shelf.asMatrix());
    }
}