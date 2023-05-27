package util.patterns.commonObj;

import Model.entities.Card;
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
    @Override
    public int verifyPatternWithOneCard(Card[][] shelfMatrix) {
        return this.verifyPatternWithOneCardCore(shelfMatrix);
    }
}
