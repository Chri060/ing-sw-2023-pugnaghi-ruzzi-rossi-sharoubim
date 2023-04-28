package Model.entities.commonObjectives.GroupCommonObjective;

import Exceptions.InvalidArgumentException;
import Model.entities.Card;
import Model.entities.Shelf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Config;

import java.util.ArrayList;
import java.util.List;

class GroupCommonObjectiveTest {

    List<Card> carte;
    Shelf lib;


    @BeforeEach
    void setUp() {
        Config.initialise(2);
        carte = new ArrayList<>();
        lib = new Shelf();
    }


    @Test
    void ComObjTwoVerified_1() throws InvalidArgumentException {
        GroupCommonObjective commonObjective1 = new CommonObjective1(0);
        carte.add(new Card(Card.Type.FRAME,1));
        carte.add(new Card(Card.Type.GAME,1));
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.BOOK,1));
        carte.add(new Card(Card.Type.BOOK,0));
        carte.add(new Card(Card.Type.GAME,0));
        lib.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY,0));
        carte.add(new Card(Card.Type.BOOK,2));
        carte.add(new Card(Card.Type.CAT,0));
        carte.add(new Card(Card.Type.FRAME,2));
        carte.add(new Card(Card.Type.GAME,3));
        carte.add(new Card(Card.Type.GAME,2));
        lib.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY,1));
        carte.add(new Card(Card.Type.PLANT,0));
        carte.add(new Card(Card.Type.CAT,1));
        carte.add(new Card(Card.Type.GAME,4));
        carte.add(new Card(Card.Type.FRAME,3));
        carte.add(new Card(Card.Type.BOOK,3));
        lib.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY,2));
        carte.add(new Card(Card.Type.PLANT,1));
        carte.add(new Card(Card.Type.GAME,5));
        carte.add(new Card(Card.Type.PLANT,2));
        carte.add(new Card(Card.Type.TROPHY,2));
        carte.add(new Card(Card.Type.PLANT,1));
        lib.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.GAME,8));
        carte.add(new Card(Card.Type.GAME,7));
        carte.add(new Card(Card.Type.GAME,6));
        carte.add(new Card(Card.Type.PLANT,3));
        carte.add(new Card(Card.Type.BOOK,4));
        carte.add(new Card(Card.Type.TROPHY,3));
        lib.insert(carte,4);


    }



}