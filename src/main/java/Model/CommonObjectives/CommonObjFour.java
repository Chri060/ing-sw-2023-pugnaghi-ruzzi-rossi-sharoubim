package Model.CommonObjectives;

import Model.Cards;
import Model.CardsType;
import Model.CommonObjective;
import Model.Library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonObjFour extends CommonObjective {
    public CommonObjFour () {

    }

    public boolean verify (Library library) {
        Cards[][] libraryCopy = library.getAsMatrix();
        int row = libraryCopy.length;
        int col = libraryCopy[0].length;
        int count = 0;

        List<CardsType> card = new ArrayList<>();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++){
                if (libraryCopy[i][j] != null && !card.contains(libraryCopy[i][j].getType())) {
                    card.add(libraryCopy[i][j].getType());
                }
            }
            if (card.size() <= 3) {
                count++;
            }
            card.clear();
        }

        return count >= 4;
    }
}