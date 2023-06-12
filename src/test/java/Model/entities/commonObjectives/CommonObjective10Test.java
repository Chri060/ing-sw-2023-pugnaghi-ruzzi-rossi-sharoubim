package Model.entities.commonObjectives;

import Model.entities.Card;
import Model.entities.Shelf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Config;

import java.util.ArrayList;
import java.util.List;

class CommonObjective10Test {

    Shelf shelfOne;
    Shelf shelfTwo;
    Shelf shelfThree;
    Shelf shelfFour;
    Shelf shelfFive;
    List<Card> cards;
    CommonObjective10 commonObjective10;

    /**
     * Construct various shelves and an array of cards
     */
    @BeforeEach
    void setup(){
        Config.initialise(2);
        shelfOne = new Shelf();
        shelfTwo = new Shelf();
        shelfThree = new Shelf();
        shelfFour = new Shelf();
        shelfFive = new Shelf();
        cards = new ArrayList<>();
        commonObjective10 = new CommonObjective10(4);
    }

    @Test
    void CommonObjective10() {
        cards.add(new Card(Card.Type.PLANT,0));
        cards.add(new Card(Card.Type.FRAME,0));
        cards.add(new Card(Card.Type.BOOK,1));
        cards.add(new Card(Card.Type.BOOK,0));
        cards.add(new Card(Card.Type.GAME,0));
        cards.add(new Card(Card.Type.CAT,0));
        shelfOne.insert(cards,0);
        cards.clear();
        cards.add(new Card(Card.Type.PLANT,2));
        cards.add(new Card(Card.Type.CAT,2));
        cards.add(new Card(Card.Type.GAME,1));
        cards.add(new Card(Card.Type.PLANT,1));
        cards.add(new Card(Card.Type.FRAME,1));
        cards.add(new Card(Card.Type.CAT,1));
        shelfOne.insert(cards,1);
        cards.clear();
        cards.add(new Card(Card.Type.PLANT,5));
        cards.add(new Card(Card.Type.CAT,4));
        cards.add(new Card(Card.Type.PLANT,3));
        cards.add(new Card(Card.Type.PLANT,2));
        cards.add(new Card(Card.Type.CAT,3));
        cards.add(new Card(Card.Type.FRAME,2));
        shelfOne.insert(cards,2);
        cards.clear();
        cards.add(new Card(Card.Type.CAT,6));
        cards.add(new Card(Card.Type.PLANT,7));
        cards.add(new Card(Card.Type.BOOK,3));
        cards.add(new Card(Card.Type.GAME,3));
        cards.add(new Card(Card.Type.PLANT,6));
        cards.add(new Card(Card.Type.FRAME,3));
        shelfOne.insert(cards,3);
        cards.clear();
        cards.add(new Card(Card.Type.PLANT,10));
        cards.add(new Card(Card.Type.FRAME,5));
        cards.add(new Card(Card.Type.PLANT,9));
        cards.add(new Card(Card.Type.PLANT,8));
        cards.add(new Card(Card.Type.GAME,5));
        cards.add(new Card(Card.Type.FRAME,4));
        shelfOne.insert(cards,4);
        cards.clear();
        assert (commonObjective10.verify(shelfOne));

        cards.add(new Card(Card.Type.PLANT,0));
        cards.add(new Card(Card.Type.FRAME,0));
        cards.add(new Card(Card.Type.PLANT,19));
        cards.add(new Card(Card.Type.BOOK,0));
        cards.add(new Card(Card.Type.PLANT,20));
        cards.add(new Card(Card.Type.CAT,0));
        shelfTwo.insert(cards,0);
        cards.clear();
        cards.add(new Card(Card.Type.PLANT,2));
        cards.add(new Card(Card.Type.CAT,2));
        cards.add(new Card(Card.Type.GAME,1));
        cards.add(new Card(Card.Type.PLANT,1));
        cards.add(new Card(Card.Type.FRAME,1));
        cards.add(new Card(Card.Type.CAT,1));
        shelfTwo.insert(cards,1);
        cards.clear();
        cards.add(new Card(Card.Type.PLANT,5));
        cards.add(new Card(Card.Type.CAT,4));
        cards.add(new Card(Card.Type.PLANT,3));
        cards.add(new Card(Card.Type.PLANT,2));
        cards.add(new Card(Card.Type.PLANT,13));
        cards.add(new Card(Card.Type.FRAME,2));
        shelfTwo.insert(cards,2);
        cards.clear();
        cards.add(new Card(Card.Type.CAT,6));
        cards.add(new Card(Card.Type.PLANT,7));
        cards.add(new Card(Card.Type.BOOK,3));
        cards.add(new Card(Card.Type.GAME,3));
        cards.add(new Card(Card.Type.PLANT,6));
        cards.add(new Card(Card.Type.FRAME,3));
        shelfTwo.insert(cards,3);
        cards.clear();
        cards.add(new Card(Card.Type.PLANT,10));
        cards.add(new Card(Card.Type.FRAME,5));
        cards.add(new Card(Card.Type.PLANT,9));
        cards.add(new Card(Card.Type.PLANT,8));
        cards.add(new Card(Card.Type.GAME,5));
        cards.add(new Card(Card.Type.FRAME,4));
        shelfTwo.insert(cards,4);
        cards.clear();
        assert (commonObjective10.verify(shelfTwo));

        cards.add(new Card(Card.Type.PLANT,0));
        cards.add(new Card(Card.Type.FRAME,0));
        cards.add(new Card(Card.Type.PLANT,19));
        cards.add(new Card(Card.Type.BOOK,0));
        cards.add(new Card(Card.Type.CAT,20));
        cards.add(new Card(Card.Type.CAT,0));
        shelfThree.insert(cards,0);
        cards.clear();
        cards.add(new Card(Card.Type.PLANT,2));
        cards.add(new Card(Card.Type.CAT,2));
        cards.add(new Card(Card.Type.GAME,1));
        cards.add(new Card(Card.Type.PLANT,1));
        cards.add(new Card(Card.Type.FRAME,1));
        cards.add(new Card(Card.Type.CAT,1));
        shelfThree.insert(cards,1);
        cards.clear();
        cards.add(new Card(Card.Type.PLANT,5));
        cards.add(new Card(Card.Type.CAT,4));
        cards.add(new Card(Card.Type.PLANT,3));
        cards.add(new Card(Card.Type.PLANT,2));
        cards.add(new Card(Card.Type.PLANT,13));
        cards.add(new Card(Card.Type.FRAME,2));
        shelfThree.insert(cards,2);
        cards.clear();
        cards.add(new Card(Card.Type.CAT,6));
        cards.add(new Card(Card.Type.PLANT,7));
        cards.add(new Card(Card.Type.BOOK,3));
        cards.add(new Card(Card.Type.GAME,3));
        cards.add(new Card(Card.Type.PLANT,6));
        cards.add(new Card(Card.Type.FRAME,3));
        shelfThree.insert(cards,3);
        cards.clear();
        cards.add(new Card(Card.Type.PLANT,10));
        cards.add(new Card(Card.Type.FRAME,5));
        cards.add(new Card(Card.Type.CAT,9));
        cards.add(new Card(Card.Type.PLANT,8));
        cards.add(new Card(Card.Type.GAME,5));
        cards.add(new Card(Card.Type.FRAME,4));
        shelfThree.insert(cards,4);
        cards.clear();
        assert (!commonObjective10.verify(shelfThree));

        assert (!commonObjective10.verify(shelfFour));
        cards.add(new Card(Card.Type.CAT, 0));
        cards.add(new Card(Card.Type.CAT, 0));
        cards.add(new Card(Card.Type.CAT, 0));
        shelfFour.insert(cards, 0);
        assert (!commonObjective10.verify(shelfFour));
        shelfFour.insert(cards, 1);
        assert (!commonObjective10.verify(shelfFour));
        shelfFour.insert(cards, 4);
        assert (!commonObjective10.verify(shelfFour));
        shelfFour.insert(cards, 3);
        assert (!commonObjective10.verify(shelfFour));
        cards.clear();
        cards.add(new Card(Card.Type.TROPHY, 0));
        cards.add(new Card(Card.Type.CAT, 0));
        cards.add(new Card(Card.Type.TROPHY, 0));
        shelfFour.insert(cards, 2);
        assert (commonObjective10.verify(shelfFour));
        cards.clear();

        assert (!commonObjective10.verify(shelfFive));
        cards.add(new Card(Card.Type.CAT, 0));
        cards.add(new Card(Card.Type.TROPHY, 0));
        cards.add(new Card(Card.Type.CAT, 0));
        shelfFive.insert(cards, 0);
        assert (!commonObjective10.verify(shelfFive));
        shelfFive.insert(cards, 1);
        assert (!commonObjective10.verify(shelfFive));
        shelfFive.insert(cards, 4);
        assert (!commonObjective10.verify(shelfFive));
        shelfFive.insert(cards, 3);
        assert (!commonObjective10.verify(shelfFive));
        cards.clear();
        cards.add(new Card(Card.Type.TROPHY, 0));
        cards.add(new Card(Card.Type.CAT, 0));
        cards.add(new Card(Card.Type.TROPHY, 0));
        shelfFive.insert(cards, 2);
        assert (commonObjective10.verify(shelfFive));
        cards.clear();
    }
}