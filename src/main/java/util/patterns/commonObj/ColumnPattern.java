package util.patterns.commonObj;

import Model.entities.Card;
import util.Config;

/**
 * Class for the column pattern
 */
public class ColumnPattern extends CommonObjectivePattern{

    /**
     * Constructs the column pattern object given the shelf rows
     */
    public ColumnPattern() {
        this(Config.getShelfRows());
    }

    /**
     * Constructs the column pattern object given the size of the rows
     *
     * @param size is the size of the rows to set
     */
    public ColumnPattern(int size) {
        this.pattern = new boolean[size][1];
        this.patternLength = size;
        for (int i = 0; i < this.patternLength; i++) {
            this.pattern[i][0] = true;
        }
    }

    /**
     * Verifies the pattern with one card
     *
     * @param shelfMatrix is the shelf as an array of Card
     *
     * @return the number of verified pattern
     */
    @Override
    public int verifyPatternWithOneCard(Card[][] shelfMatrix) {
        return this.verifyPatternWithOneCardCore(shelfMatrix);
    }
}