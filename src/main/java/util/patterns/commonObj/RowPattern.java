package util.patterns.commonObj;

import Model.entities.Card;
import util.Config;

/**
 * Class for the row pattern
 */
public class RowPattern extends CommonObjectivePattern{

    /**
     * Constructs the row pattern object
     */
    public RowPattern() {
        this(Config.getShelfColumns());
    }

    /**
     * Constructs the row pattern object given the size
     *
     * @param size is the size of the diagonals
     */
    public RowPattern(int size) {
        this.pattern = new boolean[1][size];
        this.patternLength = Config.getShelfColumns();
        for (int i = 0; i < this.patternLength; i++) {
            this.pattern[0][i] = true;
        }
    }
    @Override
    public int verifyPatternWithOneCard(Card[][] shelfMatrix) {
        return this.verifyPatternWithOneCardCore(shelfMatrix);
    }
}
