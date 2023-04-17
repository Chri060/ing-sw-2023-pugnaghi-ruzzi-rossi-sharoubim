package Model.CommonObjectives;

import Exceptions.MatchException;
import Model.Cards;
import Model.CardsType;
import Model.Shelf;

import java.util.ArrayList;
import java.util.List;

public class CommonObjEight extends CommonObjective {



    public CommonObjEight (int numOfPlayers) throws MatchException {
        super(numOfPlayers);
        objID = 8;
    }

    public boolean verify (Shelf shelf) {
        Cards[][] libraryCopy = shelf.getAsMatrix();
        int row = libraryCopy.length;
        int col = libraryCopy[0].length;
        int count = 0;
        List<CardsType> card = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++){
                if (libraryCopy[i][j] != null && !card.contains(libraryCopy[i][j].getType())) {
                    card.add(libraryCopy[i][j].getType());
                }
            }
            if (card.size() >= 5) {
                count++;
            }
            card.clear();
        }
        return count >= 2;
    }
}