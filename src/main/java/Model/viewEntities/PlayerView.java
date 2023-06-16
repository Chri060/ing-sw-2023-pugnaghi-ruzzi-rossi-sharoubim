package Model.viewEntities;

import Model.entities.Card;
import Model.entities.Point;

import java.io.Serializable;
import java.util.List;

/**
 * Class that implements the view that represent the player
 */
public class PlayerView implements Serializable {
    private String name;
    private List<Point> pointList;
    private ShelfView shelf;

    /**
     * Sets the name of the player
     *
     * @param name is the name of the player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the list of points given to the player
     *
     * @param point is the list of point to update
     */
    public void setPoint(List<Point> point) {
        this.pointList = point;
    }

    /**
     * Sets the Shelf for the player
     *
     * @param shelf is the array of Card that represent the shelf
     */
    public void setShelf(Card[][] shelf) {
        this.shelf = new ShelfView(shelf);
    }

    /**
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * @return the list of Point of the player
     */
    public List<Point> getPoint() {
        return pointList;
    }

    /**
     * @return the sum of the points as a Point
     */
    public Point getTotalPoint() {
        return new Point(pointList.stream().mapToInt(x -> x.getValue()).sum(), name + "' total points");
    }

    /**
     * @return the shelf as an array of Card
     */
    public Card[][] getShelf() {
        return shelf.getShelf();
    }
}