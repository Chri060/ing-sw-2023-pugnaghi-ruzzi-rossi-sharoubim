package util.patterns.commonObj;

import util.Config;

public class StairPattern extends  CommonObjectivePattern{

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

    public StairPattern() {
        this(Math.min(Config.getShelfRows(), Config.getShelfColumns()));
    }
}
