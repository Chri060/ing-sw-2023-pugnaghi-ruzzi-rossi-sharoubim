package Model;

import Exceptions.ColumFullException;
import Exceptions.IncorrectPlayersNumberException;
import Exceptions.NotEnoughPrivateObjectivesException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CommonObjectiveTest {

    @Test
    void test() throws ColumFullException {
        for (int k = 0; k < 2000; k++) {
            Library library = new Library();
            List<Cards> cards = new ArrayList<>();
            Random rand = new Random();

            int row = 6;
            int col = 5;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    cards.add(new Cards(CardsType.values()[rand.nextInt(6)], rand.nextInt(22)));
                    library.insert(cards, j);
                    cards.clear();

                }
            }

            System.out.println();

            Cards[][] c = library.getAsMatrix();
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


            CommonObjSeven obiettivo = new CommonObjSeven();

            System.out.println(obiettivo.verify(library));
        }
    }

    @Test
    void getRandomObjectives() {


        List<String> player = new ArrayList<>();

        player.add("a");
        player.add("b");

        CommonObjectiveFactory objs = new CommonObjectiveFactory();
        try {
            Match match = new Match(player);


            for (int i = 0; i < 2; i++) {
                System.out.println(match.getCommonObjectives().get(i).getObjID());
            }
        }
        catch (NotEnoughPrivateObjectivesException |IncorrectPlayersNumberException e){
            e.printStackTrace();
        }
    }
}