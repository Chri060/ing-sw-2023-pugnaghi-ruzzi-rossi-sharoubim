package Model.entities.commonObjectives.patternObjectives;

import Exceptions.InvalidArgumentException;
import Model.entities.Card;
import Model.entities.Shelf;
import Model.entities.commonObjectives.CommonObjective;
import Model.entities.commonObjectives.CommonObjective6;
import org.junit.jupiter.api.Test;
import util.Config;

import java.util.ArrayList;
import java.util.List;

class CommonObjective6Test {

    @Test
    void test1() throws InvalidArgumentException {
        Config.initialise(2);
        Shelf shelf = new Shelf();
        List<Card> cardList = new ArrayList<>();
        List<Card> cardList1 = new ArrayList<>();

        CommonObjective commonObjective6 = new CommonObjective6(0);

        assert (!commonObjective6.verify(shelf));

        cardList.add(new Card(Card.Type.TROPHY, 0));
        cardList.add(new Card(Card.Type.CAT, 0));
        cardList.add(new Card(Card.Type.BOOK, 0));
        cardList.add(new Card(Card.Type.FRAME, 0));
        cardList.add(new Card(Card.Type.PLANT, 0));

        shelf.insert(cardList, 0);
        shelf.insert(cardList, 2);

        assert (!commonObjective6.verify(shelf));

        cardList.stream().forEach(x -> cardList1.add(0, x));

        shelf.insert(cardList1, 1);

        assert (!commonObjective6.verify(shelf));

        shelf.insert(cardList1, 3);

        assert (!commonObjective6.verify(shelf));

        cardList.clear();
        cardList.add(new Card(Card.Type.GAME, 0));

        cardList1.clear();
        cardList1.add(new Card(Card.Type.CAT, 0));

        shelf.insert(cardList1, 1);

        assert (!commonObjective6.verify(shelf));

        shelf.insert(cardList, 0);

        assert (!commonObjective6.verify(shelf));


        shelf.insert(cardList1, 2);

        assert (!commonObjective6.verify(shelf));

        shelf.insert(cardList, 3);

        assert (commonObjective6.verify(shelf));

    }

}