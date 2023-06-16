package util.patterns.privateObj;

import Model.entities.Card;
import util.Config;
import util.PlanarCoordinate;

import java.util.Random;

/**
 * Class that implements the pattern of a private objective
 */
public class PrivateObjectivePattern {
    private PlanarCoordinate[] coordinates;
    private Card.Type[] items;
    private int numberOfItems;

    /**
     * Construct the pattern of a private objective
     *
     * @param coordinates is an array of coordinates that represent the private objective
     * @param items is an array of items of the private objective
     */
    public PrivateObjectivePattern(PlanarCoordinate[] coordinates, Card.Type[] items) {
        this.numberOfItems = Math.min(coordinates.length, items.length);
        this.coordinates = new PlanarCoordinate[numberOfItems];
        this.items = new Card.Type[numberOfItems];
        for (int i = 0; i < numberOfItems; i++) {
            this.coordinates[i] = new PlanarCoordinate(coordinates[i]);
            this.items[i] = items[i];
        }
    }

    /**
     * Creates a random pattern for the private objective
     *
     * @param numberOfItems is the number of cards that you want to be in the new pattern
     *
     * @return a PrivateObjectivePattern with the newly created pattern
     */
    public static PrivateObjectivePattern getRandomPattern(int numberOfItems) {
        Random random = new Random();
        int shelfRows = Config.getShelfRows();
        int shelfColumns = Config.getShelfColumns();
        int numberOfDifferentTypes = Card.Type.values().length;
        PlanarCoordinate[] coordinates = new PlanarCoordinate[numberOfItems];
        Card.Type[] items = new Card.Type[numberOfItems];
        for (int i = 0; i < numberOfItems; i++) {
            coordinates[i] = new PlanarCoordinate(random.nextInt(shelfRows), random.nextInt(shelfColumns));
            items[i] = Card.Type.values()[random.nextInt(numberOfDifferentTypes)];
        }
        return new PrivateObjectivePattern(coordinates, items);
    }

    /**
     * @return the number of items in the objective selected
     */
    public int getNumberOfItems() {
        return numberOfItems;
    }

    /**
     * @param i is the index of the item selected
     *
     * @return the planar coordinate of the item selected
     */
    public PlanarCoordinate getCoordinate(int i) {
        return this.coordinates[i];
    }

    /**
     * @param i is the index of the item selected
     *
     * @return the type of the item selected
     */
    public Card.Type getType(int i) {
        return this.items[i];
    }

    /**
     * @return the pattern of the selected shelf
     */
    public Card.Type[][] getShelfPattern() {
        int shelfRows = Config.getShelfRows();
        int shelfColumns = Config.getShelfColumns();
        Card.Type[][] result = new Card.Type[shelfRows][shelfColumns];
        for (int i = 0; i < numberOfItems; i++) {
            int row = coordinates[i].getRow();
            int column = coordinates[i].getColumn();
            result[row][column] = items[i];
        }
        return result;
    }
}