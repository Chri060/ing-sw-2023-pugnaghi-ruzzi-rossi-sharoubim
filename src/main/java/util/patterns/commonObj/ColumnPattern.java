package util.patterns.commonObj;

import Model.entities.Card;
import util.Config;

public class ColumnPattern extends CommonObjectivePattern{

    public ColumnPattern() {
        this(Config.getShelfRows());
    }

    public ColumnPattern(int size) {
        this.pattern = new boolean[size][1];
        this.patternLength = size;
        for (int i = 0; i < this.patternLength; i++) {
            this.pattern[i][0] = true;
        }
    }

    @Override
    public int verifyPatternWithOneCard(Card[][] shelfMatrix) {
        return this.verifyPatternWithOneCardCore(shelfMatrix);
    }
}
