package Model;

import Exceptions.MatchException;

public class CommonObjTen extends CommonObjective {



    public CommonObjTen(int numOfPlayers) throws MatchException {
        super(numOfPlayers);
        objID = 10;
    }

    public boolean verify(Library library) {
        Cards[][] libraryCopy = library.getAsMatrix();
        int row = libraryCopy.length;
        int col = libraryCopy[0].length;
        for (int i = 1; i < row - 2; i++) {
            for (int j = 1; j < col - 2; j++) {
                if (libraryCopy[i][j] != null &&
                    libraryCopy[i + 1][j + 1] != null &&
                    libraryCopy[i + 1][j - 1] != null &&
                    libraryCopy[i - 1][j + 1] != null &&
                    libraryCopy[i - 1][j - 1] != null)
                { if (libraryCopy[i][j].getType() == libraryCopy[i + 1][j + 1].getType() &&
                      libraryCopy[i][j].getType() == libraryCopy[i + 1][j - 1].getType() &&
                      libraryCopy[i][j].getType() == libraryCopy[i - 1][j + 1].getType() &&
                      libraryCopy[i][j].getType() == libraryCopy[i - 1][j - 1].getType())
                    { return true; }
                }
            }
        }
        return false;
    }
}