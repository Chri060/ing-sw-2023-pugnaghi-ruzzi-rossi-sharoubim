package util.patterns.commonObj;

public class SquarePattern extends CommonObjectivePattern {

    public SquarePattern(int size) {
        this.pattern = new boolean[size][size];
        this.patternLength = size * size;
        for (boolean[] row : this.pattern) {
            for (int i = 0; i < row.length; i++) {
                row[i] = true;
            }
        }
    }
}
