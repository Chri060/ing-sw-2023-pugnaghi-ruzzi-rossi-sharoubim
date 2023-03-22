package Model.CommonObjectives;

import Model.Cards;
import Model.CardsType;
import Model.CommonObjective;
import Model.Library;

import java.util.ArrayList;
import java.util.List;

public class CommonObjSeven extends CommonObjective {

    public CommonObjSeven () {

    }

    public boolean verify (Library library) {
        Cards[][] libraryCopy = library.getAsMatrix();
        int row = libraryCopy.length;;
        int col = libraryCopy[0].length;
        CardsType card = CardsType.CATS;

        List<CardsType> squareFound = new ArrayList<>();

        for (int i = 0; i < row - 1; i++) {
            for (int j = 0; j < col - 1; j++) {
                if (libraryCopy[i][j] != null && libraryCopy[i][j + 1] != null && libraryCopy[i + 1][j] != null && libraryCopy[i + 1][j + 1] != null) {
                    if (libraryCopy[i][j].getType() == libraryCopy[i][j + 1].getType() &&
                        libraryCopy[i][j].getType() == libraryCopy[i + 1][j].getType() &&
                        libraryCopy[i][j].getType() == libraryCopy[i + 1][j + 1].getType()) {
                        if (squareFound.contains(libraryCopy[i][j].getType())) {
                            return true;
                        }
                        else {
                            squareFound.add(libraryCopy[i][j].getType());
                        }
                        libraryCopy[i][j] = null;
                        libraryCopy[i][j + 1] = null;
                        libraryCopy[i + 1][j] = null;
                        libraryCopy[i + 1][j + 1] = null;
                    }
                }
            }
        }
        return false;
    }
}