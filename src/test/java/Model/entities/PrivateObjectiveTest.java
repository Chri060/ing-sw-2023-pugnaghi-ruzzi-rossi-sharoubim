package Model.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class PrivateObjectiveTest {
    Shelf l;
    ArrayList<Card> carte;


    @BeforeEach
    void setupPriv() {
        l = new Shelf();     // come oggetti globali
        carte = new ArrayList<>();
    }


    @Test
    void PrivObjOneVerified_1() {
        PrivateObjective pob1 = new PrivateObjective(0);
        carte.add(new Card(Card.Type.FRAME, 0));
        carte.add(new Card(Card.Type.PLANT, 0));
        carte.add(new Card(Card.Type.TROPHY, 0));
        carte.add(new Card(Card.Type.PLANT, 1));
        carte.add(new Card(Card.Type.GAME, 0));
        carte.add(new Card(Card.Type.PLANT, 2));
        l.insert(carte, 0);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT, 3));
        carte.add(new Card(Card.Type.FRAME, 1));
        carte.add(new Card(Card.Type.GAME, 2));
        carte.add(new Card(Card.Type.FRAME, 1));
        carte.add(new Card(Card.Type.BOOK, 0));
        carte.add(new Card(Card.Type.CAT, 0));
        l.insert(carte, 1);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY, 2));
        carte.add(new Card(Card.Type.GAME, 2));
        carte.add(new Card(Card.Type.PLANT, 6));
        carte.add(new Card(Card.Type.TROPHY, 3));
        carte.add(new Card(Card.Type.TROPHY, 4));
        carte.add(new Card(Card.Type.FRAME, 7));
        l.insert(carte, 2);
        carte.clear();
        carte.add(new Card(Card.Type.CAT, 3));
        carte.add(new Card(Card.Type.CAT, 4));
        carte.add(new Card(Card.Type.FRAME, 8));
        carte.add(new Card(Card.Type.BOOK, 4));
        carte.add(new Card(Card.Type.BOOK, 5));
        carte.add(new Card(Card.Type.TROPHY, 6));
        l.insert(carte, 3);
        carte.clear();
        carte.add(new Card(Card.Type.GAME, 8));
        carte.add(new Card(Card.Type.CAT, 5));
        carte.add(new Card(Card.Type.GAME, 9));
        carte.add(new Card(Card.Type.BOOK, 7));
        carte.add(new Card(Card.Type.CAT, 9));
        carte.add(new Card(Card.Type.PLANT, 8));
        l.insert(carte, 4);

        Assertions.assertEquals(12, pob1.getMaxPoints(l).getValue());
    }

    //Altro caso specifico

    @Test
    void PrivObjOneVerified_2() {
        PrivateObjective pob1 = new PrivateObjective(0);
        carte.add(new Card(Card.Type.FRAME, 0));
        carte.add(new Card(Card.Type.PLANT, 0));
        carte.add(new Card(Card.Type.TROPHY, 0));
        carte.add(new Card(Card.Type.PLANT, 1));
        carte.add(new Card(Card.Type.GAME, 0));
        carte.add(new Card(Card.Type.PLANT, 2));
        l.insert(carte, 0);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT, 3));
        carte.add(new Card(Card.Type.FRAME, 1));
        carte.add(new Card(Card.Type.GAME, 2));
        carte.add(new Card(Card.Type.FRAME, 1));
        carte.add(new Card(Card.Type.BOOK, 0));
        carte.add(new Card(Card.Type.CAT, 0));
        l.insert(carte, 1);
        carte.clear();
        carte.add(new Card(Card.Type.GAME, 10));
        carte.add(new Card(Card.Type.GAME, 2));
        carte.add(new Card(Card.Type.PLANT, 6));
        carte.add(new Card(Card.Type.TROPHY, 3));
        carte.add(new Card(Card.Type.TROPHY, 4));
        carte.add(new Card(Card.Type.FRAME, 7));
        l.insert(carte, 2);
        carte.clear();
        carte.add(new Card(Card.Type.CAT, 3));
        carte.add(new Card(Card.Type.CAT, 4));
        carte.add(new Card(Card.Type.FRAME, 8));
        carte.add(new Card(Card.Type.BOOK, 4));
        carte.add(new Card(Card.Type.BOOK, 5));
        carte.add(new Card(Card.Type.TROPHY, 6));
        l.insert(carte, 3);
        carte.clear();
        carte.add(new Card(Card.Type.GAME, 8));
        carte.add(new Card(Card.Type.CAT, 5));
        carte.add(new Card(Card.Type.GAME, 9));
        carte.add(new Card(Card.Type.BOOK, 7));
        carte.add(new Card(Card.Type.CAT, 9));
        carte.add(new Card(Card.Type.PLANT, 8));
        l.insert(carte, 4);

        Assertions.assertEquals(9, pob1.getMaxPoints(l).getValue());
    }




    @Test
    void PrivObjOneVerified_3() {
        PrivateObjective pob1 = new PrivateObjective(0);
        carte.add(new Card(Card.Type.FRAME, 0));
        carte.add(new Card(Card.Type.TROPHY, 0));
        carte.add(new Card(Card.Type.PLANT, 0));
        carte.add(new Card(Card.Type.FRAME, 1));
        carte.add(new Card(Card.Type.FRAME, 2));
        carte.add(new Card(Card.Type.CAT, 0));
        l.insert(carte, 0);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY, 1));
        carte.add(new Card(Card.Type.PLANT, 1));
        carte.add(new Card(Card.Type.GAME, 0));
        carte.add(new Card(Card.Type.TROPHY, 2));
        carte.add(new Card(Card.Type.BOOK, 0));
        carte.add(new Card(Card.Type.PLANT, 2));
        l.insert(carte, 1);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 3));
        carte.add(new Card(Card.Type.TROPHY, 4));
        carte.add(new Card(Card.Type.PLANT, 3));
        carte.add(new Card(Card.Type.TROPHY, 3));
        carte.add(new Card(Card.Type.BOOK, 1));
        carte.add(new Card(Card.Type.PLANT, 4));
        l.insert(carte, 2);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY, 5));
        carte.add(new Card(Card.Type.GAME, 1));
        carte.add(new Card(Card.Type.TROPHY, 6));
        carte.add(new Card(Card.Type.PLANT, 5));
        carte.add(new Card(Card.Type.BOOK, 2));
        carte.add(new Card(Card.Type.GAME, 2));
        l.insert(carte, 3);
        carte.clear();
        carte.add(new Card(Card.Type.CAT, 2));
        carte.add(new Card(Card.Type.PLANT, 7));
        carte.add(new Card(Card.Type.TROPHY, 9));
        carte.add(new Card(Card.Type.TROPHY, 7));
        carte.add(new Card(Card.Type.GAME, 9));
        carte.add(new Card(Card.Type.GAME, 8));
        l.insert(carte, 4);

        Assertions.assertEquals(1, pob1.getMaxPoints(l).getValue());
    }


    //Private Objective 2 id=1



    @Test
    void PrivObjTwoVerified_1() {
        PrivateObjective pob2 = new PrivateObjective(1);
        carte.add(new Card(Card.Type.PLANT, 0));
        carte.add(new Card(Card.Type.PLANT, 1));
        carte.add(new Card(Card.Type.PLANT, 2));
        carte.add(new Card(Card.Type.CAT, 1));
        carte.add(new Card(Card.Type.CAT, 0));
        carte.add(new Card(Card.Type.PLANT, 3));
        l.insert(carte, 0);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK, 0));
        carte.add(new Card(Card.Type.FRAME, 0));
        carte.add(new Card(Card.Type.BOOK, 1));
        carte.add(new Card(Card.Type.FRAME, 1));
        carte.add(new Card(Card.Type.PLANT, 4));
        carte.add(new Card(Card.Type.CAT, 2));
        l.insert(carte, 1);
        carte.clear();
        carte.add(new Card(Card.Type.CAT, 3));
        carte.add(new Card(Card.Type.BOOK, 4));
        carte.add(new Card(Card.Type.GAME, 3));
        carte.add(new Card(Card.Type.GAME, 4));
        carte.add(new Card(Card.Type.BOOK, 2));
        carte.add(new Card(Card.Type.FRAME, 4));
        l.insert(carte, 2);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY, 5));
        carte.add(new Card(Card.Type.TROPHY, 1));
        carte.add(new Card(Card.Type.GAME, 6));
        carte.add(new Card(Card.Type.PLANT, 5));
        carte.add(new Card(Card.Type.BOOK, 6));
        carte.add(new Card(Card.Type.PLANT, 6));
        l.insert(carte, 3);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 5));
        carte.add(new Card(Card.Type.GAME, 7));
        carte.add(new Card(Card.Type.BOOK, 9));
        carte.add(new Card(Card.Type.GAME, 7));
        carte.add(new Card(Card.Type.GAME, 9));
        carte.add(new Card(Card.Type.GAME, 8));
        l.insert(carte, 4);

        Assertions.assertEquals(12, pob2.getMaxPoints(l).getValue());
    }



    @Test
    void PrivObjTwoVerified_2() {
        PrivateObjective pob2 = new PrivateObjective(1);
        carte.add(new Card(Card.Type.PLANT, 0));
        carte.add(new Card(Card.Type.PLANT, 1));
        carte.add(new Card(Card.Type.PLANT, 2));
        carte.add(new Card(Card.Type.CAT, 1));
        carte.add(new Card(Card.Type.CAT, 0));
        carte.add(new Card(Card.Type.PLANT, 3));
        l.insert(carte, 0);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK, 0));
        carte.add(new Card(Card.Type.FRAME, 0));
        carte.add(new Card(Card.Type.BOOK, 1));
        carte.add(new Card(Card.Type.FRAME, 1));
        carte.add(new Card(Card.Type.PLANT, 4));
        carte.add(new Card(Card.Type.CAT, 2));
        l.insert(carte, 1);
        carte.clear();
        carte.add(new Card(Card.Type.CAT, 3));
        carte.add(new Card(Card.Type.BOOK, 4));
        carte.add(new Card(Card.Type.GAME, 3));
        carte.add(new Card(Card.Type.BOOK, 16));
        carte.add(new Card(Card.Type.BOOK, 2));
        carte.add(new Card(Card.Type.FRAME, 4));
        l.insert(carte, 2);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY, 5));
        carte.add(new Card(Card.Type.TROPHY, 1));
        carte.add(new Card(Card.Type.GAME, 6));
        carte.add(new Card(Card.Type.PLANT, 5));
        carte.add(new Card(Card.Type.BOOK, 6));
        carte.add(new Card(Card.Type.PLANT, 6));
        l.insert(carte, 3);
        carte.clear();
        carte.add(new Card(Card.Type.GAME, 10));
        carte.add(new Card(Card.Type.GAME, 7));
        carte.add(new Card(Card.Type.BOOK, 9));
        carte.add(new Card(Card.Type.GAME, 7));
        carte.add(new Card(Card.Type.GAME, 9));
        carte.add(new Card(Card.Type.GAME, 8));
        l.insert(carte, 4);

        Assertions.assertEquals(6, pob2.getMaxPoints(l).getValue());
    }




    @Test
    void PrivObjTwoVerified_3() {
        PrivateObjective pob2 = new PrivateObjective(1);
        carte.add(new Card(Card.Type.PLANT, 0));
        carte.add(new Card(Card.Type.PLANT, 1));
        carte.add(new Card(Card.Type.PLANT, 2));
        carte.add(new Card(Card.Type.FRAME, 16));
        carte.add(new Card(Card.Type.CAT, 0));
        carte.add(new Card(Card.Type.PLANT, 3));
        l.insert(carte, 0);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK, 0));
        carte.add(new Card(Card.Type.FRAME, 0));
        carte.add(new Card(Card.Type.BOOK, 1));
        carte.add(new Card(Card.Type.FRAME, 1));
        carte.add(new Card(Card.Type.BOOK, 17));
        carte.add(new Card(Card.Type.CAT, 2));
        l.insert(carte, 1);
        carte.clear();
        carte.add(new Card(Card.Type.CAT, 3));
        carte.add(new Card(Card.Type.BOOK, 4));
        carte.add(new Card(Card.Type.GAME, 3));
        carte.add(new Card(Card.Type.BOOK, 16));
        carte.add(new Card(Card.Type.BOOK, 2));
        carte.add(new Card(Card.Type.FRAME, 4));
        l.insert(carte, 2);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY, 5));
        carte.add(new Card(Card.Type.PLANT, 18));
        carte.add(new Card(Card.Type.GAME, 6));
        carte.add(new Card(Card.Type.PLANT, 5));
        carte.add(new Card(Card.Type.BOOK, 6));
        carte.add(new Card(Card.Type.PLANT, 6));
        l.insert(carte, 3);
        carte.clear();
        carte.add(new Card(Card.Type.GAME, 10));
        carte.add(new Card(Card.Type.GAME, 7));
        carte.add(new Card(Card.Type.PLANT, 9));
        carte.add(new Card(Card.Type.GAME, 7));
        carte.add(new Card(Card.Type.GAME, 9));
        carte.add(new Card(Card.Type.GAME, 8));
        l.insert(carte, 4);

        Assertions.assertEquals(0, pob2.getMaxPoints(l).getValue());
    }




    //Private Objective 3 id=2//



    @Test
    void PrivObjThreeVerified_1() {
        PrivateObjective pob3 = new PrivateObjective(2);
        carte.add(new Card(Card.Type.BOOK, 0));
        carte.add(new Card(Card.Type.BOOK, 1));
        carte.add(new Card(Card.Type.TROPHY, 2));
        carte.add(new Card(Card.Type.TROPHY, 1));
        carte.add(new Card(Card.Type.FRAME, 0));
        carte.add(new Card(Card.Type.FRAME, 3));
        l.insert(carte, 0);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 4));
        carte.add(new Card(Card.Type.CAT, 0));
        carte.add(new Card(Card.Type.CAT, 1));
        carte.add(new Card(Card.Type.TROPHY, 3));
        carte.add(new Card(Card.Type.BOOK, 4));
        carte.add(new Card(Card.Type.BOOK, 2));
        l.insert(carte, 1);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 5));
        carte.add(new Card(Card.Type.FRAME, 6));
        carte.add(new Card(Card.Type.PLANT, 3));
        carte.add(new Card(Card.Type.PLANT, 4));
        carte.add(new Card(Card.Type.CAT, 2));
        carte.add(new Card(Card.Type.CAT, 4));
        l.insert(carte, 2);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 7));
        carte.add(new Card(Card.Type.PLANT, 7));
        carte.add(new Card(Card.Type.PLANT, 6));
        carte.add(new Card(Card.Type.CAT, 5));
        carte.add(new Card(Card.Type.GAME, 6));
        carte.add(new Card(Card.Type.FRAME, 8));
        l.insert(carte, 3);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 9));
        carte.add(new Card(Card.Type.GAME, 7));
        carte.add(new Card(Card.Type.TROPHY, 9));
        carte.add(new Card(Card.Type.CAT, 7));
        carte.add(new Card(Card.Type.BOOK, 9));
        carte.add(new Card(Card.Type.PLANT, 8));
        l.insert(carte, 4);

        Assertions.assertEquals(12, pob3.getMaxPoints(l).getValue());
    }





    @Test
    void PrivObjThreeVerified_2() {
        PrivateObjective pob3 = new PrivateObjective(2);
        carte.add(new Card(Card.Type.PLANT, 15));
        carte.add(new Card(Card.Type.BOOK, 1));
        carte.add(new Card(Card.Type.TROPHY, 2));
        carte.add(new Card(Card.Type.TROPHY, 1));
        carte.add(new Card(Card.Type.CAT, 15));
        carte.add(new Card(Card.Type.FRAME, 3));
        l.insert(carte, 0);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 4));
        carte.add(new Card(Card.Type.CAT, 0));
        carte.add(new Card(Card.Type.FRAME, 16));
        carte.add(new Card(Card.Type.TROPHY, 3));
        carte.add(new Card(Card.Type.BOOK, 4));
        carte.add(new Card(Card.Type.BOOK, 2));
        l.insert(carte, 1);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 5));
        carte.add(new Card(Card.Type.FRAME, 6));
        carte.add(new Card(Card.Type.PLANT, 3));
        carte.add(new Card(Card.Type.CAT, 18));
        carte.add(new Card(Card.Type.CAT, 2));
        carte.add(new Card(Card.Type.CAT, 4));
        l.insert(carte, 2);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 7));
        carte.add(new Card(Card.Type.PLANT, 7));
        carte.add(new Card(Card.Type.PLANT, 6));
        carte.add(new Card(Card.Type.CAT, 5));
        carte.add(new Card(Card.Type.GAME, 6));
        carte.add(new Card(Card.Type.FRAME, 8));
        l.insert(carte, 3);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 9));
        carte.add(new Card(Card.Type.GAME, 7));
        carte.add(new Card(Card.Type.TROPHY, 9));
        carte.add(new Card(Card.Type.CAT, 7));
        carte.add(new Card(Card.Type.BOOK, 9));
        carte.add(new Card(Card.Type.PLANT, 8));
        l.insert(carte, 4);

        Assertions.assertEquals(2, pob3.getMaxPoints(l).getValue());
    }





    @Test
    void PrivObjThreeVerified_3() {
        PrivateObjective pob3 = new PrivateObjective(2);
        carte.add(new Card(Card.Type.BOOK, 19));
        carte.add(new Card(Card.Type.BOOK, 1));
        carte.add(new Card(Card.Type.TROPHY, 2));
        carte.add(new Card(Card.Type.TROPHY, 1));
        carte.add(new Card(Card.Type.FRAME, 15));
        carte.add(new Card(Card.Type.FRAME, 3));
        l.insert(carte, 0);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 4));
        carte.add(new Card(Card.Type.CAT, 0));
        carte.add(new Card(Card.Type.BOOK, 16));
        carte.add(new Card(Card.Type.TROPHY, 3));
        carte.add(new Card(Card.Type.BOOK, 4));
        carte.add(new Card(Card.Type.BOOK, 2));
        l.insert(carte, 1);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 5));
        carte.add(new Card(Card.Type.FRAME, 6));
        carte.add(new Card(Card.Type.PLANT, 3));
        carte.add(new Card(Card.Type.CAT, 18));
        carte.add(new Card(Card.Type.CAT, 2));
        carte.add(new Card(Card.Type.CAT, 4));
        l.insert(carte, 2);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 7));
        carte.add(new Card(Card.Type.PLANT, 7));
        carte.add(new Card(Card.Type.PLANT, 6));
        carte.add(new Card(Card.Type.CAT, 5));
        carte.add(new Card(Card.Type.GAME, 6));
        carte.add(new Card(Card.Type.FRAME, 8));
        l.insert(carte, 3);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 9));
        carte.add(new Card(Card.Type.GAME, 7));
        carte.add(new Card(Card.Type.GAME, 9));
        carte.add(new Card(Card.Type.CAT, 7));
        carte.add(new Card(Card.Type.BOOK, 9));
        carte.add(new Card(Card.Type.PLANT, 8));
        l.insert(carte, 4);

        Assertions.assertEquals(4, pob3.getMaxPoints(l).getValue());
    }


    //Private Objective 4 id=3//



    @Test
    void PrivObjFourVerified_1() {
        PrivateObjective pob4 = new PrivateObjective(3);
        carte.add(new Card(Card.Type.CAT, 0));
        carte.add(new Card(Card.Type.FRAME, 1));
        carte.add(new Card(Card.Type.PLANT, 1));
        carte.add(new Card(Card.Type.TROPHY, 0));
        carte.add(new Card(Card.Type.PLANT, 2));
        carte.add(new Card(Card.Type.FRAME,0 ));
        l.insert(carte, 0);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK, 1));
        carte.add(new Card(Card.Type.BOOK, 0));
        carte.add(new Card(Card.Type.CAT, 3));
        carte.add(new Card(Card.Type.PLANT, 3));
        carte.add(new Card(Card.Type.BOOK, 4));
        carte.add(new Card(Card.Type.PLANT, 3));
        l.insert(carte, 1);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 5));
        carte.add(new Card(Card.Type.CAT, 6));
        carte.add(new Card(Card.Type.CAT, 4));
        carte.add(new Card(Card.Type.FRAME, 4));
        carte.add(new Card(Card.Type.TROPHY, 2));
        carte.add(new Card(Card.Type.CAT, 5));
        l.insert(carte, 2);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT, 7));
        carte.add(new Card(Card.Type.CAT, 7));
        carte.add(new Card(Card.Type.PLANT, 6));
        carte.add(new Card(Card.Type.PLANT, 5));
        carte.add(new Card(Card.Type.TROPHY, 6));
        carte.add(new Card(Card.Type.GAME, 8));
        l.insert(carte, 3);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT, 9));
        carte.add(new Card(Card.Type.FRAME, 7));
        carte.add(new Card(Card.Type.PLANT, 9));
        carte.add(new Card(Card.Type.CAT, 8));
        carte.add(new Card(Card.Type.TROPHY, 9));
        carte.add(new Card(Card.Type.GAME, 10));
        l.insert(carte, 4);

        Assertions.assertEquals(12, pob4.getMaxPoints(l).getValue());
    }




    @Test
    void PrivObjFourVerified_2() {
        PrivateObjective pob4 = new PrivateObjective(3);
        carte.add(new Card(Card.Type.CAT, 0));
        carte.add(new Card(Card.Type.FRAME, 1));
        carte.add(new Card(Card.Type.PLANT, 1));
        carte.add(new Card(Card.Type.TROPHY, 0));
        carte.add(new Card(Card.Type.PLANT, 2));
        carte.add(new Card(Card.Type.FRAME,0 ));
        l.insert(carte, 0);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK, 1));
        carte.add(new Card(Card.Type.BOOK, 0));
        carte.add(new Card(Card.Type.CAT, 3));
        carte.add(new Card(Card.Type.PLANT, 3));
        carte.add(new Card(Card.Type.BOOK, 4));
        carte.add(new Card(Card.Type.PLANT, 3));
        l.insert(carte, 1);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 5));
        carte.add(new Card(Card.Type.CAT, 6));
        carte.add(new Card(Card.Type.CAT, 4));
        carte.add(new Card(Card.Type.FRAME, 4));
        carte.add(new Card(Card.Type.TROPHY, 2));
        carte.add(new Card(Card.Type.CAT, 5));
        l.insert(carte, 2);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT, 7));
        carte.add(new Card(Card.Type.CAT, 7));
        carte.add(new Card(Card.Type.CAT, 20));
        carte.add(new Card(Card.Type.PLANT, 5));
        carte.add(new Card(Card.Type.TROPHY, 6));
        carte.add(new Card(Card.Type.GAME, 8));
        l.insert(carte, 3);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT, 9));
        carte.add(new Card(Card.Type.FRAME, 7));
        carte.add(new Card(Card.Type.PLANT, 9));
        carte.add(new Card(Card.Type.CAT, 8));
        carte.add(new Card(Card.Type.TROPHY, 9));
        carte.add(new Card(Card.Type.GAME, 10));
        l.insert(carte, 4);

        Assertions.assertEquals(9, pob4.getMaxPoints(l).getValue());
    }




    @Test
    void PrivObjFourVerified_3() {
        PrivateObjective pob4 = new PrivateObjective(3);
        carte.add(new Card(Card.Type.CAT, 0));
        carte.add(new Card(Card.Type.FRAME, 1));
        carte.add(new Card(Card.Type.PLANT, 1));
        carte.add(new Card(Card.Type.BOOK, 20));
        carte.add(new Card(Card.Type.PLANT, 2));
        carte.add(new Card(Card.Type.FRAME,0 ));
        l.insert(carte, 0);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK, 1));
        carte.add(new Card(Card.Type.PLANT, 20));
        carte.add(new Card(Card.Type.CAT, 3));
        carte.add(new Card(Card.Type.PLANT, 3));
        carte.add(new Card(Card.Type.BOOK, 4));
        carte.add(new Card(Card.Type.PLANT, 3));
        l.insert(carte, 1);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 5));
        carte.add(new Card(Card.Type.PLANT, 17));
        carte.add(new Card(Card.Type.CAT, 4));
        carte.add(new Card(Card.Type.BOOK, 13));
        carte.add(new Card(Card.Type.TROPHY, 2));
        carte.add(new Card(Card.Type.CAT, 5));
        l.insert(carte, 2);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT, 7));
        carte.add(new Card(Card.Type.CAT, 7));
        carte.add(new Card(Card.Type.CAT, 17));
        carte.add(new Card(Card.Type.PLANT, 5));
        carte.add(new Card(Card.Type.TROPHY, 6));
        carte.add(new Card(Card.Type.GAME, 8));
        l.insert(carte, 3);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT, 9));
        carte.add(new Card(Card.Type.FRAME, 7));
        carte.add(new Card(Card.Type.PLANT, 9));
        carte.add(new Card(Card.Type.CAT, 8));
        carte.add(new Card(Card.Type.TROPHY, 9));
        carte.add(new Card(Card.Type.TROPHY, 10));
        l.insert(carte, 4);

        Assertions.assertEquals(0, pob4.getMaxPoints(l).getValue());
    }

    //Private Objective 5 id=4//


    @Test
    void PrivObjFiveVerified_1() {
        PrivateObjective pob5 = new PrivateObjective(4);
        carte.add(new Card(Card.Type.GAME, 0));
        carte.add(new Card(Card.Type.PLANT, 0));
        carte.add(new Card(Card.Type.CAT, 0));
        carte.add(new Card(Card.Type.PLANT, 1));
        carte.add(new Card(Card.Type.TROPHY, 0));
        carte.add(new Card(Card.Type.FRAME,0 ));
        l.insert(carte, 0);
        carte.clear();
        carte.add(new Card(Card.Type.CAT, 1));
        carte.add(new Card(Card.Type.FRAME, 1));
        carte.add(new Card(Card.Type.FRAME, 2));
        carte.add(new Card(Card.Type.PLANT, 2));
        carte.add(new Card(Card.Type.TROPHY, 1));
        carte.add(new Card(Card.Type.PLANT, 3));
        l.insert(carte, 1);
        carte.clear();
        carte.add(new Card(Card.Type.GAME, 1));
        carte.add(new Card(Card.Type.CAT, 2));
        carte.add(new Card(Card.Type.BOOK, 0));
        carte.add(new Card(Card.Type.BOOK, 1));
        carte.add(new Card(Card.Type.BOOK, 2));
        carte.add(new Card(Card.Type.CAT, 3));
        l.insert(carte, 2);
        carte.clear();
        carte.add(new Card(Card.Type.CAT, 4));
        carte.add(new Card(Card.Type.CAT, 5));
        carte.add(new Card(Card.Type.FRAME, 3));
        carte.add(new Card(Card.Type.FRAME, 4));
        carte.add(new Card(Card.Type.BOOK, 6));
        carte.add(new Card(Card.Type.TROPHY, 8));
        l.insert(carte, 3);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 9));
        carte.add(new Card(Card.Type.PLANT, 7));
        carte.add(new Card(Card.Type.CAT, 9));
        carte.add(new Card(Card.Type.GAME, 8));
        carte.add(new Card(Card.Type.PLANT, 9));
        carte.add(new Card(Card.Type.BOOK, 10));
        l.insert(carte, 4);

        Assertions.assertEquals(12, pob5.getMaxPoints(l).getValue());
    }



    @Test
    void PrivObjFiveVerified_2() {
        PrivateObjective pob5 = new PrivateObjective(4);
        carte.add(new Card(Card.Type.GAME, 0));
        carte.add(new Card(Card.Type.PLANT, 0));
        carte.add(new Card(Card.Type.CAT, 0));
        carte.add(new Card(Card.Type.PLANT, 1));
        carte.add(new Card(Card.Type.TROPHY, 0));
        carte.add(new Card(Card.Type.FRAME,0 ));
        l.insert(carte, 0);
        carte.clear();
        carte.add(new Card(Card.Type.CAT, 1));
        carte.add(new Card(Card.Type.FRAME, 1));
        carte.add(new Card(Card.Type.GAME, 12));
        carte.add(new Card(Card.Type.FRAME, 12));
        carte.add(new Card(Card.Type.BOOK, 12));
        carte.add(new Card(Card.Type.PLANT, 3));
        l.insert(carte, 1);
        carte.clear();
        carte.add(new Card(Card.Type.GAME, 1));
        carte.add(new Card(Card.Type.CAT, 2));
        carte.add(new Card(Card.Type.BOOK, 0));
        carte.add(new Card(Card.Type.BOOK, 1));
        carte.add(new Card(Card.Type.BOOK, 2));
        carte.add(new Card(Card.Type.CAT, 3));
        l.insert(carte, 2);
        carte.clear();
        carte.add(new Card(Card.Type.CAT, 4));
        carte.add(new Card(Card.Type.CAT, 5));
        carte.add(new Card(Card.Type.FRAME, 3));
        carte.add(new Card(Card.Type.FRAME, 4));
        carte.add(new Card(Card.Type.BOOK, 6));
        carte.add(new Card(Card.Type.TROPHY, 8));
        l.insert(carte, 3);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 9));
        carte.add(new Card(Card.Type.PLANT, 7));
        carte.add(new Card(Card.Type.CAT, 9));
        carte.add(new Card(Card.Type.GAME, 8));
        carte.add(new Card(Card.Type.PLANT, 9));
        carte.add(new Card(Card.Type.BOOK, 10));
        l.insert(carte, 4);

        Assertions.assertEquals(6, pob5.getMaxPoints(l).getValue());
    }



    @Test
    void PrivObjFiveVerified_3() {
        PrivateObjective pob5 = new PrivateObjective(4);
        carte.add(new Card(Card.Type.CAT, 11));
        carte.add(new Card(Card.Type.PLANT, 0));
        carte.add(new Card(Card.Type.CAT, 0));
        carte.add(new Card(Card.Type.PLANT, 1));
        carte.add(new Card(Card.Type.TROPHY, 0));
        carte.add(new Card(Card.Type.FRAME,0 ));
        l.insert(carte, 0);
        carte.clear();
        carte.add(new Card(Card.Type.CAT, 1));
        carte.add(new Card(Card.Type.FRAME, 1));
        carte.add(new Card(Card.Type.BOOK, 13));
        carte.add(new Card(Card.Type.FRAME, 12));
        carte.add(new Card(Card.Type.BOOK, 12));
        carte.add(new Card(Card.Type.PLANT, 3));
        l.insert(carte, 1);
        carte.clear();
        carte.add(new Card(Card.Type.GAME, 1));
        carte.add(new Card(Card.Type.CAT, 2));
        carte.add(new Card(Card.Type.FRAME, 11));
        carte.add(new Card(Card.Type.BOOK, 1));
        carte.add(new Card(Card.Type.BOOK, 2));
        carte.add(new Card(Card.Type.CAT, 3));
        l.insert(carte, 2);
        carte.clear();
        carte.add(new Card(Card.Type.GAME, 14));
        carte.add(new Card(Card.Type.CAT, 5));
        carte.add(new Card(Card.Type.FRAME, 3));
        carte.add(new Card(Card.Type.FRAME, 4));
        carte.add(new Card(Card.Type.BOOK, 6));
        carte.add(new Card(Card.Type.TROPHY, 8));
        l.insert(carte, 3);
        carte.clear();
        carte.add(new Card(Card.Type.CAT, 14));
        carte.add(new Card(Card.Type.CAT, 12));
        carte.add(new Card(Card.Type.CAT, 9));
        carte.add(new Card(Card.Type.GAME, 8));
        carte.add(new Card(Card.Type.PLANT, 9));
        carte.add(new Card(Card.Type.BOOK, 10));
        l.insert(carte, 4);

        Assertions.assertEquals(0, pob5.getMaxPoints(l).getValue());
    }



    //Private Objective 6 id=5//


    @Test
    void PrivObjSixVerified_1() {
        PrivateObjective pob6 = new PrivateObjective(5);
        carte.add(new Card(Card.Type.PLANT, 0));
        carte.add(new Card(Card.Type.PLANT, 1));
        carte.add(new Card(Card.Type.FRAME, 0));
        carte.add(new Card(Card.Type.BOOK, 1));
        carte.add(new Card(Card.Type.BOOK, 0));
        carte.add(new Card(Card.Type.GAME, 0));
        l.insert(carte, 0);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 1));
        carte.add(new Card(Card.Type.GAME, 1));
        carte.add(new Card(Card.Type.FRAME, 2));
        carte.add(new Card(Card.Type.GAME, 2));
        carte.add(new Card(Card.Type.PLANT, 2));
        carte.add(new Card(Card.Type.GAME, 3));
        l.insert(carte, 1);
        carte.clear();
        carte.add(new Card(Card.Type.CAT, 1));
        carte.add(new Card(Card.Type.CAT, 2));
        carte.add(new Card(Card.Type.PLANT, 3));
        carte.add(new Card(Card.Type.CAT, 3));
        carte.add(new Card(Card.Type.FRAME, 3));
        carte.add(new Card(Card.Type.TROPHY, 3));
        l.insert(carte, 2);
        carte.clear();
        carte.add(new Card(Card.Type.CAT, 4));
        carte.add(new Card(Card.Type.FRAME, 5));
        carte.add(new Card(Card.Type.CAT, 5));
        carte.add(new Card(Card.Type.BOOK, 4));
        carte.add(new Card(Card.Type.TROPHY, 4));
        carte.add(new Card(Card.Type.CAT, 6));
        l.insert(carte, 3);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 7));
        carte.add(new Card(Card.Type.FRAME, 8));
        carte.add(new Card(Card.Type.PLANT, 9));
        carte.add(new Card(Card.Type.PLANT, 8));
        carte.add(new Card(Card.Type.PLANT, 7));
        carte.add(new Card(Card.Type.CAT, 9));
        l.insert(carte, 4);

        Assertions.assertEquals(12, pob6.getMaxPoints(l).getValue());
    }




    @Test
    void PrivObjSixVerified_2() {
        PrivateObjective pob6 = new PrivateObjective(5);
        carte.add(new Card(Card.Type.PLANT, 0));
        carte.add(new Card(Card.Type.PLANT, 1));
        carte.add(new Card(Card.Type.FRAME, 0));
        carte.add(new Card(Card.Type.BOOK, 1));
        carte.add(new Card(Card.Type.BOOK, 0));
        carte.add(new Card(Card.Type.GAME, 0));
        l.insert(carte, 0);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 1));
        carte.add(new Card(Card.Type.GAME, 1));
        carte.add(new Card(Card.Type.FRAME, 2));
        carte.add(new Card(Card.Type.GAME, 2));
        carte.add(new Card(Card.Type.PLANT, 2));
        carte.add(new Card(Card.Type.GAME, 3));
        l.insert(carte, 1);
        carte.clear();
        carte.add(new Card(Card.Type.CAT, 1));
        carte.add(new Card(Card.Type.CAT, 2));
        carte.add(new Card(Card.Type.PLANT, 3));
        carte.add(new Card(Card.Type.CAT, 3));
        carte.add(new Card(Card.Type.FRAME, 3));
        carte.add(new Card(Card.Type.TROPHY, 3));
        l.insert(carte, 2);
        carte.clear();
        carte.add(new Card(Card.Type.CAT, 4));
        carte.add(new Card(Card.Type.CAT, 15));
        carte.add(new Card(Card.Type.CAT, 5));
        carte.add(new Card(Card.Type.BOOK, 4));
        carte.add(new Card(Card.Type.TROPHY, 4));
        carte.add(new Card(Card.Type.CAT, 6));
        l.insert(carte, 3);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 7));
        carte.add(new Card(Card.Type.FRAME, 8));
        carte.add(new Card(Card.Type.PLANT, 9));
        carte.add(new Card(Card.Type.PLANT, 8));
        carte.add(new Card(Card.Type.PLANT, 7));
        carte.add(new Card(Card.Type.CAT, 9));
        l.insert(carte, 4);

        Assertions.assertEquals(9, pob6.getMaxPoints(l).getValue());
    }




    @Test
    void PrivObjSixVerified_3() {
        PrivateObjective pob6 = new PrivateObjective(5);
        carte.add(new Card(Card.Type.CAT, 10));
        carte.add(new Card(Card.Type.PLANT, 1));
        carte.add(new Card(Card.Type.FRAME, 0));
        carte.add(new Card(Card.Type.BOOK, 1));
        carte.add(new Card(Card.Type.BOOK, 0));
        carte.add(new Card(Card.Type.GAME, 0));
        l.insert(carte, 0);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 1));
        carte.add(new Card(Card.Type.FRAME, 11));
        carte.add(new Card(Card.Type.FRAME, 2));
        carte.add(new Card(Card.Type.GAME, 2));
        carte.add(new Card(Card.Type.PLANT, 2));
        carte.add(new Card(Card.Type.GAME, 3));
        l.insert(carte, 1);
        carte.clear();
        carte.add(new Card(Card.Type.CAT, 1));
        carte.add(new Card(Card.Type.CAT, 2));
        carte.add(new Card(Card.Type.PLANT, 3));
        carte.add(new Card(Card.Type.CAT, 3));
        carte.add(new Card(Card.Type.FRAME, 3));
        carte.add(new Card(Card.Type.PLANT, 13));
        l.insert(carte, 2);
        carte.clear();
        carte.add(new Card(Card.Type.CAT, 4));
        carte.add(new Card(Card.Type.FRAME, 5));
        carte.add(new Card(Card.Type.CAT, 5));
        carte.add(new Card(Card.Type.GAME, 14));
        carte.add(new Card(Card.Type.TROPHY, 4));
        carte.add(new Card(Card.Type.CAT, 6));
        l.insert(carte, 3);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 7));
        carte.add(new Card(Card.Type.FRAME, 8));
        carte.add(new Card(Card.Type.PLANT, 9));
        carte.add(new Card(Card.Type.PLANT, 8));
        carte.add(new Card(Card.Type.PLANT, 7));
        carte.add(new Card(Card.Type.BOOK, 19));
        l.insert(carte, 4);

        Assertions.assertEquals(1, pob6.getMaxPoints(l).getValue());
    }



    //Private Objective 7 id=6//


    @Test
    void PrivObjSevenVerified_1() {
        PrivateObjective pob7 = new PrivateObjective(6);
        carte.add(new Card(Card.Type.BOOK, 0));
        carte.add(new Card(Card.Type.GAME, 1));
        carte.add(new Card(Card.Type.TROPHY, 0));
        carte.add(new Card(Card.Type.BOOK, 1));
        carte.add(new Card(Card.Type.CAT, 1));
        carte.add(new Card(Card.Type.CAT, 0));
        l.insert(carte, 0);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 1));
        carte.add(new Card(Card.Type.PLANT, 1));
        carte.add(new Card(Card.Type.GAME, 2));
        carte.add(new Card(Card.Type.PLANT, 2));
        carte.add(new Card(Card.Type.PLANT, 3));
        carte.add(new Card(Card.Type.PLANT, 4));
        l.insert(carte, 1);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK, 2));
        carte.add(new Card(Card.Type.CAT, 3));
        carte.add(new Card(Card.Type.PLANT, 5));
        carte.add(new Card(Card.Type.PLANT, 6));
        carte.add(new Card(Card.Type.GAME, 3));
        carte.add(new Card(Card.Type.PLANT, 7));
        l.insert(carte, 2);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 4));
        carte.add(new Card(Card.Type.BOOK, 3));
        carte.add(new Card(Card.Type.FRAME, 5));
        carte.add(new Card(Card.Type.GAME, 4));
        carte.add(new Card(Card.Type.FRAME, 6));
        carte.add(new Card(Card.Type.FRAME, 7));
        l.insert(carte, 3);
        carte.clear();
        carte.add(new Card(Card.Type.CAT, 4));
        carte.add(new Card(Card.Type.GAME, 5));
        carte.add(new Card(Card.Type.FRAME, 8));
        carte.add(new Card(Card.Type.CAT, 5));
        carte.add(new Card(Card.Type.TROPHY, 3));
        carte.add(new Card(Card.Type.GAME, 6));
        l.insert(carte, 4);

        Assertions.assertEquals(12, pob7.getMaxPoints(l).getValue());
    }



    @Test
    void PrivObjSevenVerified_2() {
        PrivateObjective pob7 = new PrivateObjective(6);
        carte.add(new Card(Card.Type.BOOK, 0));
        carte.add(new Card(Card.Type.GAME, 1));
        carte.add(new Card(Card.Type.PLANT, 10));
        carte.add(new Card(Card.Type.BOOK, 1));
        carte.add(new Card(Card.Type.CAT, 1));
        carte.add(new Card(Card.Type.GAME, 10));
        l.insert(carte, 0);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 1));
        carte.add(new Card(Card.Type.PLANT, 1));
        carte.add(new Card(Card.Type.GAME, 2));
        carte.add(new Card(Card.Type.GAME, 12));
        carte.add(new Card(Card.Type.PLANT, 3));
        carte.add(new Card(Card.Type.PLANT, 4));
        l.insert(carte, 1);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK, 2));
        carte.add(new Card(Card.Type.CAT, 3));
        carte.add(new Card(Card.Type.PLANT, 5));
        carte.add(new Card(Card.Type.PLANT, 6));
        carte.add(new Card(Card.Type.GAME, 3));
        carte.add(new Card(Card.Type.PLANT, 7));
        l.insert(carte, 2);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 4));
        carte.add(new Card(Card.Type.BOOK, 3));
        carte.add(new Card(Card.Type.FRAME, 5));
        carte.add(new Card(Card.Type.GAME, 4));
        carte.add(new Card(Card.Type.FRAME, 6));
        carte.add(new Card(Card.Type.FRAME, 7));
        l.insert(carte, 3);
        carte.clear();
        carte.add(new Card(Card.Type.CAT, 4));
        carte.add(new Card(Card.Type.GAME, 5));
        carte.add(new Card(Card.Type.FRAME, 8));
        carte.add(new Card(Card.Type.CAT, 5));
        carte.add(new Card(Card.Type.TROPHY, 3));
        carte.add(new Card(Card.Type.GAME, 6));
        l.insert(carte, 4);

        Assertions.assertEquals(4, pob7.getMaxPoints(l).getValue());
    }




    @Test
    void PrivObjSevenVerified_3() {
        PrivateObjective pob7 = new PrivateObjective(6);
        carte.add(new Card(Card.Type.BOOK, 0));
        carte.add(new Card(Card.Type.GAME, 1));
        carte.add(new Card(Card.Type.CAT, 10));
        carte.add(new Card(Card.Type.BOOK, 1));
        carte.add(new Card(Card.Type.CAT, 1));
        carte.add(new Card(Card.Type.CAT, 0));
        l.insert(carte, 0);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 1));
        carte.add(new Card(Card.Type.PLANT, 1));
        carte.add(new Card(Card.Type.GAME, 2));
        carte.add(new Card(Card.Type.CAT, 12));
        carte.add(new Card(Card.Type.PLANT, 3));
        carte.add(new Card(Card.Type.PLANT, 4));
        l.insert(carte, 1);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK, 2));
        carte.add(new Card(Card.Type.CAT, 3));
        carte.add(new Card(Card.Type.PLANT, 5));
        carte.add(new Card(Card.Type.PLANT, 6));
        carte.add(new Card(Card.Type.GAME, 3));
        carte.add(new Card(Card.Type.PLANT, 7));
        l.insert(carte, 2);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 4));
        carte.add(new Card(Card.Type.BOOK, 3));
        carte.add(new Card(Card.Type.FRAME, 5));
        carte.add(new Card(Card.Type.GAME, 4));
        carte.add(new Card(Card.Type.BOOK, 16));
        carte.add(new Card(Card.Type.FRAME, 7));
        l.insert(carte, 3);
        carte.clear();
        carte.add(new Card(Card.Type.CAT, 4));
        carte.add(new Card(Card.Type.FRAME, 15));
        carte.add(new Card(Card.Type.FRAME, 8));
        carte.add(new Card(Card.Type.CAT, 5));
        carte.add(new Card(Card.Type.TROPHY, 3));
        carte.add(new Card(Card.Type.GAME, 6));
        l.insert(carte, 4);

        Assertions.assertEquals(2, pob7.getMaxPoints(l).getValue());
    }



    //Private Objective 8 id=7//


    @Test
    void PrivObjEightVerified_1() {
        PrivateObjective pob8 = new PrivateObjective(7);
        carte.add(new Card(Card.Type.CAT, 0));
        carte.add(new Card(Card.Type.PLANT, 1));
        carte.add(new Card(Card.Type.PLANT, 0));
        carte.add(new Card(Card.Type.GAME, 1));
        carte.add(new Card(Card.Type.CAT, 1));
        carte.add(new Card(Card.Type.GAME, 0));
        l.insert(carte, 0);
        carte.clear();
        carte.add(new Card(Card.Type.GAME, 2));
        carte.add(new Card(Card.Type.BOOK, 1));
        carte.add(new Card(Card.Type.TROPHY, 2));
        carte.add(new Card(Card.Type.GAME, 3));
        carte.add(new Card(Card.Type.CAT, 2));
        carte.add(new Card(Card.Type.PLANT, 3));
        l.insert(carte, 1);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT, 4));
        carte.add(new Card(Card.Type.PLANT, 5));
        carte.add(new Card(Card.Type.TROPHY, 3));
        carte.add(new Card(Card.Type.TROPHY, 4));
        carte.add(new Card(Card.Type.BOOK, 3));
        carte.add(new Card(Card.Type.TROPHY, 5));
        l.insert(carte, 2);
        carte.clear();
        carte.add(new Card(Card.Type.GAME, 4));
        carte.add(new Card(Card.Type.BOOK, 4));
        carte.add(new Card(Card.Type.CAT, 4));
        carte.add(new Card(Card.Type.GAME, 5));
        carte.add(new Card(Card.Type.TROPHY, 6));
        carte.add(new Card(Card.Type.BOOK, 5));
        l.insert(carte, 3);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 4));
        carte.add(new Card(Card.Type.TROPHY, 7));
        carte.add(new Card(Card.Type.CAT, 6));
        carte.add(new Card(Card.Type.PLANT, 7));
        carte.add(new Card(Card.Type.PLANT, 8));
        carte.add(new Card(Card.Type.FRAME, 6));
        l.insert(carte, 4);

        Assertions.assertEquals(12, pob8.getMaxPoints(l).getValue());
    }



    @Test
    void PrivObjEightVerified_2() {
        PrivateObjective pob8 = new PrivateObjective(7);
        carte.add(new Card(Card.Type.CAT, 0));
        carte.add(new Card(Card.Type.PLANT, 1));
        carte.add(new Card(Card.Type.PLANT, 0));
        carte.add(new Card(Card.Type.GAME, 1));
        carte.add(new Card(Card.Type.CAT, 1));
        carte.add(new Card(Card.Type.GAME, 0));
        l.insert(carte, 0);
        carte.clear();
        carte.add(new Card(Card.Type.GAME, 2));
        carte.add(new Card(Card.Type.BOOK, 1));
        carte.add(new Card(Card.Type.TROPHY, 2));
        carte.add(new Card(Card.Type.GAME, 3));
        carte.add(new Card(Card.Type.CAT, 2));
        carte.add(new Card(Card.Type.PLANT, 3));
        l.insert(carte, 1);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT, 4));
        carte.add(new Card(Card.Type.PLANT, 5));
        carte.add(new Card(Card.Type.TROPHY, 3));
        carte.add(new Card(Card.Type.GAME, 14));
        carte.add(new Card(Card.Type.BOOK, 3));
        carte.add(new Card(Card.Type.TROPHY, 5));
        l.insert(carte, 2);
        carte.clear();
        carte.add(new Card(Card.Type.GAME, 4));
        carte.add(new Card(Card.Type.TROPHY, 10));
        carte.add(new Card(Card.Type.CAT, 4));
        carte.add(new Card(Card.Type.GAME, 5));
        carte.add(new Card(Card.Type.TROPHY, 6));
        carte.add(new Card(Card.Type.BOOK, 5));
        l.insert(carte, 3);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 4));
        carte.add(new Card(Card.Type.TROPHY, 7));
        carte.add(new Card(Card.Type.CAT, 6));
        carte.add(new Card(Card.Type.PLANT, 7));
        carte.add(new Card(Card.Type.PLANT, 8));
        carte.add(new Card(Card.Type.FRAME, 6));
        l.insert(carte, 4);

        Assertions.assertEquals(6, pob8.getMaxPoints(l).getValue());
    }




    @Test
    void PrivObjEightVerified_3() {
        PrivateObjective pob8 = new PrivateObjective(7);
        carte.add(new Card(Card.Type.CAT, 0));
        carte.add(new Card(Card.Type.PLANT, 1));
        carte.add(new Card(Card.Type.GAME, 10));
        carte.add(new Card(Card.Type.GAME, 1));
        carte.add(new Card(Card.Type.CAT, 1));
        carte.add(new Card(Card.Type.GAME, 0));
        l.insert(carte, 0);
        carte.clear();
        carte.add(new Card(Card.Type.GAME, 2));
        carte.add(new Card(Card.Type.BOOK, 1));
        carte.add(new Card(Card.Type.TROPHY, 2));
        carte.add(new Card(Card.Type.GAME, 3));
        carte.add(new Card(Card.Type.PLANT, 12));
        carte.add(new Card(Card.Type.PLANT, 3));
        l.insert(carte, 1);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT, 4));
        carte.add(new Card(Card.Type.PLANT, 5));
        carte.add(new Card(Card.Type.TROPHY, 3));
        carte.add(new Card(Card.Type.CAT, 14));
        carte.add(new Card(Card.Type.BOOK, 3));
        carte.add(new Card(Card.Type.TROPHY, 5));
        l.insert(carte, 2);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK, 16));
        carte.add(new Card(Card.Type.FRAME, 15));
        carte.add(new Card(Card.Type.CAT, 4));
        carte.add(new Card(Card.Type.GAME, 5));
        carte.add(new Card(Card.Type.TROPHY, 6));
        carte.add(new Card(Card.Type.BOOK, 5));
        l.insert(carte, 3);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 4));
        carte.add(new Card(Card.Type.TROPHY, 7));
        carte.add(new Card(Card.Type.CAT, 6));
        carte.add(new Card(Card.Type.PLANT, 7));
        carte.add(new Card(Card.Type.PLANT, 8));
        carte.add(new Card(Card.Type.CAT, 19));
        l.insert(carte, 4);

        Assertions.assertEquals(0, pob8.getMaxPoints(l).getValue());
    }




    //Private Objective 9 id=8//


    @Test
    void PrivObjNineVerified_1() {
        PrivateObjective pob9 = new PrivateObjective(8);
        carte.add(new Card(Card.Type.FRAME, 0));
        carte.add(new Card(Card.Type.PLANT, 1));
        carte.add(new Card(Card.Type.TROPHY, 0));
        carte.add(new Card(Card.Type.BOOK, 0));
        carte.add(new Card(Card.Type.TROPHY, 1));
        carte.add(new Card(Card.Type.PLANT, 0));
        l.insert(carte, 0);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT, 2));
        carte.add(new Card(Card.Type.TROPHY, 2));
        carte.add(new Card(Card.Type.PLANT, 3));
        carte.add(new Card(Card.Type.CAT, 0));
        carte.add(new Card(Card.Type.TROPHY, 3));
        carte.add(new Card(Card.Type.CAT, 1));
        l.insert(carte, 1);
        carte.clear();
        carte.add(new Card(Card.Type.CAT, 2));
        carte.add(new Card(Card.Type.CAT, 3));
        carte.add(new Card(Card.Type.GAME, 0));
        carte.add(new Card(Card.Type.CAT, 4));
        carte.add(new Card(Card.Type.TROPHY, 3));
        carte.add(new Card(Card.Type.GAME, 1));
        l.insert(carte, 2);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 2));
        carte.add(new Card(Card.Type.GAME, 2));
        carte.add(new Card(Card.Type.GAME, 3));
        carte.add(new Card(Card.Type.GAME, 4));
        carte.add(new Card(Card.Type.FRAME, 3));
        carte.add(new Card(Card.Type.PLANT, 4));
        l.insert(carte, 3);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK, 1));
        carte.add(new Card(Card.Type.PLANT, 5));
        carte.add(new Card(Card.Type.BOOK, 2));
        carte.add(new Card(Card.Type.BOOK, 3));
        carte.add(new Card(Card.Type.PLANT, 6));
        carte.add(new Card(Card.Type.BOOK, 4));
        l.insert(carte, 4);

        Assertions.assertEquals(12, pob9.getMaxPoints(l).getValue());
    }



    @Test
    void PrivObjNineVerified_2() {
        PrivateObjective pob9 = new PrivateObjective(8);
        carte.add(new Card(Card.Type.TROPHY, 10));
        carte.add(new Card(Card.Type.PLANT, 1));
        carte.add(new Card(Card.Type.TROPHY, 0));
        carte.add(new Card(Card.Type.BOOK, 0));
        carte.add(new Card(Card.Type.TROPHY, 1));
        carte.add(new Card(Card.Type.PLANT, 0));
        l.insert(carte, 0);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT, 2));
        carte.add(new Card(Card.Type.FRAME, 12));
        carte.add(new Card(Card.Type.PLANT, 3));
        carte.add(new Card(Card.Type.CAT, 0));
        carte.add(new Card(Card.Type.TROPHY, 3));
        carte.add(new Card(Card.Type.CAT, 1));
        l.insert(carte, 1);
        carte.clear();
        carte.add(new Card(Card.Type.CAT, 2));
        carte.add(new Card(Card.Type.CAT, 3));
        carte.add(new Card(Card.Type.GAME, 0));
        carte.add(new Card(Card.Type.TROPHY, 14));
        carte.add(new Card(Card.Type.TROPHY, 3));
        carte.add(new Card(Card.Type.GAME, 1));
        l.insert(carte, 2);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 2));
        carte.add(new Card(Card.Type.GAME, 2));
        carte.add(new Card(Card.Type.GAME, 3));
        carte.add(new Card(Card.Type.GAME, 4));
        carte.add(new Card(Card.Type.FRAME, 3));
        carte.add(new Card(Card.Type.PLANT, 4));
        l.insert(carte, 3);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK, 1));
        carte.add(new Card(Card.Type.PLANT, 5));
        carte.add(new Card(Card.Type.BOOK, 2));
        carte.add(new Card(Card.Type.BOOK, 3));
        carte.add(new Card(Card.Type.PLANT, 6));
        carte.add(new Card(Card.Type.BOOK, 4));
        l.insert(carte, 4);

        Assertions.assertEquals(4, pob9.getMaxPoints(l).getValue());
    }



    @Test
    void PrivObjNineVerified_3() {
        PrivateObjective pob9 = new PrivateObjective(8);
        carte.add(new Card(Card.Type.FRAME, 0));
        carte.add(new Card(Card.Type.PLANT, 1));
        carte.add(new Card(Card.Type.TROPHY, 0));
        carte.add(new Card(Card.Type.BOOK, 0));
        carte.add(new Card(Card.Type.TROPHY, 1));
        carte.add(new Card(Card.Type.PLANT, 0));
        l.insert(carte, 0);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT, 2));
        carte.add(new Card(Card.Type.FRAME, 12));
        carte.add(new Card(Card.Type.PLANT, 3));
        carte.add(new Card(Card.Type.CAT, 0));
        carte.add(new Card(Card.Type.TROPHY, 3));
        carte.add(new Card(Card.Type.CAT, 1));
        l.insert(carte, 1);
        carte.clear();
        carte.add(new Card(Card.Type.CAT, 2));
        carte.add(new Card(Card.Type.CAT, 3));
        carte.add(new Card(Card.Type.GAME, 0));
        carte.add(new Card(Card.Type.GAME, 14));
        carte.add(new Card(Card.Type.TROPHY, 3));
        carte.add(new Card(Card.Type.CAT, 11));
        l.insert(carte, 2);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 2));
        carte.add(new Card(Card.Type.GAME, 2));
        carte.add(new Card(Card.Type.GAME, 3));
        carte.add(new Card(Card.Type.GAME, 4));
        carte.add(new Card(Card.Type.FRAME, 3));
        carte.add(new Card(Card.Type.PLANT, 4));
        l.insert(carte, 3);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK, 1));
        carte.add(new Card(Card.Type.BOOK, 15));
        carte.add(new Card(Card.Type.PLANT, 20));
        carte.add(new Card(Card.Type.BOOK, 3));
        carte.add(new Card(Card.Type.PLANT, 6));
        carte.add(new Card(Card.Type.BOOK, 4));
        l.insert(carte, 4);

        Assertions.assertEquals(1, pob9.getMaxPoints(l).getValue());
    }




    //Private Objective 10 id=9//


    @Test
    void PrivObjTenVerified_1() {
        PrivateObjective pob10 = new PrivateObjective(9);
        carte.add(new Card(Card.Type.PLANT, 0));
        carte.add(new Card(Card.Type.BOOK, 1));
        carte.add(new Card(Card.Type.TROPHY, 0));
        carte.add(new Card(Card.Type.BOOK, 0));
        carte.add(new Card(Card.Type.CAT, 0));
        carte.add(new Card(Card.Type.GAME, 0));
        l.insert(carte, 0);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT, 1));
        carte.add(new Card(Card.Type.FRAME, 1));
        carte.add(new Card(Card.Type.TROPHY, 1));
        carte.add(new Card(Card.Type.FRAME, 2));
        carte.add(new Card(Card.Type.GAME, 1));
        carte.add(new Card(Card.Type.TROPHY, 2));
        l.insert(carte, 1);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK, 2));
        carte.add(new Card(Card.Type.TROPHY, 3));
        carte.add(new Card(Card.Type.GAME, 2));
        carte.add(new Card(Card.Type.GAME, 3));
        carte.add(new Card(Card.Type.BOOK, 3));
        carte.add(new Card(Card.Type.CAT, 1));
        l.insert(carte, 2);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT, 2));
        carte.add(new Card(Card.Type.CAT, 2));
        carte.add(new Card(Card.Type.CAT, 3));
        carte.add(new Card(Card.Type.PLANT, 4));
        carte.add(new Card(Card.Type.PLANT, 3));
        carte.add(new Card(Card.Type.BOOK, 4));
        l.insert(carte, 3);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 3));
        carte.add(new Card(Card.Type.CAT, 5));
        carte.add(new Card(Card.Type.FRAME, 4));
        carte.add(new Card(Card.Type.PLANT, 6));
        carte.add(new Card(Card.Type.FRAME, 6));
        carte.add(new Card(Card.Type.TROPHY, 5));
        l.insert(carte, 4);

        Assertions.assertEquals(12, pob10.getMaxPoints(l).getValue());
    }





    @Test
    void PrivObjTenVerified_2() {
        PrivateObjective pob10 = new PrivateObjective(9);
        carte.add(new Card(Card.Type.PLANT, 0));
        carte.add(new Card(Card.Type.BOOK, 1));
        carte.add(new Card(Card.Type.TROPHY, 0));
        carte.add(new Card(Card.Type.BOOK, 0));
        carte.add(new Card(Card.Type.CAT, 0));
        carte.add(new Card(Card.Type.GAME, 0));
        l.insert(carte, 0);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT, 1));
        carte.add(new Card(Card.Type.FRAME, 1));
        carte.add(new Card(Card.Type.TROPHY, 1));
        carte.add(new Card(Card.Type.FRAME, 2));
        carte.add(new Card(Card.Type.GAME, 1));
        carte.add(new Card(Card.Type.TROPHY, 2));
        l.insert(carte, 1);
        carte.clear();
        carte.add(new Card(Card.Type.GAME, 12));
        carte.add(new Card(Card.Type.TROPHY, 3));
        carte.add(new Card(Card.Type.GAME, 2));
        carte.add(new Card(Card.Type.GAME, 3));
        carte.add(new Card(Card.Type.BOOK, 3));
        carte.add(new Card(Card.Type.CAT, 1));
        l.insert(carte, 2);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT, 2));
        carte.add(new Card(Card.Type.CAT, 2));
        carte.add(new Card(Card.Type.FRAME, 13));
        carte.add(new Card(Card.Type.PLANT, 4));
        carte.add(new Card(Card.Type.PLANT, 3));
        carte.add(new Card(Card.Type.BOOK, 4));
        l.insert(carte, 3);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 3));
        carte.add(new Card(Card.Type.CAT, 5));
        carte.add(new Card(Card.Type.PLANT, 14));
        carte.add(new Card(Card.Type.PLANT, 6));
        carte.add(new Card(Card.Type.FRAME, 6));
        carte.add(new Card(Card.Type.TROPHY, 5));
        l.insert(carte, 4);

        Assertions.assertEquals(9, pob10.getMaxPoints(l).getValue());
    }





    @Test
    void PrivObjTenVerified_3() {
        PrivateObjective pob10 = new PrivateObjective(9);
        carte.add(new Card(Card.Type.PLANT, 0));
        carte.add(new Card(Card.Type.BOOK, 1));
        carte.add(new Card(Card.Type.TROPHY, 0));
        carte.add(new Card(Card.Type.GAME, 10));
        carte.add(new Card(Card.Type.CAT, 0));
        carte.add(new Card(Card.Type.GAME, 0));
        l.insert(carte, 0);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT, 1));
        carte.add(new Card(Card.Type.PLANT, 13));
        carte.add(new Card(Card.Type.TROPHY, 1));
        carte.add(new Card(Card.Type.FRAME, 2));
        carte.add(new Card(Card.Type.CAT, 11));
        carte.add(new Card(Card.Type.TROPHY, 2));
        l.insert(carte, 1);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK, 2));
        carte.add(new Card(Card.Type.TROPHY, 3));
        carte.add(new Card(Card.Type.GAME, 2));
        carte.add(new Card(Card.Type.GAME, 3));
        carte.add(new Card(Card.Type.BOOK, 3));
        carte.add(new Card(Card.Type.CAT, 1));
        l.insert(carte, 2);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY, 17));
        carte.add(new Card(Card.Type.CAT, 2));
        carte.add(new Card(Card.Type.CAT, 3));
        carte.add(new Card(Card.Type.PLANT, 4));
        carte.add(new Card(Card.Type.PLANT, 3));
        carte.add(new Card(Card.Type.BOOK, 4));
        l.insert(carte, 3);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 3));
        carte.add(new Card(Card.Type.CAT, 5));
        carte.add(new Card(Card.Type.FRAME, 4));
        carte.add(new Card(Card.Type.PLANT, 6));
        carte.add(new Card(Card.Type.FRAME, 6));
        carte.add(new Card(Card.Type.BOOK, 19));
        l.insert(carte, 4);

        Assertions.assertEquals(1, pob10.getMaxPoints(l).getValue());
    }





    //Private Objective 11 id=10//



    @Test
    void PrivObjElevenVerified_1() {
        PrivateObjective pob11 = new PrivateObjective(10);
        carte.add(new Card(Card.Type.PLANT, 0));
        carte.add(new Card(Card.Type.TROPHY, 0));
        carte.add(new Card(Card.Type.PLANT, 1));
        carte.add(new Card(Card.Type.GAME, 0));
        carte.add(new Card(Card.Type.FRAME, 0));
        carte.add(new Card(Card.Type.CAT, 0));
        l.insert(carte, 0);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT, 2));
        carte.add(new Card(Card.Type.TROPHY, 1));
        carte.add(new Card(Card.Type.FRAME, 1));
        carte.add(new Card(Card.Type.PLANT, 3));
        carte.add(new Card(Card.Type.BOOK, 1));
        carte.add(new Card(Card.Type.GAME, 2));
        l.insert(carte, 1);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK, 2));
        carte.add(new Card(Card.Type.CAT, 2));
        carte.add(new Card(Card.Type.FRAME, 2));
        carte.add(new Card(Card.Type.PLANT, 4));
        carte.add(new Card(Card.Type.BOOK, 3));
        carte.add(new Card(Card.Type.PLANT, 5));
        l.insert(carte, 2);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY, 2));
        carte.add(new Card(Card.Type.GAME, 4));
        carte.add(new Card(Card.Type.CAT, 3));
        carte.add(new Card(Card.Type.CAT, 4));
        carte.add(new Card(Card.Type.CAT, 5));
        carte.add(new Card(Card.Type.FRAME, 4));
        l.insert(carte, 3);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK, 4));
        carte.add(new Card(Card.Type.CAT, 6));
        carte.add(new Card(Card.Type.GAME, 6));
        carte.add(new Card(Card.Type.TROPHY, 4));
        carte.add(new Card(Card.Type.PLANT, 6));
        carte.add(new Card(Card.Type.FRAME, 5));
        l.insert(carte, 4);

        Assertions.assertEquals(12, pob11.getMaxPoints(l).getValue());
    }




    @Test
    void PrivObjElevenVerified_2() {
        PrivateObjective pob11 = new PrivateObjective(10);
        carte.add(new Card(Card.Type.PLANT, 0));
        carte.add(new Card(Card.Type.TROPHY, 0));
        carte.add(new Card(Card.Type.PLANT, 1));
        carte.add(new Card(Card.Type.GAME, 0));
        carte.add(new Card(Card.Type.FRAME, 0));
        carte.add(new Card(Card.Type.CAT, 0));
        l.insert(carte, 0);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT, 2));
        carte.add(new Card(Card.Type.TROPHY, 1));
        carte.add(new Card(Card.Type.FRAME, 1));
        carte.add(new Card(Card.Type.PLANT, 3));
        carte.add(new Card(Card.Type.CAT, 11));
        carte.add(new Card(Card.Type.GAME, 2));
        l.insert(carte, 1);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK, 2));
        carte.add(new Card(Card.Type.CAT, 2));
        carte.add(new Card(Card.Type.FRAME, 2));
        carte.add(new Card(Card.Type.PLANT, 4));
        carte.add(new Card(Card.Type.BOOK, 3));
        carte.add(new Card(Card.Type.PLANT, 5));
        l.insert(carte, 2);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY, 2));
        carte.add(new Card(Card.Type.GAME, 4));
        carte.add(new Card(Card.Type.CAT, 3));
        carte.add(new Card(Card.Type.CAT, 4));
        carte.add(new Card(Card.Type.CAT, 5));
        carte.add(new Card(Card.Type.FRAME, 4));
        l.insert(carte, 3);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK, 4));
        carte.add(new Card(Card.Type.TROPHY, 16));
        carte.add(new Card(Card.Type.GAME, 6));
        carte.add(new Card(Card.Type.TROPHY, 4));
        carte.add(new Card(Card.Type.PLANT, 6));
        carte.add(new Card(Card.Type.FRAME, 5));
        l.insert(carte, 4);

        Assertions.assertEquals(6, pob11.getMaxPoints(l).getValue());
    }





    @Test
    void PrivObjElevenVerified_3() {
        PrivateObjective pob11 = new PrivateObjective(10);
        carte.add(new Card(Card.Type.PLANT, 0));
        carte.add(new Card(Card.Type.TROPHY, 0));
        carte.add(new Card(Card.Type.PLANT, 1));
        carte.add(new Card(Card.Type.BOOK, 10));
        carte.add(new Card(Card.Type.FRAME, 0));
        carte.add(new Card(Card.Type.CAT, 0));
        l.insert(carte, 0);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT, 2));
        carte.add(new Card(Card.Type.TROPHY, 1));
        carte.add(new Card(Card.Type.FRAME, 1));
        carte.add(new Card(Card.Type.PLANT, 3));
        carte.add(new Card(Card.Type.BOOK, 1));
        carte.add(new Card(Card.Type.GAME, 2));
        l.insert(carte, 1);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK, 2));
        carte.add(new Card(Card.Type.CAT, 2));
        carte.add(new Card(Card.Type.CAT, 12));
        carte.add(new Card(Card.Type.PLANT, 4));
        carte.add(new Card(Card.Type.BOOK, 3));
        carte.add(new Card(Card.Type.FRAME, 15));
        l.insert(carte, 2);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK, 15));
        carte.add(new Card(Card.Type.GAME, 4));
        carte.add(new Card(Card.Type.CAT, 3));
        carte.add(new Card(Card.Type.CAT, 4));
        carte.add(new Card(Card.Type.CAT, 5));
        carte.add(new Card(Card.Type.FRAME, 4));
        l.insert(carte, 3);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK, 4));
        carte.add(new Card(Card.Type.CAT, 6));
        carte.add(new Card(Card.Type.GAME, 6));
        carte.add(new Card(Card.Type.TROPHY, 4));
        carte.add(new Card(Card.Type.PLANT, 6));
        carte.add(new Card(Card.Type.FRAME, 5));
        l.insert(carte, 4);

        Assertions.assertEquals(2, pob11.getMaxPoints(l).getValue());
    }






    //Private Objective 12 id=11//



    @Test
    void PrivObjTwelveVerified_1() {
        PrivateObjective pob12 = new PrivateObjective(11);
        carte.add(new Card(Card.Type.CAT, 0));
        carte.add(new Card(Card.Type.BOOK, 0));
        carte.add(new Card(Card.Type.GAME, 0));
        carte.add(new Card(Card.Type.PLANT, 0));
        carte.add(new Card(Card.Type.TROPHY, 0));
        carte.add(new Card(Card.Type.PLANT, 1));
        l.insert(carte, 0);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 0));
        carte.add(new Card(Card.Type.BOOK, 1));
        carte.add(new Card(Card.Type.GAME, 1));
        carte.add(new Card(Card.Type.BOOK, 2));
        carte.add(new Card(Card.Type.PLANT, 2));
        carte.add(new Card(Card.Type.CAT, 2));
        l.insert(carte, 1);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK, 3));
        carte.add(new Card(Card.Type.GAME, 2));
        carte.add(new Card(Card.Type.PLANT, 3));
        carte.add(new Card(Card.Type.FRAME, 4));
        carte.add(new Card(Card.Type.FRAME, 3));
        carte.add(new Card(Card.Type.BOOK, 5));
        l.insert(carte, 2);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY, 2));
        carte.add(new Card(Card.Type.CAT, 4));
        carte.add(new Card(Card.Type.TROPHY, 3));
        carte.add(new Card(Card.Type.PLANT, 4));
        carte.add(new Card(Card.Type.CAT, 5));
        carte.add(new Card(Card.Type.GAME, 4));
        l.insert(carte, 3);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY, 5));
        carte.add(new Card(Card.Type.GAME, 6));
        carte.add(new Card(Card.Type.FRAME, 6));
        carte.add(new Card(Card.Type.CAT, 7));
        carte.add(new Card(Card.Type.PLANT, 6));
        carte.add(new Card(Card.Type.FRAME, 8));
        l.insert(carte, 4);

        Assertions.assertEquals(12, pob12.getMaxPoints(l).getValue());
    }




    @Test
    void PrivObjTwelveVerified_2() {
        PrivateObjective pob12 = new PrivateObjective(11);
        carte.add(new Card(Card.Type.CAT, 0));
        carte.add(new Card(Card.Type.BOOK, 0));
        carte.add(new Card(Card.Type.GAME, 0));
        carte.add(new Card(Card.Type.PLANT, 0));
        carte.add(new Card(Card.Type.TROPHY, 0));
        carte.add(new Card(Card.Type.PLANT, 1));
        l.insert(carte, 0);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 0));
        carte.add(new Card(Card.Type.BOOK, 1));
        carte.add(new Card(Card.Type.GAME, 1));
        carte.add(new Card(Card.Type.BOOK, 2));
        carte.add(new Card(Card.Type.TROPHY, 12));
        carte.add(new Card(Card.Type.CAT, 2));
        l.insert(carte, 1);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK, 3));
        carte.add(new Card(Card.Type.GAME, 2));
        carte.add(new Card(Card.Type.PLANT, 3));
        carte.add(new Card(Card.Type.FRAME, 4));
        carte.add(new Card(Card.Type.FRAME, 3));
        carte.add(new Card(Card.Type.FRAME, 15));
        l.insert(carte, 2);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY, 2));
        carte.add(new Card(Card.Type.CAT, 4));
        carte.add(new Card(Card.Type.TROPHY, 3));
        carte.add(new Card(Card.Type.PLANT, 4));
        carte.add(new Card(Card.Type.CAT, 5));
        carte.add(new Card(Card.Type.GAME, 4));
        l.insert(carte, 3);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY, 5));
        carte.add(new Card(Card.Type.CAT, 16));
        carte.add(new Card(Card.Type.FRAME, 6));
        carte.add(new Card(Card.Type.CAT, 7));
        carte.add(new Card(Card.Type.PLANT, 6));
        carte.add(new Card(Card.Type.FRAME, 8));
        l.insert(carte, 4);

        Assertions.assertEquals(4, pob12.getMaxPoints(l).getValue());
    }




    @Test
    void PrivObjTwelveVerified_3() {
        PrivateObjective pob12 = new PrivateObjective(11);
        carte.add(new Card(Card.Type.TROPHY, 10));
        carte.add(new Card(Card.Type.BOOK, 0));
        carte.add(new Card(Card.Type.GAME, 0));
        carte.add(new Card(Card.Type.PLANT, 0));
        carte.add(new Card(Card.Type.TROPHY, 0));
        carte.add(new Card(Card.Type.PLANT, 1));
        l.insert(carte, 0);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 0));
        carte.add(new Card(Card.Type.BOOK, 1));
        carte.add(new Card(Card.Type.GAME, 1));
        carte.add(new Card(Card.Type.BOOK, 2));
        carte.add(new Card(Card.Type.GAME, 12));
        carte.add(new Card(Card.Type.CAT, 2));
        l.insert(carte, 1);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK, 3));
        carte.add(new Card(Card.Type.GAME, 2));
        carte.add(new Card(Card.Type.PLANT, 3));
        carte.add(new Card(Card.Type.CAT, 14));
        carte.add(new Card(Card.Type.FRAME, 3));
        carte.add(new Card(Card.Type.FRAME, 15));
        l.insert(carte, 2);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY, 2));
        carte.add(new Card(Card.Type.CAT, 4));
        carte.add(new Card(Card.Type.GAME, 13));
        carte.add(new Card(Card.Type.PLANT, 4));
        carte.add(new Card(Card.Type.CAT, 5));
        carte.add(new Card(Card.Type.GAME, 4));
        l.insert(carte, 3);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY, 5));
        carte.add(new Card(Card.Type.CAT, 16));
        carte.add(new Card(Card.Type.FRAME, 6));
        carte.add(new Card(Card.Type.CAT, 7));
        carte.add(new Card(Card.Type.PLANT, 6));
        carte.add(new Card(Card.Type.FRAME, 8));
        l.insert(carte, 4);

        Assertions.assertEquals(0, pob12.getMaxPoints(l).getValue());
    }




    @Test
    void PrivObjTwelveVerified_4() {
        PrivateObjective pob12 = new PrivateObjective(11);
        carte.add(new Card(Card.Type.TROPHY, 10));
        carte.add(new Card(Card.Type.BOOK, 0));
        carte.add(new Card(Card.Type.GAME, 0));
        carte.add(new Card(Card.Type.PLANT, 0));
        carte.add(new Card(Card.Type.TROPHY, 0));
        carte.add(new Card(Card.Type.PLANT, 1));
        l.insert(carte, 0);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 0));
        carte.add(new Card(Card.Type.BOOK, 1));
        carte.add(new Card(Card.Type.GAME, 1));
        carte.add(new Card(Card.Type.BOOK, 2));
        carte.add(new Card(Card.Type.PLANT, 2));
        carte.add(new Card(Card.Type.CAT, 2));
        l.insert(carte, 1);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK, 3));
        carte.add(new Card(Card.Type.GAME, 2));
        carte.add(new Card(Card.Type.PLANT, 3));
        carte.add(new Card(Card.Type.FRAME, 4));
        carte.add(new Card(Card.Type.FRAME, 3));
        carte.add(new Card(Card.Type.BOOK, 5));
        l.insert(carte, 2);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY, 2));
        carte.add(new Card(Card.Type.CAT, 4));
        carte.add(new Card(Card.Type.TROPHY, 3));
        carte.add(new Card(Card.Type.PLANT, 4));
        carte.add(new Card(Card.Type.CAT, 5));
        carte.add(new Card(Card.Type.GAME, 4));
        l.insert(carte, 3);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY, 5));
        carte.add(new Card(Card.Type.GAME, 6));
        carte.add(new Card(Card.Type.FRAME, 6));
        carte.add(new Card(Card.Type.CAT, 7));
        carte.add(new Card(Card.Type.PLANT, 6));
        carte.add(new Card(Card.Type.FRAME, 8));
        l.insert(carte, 4);

        Assertions.assertEquals(9, pob12.getMaxPoints(l).getValue());
    }
}