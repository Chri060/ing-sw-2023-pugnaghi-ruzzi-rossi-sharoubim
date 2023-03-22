package Model.CommonObjectives;

import Model.Cards;
import Model.CommonObjective;
import Model.Library;

public class CommonObjSeven extends CommonObjective {

    public CommonObjSeven () {

    }

    public boolean verify (Library library) {
        int count = 0;
        Cards[][] libraryCopy = library.getAsMatrix();
        int row = libraryCopy.length;;
        int col = libraryCopy[0].length;

        count = 0;
        for (int i = 0; i < row - 1; i++) {
            for (int j = 0; j < col - 1; j++) {
                if (libraryCopy[i][j] != null && libraryCopy[i][j + 1] != null && libraryCopy[i + 1][j] != null && libraryCopy[i + 1][j + 1] != null) {
                    if (libraryCopy[i][j].getType() == libraryCopy[i][j + 1].getType() &&
                        libraryCopy[i][j].getType() == libraryCopy[i + 1][j].getType() &&
                        libraryCopy[i][j].getType() == libraryCopy[i + 1][j + 1].getType()) {
                        count++;
                        libraryCopy[i][j] = null;
                        libraryCopy[i][j + 1] = null;
                        libraryCopy[i + 1][j] = null;
                        libraryCopy[i + 1][j + 1] = null;
                    }
                }
            }
        }

        System.out.println(count);

        return (count >= 2);
    }


}