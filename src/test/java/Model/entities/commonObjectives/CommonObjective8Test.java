package Model.entities.commonObjectives;

import Model.entities.Card;
import Model.entities.Shelf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Config;

import java.util.ArrayList;
import java.util.List;

class CommonObjective8Test {

    Shelf shelfOne;
    Shelf shelfTwo;
    Shelf shelfThree;
    List<Card> cards;
    CommonObjective8 commonObjective8;

    /**
     * Construct various shelves and an array of cards
     */
    @BeforeEach
    void setup(){
        Config.initialise(2);
        shelfOne = new Shelf();
        shelfTwo = new Shelf();
        shelfThree = new Shelf();
        cards = new ArrayList<>();
        commonObjective8 = new CommonObjective8(4);
    }

    @Test
    void CommonObjective8() {
        cards.add(new Card(Card.Type.CAT,2));
        cards.add(new Card(Card.Type.CAT,1));
        cards.add(new Card(Card.Type.BOOK,1));
        cards.add(new Card(Card.Type.BOOK,0));
        cards.add(new Card(Card.Type.CAT,0));
        cards.add(new Card(Card.Type.FRAME,0));
        shelfOne.insert(cards,0);
        cards.clear();
        cards.add(new Card(Card.Type.CAT,3));
        cards.add(new Card(Card.Type.FRAME,1));
        cards.add(new Card(Card.Type.TROPHY,0));
        cards.add(new Card(Card.Type.BOOK,2));
        cards.add(new Card(Card.Type.FRAME,2));
        cards.add(new Card(Card.Type.FRAME,3));
        shelfOne.insert(cards,1);
        cards.clear();
        cards.add(new Card(Card.Type.CAT,4));
        cards.add(new Card(Card.Type.TROPHY,3));
        cards.add(new Card(Card.Type.PLANT,0));
        cards.add(new Card(Card.Type.FRAME,5));
        cards.add(new Card(Card.Type.TROPHY,2));
        cards.add(new Card(Card.Type.FRAME,4));
        shelfOne.insert(cards,2);
        cards.clear();
        cards.add(new Card(Card.Type.CAT,5));
        cards.add(new Card(Card.Type.PLANT,4));
        cards.add(new Card(Card.Type.GAME,5));
        cards.add(new Card(Card.Type.TROPHY,5));
        cards.add(new Card(Card.Type.PLANT,2));
        cards.add(new Card(Card.Type.PLANT,3));
        shelfOne.insert(cards,3);
        cards.clear();
        cards.add(new Card(Card.Type.FRAME,7));
        cards.add(new Card(Card.Type.TROPHY,6));
        cards.add(new Card(Card.Type.CAT,6));
        cards.add(new Card(Card.Type.PLANT,6));
        cards.add(new Card(Card.Type.BOOK,5));
        cards.add(new Card(Card.Type.FRAME,6));
        shelfOne.insert(cards,4);
        cards.clear();
        assert (commonObjective8.verify(shelfOne));

        cards.add(new Card(Card.Type.CAT,2));
        cards.add(new Card(Card.Type.CAT,1));
        cards.add(new Card(Card.Type.BOOK,1));
        cards.add(new Card(Card.Type.BOOK,0));
        cards.add(new Card(Card.Type.CAT,0));
        cards.add(new Card(Card.Type.FRAME,0));
        shelfTwo.insert(cards,0);
        cards.clear();
        cards.add(new Card(Card.Type.CAT,3));
        cards.add(new Card(Card.Type.FRAME,1));
        cards.add(new Card(Card.Type.TROPHY,0));
        cards.add(new Card(Card.Type.BOOK,2));
        cards.add(new Card(Card.Type.FRAME,2));
        cards.add(new Card(Card.Type.FRAME,3));
        shelfTwo.insert(cards,1);
        cards.clear();
        cards.add(new Card(Card.Type.CAT,4));
        cards.add(new Card(Card.Type.TROPHY,3));
        cards.add(new Card(Card.Type.PLANT,0));
        cards.add(new Card(Card.Type.FRAME,5));
        cards.add(new Card(Card.Type.TROPHY,2));
        cards.add(new Card(Card.Type.FRAME,4));
        shelfTwo.insert(cards,2);
        cards.clear();
        cards.add(new Card(Card.Type.CAT,5));
        cards.add(new Card(Card.Type.PLANT,4));
        cards.add(new Card(Card.Type.GAME,5));
        cards.add(new Card(Card.Type.TROPHY,5));
        cards.add(new Card(Card.Type.PLANT,2));
        cards.add(new Card(Card.Type.PLANT,3));
        shelfTwo.insert(cards,3);
        cards.clear();
        cards.add(new Card(Card.Type.FRAME,7));
        cards.add(new Card(Card.Type.TROPHY,6));
        cards.add(new Card(Card.Type.CAT,6));
        cards.add(new Card(Card.Type.PLANT,6));
        cards.add(new Card(Card.Type.TROPHY,8));
        cards.add(new Card(Card.Type.FRAME,6));
        shelfTwo.insert(cards,4);
        cards.clear();
        assert (!commonObjective8.verify(shelfTwo));

        cards.add(new Card(Card.Type.CAT,2));
        cards.add(new Card(Card.Type.CAT,1));
        cards.add(new Card(Card.Type.BOOK,1));
        cards.add(new Card(Card.Type.BOOK,0));
        cards.add(new Card(Card.Type.CAT,0));
        cards.add(new Card(Card.Type.FRAME,0));
        shelfThree.insert(cards,0);
        cards.clear();
        cards.add(new Card(Card.Type.CAT,3));
        cards.add(new Card(Card.Type.FRAME,1));
        cards.add(new Card(Card.Type.TROPHY,0));
        cards.add(new Card(Card.Type.GAME,20));
        cards.add(new Card(Card.Type.TROPHY,20));
        cards.add(new Card(Card.Type.FRAME,3));
        shelfThree.insert(cards,1);
        cards.clear();
        cards.add(new Card(Card.Type.CAT,4));
        cards.add(new Card(Card.Type.TROPHY,3));
        cards.add(new Card(Card.Type.PLANT,0));
        cards.add(new Card(Card.Type.FRAME,5));
        cards.add(new Card(Card.Type.TROPHY,2));
        cards.add(new Card(Card.Type.FRAME,4));
        shelfThree.insert(cards,2);
        cards.clear();
        cards.add(new Card(Card.Type.CAT,5));
        cards.add(new Card(Card.Type.PLANT,4));
        cards.add(new Card(Card.Type.GAME,5));
        cards.add(new Card(Card.Type.TROPHY,5));
        cards.add(new Card(Card.Type.PLANT,2));
        cards.add(new Card(Card.Type.PLANT,3));
        shelfThree.insert(cards,3);
        cards.clear();
        cards.add(new Card(Card.Type.FRAME,7));
        cards.add(new Card(Card.Type.BOOK,6));
        cards.add(new Card(Card.Type.CAT,6));
        cards.add(new Card(Card.Type.PLANT,6));
        cards.add(new Card(Card.Type.BOOK,5));
        cards.add(new Card(Card.Type.FRAME,6));
        shelfThree.insert(cards,4);
        cards.clear();
        assert (commonObjective8.verify(shelfThree));
    }
}