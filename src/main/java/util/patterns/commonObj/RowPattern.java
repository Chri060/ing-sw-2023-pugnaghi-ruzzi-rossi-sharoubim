package util.patterns.commonObj;

import util.Config;

public class RowPattern extends CommonObjectivePattern{

    public RowPattern() {
        this(Config.getShelfColumns());
    }

    public RowPattern(int size) {
        this.pattern = new boolean[1][size];
        this.patternLength = Config.getShelfColumns();
        for (int i = 0; i < this.patternLength; i++) {
            this.pattern[0][i] = true;
        }
    }
}
