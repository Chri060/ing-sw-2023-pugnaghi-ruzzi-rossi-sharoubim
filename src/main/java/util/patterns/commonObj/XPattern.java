package util.patterns.commonObj;

public class XPattern extends CommonObjectivePattern {

    public XPattern(int size) {
        this.pattern = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            this.pattern[i][i] = true;
            this.pattern[i][size - i - 1] = true;
        }
        this.patternLength = 2 * size - 1;
    }
}
