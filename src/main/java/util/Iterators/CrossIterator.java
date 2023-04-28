package util.Iterators;

import util.PlanarCoordinate;

public class CrossIterator implements Iterator{

    private PlanarCoordinate center;
    private PlanarCoordinate actual;
    private int status;

    public CrossIterator(PlanarCoordinate coordinate) {
        this.center = coordinate;
        this.actual = center.getRight();
        this.status = 0;
    }

    @Override
    public void reset() {
        this.actual = center.getRight();
        this.status = 0;
    }

    @Override
    public PlanarCoordinate getActual() {
        return actual;
    }

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

    @Override
    public boolean iterationCompleted() {
        return status == 4;
    }
}
