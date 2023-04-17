package Model.CommonObjectives;

import Exceptions.MatchException;
import Model.Cards;
import Model.Shelf;

public class CommonObjThree extends CommonObjective {



    public CommonObjThree (int numOfPlayers) throws MatchException {
        super(numOfPlayers);
        objID = 3;
    }

    public boolean verify (Shelf shelf) {
        Cards[][] libraryCopy = shelf.getAsMatrix();
        int row = libraryCopy.length - 1;
        int col = libraryCopy[0].length - 1;
        if (libraryCopy[0][0] != null && libraryCopy[row][0] != null && libraryCopy[0][col] != null && libraryCopy[row][col] != null) {
            return libraryCopy[0][0].getType() == libraryCopy[row][0].getType() &&
                   libraryCopy[0][0].getType() == libraryCopy[0][col].getType() &&
                   libraryCopy[0][0].getType() == libraryCopy[row][col].getType();
        }
        return false;
    }
}