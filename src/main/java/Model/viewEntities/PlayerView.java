package Model.viewEntities;

import Model.entities.Card;
import Model.entities.Point;

import java.io.Serializable;

public class PlayerView implements Serializable {
    private String name;
    private Point point;
    private Card[][] shelf;

    //TODO aggiungere obiettivi privati

    public void setName(String name) {
        this.name = name;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public void setShelf(Card[][] shelf) {
        this.shelf = shelf;
    }

    public String getName() {
        return name;
    }
    public Point getPoint() {
        return point;
    }
    public Card[][] getShelf() {
        return shelf;
    }
}

