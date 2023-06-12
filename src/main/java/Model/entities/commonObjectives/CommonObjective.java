package Model.entities.commonObjectives;

import Model.entities.Point;
import Model.entities.Shelf;
import util.Config;

import java.util.Stack;

/**
 * Abstract class for the common objectives
 */
abstract public class CommonObjective {

    private Stack<Point> availablePoints;
    private int ID;
    private static int numberOfAvailableObjectives = 12;

    /**
     * Construct a common objective given its ID
     *
     * @param ID is the number of the objective to create
     */
    public CommonObjective(int ID) {
        this.ID = ID;
        int[] points = Config.getCommonPoints();
        availablePoints = new Stack<>();
        availablePoints.push(new Point(0, "Common Objective " + this.ID));
        for (int i = points.length - 1; i >= 0; i--) {
            availablePoints.push(new Point(points[i], "Common Objective " + this.ID));
        }
    }

    /**
     * Verifies if this objective has been achieved or not
     *
     * @param shelf is the shelf that is going to be checked
     *
     * @return true if the objective has been achieved, false otherwise
     */
    abstract public boolean verify(Shelf shelf);

    /**
     * @return the ID of this common objective
     */
    public int getID() {
        return ID;
    }

    /**
     * @return the max points that are still available for the current objective and removes it
     */
    public Point getMaxPoints() {
        return availablePoints.pop();
    }

    /**
     * @return the max points that are still available for the current objective without removing it
     */
    public Point checkMaxPoints() {
        return availablePoints.peek();
    }

    /**
     * @return the number of available objectives
     */
    public static int getNumberOfAvailableObjectives() {
        return numberOfAvailableObjectives;
    }
}