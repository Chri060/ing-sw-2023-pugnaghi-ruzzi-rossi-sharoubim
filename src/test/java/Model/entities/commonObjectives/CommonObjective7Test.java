package Model.entities.commonObjectives;

import Model.entities.Card;
import Model.entities.Shelf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Config;

import java.util.ArrayList;
import java.util.List;

class CommonObjective7Test {

    Shelf shelfOne;
    Shelf shelfTwo;
    Shelf shelfThree;
    Shelf shelfFour;
    List<Card> cards;
    CommonObjective7 commonObjective7;

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
        commonObjective7 = new CommonObjective7(4);
    }

    @Test
    void CommonObjective7() {
        cards.add(new Card(Card.Type.CAT,0));
        cards.add(new Card(Card.Type.CAT,1));
        cards.add(new Card(Card.Type.CAT,2));
        cards.add(new Card(Card.Type.PLANT,0));
        cards.add(new Card(Card.Type.FRAME,0));
        cards.add(new Card(Card.Type.GAME,0));
        shelfOne.insert(cards,0);
        cards.clear();
        cards.add(new Card(Card.Type.CAT,3));
        cards.add(new Card(Card.Type.CAT,4));
        cards.add(new Card(Card.Type.CAT,5));
        cards.add(new Card(Card.Type.BOOK,2));
        cards.add(new Card(Card.Type.BOOK,1));
        cards.add(new Card(Card.Type.BOOK,0));
        shelfOne.insert(cards,1);
        cards.clear();
        cards.add(new Card(Card.Type.CAT,8));
        cards.add(new Card(Card.Type.CAT,7));
        cards.add(new Card(Card.Type.CAT,6));
        cards.add(new Card(Card.Type.BOOK,5));
        cards.add(new Card(Card.Type.BOOK,4));
        cards.add(new Card(Card.Type.BOOK,3));
        shelfOne.insert(cards,2);
        cards.clear();
        cards.add(new Card(Card.Type.BOOK,8));
        cards.add(new Card(Card.Type.BOOK,7));
        cards.add(new Card(Card.Type.BOOK,6));
        cards.add(new Card(Card.Type.TROPHY,0));
        cards.add(new Card(Card.Type.FRAME,2));
        cards.add(new Card(Card.Type.GAME,3));
        shelfOne.insert(cards,3);
        cards.clear();
        cards.add(new Card(Card.Type.BOOK,12));
        cards.add(new Card(Card.Type.BOOK,10));
        cards.add(new Card(Card.Type.BOOK,9));
        cards.add(new Card(Card.Type.PLANT,5));
        cards.add(new Card(Card.Type.BOOK,11));
        cards.add(new Card(Card.Type.CAT,10));
        shelfOne.insert(cards,4);
        cards.clear();
        assert (commonObjective7.verify(shelfOne));

        cards.add(new Card(Card.Type.CAT,0));
        cards.add(new Card(Card.Type.CAT,1));
        cards.add(new Card(Card.Type.CAT,2));
        cards.add(new Card(Card.Type.PLANT,0));
        cards.add(new Card(Card.Type.FRAME,0));
        cards.add(new Card(Card.Type.GAME,0));
        shelfTwo.insert(cards,0);
        cards.clear();
        cards.add(new Card(Card.Type.CAT,3));
        cards.add(new Card(Card.Type.CAT,4));
        cards.add(new Card(Card.Type.CAT,5));
        cards.add(new Card(Card.Type.BOOK,2));
        cards.add(new Card(Card.Type.BOOK,1));
        cards.add(new Card(Card.Type.BOOK,0));
        shelfTwo.insert(cards,1);
        cards.clear();
        cards.add(new Card(Card.Type.CAT,8));
        cards.add(new Card(Card.Type.CAT,7));
        cards.add(new Card(Card.Type.CAT,6));
        cards.add(new Card(Card.Type.BOOK,5));
        cards.add(new Card(Card.Type.BOOK,4));
        cards.add(new Card(Card.Type.BOOK,3));
        shelfTwo.insert(cards,2);
        cards.clear();
        cards.add(new Card(Card.Type.CAT,18));
        cards.add(new Card(Card.Type.BOOK,7));
        cards.add(new Card(Card.Type.CAT,16));
        cards.add(new Card(Card.Type.TROPHY,0));
        cards.add(new Card(Card.Type.FRAME,2));
        cards.add(new Card(Card.Type.GAME,3));
        shelfTwo.insert(cards,3);
        cards.clear();
        cards.add(new Card(Card.Type.CAT,12));
        cards.add(new Card(Card.Type.BOOK,10));
        cards.add(new Card(Card.Type.CAT,19));
        cards.add(new Card(Card.Type.PLANT,5));
        cards.add(new Card(Card.Type.BOOK,11));
        cards.add(new Card(Card.Type.CAT,10));
        shelfTwo.insert(cards,4);
        cards.clear();
        assert (!commonObjective7.verify(shelfTwo));

        cards.add(new Card(Card.Type.CAT,0));
        cards.add(new Card(Card.Type.CAT,1));
        cards.add(new Card(Card.Type.CAT,2));
        cards.add(new Card(Card.Type.PLANT,0));
        cards.add(new Card(Card.Type.FRAME,0));
        cards.add(new Card(Card.Type.GAME,0));
        shelfThree.insert(cards,0);
        cards.clear();
        cards.add(new Card(Card.Type.CAT,3));
        cards.add(new Card(Card.Type.CAT,4));
        cards.add(new Card(Card.Type.CAT,5));
        cards.add(new Card(Card.Type.BOOK,2));
        cards.add(new Card(Card.Type.BOOK,1));
        cards.add(new Card(Card.Type.BOOK,0));
        shelfThree.insert(cards,1);
        cards.clear();
        cards.add(new Card(Card.Type.CAT,8));
        cards.add(new Card(Card.Type.CAT,7));
        cards.add(new Card(Card.Type.CAT,6));
        cards.add(new Card(Card.Type.BOOK,5));
        cards.add(new Card(Card.Type.BOOK,4));
        cards.add(new Card(Card.Type.BOOK,3));
        shelfThree.insert(cards,2);
        cards.clear();
        cards.add(new Card(Card.Type.CAT,18));
        cards.add(new Card(Card.Type.BOOK,7));
        cards.add(new Card(Card.Type.CAT,16));
        cards.add(new Card(Card.Type.TROPHY,0));
        cards.add(new Card(Card.Type.TROPHY,2));
        cards.add(new Card(Card.Type.TROPHY,3));
        shelfThree.insert(cards,3);
        cards.clear();
        cards.add(new Card(Card.Type.CAT,12));
        cards.add(new Card(Card.Type.BOOK,10));
        cards.add(new Card(Card.Type.CAT,19));
        cards.add(new Card(Card.Type.TROPHY,5));
        cards.add(new Card(Card.Type.TROPHY,11));
        cards.add(new Card(Card.Type.TROPHY,10));
        shelfThree.insert(cards,4);
        cards.clear();
        assert (!commonObjective7.verify(shelfThree));

        cards.add(new Card(Card.Type.CAT,0));
        cards.add(new Card(Card.Type.CAT,1));
        cards.add(new Card(Card.Type.CAT,2));
        cards.add(new Card(Card.Type.PLANT,0));
        cards.add(new Card(Card.Type.FRAME,0));
        cards.add(new Card(Card.Type.GAME,0));
        shelfFour.insert(cards,0);
        cards.clear();
        cards.add(new Card(Card.Type.CAT,3));
        cards.add(new Card(Card.Type.CAT,4));
        cards.add(new Card(Card.Type.BOOK,20));
        cards.add(new Card(Card.Type.BOOK,2));
        cards.add(new Card(Card.Type.BOOK,1));
        cards.add(new Card(Card.Type.BOOK,0));
        shelfFour.insert(cards,1);
        cards.clear();
        cards.add(new Card(Card.Type.CAT,8));
        cards.add(new Card(Card.Type.CAT,7));
        cards.add(new Card(Card.Type.BOOK,21));
        cards.add(new Card(Card.Type.BOOK,5));
        cards.add(new Card(Card.Type.BOOK,4));
        cards.add(new Card(Card.Type.BOOK,3));
        shelfFour.insert(cards,2);
        cards.clear();
        cards.add(new Card(Card.Type.CAT,18));
        cards.add(new Card(Card.Type.BOOK,7));
        cards.add(new Card(Card.Type.CAT,16));
        cards.add(new Card(Card.Type.TROPHY,0));
        cards.add(new Card(Card.Type.TROPHY,2));
        cards.add(new Card(Card.Type.TROPHY,3));
        shelfFour.insert(cards,3);
        cards.clear();
        cards.add(new Card(Card.Type.CAT,12));
        cards.add(new Card(Card.Type.BOOK,10));
        cards.add(new Card(Card.Type.CAT,19));
        cards.add(new Card(Card.Type.TROPHY,5));
        cards.add(new Card(Card.Type.TROPHY,11));
        cards.add(new Card(Card.Type.TROPHY,10));
        shelfFour.insert(cards,4);
        cards.clear();
        assert (commonObjective7.verify(shelfFour));
    }
}