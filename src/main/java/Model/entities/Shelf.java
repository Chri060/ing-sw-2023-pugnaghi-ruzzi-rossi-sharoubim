package Model.entities;

import Exceptions.InvalidArgumentException;
import util.Checker;
import util.Config;
import util.Iterators.Iterable;
import util.Iterators.Iterator;
import util.Iterators.MatrixIterator;
import util.PlanarCoordinate;

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
    public void insert(List<Card> cardList, int column) throws InvalidArgumentException {

        if (cardList == null) {
            throw new InvalidArgumentException("CardList to insert in shelf is null");
        }
        if (cardList.size() == 0) {
            throw new InvalidArgumentException("Empty card list provided");
        }
        if (cardList.size() > this.columnFreeSpace(column)) {
            throw new InvalidArgumentException("Not enough space in the " + (column + 1) + "° column");
        }

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

    @Override
    public Iterator getIterator() {
        return new MatrixIterator(this.rows, this.columns);
    }
}
