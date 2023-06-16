package util.Iterators;

import util.PlanarCoordinate;

/**
 * Interface for the iterators
 */
public interface Iterator {
    /**
     * Resets the iterator
     */
    void reset();

    /**
     * @return the actual value of the iterator
     */
    PlanarCoordinate getActual();

    /**
     * Switch to the next value of the iterator
     */
    void next();

    /**
     * Checks if all the iterations are completed
     *
     * @return tru if the iterations are ended, false otherwise
     */
    boolean iterationCompleted();
}