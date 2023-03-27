package Model;

public class CommonObjTwo extends CommonObjective {



    public CommonObjTwo (int numOfPlayers) {
        super(numOfPlayers);
        objID = 2;
    }

    public boolean verify (Library library) {
        Cards[][] libraryCopy = library.getAsMatrix();
        final int diagonalLength = 5;
        int row = libraryCopy.length;;
        int col = libraryCopy[0].length;
        for (int i = 0; i <= row - diagonalLength; i++) {
            for (int j = 0; j <= col - diagonalLength; j++) {
                if (libraryCopy[i][j] != null) {
                    for (int k = 1; k < diagonalLength; k++) {
                        if (libraryCopy[i + k][j + k] != null) {
                            if (libraryCopy[i + k][j + k].getType() != libraryCopy[i][j].getType()) {
                                break;
                            }
                            if (k == diagonalLength - 1) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        for (int i = row - 1; i >= diagonalLength - 1; i--) {
            for (int j = 0; j <= col - diagonalLength; j++) {
                if (libraryCopy[i][j] != null) {
                    for (int k = 0; k <  diagonalLength; k++) {
                        if (libraryCopy[i - k][j + k] != null) {
                            if (libraryCopy[i - k][j + k].getType() != libraryCopy[i][j].getType()) {
                                break;
                            }
                            if (k == diagonalLength - 1) {
                                return true;
                            }
                        }

                    }
                }
            }
        }
        return false;
    }
}