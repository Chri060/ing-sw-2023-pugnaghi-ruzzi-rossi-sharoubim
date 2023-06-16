package util.patterns.commonObj;

/**
 * Class for the X pattern
 */
public class XPattern extends CommonObjectivePattern {

    /**
     * Constructs the XPattern pattern object given the size
     *
     * @param size is the size of the XPattern
     */
    public XPattern(int size) {
        this.pattern = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            this.pattern[i][i] = true;
            this.pattern[i][size - i - 1] = true;
        }
        this.patternLength = 2 * size - 1;
    }
}