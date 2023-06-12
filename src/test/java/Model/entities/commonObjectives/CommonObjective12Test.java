package Model.entities.commonObjectives;

import Model.entities.Card;
import Model.entities.Shelf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Config;

import java.util.ArrayList;
import java.util.List;

class CommonObjective12Test {

    Shelf shelfOne;
    Shelf shelfTwo;
    Shelf shelfThree;
    Shelf shelfFour;
    Shelf shelfFive;
    Shelf shelfSix;
    Shelf shelfSeven;
    List<Card> cards;
    CommonObjective12 commonObjective12;

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
        shelfSix = new Shelf();
        shelfSeven = new Shelf();
        cards = new ArrayList<>();
        commonObjective12 = new CommonObjective12(4);
    }

    @Test
    void CommonObjective12() {
        cards.add(new Card(Card.Type.CAT,1));
        cards.add(new Card(Card.Type.CAT,0));
        cards.add(new Card(Card.Type.CAT,2));
        cards.add(new Card(Card.Type.FRAME,0));
        cards.add(new Card(Card.Type.BOOK,0));
        shelfOne.insert(cards,0);
        cards.clear();
        cards.add(new Card(Card.Type.TROPHY,2));
        cards.add(new Card(Card.Type.PLANT,2));
        cards.add(new Card(Card.Type.CAT,3));
        cards.add(new Card(Card.Type.PLANT,1));
        shelfOne.insert(cards,1);
        cards.clear();
        cards.add(new Card(Card.Type.GAME,5));
        cards.add(new Card(Card.Type.CAT,4));
        cards.add(new Card(Card.Type.TROPHY,3));
        shelfOne.insert(cards,2);
        cards.clear();
        cards.add(new Card(Card.Type.GAME,6));
        cards.add(new Card(Card.Type.FRAME,7));
        shelfOne.insert(cards,3);
        cards.clear();
        cards.add(new Card(Card.Type.CAT,10));
        shelfOne.insert(cards,4);
        cards.clear();
        assert (commonObjective12.verify(shelfOne));

        cards.add(new Card(Card.Type.GAME,0));
        shelfTwo.insert(cards,0);
        cards.clear();
        cards.add(new Card(Card.Type.PLANT,0));
        cards.add(new Card(Card.Type.CAT,0));
        shelfTwo.insert(cards,1);
        cards.clear();
        cards.add(new Card(Card.Type.FRAME,0));
        cards.add(new Card(Card.Type.PLANT,1));
        cards.add(new Card(Card.Type.BOOK,0));
        shelfTwo.insert(cards,2);
        cards.clear();
        cards.add(new Card(Card.Type.GAME,5));
        cards.add(new Card(Card.Type.CAT,1));
        cards.add(new Card(Card.Type.PLANT,2));
        cards.add(new Card(Card.Type.BOOK,2));
        shelfTwo.insert(cards,3);
        cards.clear();
        cards.add(new Card(Card.Type.TROPHY,10));
        cards.add(new Card(Card.Type.PLANT,5));
        cards.add(new Card(Card.Type.GAME,6));
        cards.add(new Card(Card.Type.CAT,2));
        cards.add(new Card(Card.Type.PLANT,3));
        shelfTwo.insert(cards,4);
        cards.clear();
        assert (commonObjective12.verify(shelfTwo));

        cards.add(new Card(Card.Type.GAME,0));
        shelfThree.insert(cards,0);
        cards.clear();
        cards.add(new Card(Card.Type.PLANT,0));
        cards.add(new Card(Card.Type.CAT,0));
        shelfThree.insert(cards,1);
        cards.clear();
        cards.add(new Card(Card.Type.FRAME,0));
        cards.add(new Card(Card.Type.PLANT,1));
        cards.add(new Card(Card.Type.BOOK,0));
        shelfThree.insert(cards,2);
        cards.clear();
        cards.add(new Card(Card.Type.GAME,5));
        cards.add(new Card(Card.Type.CAT,1));
        cards.add(new Card(Card.Type.PLANT,2));
        cards.add(new Card(Card.Type.BOOK,2));
        shelfThree.insert(cards,3);
        cards.clear();
        cards.add(new Card(Card.Type.TROPHY,10));
        cards.add(new Card(Card.Type.PLANT,5));
        cards.add(new Card(Card.Type.GAME,6));
        cards.add(new Card(Card.Type.CAT,2));
        shelfThree.insert(cards,4);
        cards.clear();
        assert(!commonObjective12.verify(shelfThree));

        cards.add(new Card(Card.Type.GAME,0));
        shelfFour.insert(cards,0);
        cards.clear();
        cards.add(new Card(Card.Type.PLANT,0));
        cards.add(new Card(Card.Type.CAT,0));
        shelfFour.insert(cards,1);
        cards.clear();
        cards.add(new Card(Card.Type.FRAME,0));
        cards.add(new Card(Card.Type.PLANT,1));
        cards.add(new Card(Card.Type.BOOK,0));
        shelfFour.insert(cards,2);
        cards.clear();
        cards.add(new Card(Card.Type.GAME,5));
        cards.add(new Card(Card.Type.CAT,1));
        shelfFour.insert(cards,3);
        cards.clear();
        cards.add(new Card(Card.Type.TROPHY,10));
        cards.add(new Card(Card.Type.PLANT,5));
        cards.add(new Card(Card.Type.GAME,6));
        cards.add(new Card(Card.Type.CAT,2));
        cards.add(new Card(Card.Type.PLANT,3));
        shelfFour.insert(cards,4);
        cards.clear();
        assert (!commonObjective12.verify(shelfFour));

        cards.add(new Card(Card.Type.CAT,1));
        cards.add(new Card(Card.Type.CAT,0));
        cards.add(new Card(Card.Type.CAT,2));
        cards.add(new Card(Card.Type.FRAME,0));
        cards.add(new Card(Card.Type.BOOK,0));
        shelfFive.insert(cards,0);
        cards.clear();
        cards.add(new Card(Card.Type.TROPHY,2));
        cards.add(new Card(Card.Type.PLANT,2));
        cards.add(new Card(Card.Type.CAT,3));
        cards.add(new Card(Card.Type.PLANT,4));
        shelfFive.insert(cards,1);
        cards.clear();
        cards.add(new Card(Card.Type.GAME,5));
        cards.add(new Card(Card.Type.CAT,4));
        cards.add(new Card(Card.Type.TROPHY,3));
        shelfFive.insert(cards,2);
        cards.clear();
        cards.add(new Card(Card.Type.GAME,6));
        cards.add(new Card(Card.Type.FRAME,7));
        shelfFive.insert(cards,3);
        cards.clear();
        assert (!commonObjective12.verify(shelfFive));

        cards.add(new Card(Card.Type.GAME,1));
        cards.add(new Card(Card.Type.FRAME,0));
        cards.add(new Card(Card.Type.PLANT,0));
        cards.add(new Card(Card.Type.GAME,0));
        cards.add(new Card(Card.Type.CAT,0));
        shelfSix.insert(cards,0);
        cards.clear();
        cards.add(new Card(Card.Type.GAME,2));
        cards.add(new Card(Card.Type.TROPHY,2));
        cards.add(new Card(Card.Type.BOOK,1));
        cards.add(new Card(Card.Type.TROPHY,1));
        shelfSix.insert(cards,1);
        cards.clear();
        cards.add(new Card(Card.Type.BOOK,5));
        cards.add(new Card(Card.Type.TROPHY,4));
        cards.add(new Card(Card.Type.CAT,3));
        shelfSix.insert(cards,2);
        cards.clear();
        cards.add(new Card(Card.Type.FRAME,5));
        cards.add(new Card(Card.Type.BOOK,6));
        cards.add(new Card(Card.Type.TROPHY,5));
        cards.add(new Card(Card.Type.GAME,4));
        shelfSix.insert(cards,3);
        cards.clear();
        cards.add(new Card(Card.Type.FRAME,10));
        cards.add(new Card(Card.Type.TROPHY,8));
        cards.add(new Card(Card.Type.CAT,6));
        cards.add(new Card(Card.Type.PLANT,2));
        cards.add(new Card(Card.Type.FRAME,7));
        shelfSix.insert(cards,4);
        cards.clear();
        assert (!commonObjective12.verify(shelfSix));

        cards.add(new Card(Card.Type.CAT, 0));
        shelfSeven.insert(cards, 4);
        cards.add(new Card(Card.Type.CAT, 0));
        shelfSeven.insert(cards, 3);
        cards.add(new Card(Card.Type.CAT, 0));
        shelfSeven.insert(cards, 2);
        cards.add(new Card(Card.Type.CAT, 0));
        shelfSeven.insert(cards, 1);
        cards.add(new Card(Card.Type.CAT, 0));
        shelfSeven.insert(cards, 0);
        cards.clear();
        assert (commonObjective12.verify(shelfSeven));
        cards.add(new Card(Card.Type.CAT, 0));
        shelfSeven.insert(cards, 0);
        cards.clear();
        assert (!commonObjective12.verify(shelfSeven));
    }
}