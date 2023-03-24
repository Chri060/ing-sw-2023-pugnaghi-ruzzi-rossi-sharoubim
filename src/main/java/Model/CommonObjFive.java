package Model;

import Model.Cards;
import Model.CommonObjective;
import Model.Library;

public class CommonObjFive extends CommonObjective {

    public CommonObjFive() {
        objID = 5;
    }

    public boolean verify(Library library) {
        Cards[][] libraryCopy = library.getAsMatrix();
        int row = libraryCopy.length;
        int col = libraryCopy[0].length;
        int count = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col - 3; j++) {
                if ((libraryCopy[i][j] != null) && (libraryCopy[i][j + 1] != null) && (libraryCopy[i][j + 2] != null) && (libraryCopy[i][j + 3] != null)) {
                    if (libraryCopy[i][j].getType() == libraryCopy[i][j + 1].getType() && libraryCopy[i][j + 1].getType() == libraryCopy[i][j + 2].getType() && libraryCopy[i][j + 2].getType() == libraryCopy[i][j + 3].getType()) {
                        count++;
                        System.out.println(count);
                        libraryCopy[i][j] = null;
                        libraryCopy[i][j + 1] = null;
                        libraryCopy[i][j + 2] = null;
                        libraryCopy[i][j + 3] = null;
                    }
                }
            }
        }

        for (int j = 0; j < col; j++) {
            for (int i = 0; i < row - 3; i++) {
                if ((libraryCopy[i][j] != null) && (libraryCopy[i + 1][j] != null) && (libraryCopy[i + 2][j] != null) && (libraryCopy[i + 3][j] != null)) {
                    if (libraryCopy[i][j].getType() == libraryCopy[i + 1][j].getType() && libraryCopy[i + 1][j].getType() == libraryCopy[i + 2][j].getType() && libraryCopy[i + 2][j].getType() == libraryCopy[i + 3][j].getType()) {
                        count++;
                        System.out.println(count);
                        libraryCopy[i][j] = null;
                        libraryCopy[i + 1][j] = null;
                        libraryCopy[i + 2][j] = null;
                        libraryCopy[i + 3][j] = null;
                    }
                }
            }
        }

        return (count >= 4);
    }
}