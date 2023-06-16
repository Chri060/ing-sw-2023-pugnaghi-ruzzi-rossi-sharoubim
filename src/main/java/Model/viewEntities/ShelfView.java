package Model.viewEntities;

import Model.entities.Card;

import java.io.Serializable;
import java.util.List;

/**
 * Class that implements the view that represent the shelf
 */
public class ShelfView implements Serializable{

    public Card[][] shelf;

    /**
     * Constructor of the shelf view
     *
     * @param shelf is the array of Card that represent the shelf
     */
    public ShelfView(Card[][] shelf) {
        this.shelf = shelf;
    }

    /**
     * Checks if it is possible to insert some cards in a column
     *
     * @param cardList is the list of Card to insert
     * @param column is where to insert the cards
     *
     * @return true if it is possible to insert the cards, false otherwise
     */
    public boolean canInsert(List<Card> cardList, int column) {
        if (cardList == null) return false;
        if (cardList.size() == 0) return false;
        if (column < 0 || column >= shelf[0].length) return false;
        if (cardList.size() > this.columnFreeSpace(column)) return false;
        return true;
    }

    /** Returns the number of free cells in the column selected
     *
     * @param column is the number of column to check
     *
     * @return the number of free cells in the column
     */
    private int columnFreeSpace(int column) {
        int row = 0;
        while (row < this.shelf.length && this.shelf[row][column] == null) {
            row++;
        }
        return row;
    }

    /**
     * @return the maximum free space in the shelf
     */
    public int maxFreeSpace() {
        int max = 0;
        for (int i = 0; i < this.shelf[0].length; i++) {
            if (columnFreeSpace(i) > max) {
                max = columnFreeSpace(i);
            }
        }
        return max;
    }

    /**
     * @return the shelf as an array of Card
     */
    public Card[][] getShelf() {
        return shelf;
    }
}