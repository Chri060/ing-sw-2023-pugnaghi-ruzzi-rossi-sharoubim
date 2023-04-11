package Model;

import Exceptions.MatchException;

import java.util.ArrayList;
import java.util.List;

public class CommonObjEight extends CommonObjective {



    public CommonObjEight (int numOfPlayers) throws MatchException {
        super(numOfPlayers);
        objID = 8;
    }

    public boolean verify (Library library) {
        Cards[][] libraryCopy = library.getAsMatrix();
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