package Model.entities.commonObjectives;

import Model.entities.Shelf;
import Model.entities.commonObjectives.patternObjectives.PatternCommonObjective;
import util.patterns.commonObj.RowPattern;

/**
 * Class that implements the common objective four
 */
public class CommonObjective4 extends PatternCommonObjective {

    /**
     * Construct the common objective four
     *
     * @param ID is the ID of the common objective created
     */
    public CommonObjective4(int ID) {
        super(ID);
        this.pattern = new RowPattern();
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
        return 3 < pattern.verifyWithSameType(shelf.asMatrix(), 3);
    }
}