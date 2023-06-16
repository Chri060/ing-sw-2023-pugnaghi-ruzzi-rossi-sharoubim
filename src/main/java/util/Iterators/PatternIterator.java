package util.Iterators;

import util.PlanarCoordinate;

public class PatternIterator implements Iterator{

    private boolean[][] pattern;
    private PlanarCoordinate actual;
    private int status;
    private int patternLength;

    /**
     * Construct the patter iterator
     *
     * @param pattern is the pattern to start from
     */
    public PatternIterator(boolean[][] pattern) {
        int rows = pattern.length;
        int columns = pattern[0].length;
        this.pattern = new boolean[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (pattern[i][j]) {
                    this.pattern[i][j] = true;
                    this.patternLength++;
                }
            }
        }
        reset();
    }

    /**
     * Resets the pattern iterator
     */
    @Override
    public void reset() {
        for (int i = 0; i < pattern.length; i++) {
            for (int j = 0; j < pattern[0].length; j++) {
                if (pattern[i][j]) {
                    actual = new PlanarCoordinate(i, j);
                    status = 0;
                    return;
                }
            }
        }
    }

    /**
     * @return the actual value of the iterator
     */
    @Override
    public PlanarCoordinate getActual() {
        return actual;
    }

    /**
     * Switches the iterator to the next value
     */
    @Override
    public void next() {
        status++;
        if (iterationCompleted()) {
            return;
        }
        //Starts from last pattern cell visited
        int j = actual.getColumn() + 1;
        //Checks for the next true value in the matrix -> updates iterator status
        for (int i = actual.getRow(); i < this.pattern.length; i++) {
            while (j < this.pattern[0].length) {
                if (pattern[i][j]) {
                    actual = new PlanarCoordinate(i, j);
                    return;
                }
                j++;
            }
            j = 0;
        }
    }

    /**
     * @return true if the iteration is completed
     */
    @Override
    public boolean iterationCompleted() {
        return status == patternLength;
    }
}