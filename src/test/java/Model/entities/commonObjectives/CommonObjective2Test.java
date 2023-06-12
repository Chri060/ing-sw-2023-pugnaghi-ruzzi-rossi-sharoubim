package Model.entities.commonObjectives;

import Model.entities.Card;
import Model.entities.Shelf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Config;

import java.util.ArrayList;
import java.util.List;

class CommonObjective2Test {

    Shelf shelfOne;
    Shelf shelfTwo;
    Shelf shelfThree;
    Shelf shelfFour;
    List<Card> cards;
    CommonObjective2 commonObjective2;

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
        cards = new ArrayList<>();
        commonObjective2 = new CommonObjective2(4);
    }

    @Test
    void CommonObjective2() {
        cards.add(new Card(Card.Type.FRAME,1));
        cards.add(new Card(Card.Type.GAME,1));
        cards.add(new Card(Card.Type.FRAME,0));
        cards.add(new Card(Card.Type.BOOK,1));
        cards.add(new Card(Card.Type.BOOK,0));
        cards.add(new Card(Card.Type.GAME,0));
        shelfOne.insert(cards,0);
        cards.clear();
        cards.add(new Card(Card.Type.TROPHY,0));
        cards.add(new Card(Card.Type.BOOK,2));
        cards.add(new Card(Card.Type.CAT,0));
        cards.add(new Card(Card.Type.FRAME,2));
        cards.add(new Card(Card.Type.GAME,3));
        cards.add(new Card(Card.Type.GAME,2));
        shelfOne.insert(cards,1);
        cards.clear();
        cards.add(new Card(Card.Type.TROPHY,1));
        cards.add(new Card(Card.Type.PLANT,0));
        cards.add(new Card(Card.Type.CAT,1));
        cards.add(new Card(Card.Type.GAME,4));
        cards.add(new Card(Card.Type.FRAME,3));
        cards.add(new Card(Card.Type.BOOK,3));
        shelfOne.insert(cards,2);
        cards.clear();
        cards.add(new Card(Card.Type.TROPHY,2));
        cards.add(new Card(Card.Type.PLANT,1));
        cards.add(new Card(Card.Type.GAME,5));
        cards.add(new Card(Card.Type.PLANT,2));
        cards.add(new Card(Card.Type.TROPHY,2));
        cards.add(new Card(Card.Type.PLANT,1));
        shelfOne.insert(cards,3);
        cards.clear();
        cards.add(new Card(Card.Type.GAME,8));
        cards.add(new Card(Card.Type.GAME,7));
        cards.add(new Card(Card.Type.GAME,6));
        cards.add(new Card(Card.Type.PLANT,3));
        cards.add(new Card(Card.Type.BOOK,4));
        cards.add(new Card(Card.Type.TROPHY,3));
        shelfOne.insert(cards,4);
        cards.clear();
        assert (commonObjective2.verify(shelfOne));

        cards.add(new Card(Card.Type.BOOK,2));
        cards.add(new Card(Card.Type.GAME,1));
        cards.add(new Card(Card.Type.FRAME,0));
        cards.add(new Card(Card.Type.BOOK,1));
        cards.add(new Card(Card.Type.BOOK,0));
        cards.add(new Card(Card.Type.GAME,0));
        shelfTwo.insert(cards,0);
        cards.clear();
        cards.add(new Card(Card.Type.TROPHY,0));
        cards.add(new Card(Card.Type.BOOK,3));
        cards.add(new Card(Card.Type.CAT,0));
        cards.add(new Card(Card.Type.FRAME,2));
        cards.add(new Card(Card.Type.GAME,3));
        cards.add(new Card(Card.Type.GAME,2));
        shelfTwo.insert(cards,1);
        cards.clear();
        cards.add(new Card(Card.Type.TROPHY,1));
        cards.add(new Card(Card.Type.PLANT,0));
        cards.add(new Card(Card.Type.BOOK,4));
        cards.add(new Card(Card.Type.GAME,4));
        cards.add(new Card(Card.Type.FRAME,3));
        cards.add(new Card(Card.Type.BOOK,4));
        shelfTwo.insert(cards,2);
        cards.clear();
        cards.add(new Card(Card.Type.TROPHY,2));
        cards.add(new Card(Card.Type.PLANT,1));
        cards.add(new Card(Card.Type.CAT,5));
        cards.add(new Card(Card.Type.BOOK,5));
        cards.add(new Card(Card.Type.TROPHY,2));
        cards.add(new Card(Card.Type.PLANT,1));
        shelfTwo.insert(cards,3);
        cards.clear();
        cards.add(new Card(Card.Type.GAME,8));
        cards.add(new Card(Card.Type.CAT,7));
        cards.add(new Card(Card.Type.GAME,6));
        cards.add(new Card(Card.Type.PLANT,3));
        cards.add(new Card(Card.Type.BOOK,6));
        cards.add(new Card(Card.Type.TROPHY,3));
        shelfTwo.insert(cards,4);
        cards.clear();
        assert (commonObjective2.verify(shelfTwo));

        cards.add(new Card(Card.Type.GAME,2));
        cards.add(new Card(Card.Type.PLANT,1));
        cards.add(new Card(Card.Type.GAME,0));
        cards.add(new Card(Card.Type.FRAME,0));
        cards.add(new Card(Card.Type.BOOK,0));
        cards.add(new Card(Card.Type.PLANT,0));
        shelfThree.insert(cards,0);
        cards.clear();
        cards.add(new Card(Card.Type.CAT,0));
        cards.add(new Card(Card.Type.PLANT,3));
        cards.add(new Card(Card.Type.PLANT,4));
        cards.add(new Card(Card.Type.BOOK,1));
        cards.add(new Card(Card.Type.PLANT,2));
        cards.add(new Card(Card.Type.GAME,3));
        shelfThree.insert(cards,1);
        cards.clear();
        cards.add(new Card(Card.Type.CAT,1));
        cards.add(new Card(Card.Type.FRAME,1));
        cards.add(new Card(Card.Type.BOOK,2));
        cards.add(new Card(Card.Type.PLANT,5));
        cards.add(new Card(Card.Type.TROPHY,1));
        cards.add(new Card(Card.Type.TROPHY,0));
        shelfThree.insert(cards,2);
        cards.clear();
        cards.add(new Card(Card.Type.BOOK,4));
        cards.add(new Card(Card.Type.BOOK,3));
        cards.add(new Card(Card.Type.PLANT,8));
        cards.add(new Card(Card.Type.PLANT,7));
        cards.add(new Card(Card.Type.PLANT,6));
        cards.add(new Card(Card.Type.TROPHY,1));
        shelfThree.insert(cards,3);
        cards.clear();
        cards.add(new Card(Card.Type.BOOK,8));
        cards.add(new Card(Card.Type.GAME,7));
        cards.add(new Card(Card.Type.FRAME,2));
        cards.add(new Card(Card.Type.GAME,4));
        cards.add(new Card(Card.Type.TROPHY,2));
        cards.add(new Card(Card.Type.PLANT,9));
        shelfThree.insert(cards,4);
        cards.clear();
        assert (commonObjective2.verify(shelfThree));

        cards.add(new Card(Card.Type.GAME,2));
        cards.add(new Card(Card.Type.GAME,10));
        cards.add(new Card(Card.Type.GAME,0));
        cards.add(new Card(Card.Type.FRAME,0));
        cards.add(new Card(Card.Type.BOOK,0));
        cards.add(new Card(Card.Type.PLANT,0));
        shelfFour.insert(cards,0);
        cards.clear();
        cards.add(new Card(Card.Type.CAT,0));
        cards.add(new Card(Card.Type.PLANT,3));
        cards.add(new Card(Card.Type.PLANT,4));
        cards.add(new Card(Card.Type.BOOK,1));
        cards.add(new Card(Card.Type.PLANT,2));
        cards.add(new Card(Card.Type.GAME,3));
        shelfFour.insert(cards,1);
        cards.clear();
        cards.add(new Card(Card.Type.CAT,1));
        cards.add(new Card(Card.Type.FRAME,1));
        cards.add(new Card(Card.Type.BOOK,2));
        cards.add(new Card(Card.Type.PLANT,5));
        cards.add(new Card(Card.Type.TROPHY,1));
        cards.add(new Card(Card.Type.TROPHY,0));
        shelfFour.insert(cards,2);
        cards.clear();
        cards.add(new Card(Card.Type.BOOK,4));
        cards.add(new Card(Card.Type.BOOK,3));
        cards.add(new Card(Card.Type.PLANT,8));
        cards.add(new Card(Card.Type.PLANT,7));
        cards.add(new Card(Card.Type.PLANT,6));
        cards.add(new Card(Card.Type.TROPHY,1));
        shelfFour.insert(cards,3);
        cards.clear();
        cards.add(new Card(Card.Type.GAME,8));
        cards.add(new Card(Card.Type.GAME,7));
        cards.add(new Card(Card.Type.FRAME,2));
        cards.add(new Card(Card.Type.GAME,4));
        cards.add(new Card(Card.Type.TROPHY,2));
        cards.add(new Card(Card.Type.PLANT,9));
        shelfFour.insert(cards,4);
        assert (!commonObjective2.verify(shelfFour));
    }
}