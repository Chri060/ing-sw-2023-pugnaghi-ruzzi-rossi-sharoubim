package Model.viewEntities;

import Model.entities.Point;

import java.io.Serializable;

public class CommonObjectiveView implements Serializable {
    private int ID;
    private Point maxPoint;

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setMaxPoint(Point maxPoint) {
        this.maxPoint = maxPoint;
    }

    public int getID() {
        return ID;
    }
    public Point getMaxPoint() {
        return maxPoint;
    }
}


