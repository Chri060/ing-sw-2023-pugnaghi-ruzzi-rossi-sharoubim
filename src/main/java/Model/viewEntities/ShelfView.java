package Model.viewEntities;

import Model.entities.Card;
import Model.entities.Shelf;

import java.awt.*;
import java.io.Serializable;
import java.util.List;

public class ShelfView implements Serializable{

    public Card[][] shelf;

    public ShelfView(Card[][] shelf) {
        this.shelf = shelf;
    }

    public boolean canInsert(List<Card> cardList, int column) {
        if (cardList == null) {
            return false;
        }
        if (cardList.size() == 0) {
            return false;
        }
        if (column < 0 || column >= shelf[0].length) {
            return false;
        }

        if (cardList.size() > this.columnFreeSpace(column)) {
            return false;
        }
        return true;
    }

    private int columnFreeSpace(int column) {
        int row = 0;
        while (row < this.shelf.length && this.shelf[row][column] == null) {
            row++;
        }
        return row;
    }
    public int maxFreeSpace() {
        int max = 0;
        for (int i = 0; i < this.shelf[0].length; i++) {
            if (columnFreeSpace(i) > max) {
                max = columnFreeSpace(i);
            }
        }
        return max;
    }

    Card[][] getShelf() {
        return shelf;
    }

}
