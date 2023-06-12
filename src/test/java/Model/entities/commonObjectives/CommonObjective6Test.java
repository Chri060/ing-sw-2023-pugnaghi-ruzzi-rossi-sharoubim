package Model.entities.commonObjectives;

import Model.entities.Card;
import Model.entities.Shelf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Config;

import java.util.ArrayList;
import java.util.List;

class CommonObjective6Test {

    Shelf shelfOne;
    Shelf shelfTwo;
    Shelf shelfThree;
    Shelf shelfFour;
    List<Card> cards;
    List<Card> cardsOne;
    CommonObjective6 commonObjective6;

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
        cardsOne = new ArrayList<>();
        commonObjective6 = new CommonObjective6(4);
    }

    @Test
    void CommonObjectives6() {
        cards.add(new Card(Card.Type.GAME,0));
        cards.add(new Card(Card.Type.PLANT,1));
        cards.add(new Card(Card.Type.CAT,1));
        cards.add(new Card(Card.Type.PLANT,0));
        cards.add(new Card(Card.Type.FRAME,0));
        cards.add(new Card(Card.Type.CAT,0));
        shelfOne.insert(cards,0);
        cards.clear();
        cards.add(new Card(Card.Type.FRAME,1));
        cards.add(new Card(Card.Type.BOOK,0));
        cards.add(new Card(Card.Type.CAT,2));
        cards.add(new Card(Card.Type.PLANT,2));
        cards.add(new Card(Card.Type.GAME,1));
        cards.add(new Card(Card.Type.TROPHY,0));
        shelfOne.insert(cards,1);
        cards.clear();
        cards.add(new Card(Card.Type.PLANT,8));
        cards.add(new Card(Card.Type.PLANT,7));
        cards.add(new Card(Card.Type.PLANT,6));
        cards.add(new Card(Card.Type.PLANT,5));
        cards.add(new Card(Card.Type.PLANT,4));
        cards.add(new Card(Card.Type.PLANT,3));
        shelfOne.insert(cards,2);
        cards.clear();
        cards.add(new Card(Card.Type.GAME,2));
        cards.add(new Card(Card.Type.GAME,3));
        cards.add(new Card(Card.Type.GAME,4));
        cards.add(new Card(Card.Type.PLANT,9));
        cards.add(new Card(Card.Type.FRAME,2));
        cards.add(new Card(Card.Type.FRAME,3));
        shelfOne.insert(cards,3);
        cards.clear();
        cards.add(new Card(Card.Type.BOOK,2));
        cards.add(new Card(Card.Type.FRAME,5));
        cards.add(new Card(Card.Type.CAT,4));
        cards.add(new Card(Card.Type.GAME,5));
        cards.add(new Card(Card.Type.PLANT,10));
        cards.add(new Card(Card.Type.TROPHY,10));
        shelfOne.insert(cards,4);
        cards.clear();
        assert (commonObjective6.verify(shelfOne));

        cards.add(new Card(Card.Type.GAME,0));
        cards.add(new Card(Card.Type.PLANT,1));
        cards.add(new Card(Card.Type.CAT,1));
        cards.add(new Card(Card.Type.PLANT,0));
        cards.add(new Card(Card.Type.FRAME,0));
        cards.add(new Card(Card.Type.CAT,0));
        shelfTwo.insert(cards,0);
        cards.clear();
        cards.add(new Card(Card.Type.BOOK,9));
        cards.add(new Card(Card.Type.BOOK,0));
        cards.add(new Card(Card.Type.CAT,2));
        cards.add(new Card(Card.Type.PLANT,2));
        cards.add(new Card(Card.Type.GAME,1));
        cards.add(new Card(Card.Type.TROPHY,0));
        shelfTwo.insert(cards,1);
        cards.clear();
        cards.add(new Card(Card.Type.PLANT,8));
        cards.add(new Card(Card.Type.PLANT,7));
        cards.add(new Card(Card.Type.PLANT,6));
        cards.add(new Card(Card.Type.PLANT,5));
        cards.add(new Card(Card.Type.PLANT,4));
        cards.add(new Card(Card.Type.PLANT,3));
        shelfTwo.insert(cards,2);
        cards.clear();
        cards.add(new Card(Card.Type.GAME,2));
        cards.add(new Card(Card.Type.GAME,3));
        cards.add(new Card(Card.Type.GAME,4));
        cards.add(new Card(Card.Type.PLANT,9));
        cards.add(new Card(Card.Type.FRAME,2));
        cards.add(new Card(Card.Type.FRAME,3));
        shelfTwo.insert(cards,3);
        cards.clear();
        cards.add(new Card(Card.Type.BOOK,2));
        cards.add(new Card(Card.Type.FRAME,5));
        cards.add(new Card(Card.Type.CAT,4));
        cards.add(new Card(Card.Type.GAME,5));
        cards.add(new Card(Card.Type.PLANT,10));
        cards.add(new Card(Card.Type.TROPHY,10));
        shelfTwo.insert(cards,4);
        cards.clear();
        assert (!commonObjective6.verify(shelfTwo));

        cards.add(new Card(Card.Type.TROPHY,0));
        cards.add(new Card(Card.Type.BOOK,1));
        cards.add(new Card(Card.Type.GAME,1));
        cards.add(new Card(Card.Type.PLANT,0));
        cards.add(new Card(Card.Type.FRAME,0));
        cards.add(new Card(Card.Type.CAT,0));
        shelfThree.insert(cards,0);
        cards.clear();
        cards.add(new Card(Card.Type.BOOK,19));
        cards.add(new Card(Card.Type.BOOK,0));
        cards.add(new Card(Card.Type.CAT,2));
        cards.add(new Card(Card.Type.PLANT,2));
        cards.add(new Card(Card.Type.GAME,14));
        cards.add(new Card(Card.Type.TROPHY,11));
        shelfThree.insert(cards,1);
        cards.clear();
        cards.add(new Card(Card.Type.FRAME,18));
        cards.add(new Card(Card.Type.BOOK,20));
        cards.add(new Card(Card.Type.TROPHY,16));
        cards.add(new Card(Card.Type.GAME,15));
        cards.add(new Card(Card.Type.PLANT,4));
        cards.add(new Card(Card.Type.CAT,13));
        shelfThree.insert(cards,2);
        cards.clear();
        cards.add(new Card(Card.Type.GAME,2));
        cards.add(new Card(Card.Type.GAME,3));
        cards.add(new Card(Card.Type.GAME,4));
        cards.add(new Card(Card.Type.PLANT,9));
        cards.add(new Card(Card.Type.FRAME,2));
        cards.add(new Card(Card.Type.FRAME,3));
        shelfThree.insert(cards,3);
        cards.clear();
        cards.add(new Card(Card.Type.BOOK,2));
        cards.add(new Card(Card.Type.FRAME,5));
        cards.add(new Card(Card.Type.CAT,4));
        cards.add(new Card(Card.Type.GAME,5));
        cards.add(new Card(Card.Type.PLANT,10));
        cards.add(new Card(Card.Type.TROPHY,10));
        shelfThree.insert(cards,4);
        cards.clear();
        assert (commonObjective6.verify(shelfThree));

        assert (!commonObjective6.verify(shelfFour));
        cards.add(new Card(Card.Type.TROPHY, 0));
        cards.add(new Card(Card.Type.CAT, 0));
        cards.add(new Card(Card.Type.BOOK, 0));
        cards.add(new Card(Card.Type.FRAME, 0));
        cards.add(new Card(Card.Type.PLANT, 0));
        shelfFour.insert(cards, 0);
        shelfFour.insert(cards, 2);
        assert (!commonObjective6.verify(shelfFour));
        cards.stream().forEach(x -> cardsOne.add(0, x));
        shelfFour.insert(cardsOne, 1);
        assert (!commonObjective6.verify(shelfFour));
        shelfFour.insert(cardsOne, 3);
        assert (!commonObjective6.verify(shelfFour));
        cards.clear();
        cards.add(new Card(Card.Type.GAME, 0));
        cardsOne.clear();
        cardsOne.add(new Card(Card.Type.CAT, 0));
        shelfFour.insert(cardsOne, 1);
        assert (!commonObjective6.verify(shelfFour));
        shelfFour.insert(cards, 0);
        assert (!commonObjective6.verify(shelfFour));
        shelfFour.insert(cardsOne, 2);
        assert (!commonObjective6.verify(shelfFour));
        shelfFour.insert(cards, 3);
        assert (commonObjective6.verify(shelfFour));
        cards.clear();
    }
}