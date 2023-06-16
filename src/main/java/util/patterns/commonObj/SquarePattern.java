package util.patterns.commonObj;

/**
 * Class for the square pattern
 */
public class SquarePattern extends CommonObjectivePattern {

    /**
     * Constructs the square pattern object given the size
     *
     * @param size is the size of the diagonals
     */
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
