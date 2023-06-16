package util.patterns.commonObj;

/**
 * Class for the single cell pattern
 */
public class SingleCellPattern extends CommonObjectivePattern{

    /**
     * Constructs the single cell pattern object given the size
     */
    public SingleCellPattern() {
        this.pattern = new boolean[][] {{true}};
        this.patternLength = 1;
    }
}