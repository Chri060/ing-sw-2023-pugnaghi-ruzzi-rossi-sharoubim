package Model.entities;

import java.io.Serializable;

/**
 * Class that implements the representation of the points
 * (every point indicates where it came from)
 */
public class Point implements Serializable {
    private int value;
    private String origin;

    /**
     * Construct the actual point
     *
     * @param value is the number of points to give
     * @param origin is the string that indicates from where the points were generated
     */
    public Point(int value, String origin) {
            this.value = value;
            this.origin = origin;
    }

    /**
     * @return the points in this
     */
    public int getValue() {
        return value;
    }

    /**
     * @return the origin of this.points
     */
    public String getOrigin() {
        return origin;
    }
}
