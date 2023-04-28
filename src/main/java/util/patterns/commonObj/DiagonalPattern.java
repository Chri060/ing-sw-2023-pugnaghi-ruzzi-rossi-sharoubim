package util.patterns.commonObj;

import util.Config;

public class DiagonalPattern extends CommonObjectivePattern{

    public DiagonalPattern(int size) {
        this.pattern = new boolean[size][size];
        this.patternLength = size;
        for (int i = 0; i < size; i++) {
            this.pattern[i][i] = true;
        }
    }

    public DiagonalPattern() {
        this(Math.min(Config.getShelfRows(), Config.getShelfColumns()));
    }


}
