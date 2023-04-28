package util.Iterators;

import util.PlanarCoordinate;

public class MatrixIterator implements Iterator{
    private PlanarCoordinate actual;
    private int rows;
    private int columns;
    private int status;

    public MatrixIterator(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.status = 0;
        this.actual = new PlanarCoordinate(0, 0);
    }

    @Override
    public void reset() {
        this.actual = new PlanarCoordinate(0, 0);
        this.status = 0;
    }

    @Override
    public PlanarCoordinate getActual() {
        return actual;
    }

    @Override
    public void next() {
        status++;
        if (actual.getColumn() < columns - 1) {
            actual = new PlanarCoordinate(actual.getRow(), actual.getColumn() + 1);
            return;
        }
        if (actual.getRow() < rows - 1) {
            actual = new PlanarCoordinate(actual.getRow() + 1, 0);
        }

    }

    @Override
    public boolean iterationCompleted() {
        return (status == rows * columns);
    }
}
