package Model.entities;

import Exceptions.InvalidArgumentException;
import util.Config;
import util.PlanarCoordinate;
import util.patterns.privateObj.PrivateObjectivePattern;

public class PrivateObjective {

    private PrivateObjectivePattern pattern;
    private int[] points;
    private final int ID;

    public PrivateObjective(int ID) {
        this.points = Config.getPrivatePoints();
        this.ID = ID;
        this.pattern = Config.getPrivateObjectivePattern(ID);
    }

    public int getID() {
        return ID;
    }

    public int getHits(Shelf shelf) {
        PlanarCoordinate planarCoordinate;
        Card.Type type;
        int hits = 0;

        for (int i = 0; i < pattern.getNumberOfItems(); i++) {
            planarCoordinate = pattern.getCoordinate(i);
            type = pattern.getType(i);
            try {
                Card card = shelf.checkCell(planarCoordinate);
                if (card != null && card.equalsType(type)) {
                    hits++;
                }
            }
            catch (InvalidArgumentException e) {
                //C
            }
        }

        return hits;
    }

    public Point getMaxPoints(Shelf shelf) {
        int hits = getHits(shelf);
        return new Point(points[hits], "Private Objective " + this.ID);
    }

    public Card.Type[][] getPattern() {
        return pattern.getShelfPattern();
    }

}
