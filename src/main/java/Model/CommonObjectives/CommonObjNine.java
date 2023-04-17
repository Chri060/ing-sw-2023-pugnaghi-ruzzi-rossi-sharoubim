package Model.CommonObjectives;

import Exceptions.MatchException;
import Model.Cards;
import Model.CardsType;
import Model.Shelf;

import java.util.ArrayList;
import java.util.List;

public class CommonObjNine extends CommonObjective {



    public CommonObjNine (int numOfPlayers) throws MatchException {
        super(numOfPlayers);
        objID = 9;
    }

    public boolean verify (Shelf shelf) {
        Cards[][] libraryCopy = shelf.getAsMatrix();
        List<CardsType> card = new ArrayList<>();
        int row = libraryCopy.length;;
        int col = libraryCopy[0].length;
        int count = 0;


        for (int j = 0; j < col; j++) {
            for (int i = 0; i < row; i++) {
                if (libraryCopy[i][j] != null && !card.contains(libraryCopy[i][j].getType())) {
                    card.add(libraryCopy[i][j].getType());
                }
            }
            if (card.size() <= 3) {
                count++;
            }
            card.clear();
        }
        return count >= 3;
    }
}