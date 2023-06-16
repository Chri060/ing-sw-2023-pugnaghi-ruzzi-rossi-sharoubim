package util.Iterators;

import util.PlanarCoordinate;

/**
 * Iterator for matrix
 */
public class MatrixIterator implements Iterator{
    private PlanarCoordinate actual;
    private int rows;
    private int columns;
    private int status;

    /**
     * Construct the cross iterator
     *
     * @param rows is the row from where to start
     * @param columns is the column from where to start from
     */
    public MatrixIterator(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.status = 0;
        this.actual = new PlanarCoordinate(0, 0);
    }

    /**
     * Resets the matrix iterator
     */
    @Override
    public void reset() {
        this.actual = new PlanarCoordinate(0, 0);
        this.status = 0;
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
        if (iterationCompleted()) {
            return;
        }
        status++;
        if (actual.getColumn() < columns - 1) {
            actual = new PlanarCoordinate(actual.getRow(), actual.getColumn() + 1);
            return;
        }
        if (actual.getRow() < rows - 1) {
            actual = new PlanarCoordinate(actual.getRow() + 1, 0);
        }

    }

    /**
     * @return true if the iteration is completed
     */
    @Override
    public boolean iterationCompleted() {
        return (status == rows * columns);
    }
}