package Model.CommonObjectives;

import Exceptions.MatchException;
import Model.Cards;
import Model.CardsType;
import Model.Shelf;

public class CommonObjEleven extends CommonObjective {



    public CommonObjEleven (int numOfPlayers) throws MatchException {
        super(numOfPlayers);
        objID = 11;
    }

    public boolean verify (Shelf shelf) {
        Cards[][] libraryCopy = shelf.getAsMatrix();
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