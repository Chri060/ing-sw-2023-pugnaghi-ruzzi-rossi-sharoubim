package util.patterns.commonObj;

import util.Config;

public class CornerPattern extends CommonObjectivePattern{

    public CornerPattern() {
        int rows = Config.getShelfRows();
        int columns = Config.getShelfColumns();
        this.pattern = new boolean[rows][columns];
        this.pattern[0][0] = true;
        this.pattern[0][columns - 1] = true;
        this.pattern[rows - 1][0] = true;
        this.pattern[rows - 1][columns - 1] = true;

        this.patternLength = 4;

    }

}
