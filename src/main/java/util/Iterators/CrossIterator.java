package util.Iterators;

import util.PlanarCoordinate;

/**
 * Iterator for crosses
 */
public class CrossIterator implements Iterator{

    private PlanarCoordinate center;
    private PlanarCoordinate actual;
    private int status;

    /**
     * Construct the cross iterator
     *
     * @param coordinate is the coordinate from where to start
     */
    public CrossIterator(PlanarCoordinate coordinate) {
        this.center = coordinate;
        this.actual = center.getRight();
        this.status = 0;
    }

    /**
     * Resets the cross iterator
     */
    @Override
    public void reset() {
        this.actual = center.getRight();
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
        switch (status) {
            case 0 -> actual = center.getDown();
            case 1 -> actual = center.getLeft();
            case 2 -> actual = center.getUp();
            case 3 -> actual = center.getRight();
        }
        status++;
    }

    /**
     * @return true if the iteration is completed
     */
    @Override
    public boolean iterationCompleted() {
        return status == 4;
    }
}