package Model.entities.commonObjectives.patternObjectives;

import Exceptions.InvalidArgumentException;
import Model.entities.Card;
import Model.entities.Shelf;
import Model.entities.commonObjectives.CommonObjective;
import Model.entities.commonObjectives.CommonObjective10;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Config;

import java.util.ArrayList;
import java.util.List;

class CommonObjective10Test {

    @Test
    void verifyTest1() throws InvalidArgumentException {
        Config.initialise(2);
        Shelf shelf = new Shelf();
        CommonObjective commonObjective10 = new CommonObjective10(0);
        List<Card> cardList = new ArrayList<>();

        assert (!commonObjective10.verify(shelf));

        cardList.add(new Card(Card.Type.CAT, 0));
        cardList.add(new Card(Card.Type.CAT, 0));
        cardList.add(new Card(Card.Type.CAT, 0));


        shelf.insert(cardList, 0);
        assert (!commonObjective10.verify(shelf));

        shelf.insert(cardList, 1);
        assert (!commonObjective10.verify(shelf));

        shelf.insert(cardList, 4);
        assert (!commonObjective10.verify(shelf));

        shelf.insert(cardList, 3);
        assert (!commonObjective10.verify(shelf));

        cardList.clear();

        cardList.add(new Card(Card.Type.TROPHY, 0));
        cardList.add(new Card(Card.Type.CAT, 0));
        cardList.add(new Card(Card.Type.TROPHY, 0));
        shelf.insert(cardList, 2);
        assert (commonObjective10.verify(shelf));

    }

    @Test
    void verifyTest2() throws InvalidArgumentException {
        Config.initialise(2);
        Shelf shelf = new Shelf();
        CommonObjective commonObjective10 = new CommonObjective10(0);
        List<Card> cardList = new ArrayList<>();

        assert (!commonObjective10.verify(shelf));

        cardList.add(new Card(Card.Type.CAT, 0));
        cardList.add(new Card(Card.Type.TROPHY, 0));
        cardList.add(new Card(Card.Type.CAT, 0));


        shelf.insert(cardList, 0);
        assert (!commonObjective10.verify(shelf));

        shelf.insert(cardList, 1);
        assert (!commonObjective10.verify(shelf));

        shelf.insert(cardList, 4);
        assert (!commonObjective10.verify(shelf));

        shelf.insert(cardList, 3);
        assert (!commonObjective10.verify(shelf));

        cardList.clear();

        cardList.add(new Card(Card.Type.TROPHY, 0));
        cardList.add(new Card(Card.Type.CAT, 0));
        cardList.add(new Card(Card.Type.TROPHY, 0));
        shelf.insert(cardList, 2);
        assert (commonObjective10.verify(shelf));
    }



}
