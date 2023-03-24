package Model;

import Model.Cards;
import Model.CommonObjective;
import Model.Library;

public class CommonObjOne extends CommonObjective {

    public CommonObjOne() {
        objID = 1;
    }

    public boolean verify (Library library) {
        Cards[][] libraryCopy = library.getAsMatrix();
        int row = libraryCopy.length;;
        int col = libraryCopy[0].length;
        int count = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col - 1; j++) {
                if (libraryCopy[i][j] != null && libraryCopy[i][j + 1] != null) {
                    if (libraryCopy[i][j].getType() == libraryCopy[i][j + 1].getType()) {
                        count++;
                        libraryCopy[i][j] = null;
                        libraryCopy[i][j + 1] = null;
                    }
                }
            }
        }
        for (int j = 0; j < col; j++) {
            for (int i = 0; i < row - 1; i++) {
                if (libraryCopy[i][j] != null && libraryCopy[i + 1][j] != null) {
                    if (libraryCopy[i][j].getType() == libraryCopy[i + 1][j].getType()) {
                        count++;
                        libraryCopy[i][j] = null;
                        libraryCopy[i + 1][j] = null;
                    }
                }
            }
        }
        return (count >= 6);
    }
}