package util.Iterators;

import util.PlanarCoordinate;

public interface Iterator {
    void reset();
    PlanarCoordinate getActual();
    void next();
    boolean iterationCompleted();
}
