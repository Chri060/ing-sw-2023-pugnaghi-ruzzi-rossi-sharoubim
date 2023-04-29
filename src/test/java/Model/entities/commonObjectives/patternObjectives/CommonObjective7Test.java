package Model.entities.commonObjectives.patternObjectives;

import Exceptions.InvalidArgumentException;
import Model.entities.Card;
import Model.entities.Shelf;
import Model.entities.commonObjectives.CommonObjective;
import Model.entities.commonObjectives.CommonObjective6;
import Model.entities.commonObjectives.CommonObjective7;
import org.junit.jupiter.api.Test;
import util.Config;

import java.util.ArrayList;
import java.util.List;

class CommonObjective7Test {

    @Test
    void test1() throws InvalidArgumentException {
        Config.initialise(2);
        Shelf shelf = new Shelf();
        List<Card> cardList = new ArrayList<>();
        CommonObjective commonObjective7 = new CommonObjective7(0);

        cardList.add(new Card(Card.Type.CAT, 0));
        cardList.add(new Card(Card.Type.CAT, 0));

        assert (!commonObjective7.verify(shelf));

        shelf.insert(cardList, 0);
        assert (!commonObjective7.verify(shelf));

        shelf.insert(cardList, 1);
        assert (!commonObjective7.verify(shelf));

        shelf.insert(cardList, 2);
        assert (!commonObjective7.verify(shelf));

        shelf.insert(cardList, 4);
        assert (!commonObjective7.verify(shelf));
    }

    @Test
    void test2() throws InvalidArgumentException {
        Config.initialise(2);
        Shelf shelf = new Shelf();
        List<Card> cardList = new ArrayList<>();
        CommonObjective commonObjective7 = new CommonObjective7(0);

        cardList.add(new Card(Card.Type.CAT, 0));
        cardList.add(new Card(Card.Type.CAT, 0));

        //TODO togliere i commenti

        //assert (!commonObjective7.verify(shelf));

        shelf.insert(cardList, 0);
        //assert (!commonObjective7.verify(shelf));

        shelf.insert(cardList, 1);
        //assert (!commonObjective7.verify(shelf));

        cardList.add(0,new Card(Card.Type.TROPHY, 0));

        shelf.insert(cardList, 2);
        //assert (!commonObjective7.verify(shelf));

        shelf.insert(cardList, 3);
        assert (commonObjective7.verify(shelf));
    }


    @Test
    void test3() throws InvalidArgumentException {
        Config.initialise(2);
        Shelf shelf = new Shelf();
        List<Card> cardList = new ArrayList<>();
        CommonObjective commonObjective7 = new CommonObjective7(0);

        cardList.add(new Card(Card.Type.CAT, 0));
        cardList.add(new Card(Card.Type.CAT, 0));

        shelf.insert(cardList, 0);
        shelf.insert(cardList, 1);

        //assert (!commonObjective7.verify(shelf));

        shelf.insert(cardList, 2);
        //assert (!commonObjective7.verify(shelf));


        cardList.clear();

        cardList.add(new Card(Card.Type.BOOK, 0));
        cardList.add(new Card(Card.Type.CAT, 0));

        shelf.insert(cardList, 3);

        assert (!commonObjective7.verify(shelf));

    }

    @Test
    void test4() throws InvalidArgumentException {
        Config.initialise(2);

        CommonObjective commonObjective = new CommonObjective7(2);

        List<Card> cardList = new ArrayList<>();
        Shelf shelf = new Shelf();

        cardList.add(new Card(Card.Type.TROPHY, 0));

        shelf.insert(cardList,0);
        shelf.insert(cardList,0);
        shelf.insert(cardList,1);
        shelf.insert(cardList,1);
        shelf.insert(cardList,1);
        shelf.insert(cardList,2);
        shelf.insert(cardList,2);
        shelf.insert(cardList,2);
        shelf.insert(cardList,3);
        shelf.insert(cardList,3);

        assert(commonObjective.verify(shelf));
    }

}