package Model.entities.commonObjectives;

import Model.entities.Point;
import Model.entities.Shelf;
import util.Config;

import java.util.Stack;

abstract public class CommonObjective {

    private Stack<Point> availablePoints;
    private int ID;
    private static int numberOfAvailableObjectives = 12;
    public CommonObjective(int ID) {
        this.ID = ID;
        int[] points = Config.getCommonPoints();
        availablePoints = new Stack<>();
        for (int i = points.length - 1; i >= 0; i--) {
            availablePoints.push(new Point(points[i], "Common Objective " + this.ID));
        }
    }

    abstract public boolean verify(Shelf shelf);

    public int getID() {
        return ID;
    }

    public Point getMaxPoints() {
        return availablePoints.pop();
    }
    public Point checkMaxPoints() {
        return availablePoints.peek();
    }

    public static int getNumberOfAvailableObjectives() {
        return numberOfAvailableObjectives;
    }

}
