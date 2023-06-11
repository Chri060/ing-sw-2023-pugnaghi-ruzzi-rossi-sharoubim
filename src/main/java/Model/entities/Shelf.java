package Model.entities;

import Exceptions.InvalidArgumentException;
import util.Checker;
import util.Config;
import util.Iterators.Iterable;
import util.Iterators.Iterator;
import util.Iterators.MatrixIterator;
import util.PlanarCoordinate;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements a player's shelf
 */
public class Shelf implements Iterable {

    private int rows;
    private int columns;
    private Card[][] shelf;

    /**
     * Construct the shelf based on the config files
     */
    public Shelf() {
        rows = Config.getShelfRows();
        columns = Config.getShelfColumns();
        shelf = new Card[rows][columns];
    }

    /**
     * @return the integer that indicates the number of rows of the shelf
     */
    public int getRows() {
        return rows;
    }

    /**
     * @return the integer that indicates the number of columns of the shelf
     */
    public int getColumns() {
        return columns;
    }

    /**
     * @return true if the shelf is full of card, false otherwise
     */
    public boolean isFull() {
        for (Card[] row : this.shelf) {
            for (Card card : row) {
                if (card == null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @return a matrix of cards that represent the shelf
     */
    public Card[][] asMatrix() {
        Card[][] result = new Card[this.getRows()][this.getColumns()];
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++) {
                if (shelf[i][j] != null) {
                    result[i][j] = new Card(shelf[i][j].getType(), shelf[i][j].getId());
                }
            }
        }
        return result;
    }

    /**
     * Checks a cell in the shelf
     *
     * @param planarCoordinate is at the coordinate that you want to check
     *
     * @return the card that is in the selected cell, null if there is none
     *
     * @throws InvalidArgumentException on invalid coordinates
     */
    public Card checkCell(PlanarCoordinate planarCoordinate) throws InvalidArgumentException {
        if (planarCoordinate == null) {
            throw new InvalidArgumentException("Coordinates cannot be null");
        }
        if (!Checker.shelfCoordinatesAreValid(planarCoordinate)) {
            throw new InvalidArgumentException("Coordinates are out of bounds");
        }
        return shelf[planarCoordinate.getRow()][planarCoordinate.getColumn()];
    }

    /**
     * Check if you can insert the cards in the column selected
     *
     * @param cardList is the list of cards that you had withdraw
     * @param column is the column selected to insert the cards of the list
     *
     * @return true if you can do the selected action, false otherwise
     */
    public boolean canInsert(List<Card> cardList, int column) {
        if (cardList == null) {
            return false;
        }
        if (cardList.size() == 0) {
            return false;
        }
        if (column < 0 || column >= getColumns()) {
            return false;
        }
        if (cardList.size() > this.columnFreeSpace(column)) {
            return false;
        }
        return true;
    }

    /**
     * Inserts the card in the selected column (is best to check with canInsert)
     *
     * @param cardList is a list of card that you are going to insert
     * @param column is the column selected to insert the card
     */
    public void insert(List<Card> cardList, int column) {
        int row = this.rows - 1;
        while (row > 0 && this.shelf[row][column] != null) {
            row--;
        }
        for (int i = 0; i < cardList.size(); i++) {
            this.shelf[row - i][column] = cardList.get(i);
        }
    }

    /**
     * Check how many free spaces there are in a certain columns
     *
     * @param column is the column you want to check
     *
     * @return an integer representing the number of free spaces
     */
    private int columnFreeSpace(int column) {
        int row = 0;
        while (row < this.rows && this.shelf[row][column] == null) {
            row++;
        }
        return row;
    }

    /**
     * @return the max vertical space available in all the columns of the shelf
     */
    public int maxFreeSpace() {
        int max = 0;
        for (int i = 0; i < this.columns; i++) {
            if (columnFreeSpace(i) > max) {
                max = columnFreeSpace(i);
            }
        }
        return max;
    }

    /**
     * @return sizes of the groups of adjacent cards (of the same type) in the shelf
     */
    public List<Integer> getAdjacentGroupsSizes() {
        List<Integer> result = new ArrayList<>();
        Card[][] shelfMatrix = asMatrix();

        for (int i = 0; i < shelfMatrix.length; i++) {
            for (int j = 0; j < shelfMatrix[0].length; j++) {
                if (shelfMatrix[i][j] != null) {
                    int groupSize = getGroupSize(shelfMatrix, new PlanarCoordinate(i, j), shelfMatrix[i][j].getType());
                    if (groupSize > 0) {
                        result.add(groupSize);
                    }
                }
            }
        }
        return result;
    }

    /**
     * @param shelfMatrix is the matrix representation of the shelf
     * @param actual is the coordinate from where you want to check
     * @param actualType is the type of the card
     *
     * @return the size of the group
     */
    public int getGroupSize(Card[][] shelfMatrix, PlanarCoordinate actual, Card.Type actualType) {
        if (!Checker.shelfCoordinatesAreValid(actual)) {
            return 0;
        }
        if (shelfMatrix[actual.getRow()][actual.getColumn()] == null) {
            return 0;
        }
        if (!shelfMatrix[actual.getRow()][actual.getColumn()].equalsType(actualType)) {
            return 0;
        }
        shelfMatrix[actual.getRow()][actual.getColumn()] = null;
        return 1 + getGroupSize(shelfMatrix, actual.getRight(), actualType) +
                getGroupSize(shelfMatrix, actual.getLeft(), actualType) +
                getGroupSize(shelfMatrix, actual.getUp(), actualType) +
                getGroupSize(shelfMatrix, actual.getDown(), actualType);
    }

    /**
     * @return the iterator of the shelf matrix
     */
    @Override
    public Iterator getIterator() {
        return new MatrixIterator(this.rows, this.columns);
    }
}