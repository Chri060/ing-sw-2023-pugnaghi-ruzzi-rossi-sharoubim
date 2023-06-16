package Model.viewEntities;

import Model.entities.Point;

import java.io.Serializable;

/**
 * Class that implements the view that represent the common objectives
 */
public class CommonObjectiveView implements Serializable {
    private int ID;
    private Point maxPoint;

    /**
     * Sets the ID of the common objective
     *
     * @param ID is the number of common objective selected
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Sets the number of points available for this common objective
     *
     * @param maxPoint is the maximum number of points available
     */
    public void setMaxPoint(Point maxPoint) {
        this.maxPoint = maxPoint;
    }

    /**
     * @return the ID of the common objective
     */
    public int getID() {
        return ID;
    }

    /**
     * @return the maximum number of points available in the common objective
     */
    public Point getMaxPoint() {
        return maxPoint;
    }
}