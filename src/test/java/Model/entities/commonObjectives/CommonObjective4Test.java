package Model.entities.commonObjectives;

import Model.entities.Card;
import Model.entities.Shelf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Config;

import java.util.ArrayList;
import java.util.List;


class CommonObjective4Test {

    Shelf shelfOne;
    Shelf shelfTwo;
    Shelf shelfThree;
    Shelf shelfFour;
    List<Card> cards;
    CommonObjective4 commonObjective4;

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
        commonObjective4 = new CommonObjective4(4);
    }

    @Test
    void CommonObjective4() {
        cards.add(new Card(Card.Type.BOOK,0));
        cards.add(new Card(Card.Type.FRAME,0));
        cards.add(new Card(Card.Type.FRAME,2));
        cards.add(new Card(Card.Type.FRAME,1));
        cards.add(new Card(Card.Type.CAT,0));
        cards.add(new Card(Card.Type.CAT,1));
        shelfOne.insert(cards,0);
        cards.clear();
        cards.add(new Card(Card.Type.BOOK,2));
        cards.add(new Card(Card.Type.BOOK,3));
        cards.add(new Card(Card.Type.BOOK,4));
        cards.add(new Card(Card.Type.FRAME,3));
        cards.add(new Card(Card.Type.CAT,2));
        cards.add(new Card(Card.Type.GAME,2));
        shelfOne.insert(cards,1);
        cards.clear();
        cards.add(new Card(Card.Type.BOOK,5));
        cards.add(new Card(Card.Type.TROPHY,4));
        cards.add(new Card(Card.Type.TROPHY,5));
        cards.add(new Card(Card.Type.CAT,3));
        cards.add(new Card(Card.Type.CAT,4));
        cards.add(new Card(Card.Type.FRAME,5));
        shelfOne.insert(cards,2);
        cards.clear();
        cards.add(new Card(Card.Type.BOOK,6));
        cards.add(new Card(Card.Type.TROPHY,7));
        cards.add(new Card(Card.Type.PLANT,8));
        cards.add(new Card(Card.Type.BOOK,7));
        cards.add(new Card(Card.Type.CAT,6));
        cards.add(new Card(Card.Type.PLANT,7));
        shelfOne.insert(cards,3);
        cards.clear();
        cards.add(new Card(Card.Type.TROPHY,9));
        cards.add(new Card(Card.Type.TROPHY,8));
        cards.add(new Card(Card.Type.CAT,8));
        cards.add(new Card(Card.Type.CAT,9));
        cards.add(new Card(Card.Type.CAT,10));
        cards.add(new Card(Card.Type.BOOK,12));
        shelfOne.insert(cards,4);
        cards.clear();
        assert (commonObjective4.verify(shelfOne));

        cards.add(new Card(Card.Type.BOOK,0));
        cards.add(new Card(Card.Type.FRAME,0));
        cards.add(new Card(Card.Type.FRAME,2));
        cards.add(new Card(Card.Type.FRAME,1));
        cards.add(new Card(Card.Type.CAT,0));
        cards.add(new Card(Card.Type.CAT,1));
        shelfTwo.insert(cards,0);
        cards.clear();
        cards.add(new Card(Card.Type.BOOK,2));
        cards.add(new Card(Card.Type.FRAME,3));
        cards.add(new Card(Card.Type.BOOK,4));
        cards.add(new Card(Card.Type.FRAME,4));
        cards.add(new Card(Card.Type.CAT,2));
        cards.add(new Card(Card.Type.GAME,2));
        shelfTwo.insert(cards,1);
        cards.clear();
        cards.add(new Card(Card.Type.BOOK,5));
        cards.add(new Card(Card.Type.FRAME,15));
        cards.add(new Card(Card.Type.TROPHY,5));
        cards.add(new Card(Card.Type.CAT,3));
        cards.add(new Card(Card.Type.CAT,4));
        cards.add(new Card(Card.Type.FRAME,5));
        shelfTwo.insert(cards,2);
        cards.clear();
        cards.add(new Card(Card.Type.BOOK,6));
        cards.add(new Card(Card.Type.FRAME,9));
        cards.add(new Card(Card.Type.PLANT,8));
        cards.add(new Card(Card.Type.BOOK,7));
        cards.add(new Card(Card.Type.CAT,6));
        cards.add(new Card(Card.Type.PLANT,7));
        shelfTwo.insert(cards,3);
        cards.clear();
        cards.add(new Card(Card.Type.TROPHY,9));
        cards.add(new Card(Card.Type.FRAME,8));
        cards.add(new Card(Card.Type.CAT,8));
        cards.add(new Card(Card.Type.TROPHY,12));
        cards.add(new Card(Card.Type.CAT,10));
        cards.add(new Card(Card.Type.BOOK,12));
        shelfTwo.insert(cards,4);
        cards.clear();
        assert (!commonObjective4.verify(shelfTwo));

        cards.add(new Card(Card.Type.BOOK,0));
        cards.add(new Card(Card.Type.FRAME,0));
        cards.add(new Card(Card.Type.FRAME,2));
        cards.add(new Card(Card.Type.FRAME,1));
        cards.add(new Card(Card.Type.CAT,0));
        cards.add(new Card(Card.Type.CAT,1));
        shelfThree.insert(cards,0);
        cards.clear();
        cards.add(new Card(Card.Type.BOOK,2));
        cards.add(new Card(Card.Type.FRAME,3));
        cards.add(new Card(Card.Type.BOOK,4));
        cards.add(new Card(Card.Type.FRAME,4));
        cards.add(new Card(Card.Type.CAT,2));
        cards.add(new Card(Card.Type.GAME,2));
        shelfThree.insert(cards,1);
        cards.clear();
        cards.add(new Card(Card.Type.BOOK,5));
        cards.add(new Card(Card.Type.FRAME,15));
        cards.add(new Card(Card.Type.CAT,16));
        cards.add(new Card(Card.Type.BOOK,13));
        cards.add(new Card(Card.Type.CAT,4));
        cards.add(new Card(Card.Type.GAME,15));
        shelfThree.insert(cards,2);
        cards.clear();
        cards.add(new Card(Card.Type.BOOK,6));
        cards.add(new Card(Card.Type.FRAME,9));
        cards.add(new Card(Card.Type.CAT,11));
        cards.add(new Card(Card.Type.CAT,7));
        cards.add(new Card(Card.Type.CAT,6));
        cards.add(new Card(Card.Type.BOOK,7));
        shelfThree.insert(cards,3);
        cards.clear();
        cards.add(new Card(Card.Type.TROPHY,9));
        cards.add(new Card(Card.Type.FRAME,8));
        cards.add(new Card(Card.Type.CAT,8));
        cards.add(new Card(Card.Type.BOOK,12));
        cards.add(new Card(Card.Type.CAT,10));
        cards.add(new Card(Card.Type.GAME,  12));
        shelfThree.insert(cards,4);
        cards.clear();
        assert (commonObjective4.verify(shelfThree));

        cards.add(new Card(Card.Type.BOOK,0));
        cards.add(new Card(Card.Type.FRAME,0));
        cards.add(new Card(Card.Type.FRAME,2));
        cards.add(new Card(Card.Type.FRAME,1));
        cards.add(new Card(Card.Type.CAT,0));
        cards.add(new Card(Card.Type.CAT,1));
        shelfFour.insert(cards,0);
        cards.clear();
        cards.add(new Card(Card.Type.TROPHY,0));
        cards.add(new Card(Card.Type.FRAME,6));
        cards.add(new Card(Card.Type.FRAME,4));
        cards.add(new Card(Card.Type.FRAME,3));
        cards.add(new Card(Card.Type.FRAME,5));
        cards.add(new Card(Card.Type.GAME,2));
        shelfFour.insert(cards,1);
        cards.clear();
        cards.add(new Card(Card.Type.GAME,3));
        cards.add(new Card(Card.Type.FRAME,8));
        cards.add(new Card(Card.Type.FRAME,7));
        cards.add(new Card(Card.Type.CAT,2));
        cards.add(new Card(Card.Type.PLANT,0));
        cards.add(new Card(Card.Type.FRAME,6));
        shelfFour.insert(cards,2);
        cards.clear();
        cards.add(new Card(Card.Type.BOOK,6));
        cards.add(new Card(Card.Type.BOOK,3));
        cards.add(new Card(Card.Type.PLANT,8));
        cards.add(new Card(Card.Type.BOOK,2));
        cards.add(new Card(Card.Type.BOOK,1));
        cards.add(new Card(Card.Type.PLANT,1));
        shelfFour.insert(cards,3);
        cards.clear();
        cards.add(new Card(Card.Type.TROPHY,9));
        cards.add(new Card(Card.Type.FRAME,9));
        cards.add(new Card(Card.Type.PLANT,11));
        cards.add(new Card(Card.Type.BOOK,9));
        cards.add(new Card(Card.Type.GAME,10));
        cards.add(new Card(Card.Type.BOOK,12));
        shelfFour.insert(cards,4);
        cards.clear();
        assert (commonObjective4.verify(shelfFour));
    }
}