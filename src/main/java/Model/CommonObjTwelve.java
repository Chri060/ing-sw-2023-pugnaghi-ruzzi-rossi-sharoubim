package Model;

import Model.Cards;
import Model.CardsType;
import Model.CommonObjective;
import Model.Library;

import java.util.ArrayList;
import java.util.List;

public class CommonObjTwelve extends CommonObjective {

    public CommonObjTwelve (int numOfPlayers) {
        super(numOfPlayers);
        objID = 12;
    }

    public boolean verify (Library library) {
        Cards[][] libraryCopy = library.getAsMatrix();
        int row = libraryCopy.length;
        int col = libraryCopy[0].length;
        int count = 0;

        List<Integer> card = new ArrayList<>();

        for (int j = 0; j < col; j++) {
            for (int i = 0; i < row; i++) {
                if (libraryCopy[i][j] != null) {
                    count++;
                }
            }
            card.add(count);
            count = 0;
        }

        for (int i = 0; i <= col - 5; i++) {
            if ((card.get(i) != 0) &&
                    (card.get(i) == card.get(i + 1) - 1) &&
                    (card.get(i) == card.get(i + 2) - 2) &&
                    (card.get(i) == card.get(i + 3) - 3) &&
                    (card.get(i) == card.get(i + 4) - 4)) {
                return true;
            }
            if ((card.get(i) != 0) &&
                    (card.get(i) == card.get(i + 1) + 1) &&
                    (card.get(i) == card.get(i + 2) + 2) &&
                    (card.get(i) == card.get(i + 3) + 3) &&
                    (card.get(i) == card.get(i + 4) + 4)) {
                return true;
            }
        }

        return false;
    }
}