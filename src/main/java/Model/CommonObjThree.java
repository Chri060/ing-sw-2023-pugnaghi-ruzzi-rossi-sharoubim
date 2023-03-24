package Model;

import Model.Cards;
import Model.CommonObjective;
import Model.Library;

public class CommonObjThree extends CommonObjective {

    public CommonObjThree () {
        objID = 3;
    }

    public boolean verify (Library library) {
        Cards[][] libraryCopy = library.getAsMatrix();
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