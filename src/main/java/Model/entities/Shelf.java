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

public class Shelf implements Iterable {

    private int rows;
    private int columns;
    private Card[][] shelf;

    public Shelf() {
        rows = Config.getShelfRows();
        columns = Config.getShelfColumns();

        shelf = new Card[rows][columns];
    }

    public int getRows() {
        return rows;
    }
    public int getColumns() {
        return columns;
    }
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
    public Card[][] asMatrix() {
        Card[][] result = new Card[this.getRows()][this.getColumns()];
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++) {
                result[i][j] = this.shelf[i][j];
            }
        }
        return result;
    }
    public Card checkCell(PlanarCoordinate planarCoordinate) throws InvalidArgumentException {
        if (planarCoordinate == null) {
            throw new InvalidArgumentException("Coordinates cannot be null");
        }
        if (!Checker.shelfCoordinatesAreValid(planarCoordinate)) {
            throw new InvalidArgumentException("Coordinates are out of bounds");
        }
        return shelf[planarCoordinate.getRow()][planarCoordinate.getColumn()];
    }
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
    public void insert(List<Card> cardList, int column) {
        int row = this.rows - 1;
        while (row > 0 && this.shelf[row][column] != null) {
            row--;
        }
        for (int i = 0; i < cardList.size(); i++) {
            this.shelf[row - i][column] = cardList.get(i);
        }
    }
    private int columnFreeSpace(int column) {
        int row = 0;
        while (row < this.rows && this.shelf[row][column] == null) {
            row++;
        }
        return row;
    }
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
     * Returns the sizes of the groups of adjacent cards in the shelf that are of the same type.
     * The size of the list represents the number of groups in the shelf and the value of each element of the result list
     * is the size of a group found
     * @return List<Integer></Integer>
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



    int getGroupSize(Card[][] shelfMatrix, PlanarCoordinate actual, Card.Type actualType) {
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


    @Override
    public Iterator getIterator() {
        return new MatrixIterator(this.rows, this.columns);
    }
}
