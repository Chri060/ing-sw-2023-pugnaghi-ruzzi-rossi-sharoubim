package util.patterns.privateObj;

import Model.entities.Card;
import util.Config;
import util.PlanarCoordinate;

import java.util.Random;

public class PrivateObjectivePattern {
    private PlanarCoordinate[] coordinates;
    private Card.Type[] items;
    private int numberOfItems;

    public PrivateObjectivePattern(PlanarCoordinate[] coordinates, Card.Type[] items) {
        this.numberOfItems = Math.min(coordinates.length, items.length);
        this.coordinates = new PlanarCoordinate[numberOfItems];
        this.items = new Card.Type[numberOfItems];
        for (int i = 0; i < numberOfItems; i++) {
            this.coordinates[i] = new PlanarCoordinate(coordinates[i]);
            this.items[i] = items[i];
        }
    }

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

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public PlanarCoordinate getCoordinate(int i) {
        return this.coordinates[i];
    }

    public Card.Type getType(int i) {
        return this.items[i];
    }

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
