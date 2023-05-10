package Model.viewEntities;

import Model.entities.Card;
import Model.entities.Point;

import java.io.Serializable;
import java.util.List;

public class PlayerView implements Serializable {
    private String name;
    private Point point;
    private Card[][] shelf;
    private List<Card.Type[][]> privateObjectivePattern;

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
    public void setPrivateObjectivePattern(List<Card.Type[][]> privateObjectivePattern) {
        this.privateObjectivePattern = privateObjectivePattern;
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
    public List<Card.Type[][]> getPrivateObjectivePattern() {
        return privateObjectivePattern;
    }
}

