package Model.viewEntities;

import Model.entities.Card;
import Model.entities.Point;

import java.io.Serializable;
import java.util.List;

public class PlayerView implements Serializable {
    private String name;
    private List<Point> pointList;
    private ShelfView shelf;


    public void setName(String name) {
        this.name = name;
    }
    public void setPoint(List<Point> point) {
        this.pointList = point;
    }
    public void setShelf(Card[][] shelf) {
        this.shelf = new ShelfView(shelf);
    }


    public String getName() {
        return name;
    }
    public List<Point> getPoint() {
        return pointList;
    }
    public Point getTotalPoint() {
        return new Point(pointList.stream().mapToInt(x -> x.getValue()).sum(), name + "' total points");
    }
    public Card[][] getShelf() {
        return shelf.getShelf();
    }

    public int getMaxSpace() {
        return shelf.maxFreeSpace();
    }
}

