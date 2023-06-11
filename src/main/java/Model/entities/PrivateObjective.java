package Model.entities;

import Exceptions.InvalidArgumentException;
import util.Config;
import util.PlanarCoordinate;
import util.patterns.privateObj.PrivateObjectivePattern;

/**
 * Class that represent one private objective card
 */
public class PrivateObjective {

    private PrivateObjectivePattern pattern;
    private int[] points;
    private final int ID;

    /**
     * Construct the private objective
     *
     * @param ID is the ID to get from the config
     */
    public PrivateObjective(int ID) {
        this.points = Config.getPrivatePoints();
        this.ID = ID;
        this.pattern = Config.getPrivateObjectivePattern(ID);
    }

    /**
     * @return the ID value of the private objective
     */
    public int getID() {
        return ID;
    }

    /**
     * Check the cells that are part off the private objective pattern
     *
     * @param shelf is the shelf to check
     *
     * @return the number of cards in the right place for the objective
     */
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
                //do nothing
            }
        }
        return hits;
    }

    /**
     * @param shelf is the shelf to check
     *
     * @return the total points given by the private objective
     */
    public Point getMaxPoints(Shelf shelf) {
        int hits = getHits(shelf);
        return new Point(points[hits], "Private Objective " + this.ID);
    }

    /**
     * @return the pattern of this private objective
     */
    public Card.Type[][] getPattern() {
        return pattern.getShelfPattern();
    }
}