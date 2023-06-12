package Model.entities.commonObjectives;

import Model.entities.Shelf;
import Model.entities.commonObjectives.patternObjectives.PatternCommonObjective;
import util.patterns.commonObj.DiagonalPattern;

/**
 * Class that implements the common objective two
 */
public class CommonObjective2 extends PatternCommonObjective {

    /**
     * Construct the common objective two
     *
     * @param ID is the ID of the common objective created
     */
    public CommonObjective2(int ID) {
        super(ID);
        this.pattern = new DiagonalPattern();
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
        return pattern.verifyPatternWithOneCard(shelf.asMatrix()) > 0;
    }
}