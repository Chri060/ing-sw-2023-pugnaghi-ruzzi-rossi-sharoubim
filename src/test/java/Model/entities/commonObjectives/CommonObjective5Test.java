package Model.entities.commonObjectives;

import Model.entities.Card;
import Model.entities.Shelf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Config;

import java.util.ArrayList;
import java.util.List;

class CommonObjective5Test {

    Shelf shelfOne;
    Shelf shelfTwo;
    Shelf shelfThree;
    Shelf shelfFour;
    List<Card> cards;
    CommonObjective5 commonObjective5;

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
        commonObjective5 = new CommonObjective5(4);
    }

    @Test
    void CommonObjective5() {
        cards.add(new Card(Card.Type.TROPHY,2));
        cards.add(new Card(Card.Type.TROPHY,0));
        cards.add(new Card(Card.Type.TROPHY,1));
        cards.add(new Card(Card.Type.PLANT,0));
        cards.add(new Card(Card.Type.BOOK,0));
        cards.add(new Card(Card.Type.BOOK,1));
        shelfOne.insert(cards,0);
        cards.clear();
        cards.add(new Card(Card.Type.CAT,0));
        cards.add(new Card(Card.Type.FRAME,0));
        cards.add(new Card(Card.Type.BOOK,4));
        cards.add(new Card(Card.Type.BOOK,5));
        cards.add(new Card(Card.Type.BOOK,3));
        cards.add(new Card(Card.Type.BOOK,2));
        shelfOne.insert(cards,1);
        cards.clear();
        cards.add(new Card(Card.Type.PLANT,4));
        cards.add(new Card(Card.Type.PLANT,3));
        cards.add(new Card(Card.Type.PLANT,2));
        cards.add(new Card(Card.Type.PLANT,1));
        cards.add(new Card(Card.Type.CAT,1));
        cards.add(new Card(Card.Type.GAME,0));
        shelfOne.insert(cards,2);
        cards.clear();
        cards.add(new Card(Card.Type.PLANT,6));
        cards.add(new Card(Card.Type.PLANT,5));
        cards.add(new Card(Card.Type.CAT,3));
        cards.add(new Card(Card.Type.CAT,2));
        cards.add(new Card(Card.Type.FRAME,1));
        cards.add(new Card(Card.Type.GAME,1));
        shelfOne.insert(cards,3);
        cards.clear();
        cards.add(new Card(Card.Type.PLANT,7));
        cards.add(new Card(Card.Type.FRAME,2));
        cards.add(new Card(Card.Type.CAT,5));
        cards.add(new Card(Card.Type.CAT,4));
        cards.add(new Card(Card.Type.GAME,3));
        cards.add(new Card(Card.Type.GAME,2));
        shelfOne.insert(cards,4);
        cards.clear();
        assert (commonObjective5.verify(shelfOne));

        cards.add(new Card(Card.Type.TROPHY,2));
        cards.add(new Card(Card.Type.TROPHY,0));
        cards.add(new Card(Card.Type.TROPHY,1));
        cards.add(new Card(Card.Type.BOOK,10));
        cards.add(new Card(Card.Type.BOOK,0));
        cards.add(new Card(Card.Type.BOOK,1));
        shelfTwo.insert(cards,0);
        cards.clear();
        cards.add(new Card(Card.Type.TROPHY,10));
        cards.add(new Card(Card.Type.FRAME,0));
        cards.add(new Card(Card.Type.BOOK,4));
        cards.add(new Card(Card.Type.BOOK,5));
        cards.add(new Card(Card.Type.GAME,13));
        cards.add(new Card(Card.Type.BOOK,2));
        shelfTwo.insert(cards,1);
        cards.clear();
        cards.add(new Card(Card.Type.TROPHY,11));
        cards.add(new Card(Card.Type.PLANT,3));
        cards.add(new Card(Card.Type.CAT,7));
        cards.add(new Card(Card.Type.PLANT,1));
        cards.add(new Card(Card.Type.CAT,1));
        cards.add(new Card(Card.Type.GAME,0));
        shelfTwo.insert(cards,2);
        cards.clear();
        cards.add(new Card(Card.Type.PLANT,6));
        cards.add(new Card(Card.Type.CAT,8));
        cards.add(new Card(Card.Type.BOOK,7));
        cards.add(new Card(Card.Type.BOOK,6));
        cards.add(new Card(Card.Type.FRAME,1));
        cards.add(new Card(Card.Type.GAME,1));
        shelfTwo.insert(cards,3);
        cards.clear();
        cards.add(new Card(Card.Type.PLANT,9));
        cards.add(new Card(Card.Type.PLANT,8));
        cards.add(new Card(Card.Type.PLANT,7));
        cards.add(new Card(Card.Type.CAT,4));
        cards.add(new Card(Card.Type.GAME,3));
        cards.add(new Card(Card.Type.GAME,2));
        shelfTwo.insert(cards,4);
        cards.clear();
        assert (commonObjective5.verify(shelfTwo));

        cards.add(new Card(Card.Type.PLANT,1));
        cards.add(new Card(Card.Type.PLANT,0));
        cards.add(new Card(Card.Type.GAME,0));
        cards.add(new Card(Card.Type.CAT,0));
        cards.add(new Card(Card.Type.TROPHY,0));
        cards.add(new Card(Card.Type.TROPHY,1));
        shelfThree.insert(cards,0);
        cards.clear();
        cards.add(new Card(Card.Type.PLANT,3));
        cards.add(new Card(Card.Type.PLANT,2));
        cards.add(new Card(Card.Type.TROPHY,4));
        cards.add(new Card(Card.Type.FRAME,0));
        cards.add(new Card(Card.Type.TROPHY,3));
        cards.add(new Card(Card.Type.TROPHY,2));
        shelfThree.insert(cards,1);
        cards.clear();
        cards.add(new Card(Card.Type.CAT,3));
        cards.add(new Card(Card.Type.CAT,2));
        cards.add(new Card(Card.Type.CAT,1));
        cards.add(new Card(Card.Type.PLANT,4));
        cards.add(new Card(Card.Type.GAME,2));
        cards.add(new Card(Card.Type.GAME,1));
        shelfThree.insert(cards,2);
        cards.clear();
        cards.add(new Card(Card.Type.TROPHY,6));
        cards.add(new Card(Card.Type.CAT,8));
        cards.add(new Card(Card.Type.CAT,7));
        cards.add(new Card(Card.Type.FRAME,4));
        cards.add(new Card(Card.Type.FRAME,1));
        cards.add(new Card(Card.Type.CAT,4));
        shelfThree.insert(cards,3);
        cards.clear();
        cards.add(new Card(Card.Type.TROPHY,9));
        cards.add(new Card(Card.Type.FRAME,8));
        cards.add(new Card(Card.Type.PLANT,7));
        cards.add(new Card(Card.Type.FRAME,6));
        cards.add(new Card(Card.Type.FRAME,5));
        cards.add(new Card(Card.Type.TROPHY,7));
        shelfThree.insert(cards,4);
        cards.clear();
        assert (commonObjective5.verify(shelfThree));

        cards.add(new Card(Card.Type.PLANT,0));
        cards.add(new Card(Card.Type.FRAME,4));
        cards.add(new Card(Card.Type.FRAME,3));
        cards.add(new Card(Card.Type.FRAME,2));
        cards.add(new Card(Card.Type.FRAME,0));
        cards.add(new Card(Card.Type.FRAME,1));
        shelfFour.insert(cards,0);
        cards.clear();
        cards.add(new Card(Card.Type.FRAME,6));
        cards.add(new Card(Card.Type.FRAME,5));
        cards.add(new Card(Card.Type.CAT,0));
        cards.add(new Card(Card.Type.BOOK,0));
        cards.add(new Card(Card.Type.PLANT,1));
        cards.add(new Card(Card.Type.GAME,0));
        shelfFour.insert(cards,1);
        cards.clear();
        cards.add(new Card(Card.Type.FRAME,8));
        cards.add(new Card(Card.Type.FRAME,7));
        cards.add(new Card(Card.Type.CAT,2));
        cards.add(new Card(Card.Type.BOOK,1));
        cards.add(new Card(Card.Type.CAT,1));
        cards.add(new Card(Card.Type.PLANT,2));
        shelfFour.insert(cards,2);
        cards.clear();
        cards.add(new Card(Card.Type.FRAME,10));
        cards.add(new Card(Card.Type.FRAME,9));
        cards.add(new Card(Card.Type.CAT,7));
        cards.add(new Card(Card.Type.BOOK,6));
        cards.add(new Card(Card.Type.GAME,1));
        cards.add(new Card(Card.Type.TROPHY,1));
        shelfFour.insert(cards,3);
        cards.clear();
        cards.add(new Card(Card.Type.PLANT,9));
        cards.add(new Card(Card.Type.CAT,10));
        cards.add(new Card(Card.Type.CAT,9));
        cards.add(new Card(Card.Type.BOOK,8));
        cards.add(new Card(Card.Type.BOOK,7));
        cards.add(new Card(Card.Type.TROPHY,2));
        shelfFour.insert(cards,4);
        cards.clear();
        assert (commonObjective5.verify(shelfFour));
    }
}