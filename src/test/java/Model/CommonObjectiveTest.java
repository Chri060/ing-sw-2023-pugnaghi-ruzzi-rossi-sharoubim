package Model;

import Exceptions.NotEnoughSpaceInColumnException;
import Exceptions.MatchException;
import Exceptions.InvalidPickException;
import Model.CommonObjectives.CommonObjFive;
import Model.CommonObjectives.CommonObjectiveFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class CommonObjectiveTest {

    @Test
   void test() throws MatchException, NotEnoughSpaceInColumnException, InvalidPickException {
        for (int k = 0; k < 1; k++) {
            Shelf shelf = new Shelf();
            List<Cards> cards = new ArrayList<>();
            Random rand = new Random();

            int row = 6;
            int col = 5;
            for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {

                            cards.add(new Cards(CardsType.values()[rand.nextInt(6)], rand.nextInt(22)));
                            shelf.insert(cards, j);
                            cards.clear();
                    }

            }

            System.out.println();

            Cards[][] c = shelf.getAsMatrix();
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (c[i][j] != null) {
                        System.out.print(c[i][j].getType() + "\t");
                    } else {
                        System.out.print("null" + "\t");
                    }
                }
                System.out.println();
            }

            System.out.println();
            System.out.println();
            System.out.println();

            CommonObjFive obiettivo = new CommonObjFive(8);
            obiettivo.getMaxAvaiblePoints();
            System.out.println(obiettivo.checkMaxAvaiablePoints());
            System.out.println(obiettivo.verify(shelf));
        }
    }

    @Test
    void getRandomObjectives() {


        List<String> player = new ArrayList<>();

        player.add("a");
        player.add("b");

        CommonObjectiveFactory objs = new CommonObjectiveFactory();
        try {
            Model model = new Model(player);


            for (int i = 0; i < 2; i++) {
                System.out.println(model.getCommonObjectives().get(i).getObjID());
            }
        }
        catch (MatchException e){
            e.printStackTrace();
        }
    }
}