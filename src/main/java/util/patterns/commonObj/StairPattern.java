package util.patterns.commonObj;

import util.Config;

/**
 * Class for the stair pattern
 */
public class StairPattern extends  CommonObjectivePattern{

    /**
     * Constructs the stair pattern object given the size
     *
     * @param size is the size of the stair
     */
    public StairPattern(int size) {
        this.pattern = new boolean[size][size];
        //formula del piccolo Gauss
        this.patternLength = size * (size + 1) / 2;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i >= j) {
                    this.pattern[i][j] = true;
                }
            }
        }
    }

    /**
     * Constructs the stair pattern object
     */
    public StairPattern() {
        this(Math.min(Config.getShelfRows(), Config.getShelfColumns()));
    }
}