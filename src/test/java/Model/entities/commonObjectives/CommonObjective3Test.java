package Model.entities.commonObjectives;

import Model.entities.Card;
import Model.entities.Shelf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Config;

import java.util.ArrayList;
import java.util.List;

class CommonObjective3Test {

    Shelf shelfOne;
    Shelf shelfTwo;
    Shelf shelfThree;
    Shelf shelfFour;
    Shelf shelfFive;
    Shelf shelfSix;
    Shelf shelfSeven;
    Shelf shelfEight;
    List<Card> cards;
    CommonObjective3 commonObjective3;

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
        shelfEight = new Shelf();
        cards = new ArrayList<>();
        commonObjective3 = new CommonObjective3(4);
    }

    @Test
    void CommonObjective3() {
        cards.add(new Card(Card.Type.GAME,2));
        cards.add(new Card(Card.Type.GAME,10));
        cards.add(new Card(Card.Type.GAME,0));
        cards.add(new Card(Card.Type.FRAME,0));
        cards.add(new Card(Card.Type.BOOK,0));
        cards.add(new Card(Card.Type.GAME,11));
        shelfOne.insert(cards,0);
        cards.clear();
        cards.add(new Card(Card.Type.CAT,0));
        cards.add(new Card(Card.Type.PLANT,3));
        cards.add(new Card(Card.Type.PLANT,4));
        cards.add(new Card(Card.Type.BOOK,1));
        cards.add(new Card(Card.Type.PLANT,2));
        cards.add(new Card(Card.Type.GAME,3));
        shelfOne.insert(cards,1);
        cards.clear();
        cards.add(new Card(Card.Type.CAT,1));
        cards.add(new Card(Card.Type.FRAME,1));
        cards.add(new Card(Card.Type.BOOK,2));
        cards.add(new Card(Card.Type.PLANT,5));
        cards.add(new Card(Card.Type.TROPHY,1));
        cards.add(new Card(Card.Type.TROPHY,0));
        shelfOne.insert(cards,2);
        cards.clear();
        cards.add(new Card(Card.Type.BOOK,4));
        cards.add(new Card(Card.Type.BOOK,3));
        cards.add(new Card(Card.Type.PLANT,8));
        cards.add(new Card(Card.Type.PLANT,7));
        cards.add(new Card(Card.Type.PLANT,6));
        cards.add(new Card(Card.Type.TROPHY,1));
        shelfOne.insert(cards,3);
        cards.clear();
        cards.add(new Card(Card.Type.GAME,8));
        cards.add(new Card(Card.Type.GAME,7));
        cards.add(new Card(Card.Type.FRAME,2));
        cards.add(new Card(Card.Type.GAME,4));
        cards.add(new Card(Card.Type.TROPHY,2));
        cards.add(new Card(Card.Type.GAME,12));
        shelfOne.insert(cards,4);
        cards.clear();
        assert (commonObjective3.verify(shelfOne));

        cards.add(new Card(Card.Type.CAT,12));
        cards.add(new Card(Card.Type.GAME,10));
        cards.add(new Card(Card.Type.GAME,0));
        cards.add(new Card(Card.Type.FRAME,0));
        cards.add(new Card(Card.Type.BOOK,0));
        cards.add(new Card(Card.Type.CAT,11));
        shelfTwo.insert(cards,0);
        cards.clear();
        cards.add(new Card(Card.Type.CAT,0));
        cards.add(new Card(Card.Type.PLANT,3));
        cards.add(new Card(Card.Type.PLANT,4));
        cards.add(new Card(Card.Type.BOOK,1));
        cards.add(new Card(Card.Type.PLANT,2));
        cards.add(new Card(Card.Type.GAME,3));
        shelfTwo.insert(cards,1);
        cards.clear();
        cards.add(new Card(Card.Type.CAT,1));
        cards.add(new Card(Card.Type.FRAME,1));
        cards.add(new Card(Card.Type.BOOK,2));
        cards.add(new Card(Card.Type.PLANT,5));
        cards.add(new Card(Card.Type.TROPHY,1));
        cards.add(new Card(Card.Type.TROPHY,0));
        shelfTwo.insert(cards,2);
        cards.clear();
        cards.add(new Card(Card.Type.BOOK,4));
        cards.add(new Card(Card.Type.BOOK,3));
        cards.add(new Card(Card.Type.PLANT,8));
        cards.add(new Card(Card.Type.PLANT,7));
        cards.add(new Card(Card.Type.PLANT,6));
        cards.add(new Card(Card.Type.TROPHY,1));
        shelfTwo.insert(cards,3);
        cards.clear();
        cards.add(new Card(Card.Type.CAT,8));
        cards.add(new Card(Card.Type.GAME,7));
        cards.add(new Card(Card.Type.FRAME,2));
        cards.add(new Card(Card.Type.GAME,4));
        cards.add(new Card(Card.Type.TROPHY,2));
        cards.add(new Card(Card.Type.GAME,12));
        shelfTwo.insert(cards,4);
        cards.clear();
        assert (!commonObjective3.verify(shelfTwo));

        cards.add(new Card(Card.Type.CAT,12));
        cards.add(new Card(Card.Type.GAME,10));
        cards.add(new Card(Card.Type.GAME,0));
        cards.add(new Card(Card.Type.FRAME,0));
        cards.add(new Card(Card.Type.BOOK,0));
        cards.add(new Card(Card.Type.CAT,11));
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
        cards.add(new Card(Card.Type.CAT,8));
        cards.add(new Card(Card.Type.GAME,7));
        cards.add(new Card(Card.Type.FRAME,2));
        cards.add(new Card(Card.Type.GAME,4));
        cards.add(new Card(Card.Type.TROPHY,2));
        cards.add(new Card(Card.Type.GAME,12));
        shelfThree.insert(cards,4);
        cards.clear();
        assert (!commonObjective3.verify(shelfThree));

        cards.add(new Card(Card.Type.BOOK,12));
        cards.add(new Card(Card.Type.GAME,10));
        cards.add(new Card(Card.Type.GAME,0));
        cards.add(new Card(Card.Type.FRAME,0));
        cards.add(new Card(Card.Type.BOOK,0));
        cards.add(new Card(Card.Type.BOOK,11));
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
        cards.add(new Card(Card.Type.BOOK,8));
        cards.add(new Card(Card.Type.GAME,7));
        cards.add(new Card(Card.Type.FRAME,2));
        cards.add(new Card(Card.Type.GAME,4));
        cards.add(new Card(Card.Type.TROPHY,2));
        cards.add(new Card(Card.Type.BOOK,13));
        shelfFour.insert(cards,4);
        cards.clear();
        assert (commonObjective3.verify(shelfFour));

        cards.add(new Card(Card.Type.FRAME,1));
        cards.add(new Card(Card.Type.GAME,0));
        cards.add(new Card(Card.Type.CAT,0));
        cards.add(new Card(Card.Type.BOOK,1));
        cards.add(new Card(Card.Type.BOOK,0));
        cards.add(new Card(Card.Type.FRAME,0));
        shelfFive.insert(cards,0);
        cards.clear();
        cards.add(new Card(Card.Type.FRAME,2));
        cards.add(new Card(Card.Type.GAME,3));
        cards.add(new Card(Card.Type.GAME,4));
        cards.add(new Card(Card.Type.GAME,2));
        cards.add(new Card(Card.Type.GAME,1));
        cards.add(new Card(Card.Type.BOOK,2));
        shelfFive.insert(cards,1);
        cards.clear();
        cards.add(new Card(Card.Type.FRAME,3));
        cards.add(new Card(Card.Type.FRAME,4));
        cards.add(new Card(Card.Type.FRAME,5));
        cards.add(new Card(Card.Type.PLANT,0));
        cards.add(new Card(Card.Type.TROPHY,0));
        cards.add(new Card(Card.Type.BOOK,3));
        shelfFive.insert(cards,2);
        cards.clear();
        cards.add(new Card(Card.Type.FRAME,6));
        cards.add(new Card(Card.Type.PLANT,1));
        cards.add(new Card(Card.Type.BOOK,8));
        cards.add(new Card(Card.Type.CAT,7));
        cards.add(new Card(Card.Type.BOOK,6));
        cards.add(new Card(Card.Type.FRAME,7));
        shelfFive.insert(cards,3);
        cards.clear();
        cards.add(new Card(Card.Type.CAT,8));
        cards.add(new Card(Card.Type.GAME,7));
        cards.add(new Card(Card.Type.GAME,8));
        cards.add(new Card(Card.Type.GAME,9));
        cards.add(new Card(Card.Type.FRAME,8));
        cards.add(new Card(Card.Type.CAT,12));
        shelfFive.insert(cards,4);
        cards.clear();
        assert (!commonObjective3.verify(shelfFive));

        cards.add(new Card(Card.Type.FRAME,1));
        cards.add(new Card(Card.Type.GAME,0));
        cards.add(new Card(Card.Type.CAT,0));
        cards.add(new Card(Card.Type.BOOK,1));
        cards.add(new Card(Card.Type.BOOK,0));
        cards.add(new Card(Card.Type.FRAME,0));
        shelfSix.insert(cards,0);
        cards.clear();
        cards.add(new Card(Card.Type.FRAME,2));
        cards.add(new Card(Card.Type.GAME,3));
        cards.add(new Card(Card.Type.GAME,4));
        cards.add(new Card(Card.Type.GAME,2));
        cards.add(new Card(Card.Type.GAME,1));
        cards.add(new Card(Card.Type.BOOK,2));
        shelfSix.insert(cards,1);
        cards.clear();
        cards.add(new Card(Card.Type.FRAME,3));
        cards.add(new Card(Card.Type.FRAME,4));
        cards.add(new Card(Card.Type.FRAME,5));
        cards.add(new Card(Card.Type.PLANT,0));
        cards.add(new Card(Card.Type.TROPHY,0));
        cards.add(new Card(Card.Type.BOOK,3));
        shelfSix.insert(cards,2);
        cards.clear();
        cards.add(new Card(Card.Type.FRAME,6));
        cards.add(new Card(Card.Type.PLANT,1));
        cards.add(new Card(Card.Type.BOOK,8));
        cards.add(new Card(Card.Type.CAT,7));
        cards.add(new Card(Card.Type.BOOK,6));
        cards.add(new Card(Card.Type.FRAME,7));
        shelfSix.insert(cards,3);
        cards.clear();
        cards.add(new Card(Card.Type.FRAME,8));
        cards.add(new Card(Card.Type.GAME,7));
        cards.add(new Card(Card.Type.GAME,8));
        cards.add(new Card(Card.Type.GAME,9));
        cards.add(new Card(Card.Type.FRAME,9));
        cards.add(new Card(Card.Type.FRAME,12));
        shelfSix.insert(cards,4);
        cards.clear();
        assert (commonObjective3.verify(shelfSix));

        cards.add(new Card(Card.Type.FRAME,1));
        cards.add(new Card(Card.Type.GAME,0));
        cards.add(new Card(Card.Type.CAT,0));
        cards.add(new Card(Card.Type.BOOK,1));
        cards.add(new Card(Card.Type.BOOK,0));
        cards.add(new Card(Card.Type.CAT,15));
        shelfSeven.insert(cards,0);
        cards.clear();
        cards.add(new Card(Card.Type.FRAME,2));
        cards.add(new Card(Card.Type.GAME,3));
        cards.add(new Card(Card.Type.GAME,4));
        cards.add(new Card(Card.Type.GAME,2));
        cards.add(new Card(Card.Type.GAME,1));
        cards.add(new Card(Card.Type.BOOK,2));
        shelfSeven.insert(cards,1);
        cards.clear();
        cards.add(new Card(Card.Type.FRAME,3));
        cards.add(new Card(Card.Type.FRAME,4));
        cards.add(new Card(Card.Type.FRAME,5));
        cards.add(new Card(Card.Type.PLANT,0));
        cards.add(new Card(Card.Type.TROPHY,0));
        cards.add(new Card(Card.Type.BOOK,3));
        shelfSeven.insert(cards,2);
        cards.clear();
        cards.add(new Card(Card.Type.FRAME,6));
        cards.add(new Card(Card.Type.PLANT,1));
        cards.add(new Card(Card.Type.BOOK,8));
        cards.add(new Card(Card.Type.CAT,7));
        cards.add(new Card(Card.Type.BOOK,6));
        cards.add(new Card(Card.Type.FRAME,7));
        shelfSeven.insert(cards,3);
        cards.clear();
        cards.add(new Card(Card.Type.FRAME,9));
        cards.add(new Card(Card.Type.GAME,7));
        cards.add(new Card(Card.Type.GAME,8));
        cards.add(new Card(Card.Type.GAME,9));
        cards.add(new Card(Card.Type.FRAME,8));
        cards.add(new Card(Card.Type.CAT,12));
        shelfSeven.insert(cards,4);
        cards.clear();
        assert (!commonObjective3.verify(shelfSeven));

        cards.add(new Card(Card.Type.FRAME,1));
        cards.add(new Card(Card.Type.GAME,0));
        cards.add(new Card(Card.Type.CAT,0));
        cards.add(new Card(Card.Type.BOOK,1));
        cards.add(new Card(Card.Type.BOOK,0));
        cards.add(new Card(Card.Type.CAT,15));
        shelfEight.insert(cards,0);
        cards.clear();
        cards.add(new Card(Card.Type.FRAME,2));
        cards.add(new Card(Card.Type.GAME,3));
        cards.add(new Card(Card.Type.GAME,4));
        cards.add(new Card(Card.Type.GAME,2));
        cards.add(new Card(Card.Type.GAME,1));
        cards.add(new Card(Card.Type.BOOK,2));
        shelfEight.insert(cards,1);
        cards.clear();
        cards.add(new Card(Card.Type.FRAME,3));
        cards.add(new Card(Card.Type.FRAME,4));
        cards.add(new Card(Card.Type.FRAME,5));
        cards.add(new Card(Card.Type.PLANT,0));
        cards.add(new Card(Card.Type.TROPHY,0));
        cards.add(new Card(Card.Type.BOOK,3));
        shelfEight.insert(cards,2);
        cards.clear();
        cards.add(new Card(Card.Type.FRAME,6));
        cards.add(new Card(Card.Type.PLANT,1));
        cards.add(new Card(Card.Type.BOOK,8));
        cards.add(new Card(Card.Type.CAT,7));
        cards.add(new Card(Card.Type.BOOK,6));
        cards.add(new Card(Card.Type.FRAME,7));
        shelfEight.insert(cards,3);
        cards.clear();
        cards.add(new Card(Card.Type.GAME,10));
        cards.add(new Card(Card.Type.GAME,7));
        cards.add(new Card(Card.Type.GAME,8));
        cards.add(new Card(Card.Type.GAME,9));
        cards.add(new Card(Card.Type.FRAME,8));
        cards.add(new Card(Card.Type.BOOK,12));
        shelfEight.insert(cards,4);
        cards.clear();
        assert (!commonObjective3.verify(shelfEight));
    }
}