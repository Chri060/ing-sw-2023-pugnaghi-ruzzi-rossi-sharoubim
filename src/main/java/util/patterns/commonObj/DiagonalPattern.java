package util.patterns.commonObj;

import util.Config;

/**
 * Class for the diagonal pattern
 */
public class DiagonalPattern extends CommonObjectivePattern{

    /**
     * Constructs the diagonal pattern object given the size
     *
     * @param size is the size of the diagonals
     */
    public DiagonalPattern(int size) {
        this.pattern = new boolean[size][size];
        this.patternLength = size;
        for (int i = 0; i < size; i++) {
            this.pattern[i][i] = true;
        }
    }

    /**
     * Constructs the diagonal pattern object
     */
    public DiagonalPattern() {
        this(Math.min(Config.getShelfRows(), Config.getShelfColumns()));
    }
}