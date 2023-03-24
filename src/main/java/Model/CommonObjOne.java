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
        int[][] groups = new int[row][col];
        int count = 0;
        int max, min;

        for (int k = 0; k < 4; k++) {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    groups[i][j] = 0;
                }
            }
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col - 1; j++) {
                    if (libraryCopy[i][j] != null && libraryCopy[i][j + 1] != null) {
                        if (libraryCopy[i][j].getType() == libraryCopy[i][j + 1].getType()) {
                            groups[i][j]++;
                            groups[i][j + 1]++;
                        }

                    }
                }
            }
            for (int j = 0; j < col; j++) {
                for (int i = 0; i < row - 1; i++) {
                    if (libraryCopy[i][j] != null && libraryCopy[i + 1][j] != null) {
                        if (libraryCopy[i][j].getType() == libraryCopy[i + 1][j].getType()) {
                            groups[i][j]++;
                            groups[i + 1][j]++;
                        }
                    }
                }
            }
            max = 0;
            min = 4;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (groups[i][j] > max) {
                        max = groups[i][j];
                    }
                    if (groups[i][j] < min && groups[i][j] != 0) {
                        min = groups[i][j];
                    }
                }
            }
            if (max == 0) {
                break;
            }
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col - 1; j++) {
                    if (libraryCopy[i][j] != null && libraryCopy[i][j + 1] != null) {
                        if (libraryCopy[i][j].getType() == libraryCopy[i][j + 1].getType() && (groups[i][j] == min || groups[i][j + 1] == min)) {
                            libraryCopy[i][j] = null;
                            libraryCopy[i][j + 1] = null;
                            count++;
                        }

                    }
                }
            }
            for (int j = 0; j < col; j++) {
                for (int i = 0; i < row - 1; i++) {
                    if (libraryCopy[i][j] != null && libraryCopy[i + 1][j] != null) {
                        if (libraryCopy[i][j].getType() == libraryCopy[i + 1][j].getType() && (groups[i][j] == min || groups[i + 1][j] == min)) {
                            libraryCopy[i][j] = null;
                            libraryCopy[i + 1][j] = null;
                            count++;
                        }
                    }
                }
            }
        }
        return count >=6;
    }
}