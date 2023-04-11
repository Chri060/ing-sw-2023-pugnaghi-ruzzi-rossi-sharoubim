package Model;

import Exceptions.*;
import Exceptions.MatchException;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static java.lang.Math.min;

public class MatchTest extends TestCase {

    @Test
    void constructionTest() throws MatchException {
        List<String> players = new ArrayList<>();

        players.add("Christian");
        players.add("Carlo");

        try {
            Match model = new Match(players);
            for (int i = 0; i < (new Library()).getLibraryCols(); i++) { //fills chair's player shelf
                List<Cards> cards = new ArrayList<>();
                for (int j = 0; j < (new Library()).getLibraryRows(); j++) {
                    cards.add(new Cards(CardsType.CATS, 0));
                }
                model.insert(cards, i, model.getChairPlayer());
            }

            List<CommonObjective> obj = model.getCommonObjectives();

            obj.clear();

            obj.add(new CommonObjOne(players.size()));
            obj.add(new CommonObjTwo(players.size()));

            List<Integer> result;

            result = model.getCommonObjectivesPoints(model.getChairPlayer());

            for (Integer i : result) {
                assert(8 == i);
            }

            result = model.getCommonObjectivesPoints("Carlo");

            for (Integer i : result) {
                assert(0 == i);
            }


        } catch (InvalidPickException | MatchException | NotEnoughSpaceInColumnException | PlayerNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}