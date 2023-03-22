package Model.CommonObjectives;

import Model.Cards;
import Model.CardsType;
import Model.CommonObjective;
import Model.Library;

import java.util.ArrayList;
import java.util.List;

public class CommonObjEleven extends CommonObjective {
    public CommonObjEleven () {

    }

    public boolean verify (Library library) {
        Cards[][] libraryCopy = library.getAsMatrix();
        int row = libraryCopy.length;;
        int col = libraryCopy[0].length;
        int count =0;

        for (CardsType c: CardsType.values()) {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (libraryCopy[i][j].getType() == c) {
                        count++;
                    }
                }
            }
            if (count == 8) {
                return true;
            }
            count = 0;
        }

        return false;
    }
}