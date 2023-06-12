package Model.entities.commonObjectives;

import Model.entities.Shelf;
import Model.entities.commonObjectives.patternObjectives.PatternCommonObjective;
import util.patterns.commonObj.XPattern;

/**
 * Class that implements the common objective ten
 */
public class CommonObjective10 extends PatternCommonObjective {

    /**
     * Construct the common objective ten
     *
     * @param ID is the ID of the common objective created
     */
    public CommonObjective10(int ID) {
        super(ID);
        this.pattern = new XPattern(3);
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
        return 0 < this.pattern.verifyPatternWithOneCard(shelf.asMatrix());
    }
}