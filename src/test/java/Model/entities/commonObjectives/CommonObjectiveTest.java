package Model.entities.commonObjectives;

import Model.entities.Card;
import Model.entities.Shelf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class CommonObjectiveTest {
    Shelf shelf;
    List<Card> carte;

    @BeforeEach
    void setup(){
        shelf = new Shelf();
        carte = new ArrayList<>();
    }

    @Test
    void ComObjOneVerified_1()  {
        CommonObjective1 obiettivo1 = new CommonObjective1(4);
        carte.add(new Card(Card.Type.FRAME, 0));  //i valori li inizializza il costruttore
        carte.add(new Card(Card.Type.PLANT, 0));
        carte.add(new Card(Card.Type.TROPHY, 0));
        carte.add(new Card(Card.Type.PLANT, 1));
        carte.add(new Card(Card.Type.GAME, 0));
        carte.add(new Card(Card.Type.CAT, 0));
        shelf.insert(carte, 0);
        //svuoto la lista
        carte.clear();
        carte.add(new Card(Card.Type.PLANT, 2));  //i valori li inizializza il costruttore
        carte.add(new Card(Card.Type.FRAME, 1));
        carte.add(new Card(Card.Type.FRAME, 2));
        carte.add(new Card(Card.Type.GAME, 1));
        carte.add(new Card(Card.Type.BOOK, 0));
        carte.add(new Card(Card.Type.CAT, 1));
        shelf.insert(carte, 1);
        carte.clear();
        carte.add(new Card(Card.Type.CAT, 2));  //i valori li inizializza il costruttore
        carte.add(new Card(Card.Type.GAME, 2));
        carte.add(new Card(Card.Type.PLANT, 3));
        carte.add(new Card(Card.Type.TROPHY, 1));
        carte.add(new Card(Card.Type.TROPHY, 2));
        carte.add(new Card(Card.Type.BOOK, 1));
        shelf.insert(carte, 2);
        carte.clear();
        carte.add(new Card(Card.Type.CAT, 3));  //i valori li inizializza il costruttore
        carte.add(new Card(Card.Type.CAT, 4));
        carte.add(new Card(Card.Type.FRAME, 3));
        carte.add(new Card(Card.Type.PLANT, 4));
        carte.add(new Card(Card.Type.BOOK, 2));
        carte.add(new Card(Card.Type.TROPHY, 3));
        shelf.insert(carte, 3);
        carte.clear();
        carte.add(new Card(Card.Type.GAME, 3));  //i valori li inizializza il costruttore
        carte.add(new Card(Card.Type.CAT, 5));
        carte.add(new Card(Card.Type.GAME, 4));
        carte.add(new Card(Card.Type.BOOK, 3));
        carte.add(new Card(Card.Type.PLANT, 5));
        carte.add(new Card(Card.Type.PLANT, 6));
        shelf.insert(carte, 4);

        Assertions.assertTrue(obiettivo1.verify(shelf), "Common Objective one is verified on this shelf");
    }


    //Altro caso specifico//

    @Test
    void ComObjOneVerified_2()  {
        CommonObjective1 obiettivo1 = new CommonObjective1(4);
        carte.add(new Card(Card.Type.TROPHY, 0));
        carte.add(new Card(Card.Type.BOOK, 0));
        carte.add(new Card(Card.Type.GAME, 0));
        carte.add(new Card(Card.Type.FRAME, 0));
        carte.add(new Card(Card.Type.CAT, 0));
        carte.add(new Card(Card.Type.PLANT, 0));
        shelf.insert(carte, 0);
        carte.clear();
        carte.add(new Card(Card.Type.CAT, 2));
        carte.add(new Card(Card.Type.BOOK, 1));
        carte.add(new Card(Card.Type.GAME, 2));
        carte.add(new Card(Card.Type.GAME, 1));
        carte.add(new Card(Card.Type.GAME, 3));
        carte.add(new Card(Card.Type.TROPHY, 1));
        shelf.insert(carte, 1);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 2));
        carte.add(new Card(Card.Type.PLANT, 2));
        carte.add(new Card(Card.Type.GAME, 4));
        carte.add(new Card(Card.Type.GAME, 5));
        carte.add(new Card(Card.Type.GAME, 6));
        carte.add(new Card(Card.Type.TROPHY, 3));
        shelf.insert(carte, 2);
        carte.clear();
        carte.add(new Card(Card.Type.CAT, 7));
        carte.add(new Card(Card.Type.GAME, 8));
        carte.add(new Card(Card.Type.PLANT, 3));
        carte.add(new Card(Card.Type.BOOK, 4));
        carte.add(new Card(Card.Type.PLANT, 5));
        carte.add(new Card(Card.Type.PLANT, 6));
        shelf.insert(carte, 3);
        carte.clear();
        carte.add(new Card(Card.Type.GAME, 9));
        carte.add(new Card(Card.Type.GAME, 10));
        carte.add(new Card(Card.Type.FRAME, 4));
        carte.add(new Card(Card.Type.PLANT, 9));
        carte.add(new Card(Card.Type.BOOK, 5));
        carte.add(new Card(Card.Type.GAME, 11));
        shelf.insert(carte, 4);

        Assertions.assertTrue(obiettivo1.verify(shelf), "Common Objective one is verified on this shelf");
        //l'obiettivo sottointende al minimo 6//
    }

    //Altro caso specifico//

    @Test
    void ComObjOneVerified_3()  {
        CommonObjective1 obiettivo1 = new CommonObjective1(4);
        carte.add(new Card(Card.Type.BOOK, 0));
        carte.add(new Card(Card.Type.FRAME, 0));
        carte.add(new Card(Card.Type.GAME, 1));
        carte.add(new Card(Card.Type.GAME, 0));
        carte.add(new Card(Card.Type.CAT, 1));
        carte.add(new Card(Card.Type.CAT, 0));
        shelf.insert(carte, 0);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 2));
        carte.add(new Card(Card.Type.BOOK, 1));
        carte.add(new Card(Card.Type.PLANT, 2));
        carte.add(new Card(Card.Type.GAME, 2));
        carte.add(new Card(Card.Type.CAT, 3));
        carte.add(new Card(Card.Type.CAT, 2));
        shelf.insert(carte, 1);
        carte.clear();
        carte.add(new Card(Card.Type.CAT, 5));
        carte.add(new Card(Card.Type.FRAME, 4));
        carte.add(new Card(Card.Type.TROPHY, 4));
        carte.add(new Card(Card.Type.PLANT, 5));
        carte.add(new Card(Card.Type.FRAME, 6));
        carte.add(new Card(Card.Type.CAT, 4));
        shelf.insert(carte, 2);
        carte.clear();
        carte.add(new Card(Card.Type.GAME, 7));
        carte.add(new Card(Card.Type.PLANT, 8));
        carte.add(new Card(Card.Type.FRAME, 7));
        carte.add(new Card(Card.Type.BOOK, 4));
        carte.add(new Card(Card.Type.FRAME, 5));
        carte.add(new Card(Card.Type.CAT, 6));
        shelf.insert(carte, 3);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 9));
        carte.add(new Card(Card.Type.TROPHY, 10));
        carte.add(new Card(Card.Type.TROPHY, 9));
        carte.add(new Card(Card.Type.PLANT, 9));
        carte.add(new Card(Card.Type.BOOK, 10));
        carte.add(new Card(Card.Type.CAT, 11));
        shelf.insert(carte, 4);

        Assertions.assertTrue(obiettivo1.verify(shelf), "Common Objective one is verified on this shelf");
    }

    //Altro caso specifico//

    @Test
    void ComObjOneVerified_4()  {
        CommonObjective1 obiettivo1 = new CommonObjective1(4);
        carte.add(new Card(Card.Type.BOOK, 0));
        carte.add(new Card(Card.Type.FRAME, 0));
        carte.add(new Card(Card.Type.GAME, 0));
        carte.add(new Card(Card.Type.GAME, 0));
        carte.add(new Card(Card.Type.CAT, 0));
        carte.add(new Card(Card.Type.CAT, 0));
        shelf.insert(carte, 0);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 0));
        carte.add(new Card(Card.Type.BOOK, 0));
        carte.add(new Card(Card.Type.PLANT, 0));
        carte.add(new Card(Card.Type.GAME, 0));
        carte.add(new Card(Card.Type.CAT, 0));
        carte.add(new Card(Card.Type.CAT, 0));
        shelf.insert(carte, 1);
        carte.clear();
        carte.add(new Card(Card.Type.CAT, 0));
        carte.add(new Card(Card.Type.FRAME, 0));
        carte.add(new Card(Card.Type.TROPHY, 0));
        carte.add(new Card(Card.Type.PLANT, 0));
        carte.add(new Card(Card.Type.FRAME, 0));
        carte.add(new Card(Card.Type.CAT, 0));
        shelf.insert(carte, 2);
        carte.clear();
        carte.add(new Card(Card.Type.GAME, 0));
        carte.add(new Card(Card.Type.PLANT, 0));
        carte.add(new Card(Card.Type.FRAME, 0));
        carte.add(new Card(Card.Type.BOOK, 0));
        carte.add(new Card(Card.Type.FRAME, 0));
        carte.add(new Card(Card.Type.CAT, 0));
        shelf.insert(carte, 3);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME, 0));
        carte.add(new Card(Card.Type.GAME, 0));
        carte.add(new Card(Card.Type.BOOK, 0));
        carte.add(new Card(Card.Type.PLANT, 0));
        carte.add(new Card(Card.Type.BOOK, 0));
        carte.add(new Card(Card.Type.CAT, 0));
        shelf.insert(carte, 4);

        Assertions.assertFalse(obiettivo1.verify(shelf), "Common Objective one is not verified on this shelf");
    }


    //Commmon objective 2//
    @Test
    void ComObjTwoVerified_1() {
        CommonObjective2 obiettivo2=new CommonObjective2(4);
        carte.add(new Card(Card.Type.FRAME,1));
        carte.add(new Card(Card.Type.GAME,1));
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.BOOK,1));
        carte.add(new Card(Card.Type.BOOK,0));
        carte.add(new Card(Card.Type.GAME,0));
        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY,0));
        carte.add(new Card(Card.Type.BOOK,2));
        carte.add(new Card(Card.Type.CAT,0));
        carte.add(new Card(Card.Type.FRAME,2));
        carte.add(new Card(Card.Type.GAME,3));
        carte.add(new Card(Card.Type.GAME,2));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY,1));
        carte.add(new Card(Card.Type.PLANT,0));
        carte.add(new Card(Card.Type.CAT,1));
        carte.add(new Card(Card.Type.GAME,4));
        carte.add(new Card(Card.Type.FRAME,3));
        carte.add(new Card(Card.Type.BOOK,3));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY,2));
        carte.add(new Card(Card.Type.PLANT,1));
        carte.add(new Card(Card.Type.GAME,5));
        carte.add(new Card(Card.Type.PLANT,2));
        carte.add(new Card(Card.Type.TROPHY,2));
        carte.add(new Card(Card.Type.PLANT,1));
        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.GAME,8));
        carte.add(new Card(Card.Type.GAME,7));
        carte.add(new Card(Card.Type.GAME,6));
        carte.add(new Card(Card.Type.PLANT,3));
        carte.add(new Card(Card.Type.BOOK,4));
        carte.add(new Card(Card.Type.TROPHY,3));
        shelf.insert(carte,4);

        Assertions.assertTrue(obiettivo2.verify(shelf), "Common Objective Two is verified on this shelf");
    }



    @Test
    void ComObjTwoVerified_2() {
        CommonObjective2 obiettivo2=new CommonObjective2(4);
        carte.add(new Card(Card.Type.BOOK,2));
        carte.add(new Card(Card.Type.GAME,1));
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.BOOK,1));
        carte.add(new Card(Card.Type.BOOK,0));
        carte.add(new Card(Card.Type.GAME,0));
        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY,0));
        carte.add(new Card(Card.Type.BOOK,3));
        carte.add(new Card(Card.Type.CAT,0));
        carte.add(new Card(Card.Type.FRAME,2));
        carte.add(new Card(Card.Type.GAME,3));
        carte.add(new Card(Card.Type.GAME,2));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY,1));
        carte.add(new Card(Card.Type.PLANT,0));
        carte.add(new Card(Card.Type.BOOK,4));
        carte.add(new Card(Card.Type.GAME,4));
        carte.add(new Card(Card.Type.FRAME,3));
        carte.add(new Card(Card.Type.BOOK,4));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY,2));
        carte.add(new Card(Card.Type.PLANT,1));
        carte.add(new Card(Card.Type.CAT,5));
        carte.add(new Card(Card.Type.BOOK,5));
        carte.add(new Card(Card.Type.TROPHY,2));
        carte.add(new Card(Card.Type.PLANT,1));
        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.GAME,8));
        carte.add(new Card(Card.Type.CAT,7));
        carte.add(new Card(Card.Type.GAME,6));
        carte.add(new Card(Card.Type.PLANT,3));
        carte.add(new Card(Card.Type.BOOK,6));
        carte.add(new Card(Card.Type.TROPHY,3));
        shelf.insert(carte,4);

        Assertions.assertTrue(obiettivo2.verify(shelf), "Common Objective Two is verified on this shelf");
    }



    @Test
    void ComObjTwoVerified_3() {
        CommonObjective2 obiettivo2=new CommonObjective2(4);
        carte.add(new Card(Card.Type.GAME,2));  //il tipo è una enumerazione
        carte.add(new Card(Card.Type.PLANT,1));
        carte.add(new Card(Card.Type.GAME,0));
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.BOOK,0));
        carte.add(new Card(Card.Type.PLANT,0));
        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,0));
        carte.add(new Card(Card.Type.PLANT,3));
        carte.add(new Card(Card.Type.PLANT,4));
        carte.add(new Card(Card.Type.BOOK,1));
        carte.add(new Card(Card.Type.PLANT,2));
        carte.add(new Card(Card.Type.GAME,3));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,1));
        carte.add(new Card(Card.Type.FRAME,1));
        carte.add(new Card(Card.Type.BOOK,2));
        carte.add(new Card(Card.Type.PLANT,5));
        carte.add(new Card(Card.Type.TROPHY,1));
        carte.add(new Card(Card.Type.TROPHY,0));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK,4));
        carte.add(new Card(Card.Type.BOOK,3));
        carte.add(new Card(Card.Type.PLANT,8));
        carte.add(new Card(Card.Type.PLANT,7));
        carte.add(new Card(Card.Type.PLANT,6));
        carte.add(new Card(Card.Type.TROPHY,1));
        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK,8));
        carte.add(new Card(Card.Type.GAME,7));
        carte.add(new Card(Card.Type.FRAME,2));
        carte.add(new Card(Card.Type.GAME,4));
        carte.add(new Card(Card.Type.TROPHY,2));
        carte.add(new Card(Card.Type.PLANT,9));
        shelf.insert(carte,4);

        Assertions.assertTrue(obiettivo2.verify(shelf), "Common Objective Two is verified on this shelf");
    }




    @Test
    void ComObjTwoVerified_4() {
        CommonObjective2 obiettivo2=new CommonObjective2(4);
        carte.add(new Card(Card.Type.GAME,2));  //il tipo è una enumerazione
        carte.add(new Card(Card.Type.GAME,10));
        carte.add(new Card(Card.Type.GAME,0));
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.BOOK,0));
        carte.add(new Card(Card.Type.PLANT,0));
        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,0));
        carte.add(new Card(Card.Type.PLANT,3));
        carte.add(new Card(Card.Type.PLANT,4));
        carte.add(new Card(Card.Type.BOOK,1));
        carte.add(new Card(Card.Type.PLANT,2));
        carte.add(new Card(Card.Type.GAME,3));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,1));
        carte.add(new Card(Card.Type.FRAME,1));
        carte.add(new Card(Card.Type.BOOK,2));
        carte.add(new Card(Card.Type.PLANT,5));
        carte.add(new Card(Card.Type.TROPHY,1));
        carte.add(new Card(Card.Type.TROPHY,0));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK,4));
        carte.add(new Card(Card.Type.BOOK,3));
        carte.add(new Card(Card.Type.PLANT,8));
        carte.add(new Card(Card.Type.PLANT,7));
        carte.add(new Card(Card.Type.PLANT,6));
        carte.add(new Card(Card.Type.TROPHY,1));
        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.GAME,8));
        carte.add(new Card(Card.Type.GAME,7));
        carte.add(new Card(Card.Type.FRAME,2));
        carte.add(new Card(Card.Type.GAME,4));
        carte.add(new Card(Card.Type.TROPHY,2));
        carte.add(new Card(Card.Type.PLANT,9));
        shelf.insert(carte,4);
        Assertions.assertFalse(obiettivo2.verify(shelf), "Common Objective Two is not verified on this shelf");
    }




    //Commmon objective 3//


    @Test
    void ComObjThreeVerified_1() {
        CommonObjective3 obiettivo3=new CommonObjective3(4);
        carte.add(new Card(Card.Type.GAME,2));
        carte.add(new Card(Card.Type.GAME,10));
        carte.add(new Card(Card.Type.GAME,0));
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.BOOK,0));
        carte.add(new Card(Card.Type.GAME,11));
        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,0));
        carte.add(new Card(Card.Type.PLANT,3));
        carte.add(new Card(Card.Type.PLANT,4));
        carte.add(new Card(Card.Type.BOOK,1));
        carte.add(new Card(Card.Type.PLANT,2));
        carte.add(new Card(Card.Type.GAME,3));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,1));
        carte.add(new Card(Card.Type.FRAME,1));
        carte.add(new Card(Card.Type.BOOK,2));
        carte.add(new Card(Card.Type.PLANT,5));
        carte.add(new Card(Card.Type.TROPHY,1));
        carte.add(new Card(Card.Type.TROPHY,0));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK,4));
        carte.add(new Card(Card.Type.BOOK,3));
        carte.add(new Card(Card.Type.PLANT,8));
        carte.add(new Card(Card.Type.PLANT,7));
        carte.add(new Card(Card.Type.PLANT,6));
        carte.add(new Card(Card.Type.TROPHY,1));
        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.GAME,8));
        carte.add(new Card(Card.Type.GAME,7));
        carte.add(new Card(Card.Type.FRAME,2));
        carte.add(new Card(Card.Type.GAME,4));
        carte.add(new Card(Card.Type.TROPHY,2));
        carte.add(new Card(Card.Type.GAME,12));
        shelf.insert(carte,4);

        Assertions.assertTrue(obiettivo3.verify(shelf), "Common Objective Three is verified on this shelf");
    }




    @Test
    void ComObjThreeVerified_2() {
        CommonObjective3 obiettivo3=new CommonObjective3(4);
        carte.add(new Card(Card.Type.CAT,12));
        carte.add(new Card(Card.Type.GAME,10));
        carte.add(new Card(Card.Type.GAME,0));
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.BOOK,0));
        carte.add(new Card(Card.Type.CAT,11));
        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,0));
        carte.add(new Card(Card.Type.PLANT,3));
        carte.add(new Card(Card.Type.PLANT,4));
        carte.add(new Card(Card.Type.BOOK,1));
        carte.add(new Card(Card.Type.PLANT,2));
        carte.add(new Card(Card.Type.GAME,3));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,1));
        carte.add(new Card(Card.Type.FRAME,1));
        carte.add(new Card(Card.Type.BOOK,2));
        carte.add(new Card(Card.Type.PLANT,5));
        carte.add(new Card(Card.Type.TROPHY,1));
        carte.add(new Card(Card.Type.TROPHY,0));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK,4));
        carte.add(new Card(Card.Type.BOOK,3));
        carte.add(new Card(Card.Type.PLANT,8));
        carte.add(new Card(Card.Type.PLANT,7));
        carte.add(new Card(Card.Type.PLANT,6));
        carte.add(new Card(Card.Type.TROPHY,1));
        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,8));
        carte.add(new Card(Card.Type.GAME,7));
        carte.add(new Card(Card.Type.FRAME,2));
        carte.add(new Card(Card.Type.GAME,4));
        carte.add(new Card(Card.Type.TROPHY,2));
        carte.add(new Card(Card.Type.GAME,12));
        shelf.insert(carte,4);

        Assertions.assertFalse(obiettivo3.verify(shelf), "Common Objective Three is not verified on this shelf");
    }




    @Test
    void ComObjThreeVerified_3() {
        CommonObjective3 obiettivo3=new CommonObjective3(4);
        carte.add(new Card(Card.Type.CAT,12));
        carte.add(new Card(Card.Type.GAME,10));
        carte.add(new Card(Card.Type.GAME,0));
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.BOOK,0));
        carte.add(new Card(Card.Type.CAT,11));
        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,0));
        carte.add(new Card(Card.Type.PLANT,3));
        carte.add(new Card(Card.Type.PLANT,4));
        carte.add(new Card(Card.Type.BOOK,1));
        carte.add(new Card(Card.Type.PLANT,2));
        carte.add(new Card(Card.Type.GAME,3));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,1));
        carte.add(new Card(Card.Type.FRAME,1));
        carte.add(new Card(Card.Type.BOOK,2));
        carte.add(new Card(Card.Type.PLANT,5));
        carte.add(new Card(Card.Type.TROPHY,1));
        carte.add(new Card(Card.Type.TROPHY,0));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK,4));
        carte.add(new Card(Card.Type.BOOK,3));
        carte.add(new Card(Card.Type.PLANT,8));
        carte.add(new Card(Card.Type.PLANT,7));
        carte.add(new Card(Card.Type.PLANT,6));
        carte.add(new Card(Card.Type.TROPHY,1));
        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,8));
        carte.add(new Card(Card.Type.GAME,7));
        carte.add(new Card(Card.Type.FRAME,2));
        carte.add(new Card(Card.Type.GAME,4));
        carte.add(new Card(Card.Type.TROPHY,2));
        carte.add(new Card(Card.Type.GAME,12));
        shelf.insert(carte,4);

        Assertions.assertFalse(obiettivo3.verify(shelf), "Common Objective Three is not verified on this shelf");
    }



    @Test
    void ComObjThreeVerified_4() {
        CommonObjective3 obiettivo3=new CommonObjective3(4);
        carte.add(new Card(Card.Type.BOOK,12));
        carte.add(new Card(Card.Type.GAME,10));
        carte.add(new Card(Card.Type.GAME,0));
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.BOOK,0));
        carte.add(new Card(Card.Type.BOOK,11));
        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,0));
        carte.add(new Card(Card.Type.PLANT,3));
        carte.add(new Card(Card.Type.PLANT,4));
        carte.add(new Card(Card.Type.BOOK,1));
        carte.add(new Card(Card.Type.PLANT,2));
        carte.add(new Card(Card.Type.GAME,3));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,1));
        carte.add(new Card(Card.Type.FRAME,1));
        carte.add(new Card(Card.Type.BOOK,2));
        carte.add(new Card(Card.Type.PLANT,5));
        carte.add(new Card(Card.Type.TROPHY,1));
        carte.add(new Card(Card.Type.TROPHY,0));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK,4));
        carte.add(new Card(Card.Type.BOOK,3));
        carte.add(new Card(Card.Type.PLANT,8));
        carte.add(new Card(Card.Type.PLANT,7));
        carte.add(new Card(Card.Type.PLANT,6));
        carte.add(new Card(Card.Type.TROPHY,1));
        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK,8));
        carte.add(new Card(Card.Type.GAME,7));
        carte.add(new Card(Card.Type.FRAME,2));
        carte.add(new Card(Card.Type.GAME,4));
        carte.add(new Card(Card.Type.TROPHY,2));
        carte.add(new Card(Card.Type.BOOK,13));
        shelf.insert(carte,4);

        Assertions.assertTrue(obiettivo3.verify(shelf), "Common Objective Three is  verified on this shelf");
    }





    @Test
    void ComObjThreeVerified_5() {
        CommonObjective3 obiettivo3=new CommonObjective3(4);
        carte.add(new Card(Card.Type.FRAME,1));
        carte.add(new Card(Card.Type.GAME,0));
        carte.add(new Card(Card.Type.CAT,0));
        carte.add(new Card(Card.Type.BOOK,1));
        carte.add(new Card(Card.Type.BOOK,0));
        carte.add(new Card(Card.Type.FRAME,0));
        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME,2));
        carte.add(new Card(Card.Type.GAME,3));
        carte.add(new Card(Card.Type.GAME,4));
        carte.add(new Card(Card.Type.GAME,2));
        carte.add(new Card(Card.Type.GAME,1));
        carte.add(new Card(Card.Type.BOOK,2));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME,3));
        carte.add(new Card(Card.Type.FRAME,4));
        carte.add(new Card(Card.Type.FRAME,5));
        carte.add(new Card(Card.Type.PLANT,0));
        carte.add(new Card(Card.Type.TROPHY,0));
        carte.add(new Card(Card.Type.BOOK,3));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME,6));
        carte.add(new Card(Card.Type.PLANT,1));
        carte.add(new Card(Card.Type.BOOK,8));
        carte.add(new Card(Card.Type.CAT,7));
        carte.add(new Card(Card.Type.BOOK,6));
        carte.add(new Card(Card.Type.FRAME,7));
        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,8));
        carte.add(new Card(Card.Type.GAME,7));
        carte.add(new Card(Card.Type.GAME,8));
        carte.add(new Card(Card.Type.GAME,9));
        carte.add(new Card(Card.Type.FRAME,8));
        carte.add(new Card(Card.Type.CAT,12));
        shelf.insert(carte,4);

        Assertions.assertFalse(obiettivo3.verify(shelf), "Common Objective Three is not verified on this shelf");
    }



    @Test
    void ComObjThreeVerified_6() {
        CommonObjective3 obiettivo3=new CommonObjective3(4);
        carte.add(new Card(Card.Type.FRAME,1));
        carte.add(new Card(Card.Type.GAME,0));
        carte.add(new Card(Card.Type.CAT,0));
        carte.add(new Card(Card.Type.BOOK,1));
        carte.add(new Card(Card.Type.BOOK,0));
        carte.add(new Card(Card.Type.FRAME,0));
        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME,2));
        carte.add(new Card(Card.Type.GAME,3));
        carte.add(new Card(Card.Type.GAME,4));
        carte.add(new Card(Card.Type.GAME,2));
        carte.add(new Card(Card.Type.GAME,1));
        carte.add(new Card(Card.Type.BOOK,2));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME,3));
        carte.add(new Card(Card.Type.FRAME,4));
        carte.add(new Card(Card.Type.FRAME,5));
        carte.add(new Card(Card.Type.PLANT,0));
        carte.add(new Card(Card.Type.TROPHY,0));
        carte.add(new Card(Card.Type.BOOK,3));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME,6));
        carte.add(new Card(Card.Type.PLANT,1));
        carte.add(new Card(Card.Type.BOOK,8));
        carte.add(new Card(Card.Type.CAT,7));
        carte.add(new Card(Card.Type.BOOK,6));
        carte.add(new Card(Card.Type.FRAME,7));
        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME,8));
        carte.add(new Card(Card.Type.GAME,7));
        carte.add(new Card(Card.Type.GAME,8));
        carte.add(new Card(Card.Type.GAME,9));
        carte.add(new Card(Card.Type.FRAME,9));
        carte.add(new Card(Card.Type.FRAME,12));
        shelf.insert(carte,4);

        Assertions.assertTrue(obiettivo3.verify(shelf), "Common Objective Three is  verified on this shelf");
    }



    @Test
    void ComObjThreeVerified_7() {
        CommonObjective3 obiettivo3=new CommonObjective3(4);
        carte.add(new Card(Card.Type.FRAME,1));
        carte.add(new Card(Card.Type.GAME,0));
        carte.add(new Card(Card.Type.CAT,0));
        carte.add(new Card(Card.Type.BOOK,1));
        carte.add(new Card(Card.Type.BOOK,0));
        carte.add(new Card(Card.Type.CAT,15));
        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME,2));
        carte.add(new Card(Card.Type.GAME,3));
        carte.add(new Card(Card.Type.GAME,4));
        carte.add(new Card(Card.Type.GAME,2));
        carte.add(new Card(Card.Type.GAME,1));
        carte.add(new Card(Card.Type.BOOK,2));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME,3));
        carte.add(new Card(Card.Type.FRAME,4));
        carte.add(new Card(Card.Type.FRAME,5));
        carte.add(new Card(Card.Type.PLANT,0));
        carte.add(new Card(Card.Type.TROPHY,0));
        carte.add(new Card(Card.Type.BOOK,3));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME,6));
        carte.add(new Card(Card.Type.PLANT,1));
        carte.add(new Card(Card.Type.BOOK,8));
        carte.add(new Card(Card.Type.CAT,7));
        carte.add(new Card(Card.Type.BOOK,6));
        carte.add(new Card(Card.Type.FRAME,7));
        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME,9));
        carte.add(new Card(Card.Type.GAME,7));
        carte.add(new Card(Card.Type.GAME,8));
        carte.add(new Card(Card.Type.GAME,9));
        carte.add(new Card(Card.Type.FRAME,8));
        carte.add(new Card(Card.Type.CAT,12));
        shelf.insert(carte,4);

        Assertions.assertFalse(obiettivo3.verify(shelf), "Common Objective Three is not verified on this shelf");
    }



    @Test
    void ComObjThreeVerified_8() {
        CommonObjective3 obiettivo3=new CommonObjective3(4);
        carte.add(new Card(Card.Type.FRAME,1));
        carte.add(new Card(Card.Type.GAME,0));
        carte.add(new Card(Card.Type.CAT,0));
        carte.add(new Card(Card.Type.BOOK,1));
        carte.add(new Card(Card.Type.BOOK,0));
        carte.add(new Card(Card.Type.CAT,15));
        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME,2));
        carte.add(new Card(Card.Type.GAME,3));
        carte.add(new Card(Card.Type.GAME,4));
        carte.add(new Card(Card.Type.GAME,2));
        carte.add(new Card(Card.Type.GAME,1));
        carte.add(new Card(Card.Type.BOOK,2));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME,3));
        carte.add(new Card(Card.Type.FRAME,4));
        carte.add(new Card(Card.Type.FRAME,5));
        carte.add(new Card(Card.Type.PLANT,0));
        carte.add(new Card(Card.Type.TROPHY,0));
        carte.add(new Card(Card.Type.BOOK,3));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME,6));
        carte.add(new Card(Card.Type.PLANT,1));
        carte.add(new Card(Card.Type.BOOK,8));
        carte.add(new Card(Card.Type.CAT,7));
        carte.add(new Card(Card.Type.BOOK,6));
        carte.add(new Card(Card.Type.FRAME,7));
        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.GAME,10));
        carte.add(new Card(Card.Type.GAME,7));
        carte.add(new Card(Card.Type.GAME,8));
        carte.add(new Card(Card.Type.GAME,9));
        carte.add(new Card(Card.Type.FRAME,8));
        carte.add(new Card(Card.Type.BOOK,12));
        shelf.insert(carte,4);

        Assertions.assertFalse(obiettivo3.verify(shelf), "Common Objective Three is not verified on this shelf");
    }




    //Commmon objective 4//
    //4 righe intese come minimo sempre//



    @Test
    void ComObjFourVerified_1() {
        CommonObjective4 obiettivo4=new CommonObjective4(4);
        carte.add(new Card(Card.Type.BOOK,0));
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.FRAME,2));
        carte.add(new Card(Card.Type.FRAME,1));
        carte.add(new Card(Card.Type.CAT,0));
        carte.add(new Card(Card.Type.CAT,1));
        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK,2));
        carte.add(new Card(Card.Type.BOOK,3));
        carte.add(new Card(Card.Type.BOOK,4));
        carte.add(new Card(Card.Type.FRAME,3));
        carte.add(new Card(Card.Type.CAT,2));
        carte.add(new Card(Card.Type.GAME,2));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK,5));
        carte.add(new Card(Card.Type.TROPHY,4));
        carte.add(new Card(Card.Type.TROPHY,5));
        carte.add(new Card(Card.Type.CAT,3));
        carte.add(new Card(Card.Type.CAT,4));
        carte.add(new Card(Card.Type.FRAME,5));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK,6));
        carte.add(new Card(Card.Type.TROPHY,7));
        carte.add(new Card(Card.Type.PLANT,8));
        carte.add(new Card(Card.Type.BOOK,7));
        carte.add(new Card(Card.Type.CAT,6));
        carte.add(new Card(Card.Type.PLANT,7));
        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY,9));
        carte.add(new Card(Card.Type.TROPHY,8));
        carte.add(new Card(Card.Type.CAT,8));
        carte.add(new Card(Card.Type.CAT,9));
        carte.add(new Card(Card.Type.CAT,10));
        carte.add(new Card(Card.Type.BOOK,12));
        shelf.insert(carte,4);

        Assertions.assertTrue(obiettivo4.verify(shelf), "Common Objective Four is  verified on this shelf");
    }





    @Test
    void ComObjFourVerified_2() {
        CommonObjective4 obiettivo4=new CommonObjective4(4);
        carte.add(new Card(Card.Type.BOOK,0));
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.FRAME,2));
        carte.add(new Card(Card.Type.FRAME,1));
        carte.add(new Card(Card.Type.CAT,0));
        carte.add(new Card(Card.Type.CAT,1));
        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK,2));
        carte.add(new Card(Card.Type.FRAME,3));
        carte.add(new Card(Card.Type.BOOK,4));
        carte.add(new Card(Card.Type.FRAME,4));
        carte.add(new Card(Card.Type.CAT,2));
        carte.add(new Card(Card.Type.GAME,2));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK,5));
        carte.add(new Card(Card.Type.FRAME,15));
        carte.add(new Card(Card.Type.TROPHY,5));
        carte.add(new Card(Card.Type.CAT,3));
        carte.add(new Card(Card.Type.CAT,4));
        carte.add(new Card(Card.Type.FRAME,5));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK,6));
        carte.add(new Card(Card.Type.FRAME,9));
        carte.add(new Card(Card.Type.PLANT,8));
        carte.add(new Card(Card.Type.BOOK,7));
        carte.add(new Card(Card.Type.CAT,6));
        carte.add(new Card(Card.Type.PLANT,7));
        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY,9));
        carte.add(new Card(Card.Type.FRAME,8));
        carte.add(new Card(Card.Type.CAT,8));
        carte.add(new Card(Card.Type.TROPHY,12));
        carte.add(new Card(Card.Type.CAT,10));
        carte.add(new Card(Card.Type.BOOK,12));
        shelf.insert(carte,4);

        Assertions.assertFalse(obiettivo4.verify(shelf), "Common Objective Four is not verified on this shelf");
    }


    @Test
    void ComObjFourVerified_3() {
        CommonObjective4 obiettivo4=new CommonObjective4(4);
        carte.add(new Card(Card.Type.BOOK,0));
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.FRAME,2));
        carte.add(new Card(Card.Type.FRAME,1));
        carte.add(new Card(Card.Type.CAT,0));
        carte.add(new Card(Card.Type.CAT,1));
        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK,2));
        carte.add(new Card(Card.Type.FRAME,3));
        carte.add(new Card(Card.Type.BOOK,4));
        carte.add(new Card(Card.Type.FRAME,4));
        carte.add(new Card(Card.Type.CAT,2));
        carte.add(new Card(Card.Type.GAME,2));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK,5));
        carte.add(new Card(Card.Type.FRAME,15));
        carte.add(new Card(Card.Type.CAT,16));
        carte.add(new Card(Card.Type.BOOK,13));
        carte.add(new Card(Card.Type.CAT,4));
        carte.add(new Card(Card.Type.GAME,15));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK,6));
        carte.add(new Card(Card.Type.FRAME,9));
        carte.add(new Card(Card.Type.CAT,11));
        carte.add(new Card(Card.Type.CAT,7));
        carte.add(new Card(Card.Type.CAT,6));
        carte.add(new Card(Card.Type.BOOK,7));
        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY,9));
        carte.add(new Card(Card.Type.FRAME,8));
        carte.add(new Card(Card.Type.CAT,8));
        carte.add(new Card(Card.Type.BOOK,12));
        carte.add(new Card(Card.Type.CAT,10));
        carte.add(new Card(Card.Type.GAME,  12));
        shelf.insert(carte,4);

        Assertions.assertTrue(obiettivo4.verify(shelf), "Common Objective Four is verified on this shelf");
    }




    @Test
    void ComObjFourVerified_4() {
        CommonObjective4 obiettivo4=new CommonObjective4(4);
        carte.add(new Card(Card.Type.BOOK,0));
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.FRAME,2));
        carte.add(new Card(Card.Type.FRAME,1));
        carte.add(new Card(Card.Type.CAT,0));
        carte.add(new Card(Card.Type.CAT,1));
        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY,0));
        carte.add(new Card(Card.Type.FRAME,6));
        carte.add(new Card(Card.Type.FRAME,4));
        carte.add(new Card(Card.Type.FRAME,3));
        carte.add(new Card(Card.Type.FRAME,5));
        carte.add(new Card(Card.Type.GAME,2));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.GAME,3));
        carte.add(new Card(Card.Type.FRAME,8));
        carte.add(new Card(Card.Type.FRAME,7));
        carte.add(new Card(Card.Type.CAT,2));
        carte.add(new Card(Card.Type.PLANT,0));
        carte.add(new Card(Card.Type.FRAME,6));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK,6));
        carte.add(new Card(Card.Type.BOOK,3));
        carte.add(new Card(Card.Type.PLANT,8));
        carte.add(new Card(Card.Type.BOOK,2));
        carte.add(new Card(Card.Type.BOOK,1));
        carte.add(new Card(Card.Type.PLANT,1));
        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY,9));
        carte.add(new Card(Card.Type.FRAME,9));
        carte.add(new Card(Card.Type.PLANT,11));
        carte.add(new Card(Card.Type.BOOK,9));
        carte.add(new Card(Card.Type.GAME,10));
        carte.add(new Card(Card.Type.BOOK,12));
        shelf.insert(carte,4);

        Assertions.assertTrue(obiettivo4.verify(shelf), "Common Objective Four is  verified on this shelf");
    }





    //Commmon objective 5//
    //4 gruppi intesi come minimo sempre//
    //adiacenti ortogonalmente//


    @Test
    void ComObjFiveVerified_1() {
        CommonObjective5 obiettivo5=new CommonObjective5(4);
        carte.add(new Card(Card.Type.TROPHY,2));
        carte.add(new Card(Card.Type.TROPHY,0));
        carte.add(new Card(Card.Type.TROPHY,1));
        carte.add(new Card(Card.Type.PLANT,0));
        carte.add(new Card(Card.Type.BOOK,0));
        carte.add(new Card(Card.Type.BOOK,1));
        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,0));
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.BOOK,4));
        carte.add(new Card(Card.Type.BOOK,5));
        carte.add(new Card(Card.Type.BOOK,3));
        carte.add(new Card(Card.Type.BOOK,2));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT,4));
        carte.add(new Card(Card.Type.PLANT,3));
        carte.add(new Card(Card.Type.PLANT,2));
        carte.add(new Card(Card.Type.PLANT,1));
        carte.add(new Card(Card.Type.CAT,1));
        carte.add(new Card(Card.Type.GAME,0));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT,6));
        carte.add(new Card(Card.Type.PLANT,5));
        carte.add(new Card(Card.Type.CAT,3));
        carte.add(new Card(Card.Type.CAT,2));
        carte.add(new Card(Card.Type.FRAME,1));
        carte.add(new Card(Card.Type.GAME,1));
        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT,7));
        carte.add(new Card(Card.Type.FRAME,2));
        carte.add(new Card(Card.Type.CAT,5));
        carte.add(new Card(Card.Type.CAT,4));
        carte.add(new Card(Card.Type.GAME,3));
        carte.add(new Card(Card.Type.GAME,2));
        shelf.insert(carte,4);

        Assertions.assertTrue(obiettivo5.verify(shelf), "Common Objective Five is  verified on this shelf");
    }




    @Test
    void ComObjFiveVerified_2() {
        CommonObjective5 obiettivo5=new CommonObjective5(4);
        carte.add(new Card(Card.Type.TROPHY,2));
        carte.add(new Card(Card.Type.TROPHY,0));
        carte.add(new Card(Card.Type.TROPHY,1));
        carte.add(new Card(Card.Type.BOOK,10));
        carte.add(new Card(Card.Type.BOOK,0));
        carte.add(new Card(Card.Type.BOOK,1));
        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY,10));
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.BOOK,4));
        carte.add(new Card(Card.Type.BOOK,5));
        carte.add(new Card(Card.Type.GAME,13));
        carte.add(new Card(Card.Type.BOOK,2));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY,11));
        carte.add(new Card(Card.Type.PLANT,3));
        carte.add(new Card(Card.Type.CAT,7));
        carte.add(new Card(Card.Type.PLANT,1));
        carte.add(new Card(Card.Type.CAT,1));
        carte.add(new Card(Card.Type.GAME,0));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT,6));
        carte.add(new Card(Card.Type.CAT,8));
        carte.add(new Card(Card.Type.BOOK,7));
        carte.add(new Card(Card.Type.BOOK,6));
        carte.add(new Card(Card.Type.FRAME,1));
        carte.add(new Card(Card.Type.GAME,1));
        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT,9));
        carte.add(new Card(Card.Type.PLANT,8));
        carte.add(new Card(Card.Type.PLANT,7));
        carte.add(new Card(Card.Type.CAT,4));
        carte.add(new Card(Card.Type.GAME,3));
        carte.add(new Card(Card.Type.GAME,2));
        shelf.insert(carte,4);

        Assertions.assertTrue(obiettivo5.verify(shelf), "Common Objective Five is  verified on this shelf");
    }




    @Test
    void ComObjFiveVerified_3() {
        CommonObjective5 obiettivo5=new CommonObjective5(4);
        carte.add(new Card(Card.Type.PLANT,1));
        carte.add(new Card(Card.Type.PLANT,0));
        carte.add(new Card(Card.Type.GAME,0));
        carte.add(new Card(Card.Type.CAT,0));
        carte.add(new Card(Card.Type.TROPHY,0));
        carte.add(new Card(Card.Type.TROPHY,1));
        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT,3));
        carte.add(new Card(Card.Type.PLANT,2));
        carte.add(new Card(Card.Type.TROPHY,4));
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.TROPHY,3));
        carte.add(new Card(Card.Type.TROPHY,2));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,3));
        carte.add(new Card(Card.Type.CAT,2));
        carte.add(new Card(Card.Type.CAT,1));
        carte.add(new Card(Card.Type.PLANT,4));
        carte.add(new Card(Card.Type.GAME,2));
        carte.add(new Card(Card.Type.GAME,1));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY,6));
        carte.add(new Card(Card.Type.CAT,8));
        carte.add(new Card(Card.Type.CAT,7));
        carte.add(new Card(Card.Type.FRAME,4));
        carte.add(new Card(Card.Type.FRAME,1));
        carte.add(new Card(Card.Type.CAT,4));
        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY,9));
        carte.add(new Card(Card.Type.FRAME,8));
        carte.add(new Card(Card.Type.PLANT,7));
        carte.add(new Card(Card.Type.FRAME,6));
        carte.add(new Card(Card.Type.FRAME,5));
        carte.add(new Card(Card.Type.TROPHY,7));
        shelf.insert(carte,4);

        Assertions.assertTrue(obiettivo5.verify(shelf), "Common Objective Five is  verified on this shelf");
    }



    @Test
    void ComObjFiveVerified_4() {
        CommonObjective5 obiettivo5=new CommonObjective5(4);
        carte.add(new Card(Card.Type.PLANT,0));
        carte.add(new Card(Card.Type.FRAME,4));
        carte.add(new Card(Card.Type.FRAME,3));
        carte.add(new Card(Card.Type.FRAME,2));
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.FRAME,1));
        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME,6));
        carte.add(new Card(Card.Type.FRAME,5));
        carte.add(new Card(Card.Type.CAT,0));
        carte.add(new Card(Card.Type.BOOK,0));
        carte.add(new Card(Card.Type.PLANT,1));
        carte.add(new Card(Card.Type.GAME,0));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME,8));
        carte.add(new Card(Card.Type.FRAME,7));
        carte.add(new Card(Card.Type.CAT,2));
        carte.add(new Card(Card.Type.BOOK,1));
        carte.add(new Card(Card.Type.CAT,1));
        carte.add(new Card(Card.Type.PLANT,2));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME,10));
        carte.add(new Card(Card.Type.FRAME,9));
        carte.add(new Card(Card.Type.CAT,7));
        carte.add(new Card(Card.Type.BOOK,6));
        carte.add(new Card(Card.Type.GAME,1));
        carte.add(new Card(Card.Type.TROPHY,1));
        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT,9));
        carte.add(new Card(Card.Type.CAT,10));
        carte.add(new Card(Card.Type.CAT,9));
        carte.add(new Card(Card.Type.BOOK,8));
        carte.add(new Card(Card.Type.BOOK,7));
        carte.add(new Card(Card.Type.TROPHY,2));
        shelf.insert(carte,4);

        Assertions.assertTrue(obiettivo5.verify(shelf), "Common Objective Five is  verified on this shelf");
    }





    //Commmon objective 6//
    //minimo due colonne//


    @Test
    void ComObjSixVerified_1() {
        CommonObjective6 obiettivo6=new CommonObjective6(4);
        carte.add(new Card(Card.Type.GAME,0));
        carte.add(new Card(Card.Type.PLANT,1));
        carte.add(new Card(Card.Type.CAT,1));
        carte.add(new Card(Card.Type.PLANT,0));
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.CAT,0));
        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME,1));
        carte.add(new Card(Card.Type.BOOK,0));
        carte.add(new Card(Card.Type.CAT,2));
        carte.add(new Card(Card.Type.PLANT,2));
        carte.add(new Card(Card.Type.GAME,1));
        carte.add(new Card(Card.Type.TROPHY,0));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT,8));
        carte.add(new Card(Card.Type.PLANT,7));
        carte.add(new Card(Card.Type.PLANT,6));
        carte.add(new Card(Card.Type.PLANT,5));
        carte.add(new Card(Card.Type.PLANT,4));
        carte.add(new Card(Card.Type.PLANT,3));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.GAME,2));
        carte.add(new Card(Card.Type.GAME,3));
        carte.add(new Card(Card.Type.GAME,4));
        carte.add(new Card(Card.Type.PLANT,9));
        carte.add(new Card(Card.Type.FRAME,2));
        carte.add(new Card(Card.Type.FRAME,3));
        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK,2));
        carte.add(new Card(Card.Type.FRAME,5));
        carte.add(new Card(Card.Type.CAT,4));
        carte.add(new Card(Card.Type.GAME,5));
        carte.add(new Card(Card.Type.PLANT,10));
        carte.add(new Card(Card.Type.TROPHY,10));
        shelf.insert(carte,4);

        Assertions.assertTrue(obiettivo6.verify(shelf), "Common Objective Six is verified on this shelf");
    }




    @Test
    void ComObjSixVerified_2() {
        CommonObjective6 obiettivo6=new CommonObjective6(4);
        carte.add(new Card(Card.Type.GAME,0));
        carte.add(new Card(Card.Type.PLANT,1));
        carte.add(new Card(Card.Type.CAT,1));
        carte.add(new Card(Card.Type.PLANT,0));
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.CAT,0));
        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK,9));
        carte.add(new Card(Card.Type.BOOK,0));
        carte.add(new Card(Card.Type.CAT,2));
        carte.add(new Card(Card.Type.PLANT,2));
        carte.add(new Card(Card.Type.GAME,1));
        carte.add(new Card(Card.Type.TROPHY,0));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT,8));
        carte.add(new Card(Card.Type.PLANT,7));
        carte.add(new Card(Card.Type.PLANT,6));
        carte.add(new Card(Card.Type.PLANT,5));
        carte.add(new Card(Card.Type.PLANT,4));
        carte.add(new Card(Card.Type.PLANT,3));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.GAME,2));
        carte.add(new Card(Card.Type.GAME,3));
        carte.add(new Card(Card.Type.GAME,4));
        carte.add(new Card(Card.Type.PLANT,9));
        carte.add(new Card(Card.Type.FRAME,2));
        carte.add(new Card(Card.Type.FRAME,3));
        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK,2));
        carte.add(new Card(Card.Type.FRAME,5));
        carte.add(new Card(Card.Type.CAT,4));
        carte.add(new Card(Card.Type.GAME,5));
        carte.add(new Card(Card.Type.PLANT,10));
        carte.add(new Card(Card.Type.TROPHY,10));
        shelf.insert(carte,4);

        Assertions.assertFalse(obiettivo6.verify(shelf), "Common Objective Six is not verified on this shelf");
    }



    @Test
    void ComObjSixVerified_3() {
        CommonObjective6 obiettivo6=new CommonObjective6(4);
        carte.add(new Card(Card.Type.TROPHY,0));
        carte.add(new Card(Card.Type.BOOK,1));
        carte.add(new Card(Card.Type.GAME,1));
        carte.add(new Card(Card.Type.PLANT,0));
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.CAT,0));
        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK,19));
        carte.add(new Card(Card.Type.BOOK,0));
        carte.add(new Card(Card.Type.CAT,2));
        carte.add(new Card(Card.Type.PLANT,2));
        carte.add(new Card(Card.Type.GAME,14));
        carte.add(new Card(Card.Type.TROPHY,11));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME,18));
        carte.add(new Card(Card.Type.BOOK,20));
        carte.add(new Card(Card.Type.TROPHY,16));
        carte.add(new Card(Card.Type.GAME,15));
        carte.add(new Card(Card.Type.PLANT,4));
        carte.add(new Card(Card.Type.CAT,13));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.GAME,2));
        carte.add(new Card(Card.Type.GAME,3));
        carte.add(new Card(Card.Type.GAME,4));
        carte.add(new Card(Card.Type.PLANT,9));
        carte.add(new Card(Card.Type.FRAME,2));
        carte.add(new Card(Card.Type.FRAME,3));
        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK,2));
        carte.add(new Card(Card.Type.FRAME,5));
        carte.add(new Card(Card.Type.CAT,4));
        carte.add(new Card(Card.Type.GAME,5));
        carte.add(new Card(Card.Type.PLANT,10));
        carte.add(new Card(Card.Type.TROPHY,10));
        shelf.insert(carte,4);

        Assertions.assertTrue(obiettivo6.verify(shelf), "Common Objective Six is verified on this shelf");
    }




    //Commmon objective 7//
    //minimo due gruppi//
    //non fa niente cosa c'è nel resto della shelfreria//


    @Test
    void ComObjSevenVerified_1() {
        CommonObjective7 obiettivo7=new CommonObjective7(4);
        carte.add(new Card(Card.Type.CAT,0));
        carte.add(new Card(Card.Type.CAT,1));
        carte.add(new Card(Card.Type.CAT,2));
        carte.add(new Card(Card.Type.PLANT,0));
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.GAME,0));
        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,3));
        carte.add(new Card(Card.Type.CAT,4));
        carte.add(new Card(Card.Type.CAT,5));
        carte.add(new Card(Card.Type.BOOK,2));
        carte.add(new Card(Card.Type.BOOK,1));
        carte.add(new Card(Card.Type.BOOK,0));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,8));
        carte.add(new Card(Card.Type.CAT,7));
        carte.add(new Card(Card.Type.CAT,6));
        carte.add(new Card(Card.Type.BOOK,5));
        carte.add(new Card(Card.Type.BOOK,4));
        carte.add(new Card(Card.Type.BOOK,3));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK,8));
        carte.add(new Card(Card.Type.BOOK,7));
        carte.add(new Card(Card.Type.BOOK,6));
        carte.add(new Card(Card.Type.TROPHY,0));
        carte.add(new Card(Card.Type.FRAME,2));
        carte.add(new Card(Card.Type.GAME,3));
        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK,12));
        carte.add(new Card(Card.Type.BOOK,10));
        carte.add(new Card(Card.Type.BOOK,9));
        carte.add(new Card(Card.Type.PLANT,5));
        carte.add(new Card(Card.Type.BOOK,11));
        carte.add(new Card(Card.Type.CAT,10));
        shelf.insert(carte,4);

        Assertions.assertTrue(obiettivo7.verify(shelf), "Common Objective Seven is verified on this shelf");
    }




    @Test
    void ComObjSevenVerified_2() {
        CommonObjective7 obiettivo7=new CommonObjective7(4);
        carte.add(new Card(Card.Type.CAT,0));
        carte.add(new Card(Card.Type.CAT,1));
        carte.add(new Card(Card.Type.CAT,2));
        carte.add(new Card(Card.Type.PLANT,0));
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.GAME,0));
        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,3));
        carte.add(new Card(Card.Type.CAT,4));
        carte.add(new Card(Card.Type.CAT,5));
        carte.add(new Card(Card.Type.BOOK,2));
        carte.add(new Card(Card.Type.BOOK,1));
        carte.add(new Card(Card.Type.BOOK,0));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,8));
        carte.add(new Card(Card.Type.CAT,7));
        carte.add(new Card(Card.Type.CAT,6));
        carte.add(new Card(Card.Type.BOOK,5));
        carte.add(new Card(Card.Type.BOOK,4));
        carte.add(new Card(Card.Type.BOOK,3));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,18));
        carte.add(new Card(Card.Type.BOOK,7));
        carte.add(new Card(Card.Type.CAT,16));
        carte.add(new Card(Card.Type.TROPHY,0));
        carte.add(new Card(Card.Type.FRAME,2));
        carte.add(new Card(Card.Type.GAME,3));
        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,12));
        carte.add(new Card(Card.Type.BOOK,10));
        carte.add(new Card(Card.Type.CAT,19));
        carte.add(new Card(Card.Type.PLANT,5));
        carte.add(new Card(Card.Type.BOOK,11));
        carte.add(new Card(Card.Type.CAT,10));
        shelf.insert(carte,4);
        Assertions.assertFalse(obiettivo7.verify(shelf), "Common Objective Seven is not verified on this shelf");
    }



    @Test
    void ComObjSevenVerified_3() {
        CommonObjective7 obiettivo7=new CommonObjective7(4);
        carte.add(new Card(Card.Type.CAT,0));
        carte.add(new Card(Card.Type.CAT,1));
        carte.add(new Card(Card.Type.CAT,2));
        carte.add(new Card(Card.Type.PLANT,0));
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.GAME,0));
        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,3));
        carte.add(new Card(Card.Type.CAT,4));
        carte.add(new Card(Card.Type.CAT,5));
        carte.add(new Card(Card.Type.BOOK,2));
        carte.add(new Card(Card.Type.BOOK,1));
        carte.add(new Card(Card.Type.BOOK,0));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,8));
        carte.add(new Card(Card.Type.CAT,7));
        carte.add(new Card(Card.Type.CAT,6));
        carte.add(new Card(Card.Type.BOOK,5));
        carte.add(new Card(Card.Type.BOOK,4));
        carte.add(new Card(Card.Type.BOOK,3));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,18));
        carte.add(new Card(Card.Type.BOOK,7));
        carte.add(new Card(Card.Type.CAT,16));
        carte.add(new Card(Card.Type.TROPHY,0));
        carte.add(new Card(Card.Type.TROPHY,2));
        carte.add(new Card(Card.Type.TROPHY,3));
        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,12));
        carte.add(new Card(Card.Type.BOOK,10));
        carte.add(new Card(Card.Type.CAT,19));
        carte.add(new Card(Card.Type.TROPHY,5));
        carte.add(new Card(Card.Type.TROPHY,11));
        carte.add(new Card(Card.Type.TROPHY,10));
        shelf.insert(carte,4);
        Assertions.assertFalse(obiettivo7.verify(shelf), "Common Objective Seven is not verified on this shelf");
    }





    @Test
    void ComObjSevenVerified_4() {
        CommonObjective7 obiettivo7=new CommonObjective7(4);
        carte.add(new Card(Card.Type.CAT,0));
        carte.add(new Card(Card.Type.CAT,1));
        carte.add(new Card(Card.Type.CAT,2));
        carte.add(new Card(Card.Type.PLANT,0));
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.GAME,0));
        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,3));
        carte.add(new Card(Card.Type.CAT,4));
        carte.add(new Card(Card.Type.BOOK,20));
        carte.add(new Card(Card.Type.BOOK,2));
        carte.add(new Card(Card.Type.BOOK,1));
        carte.add(new Card(Card.Type.BOOK,0));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,8));
        carte.add(new Card(Card.Type.CAT,7));
        carte.add(new Card(Card.Type.BOOK,21));
        carte.add(new Card(Card.Type.BOOK,5));
        carte.add(new Card(Card.Type.BOOK,4));
        carte.add(new Card(Card.Type.BOOK,3));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,18));
        carte.add(new Card(Card.Type.BOOK,7));
        carte.add(new Card(Card.Type.CAT,16));
        carte.add(new Card(Card.Type.TROPHY,0));
        carte.add(new Card(Card.Type.TROPHY,2));
        carte.add(new Card(Card.Type.TROPHY,3));
        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,12));
        carte.add(new Card(Card.Type.BOOK,10));
        carte.add(new Card(Card.Type.CAT,19));
        carte.add(new Card(Card.Type.TROPHY,5));
        carte.add(new Card(Card.Type.TROPHY,11));
        carte.add(new Card(Card.Type.TROPHY,10));
        shelf.insert(carte,4);

        Assertions.assertTrue(obiettivo7.verify(shelf), "Common Objective Seven is verified on this shelf");
    }




    //Commmon objective 8//
    //minimo due righe//


    @Test
    void ComObjEightVerified_1() {
        CommonObjective8 obiettivo8=new CommonObjective8(4);
        carte.add(new Card(Card.Type.CAT,2));
        carte.add(new Card(Card.Type.CAT,1));
        carte.add(new Card(Card.Type.BOOK,1));
        carte.add(new Card(Card.Type.BOOK,0));
        carte.add(new Card(Card.Type.CAT,0));
        carte.add(new Card(Card.Type.FRAME,0));
        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,3));
        carte.add(new Card(Card.Type.FRAME,1));
        carte.add(new Card(Card.Type.TROPHY,0));
        carte.add(new Card(Card.Type.BOOK,2));
        carte.add(new Card(Card.Type.FRAME,2));
        carte.add(new Card(Card.Type.FRAME,3));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,4));
        carte.add(new Card(Card.Type.TROPHY,3));
        carte.add(new Card(Card.Type.PLANT,0));
        carte.add(new Card(Card.Type.FRAME,5));
        carte.add(new Card(Card.Type.TROPHY,2));
        carte.add(new Card(Card.Type.FRAME,4));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,5));
        carte.add(new Card(Card.Type.PLANT,4));
        carte.add(new Card(Card.Type.GAME,5));
        carte.add(new Card(Card.Type.TROPHY,5));
        carte.add(new Card(Card.Type.PLANT,2));
        carte.add(new Card(Card.Type.PLANT,3));
        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME,7));
        carte.add(new Card(Card.Type.TROPHY,6));
        carte.add(new Card(Card.Type.CAT,6));
        carte.add(new Card(Card.Type.PLANT,6));
        carte.add(new Card(Card.Type.BOOK,5));
        carte.add(new Card(Card.Type.FRAME,6));
        shelf.insert(carte,4);

        Assertions.assertTrue(obiettivo8.verify(shelf), "Common Objective Eight is verified on this shelf");
    }






    @Test
    void ComObjEightVerified_2() {
        CommonObjective8 obiettivo8=new CommonObjective8(4);
        carte.add(new Card(Card.Type.CAT,2));
        carte.add(new Card(Card.Type.CAT,1));
        carte.add(new Card(Card.Type.BOOK,1));
        carte.add(new Card(Card.Type.BOOK,0));
        carte.add(new Card(Card.Type.CAT,0));
        carte.add(new Card(Card.Type.FRAME,0));
        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,3));
        carte.add(new Card(Card.Type.FRAME,1));
        carte.add(new Card(Card.Type.TROPHY,0));
        carte.add(new Card(Card.Type.BOOK,2));
        carte.add(new Card(Card.Type.FRAME,2));
        carte.add(new Card(Card.Type.FRAME,3));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,4));
        carte.add(new Card(Card.Type.TROPHY,3));
        carte.add(new Card(Card.Type.PLANT,0));
        carte.add(new Card(Card.Type.FRAME,5));
        carte.add(new Card(Card.Type.TROPHY,2));
        carte.add(new Card(Card.Type.FRAME,4));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,5));
        carte.add(new Card(Card.Type.PLANT,4));
        carte.add(new Card(Card.Type.GAME,5));
        carte.add(new Card(Card.Type.TROPHY,5));
        carte.add(new Card(Card.Type.PLANT,2));
        carte.add(new Card(Card.Type.PLANT,3));
        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME,7));
        carte.add(new Card(Card.Type.TROPHY,6));
        carte.add(new Card(Card.Type.CAT,6));
        carte.add(new Card(Card.Type.PLANT,6));
        carte.add(new Card(Card.Type.TROPHY,8));
        carte.add(new Card(Card.Type.FRAME,6));
        shelf.insert(carte,4);

        Assertions.assertFalse(obiettivo8.verify(shelf), "Common Objective Eight is not verified on this shelf");
    }



    @Test
    void ComObjEightVerified_3() {
        CommonObjective8 obiettivo8=new CommonObjective8(4);
        carte.add(new Card(Card.Type.CAT,2));
        carte.add(new Card(Card.Type.CAT,1));
        carte.add(new Card(Card.Type.BOOK,1));
        carte.add(new Card(Card.Type.BOOK,0));
        carte.add(new Card(Card.Type.CAT,0));
        carte.add(new Card(Card.Type.FRAME,0));
        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,3));
        carte.add(new Card(Card.Type.FRAME,1));
        carte.add(new Card(Card.Type.TROPHY,0));
        carte.add(new Card(Card.Type.GAME,20));
        carte.add(new Card(Card.Type.TROPHY,20));
        carte.add(new Card(Card.Type.FRAME,3));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,4));
        carte.add(new Card(Card.Type.TROPHY,3));
        carte.add(new Card(Card.Type.PLANT,0));
        carte.add(new Card(Card.Type.FRAME,5));
        carte.add(new Card(Card.Type.TROPHY,2));
        carte.add(new Card(Card.Type.FRAME,4));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,5));
        carte.add(new Card(Card.Type.PLANT,4));
        carte.add(new Card(Card.Type.GAME,5));
        carte.add(new Card(Card.Type.TROPHY,5));
        carte.add(new Card(Card.Type.PLANT,2));
        carte.add(new Card(Card.Type.PLANT,3));
        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME,7));
        carte.add(new Card(Card.Type.BOOK,6));
        carte.add(new Card(Card.Type.CAT,6));
        carte.add(new Card(Card.Type.PLANT,6));
        carte.add(new Card(Card.Type.BOOK,5));
        carte.add(new Card(Card.Type.FRAME,6));
        shelf.insert(carte,4);

        Assertions.assertTrue(obiettivo8.verify(shelf), "Common Objective Eight is verified on this shelf");
    }




    //Commmon objective 9//
    //minimo tre colonne//


    @Test
    void ComObjNineVerified_1() {
        CommonObjective9 obiettivo9=new CommonObjective9(4);
        carte.add(new Card(Card.Type.CAT,2));
        carte.add(new Card(Card.Type.GAME,0));
        carte.add(new Card(Card.Type.TROPHY,0));
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.CAT,1));
        carte.add(new Card(Card.Type.CAT,0));
        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY,1));
        carte.add(new Card(Card.Type.FRAME,1));
        carte.add(new Card(Card.Type.CAT,6));
        carte.add(new Card(Card.Type.CAT,5));
        carte.add(new Card(Card.Type.CAT,4));
        carte.add(new Card(Card.Type.CAT,3));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,12));
        carte.add(new Card(Card.Type.CAT,11));
        carte.add(new Card(Card.Type.CAT,10));
        carte.add(new Card(Card.Type.CAT,9));
        carte.add(new Card(Card.Type.CAT,8));
        carte.add(new Card(Card.Type.CAT,7));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.GAME,2));
        carte.add(new Card(Card.Type.CAT,13));
        carte.add(new Card(Card.Type.PLANT,0));
        carte.add(new Card(Card.Type.GAME,1));
        carte.add(new Card(Card.Type.TROPHY,2));
        carte.add(new Card(Card.Type.FRAME,2));
        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME,4));
        carte.add(new Card(Card.Type.GAME,5));
        carte.add(new Card(Card.Type.GAME,4));
        carte.add(new Card(Card.Type.GAME,3));
        carte.add(new Card(Card.Type.TROPHY,3));
        carte.add(new Card(Card.Type.FRAME,3));
        shelf.insert(carte,4);

        Assertions.assertTrue(obiettivo9.verify(shelf), "Common Objective Nine is verified on this shelf");
    }






    @Test
    void ComObjNineVerified_2() {
        CommonObjective9 obiettivo9=new CommonObjective9(4);
        carte.add(new Card(Card.Type.CAT,2));
        carte.add(new Card(Card.Type.GAME,0));
        carte.add(new Card(Card.Type.TROPHY,0));
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.CAT,1));
        carte.add(new Card(Card.Type.CAT,0));
        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY,1));
        carte.add(new Card(Card.Type.FRAME,1));
        carte.add(new Card(Card.Type.GAME,10));
        carte.add(new Card(Card.Type.CAT,5));
        carte.add(new Card(Card.Type.CAT,4));
        carte.add(new Card(Card.Type.CAT,3));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,12));
        carte.add(new Card(Card.Type.CAT,11));
        carte.add(new Card(Card.Type.CAT,10));
        carte.add(new Card(Card.Type.CAT,9));
        carte.add(new Card(Card.Type.CAT,8));
        carte.add(new Card(Card.Type.CAT,7));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.GAME,2));
        carte.add(new Card(Card.Type.CAT,13));
        carte.add(new Card(Card.Type.PLANT,0));
        carte.add(new Card(Card.Type.GAME,1));
        carte.add(new Card(Card.Type.TROPHY,2));
        carte.add(new Card(Card.Type.FRAME,2));
        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME,4));
        carte.add(new Card(Card.Type.GAME,5));
        carte.add(new Card(Card.Type.GAME,4));
        carte.add(new Card(Card.Type.GAME,3));
        carte.add(new Card(Card.Type.TROPHY,3));
        carte.add(new Card(Card.Type.FRAME,3));
        shelf.insert(carte,4);

        Assertions.assertFalse(obiettivo9.verify(shelf), "Common Objective Nine is not verified on this shelf");
    }


    @Test
    void ComObjNineVerified_3() {
        CommonObjective9 obiettivo9=new CommonObjective9(4);
        carte.add(new Card(Card.Type.CAT,2));
        carte.add(new Card(Card.Type.GAME,0));
        carte.add(new Card(Card.Type.TROPHY,0));
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.CAT,1));
        carte.add(new Card(Card.Type.CAT,0));
        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY,1));
        carte.add(new Card(Card.Type.FRAME,1));
        carte.add(new Card(Card.Type.TROPHY,19));
        carte.add(new Card(Card.Type.CAT,5));
        carte.add(new Card(Card.Type.FRAME,20));
        carte.add(new Card(Card.Type.CAT,3));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,12));
        carte.add(new Card(Card.Type.CAT,11));
        carte.add(new Card(Card.Type.CAT,10));
        carte.add(new Card(Card.Type.CAT,9));
        carte.add(new Card(Card.Type.CAT,8));
        carte.add(new Card(Card.Type.CAT,7));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY,9));
        carte.add(new Card(Card.Type.TROPHY,13));
        carte.add(new Card(Card.Type.TROPHY,8));
        carte.add(new Card(Card.Type.FRAME,7));
        carte.add(new Card(Card.Type.FRAME,6));
        carte.add(new Card(Card.Type.FRAME,5));
        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME,4));
        carte.add(new Card(Card.Type.GAME,5));
        carte.add(new Card(Card.Type.GAME,4));
        carte.add(new Card(Card.Type.GAME,3));
        carte.add(new Card(Card.Type.TROPHY,3));
        carte.add(new Card(Card.Type.FRAME,3));
        shelf.insert(carte,4);

        Assertions.assertTrue(obiettivo9.verify(shelf), "Common Objective Nine is verified on this shelf");
    }






    @Test
    void ComObjNineVerified_4() {
        CommonObjective9 obiettivo9=new CommonObjective9(4);
        carte.add(new Card(Card.Type.CAT,2));
        carte.add(new Card(Card.Type.GAME,0));
        carte.add(new Card(Card.Type.TROPHY,0));
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.CAT,1));
        carte.add(new Card(Card.Type.CAT,0));
        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY,1));
        carte.add(new Card(Card.Type.FRAME,1));
        carte.add(new Card(Card.Type.GAME,10));
        carte.add(new Card(Card.Type.CAT,5));
        carte.add(new Card(Card.Type.CAT,4));
        carte.add(new Card(Card.Type.CAT,3));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,12));
        carte.add(new Card(Card.Type.CAT,11));
        carte.add(new Card(Card.Type.TROPHY,10));
        carte.add(new Card(Card.Type.FRAME,9));
        carte.add(new Card(Card.Type.GAME,18));
        carte.add(new Card(Card.Type.CAT,7));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.GAME,2));
        carte.add(new Card(Card.Type.CAT,13));
        carte.add(new Card(Card.Type.PLANT,0));
        carte.add(new Card(Card.Type.GAME,1));
        carte.add(new Card(Card.Type.TROPHY,2));
        carte.add(new Card(Card.Type.FRAME,2));
        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME,4));
        carte.add(new Card(Card.Type.GAME,5));
        carte.add(new Card(Card.Type.PLANT,4));
        carte.add(new Card(Card.Type.GAME,3));
        carte.add(new Card(Card.Type.TROPHY,3));
        carte.add(new Card(Card.Type.FRAME,3));
        shelf.insert(carte,4);

        Assertions.assertFalse(obiettivo9.verify(shelf), "Common Objective Nine is not verified on this shelf");
    }



//l'obiettivo è fatto se ho la colonna piena e con al max tre tipi diversi//

    @Test
    void ComObjNineVerified_5() {
        CommonObjective9 obiettivo9=new CommonObjective9(4);
        carte.add(new Card(Card.Type.CAT,2));
        carte.add(new Card(Card.Type.GAME,0));
        carte.add(new Card(Card.Type.TROPHY,0));
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.CAT,1));

        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,21));
        carte.add(new Card(Card.Type.FRAME,1));
        carte.add(new Card(Card.Type.GAME,10));
        carte.add(new Card(Card.Type.CAT,5));
        carte.add(new Card(Card.Type.CAT,4));
        carte.add(new Card(Card.Type.CAT,3));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,12));
        carte.add(new Card(Card.Type.CAT,11));
        carte.add(new Card(Card.Type.FRAME,21));
        carte.add(new Card(Card.Type.FRAME,9));
        carte.add(new Card(Card.Type.GAME,18));
        carte.add(new Card(Card.Type.CAT,7));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.GAME,2));
        carte.add(new Card(Card.Type.CAT,13));
        carte.add(new Card(Card.Type.PLANT,0));
        carte.add(new Card(Card.Type.GAME,1));

        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME,4));
        carte.add(new Card(Card.Type.GAME,5));
        carte.add(new Card(Card.Type.GAME,6));
        carte.add(new Card(Card.Type.GAME,3));
        carte.add(new Card(Card.Type.TROPHY,3));
        carte.add(new Card(Card.Type.FRAME,3));
        shelf.insert(carte,4);

        Assertions.assertTrue(obiettivo9.verify(shelf), "Common Objective Nine is  verified on this shelf");
    }





    @Test
    void ComObjNineVerified_6() {
        CommonObjective9 obiettivo9=new CommonObjective9(4);
        carte.add(new Card(Card.Type.CAT,2));
        carte.add(new Card(Card.Type.GAME,0));
        carte.add(new Card(Card.Type.TROPHY,0));
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.CAT,1));

        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,21));
        carte.add(new Card(Card.Type.FRAME,1));
        carte.add(new Card(Card.Type.GAME,10));
        carte.add(new Card(Card.Type.CAT,5));
        carte.add(new Card(Card.Type.CAT,4));

        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,12));
        carte.add(new Card(Card.Type.CAT,11));
        carte.add(new Card(Card.Type.FRAME,21));
        carte.add(new Card(Card.Type.FRAME,9));
        carte.add(new Card(Card.Type.GAME,18));
        carte.add(new Card(Card.Type.CAT,7));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.GAME,2));
        carte.add(new Card(Card.Type.CAT,13));
        carte.add(new Card(Card.Type.PLANT,0));
        carte.add(new Card(Card.Type.GAME,1));

        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME,4));
        carte.add(new Card(Card.Type.GAME,5));
        carte.add(new Card(Card.Type.GAME,6));
        carte.add(new Card(Card.Type.GAME,3));
        carte.add(new Card(Card.Type.TROPHY,3));
        carte.add(new Card(Card.Type.FRAME,3));
        shelf.insert(carte,4);

        Assertions.assertFalse(obiettivo9.verify(shelf), "Common Objective Nine is not  verified on this shelf");
    }





    //Commmon objective 10//
    //minimo tre colonne//


    @Test
    void ComObjTenVerified_1() {
        CommonObjective10 obiettivo10=new CommonObjective10(4);
        carte.add(new Card(Card.Type.PLANT,0));
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.BOOK,1));
        carte.add(new Card(Card.Type.BOOK,0));
        carte.add(new Card(Card.Type.GAME,0));
        carte.add(new Card(Card.Type.CAT,0));
        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT,2));
        carte.add(new Card(Card.Type.CAT,2));
        carte.add(new Card(Card.Type.GAME,1));
        carte.add(new Card(Card.Type.PLANT,1));
        carte.add(new Card(Card.Type.FRAME,1));
        carte.add(new Card(Card.Type.CAT,1));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT,5));
        carte.add(new Card(Card.Type.CAT,4));
        carte.add(new Card(Card.Type.PLANT,3));
        carte.add(new Card(Card.Type.PLANT,2));
        carte.add(new Card(Card.Type.CAT,3));
        carte.add(new Card(Card.Type.FRAME,2));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,6));
        carte.add(new Card(Card.Type.PLANT,7));
        carte.add(new Card(Card.Type.BOOK,3));
        carte.add(new Card(Card.Type.GAME,3));
        carte.add(new Card(Card.Type.PLANT,6));
        carte.add(new Card(Card.Type.FRAME,3));
        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT,10));
        carte.add(new Card(Card.Type.FRAME,5));
        carte.add(new Card(Card.Type.PLANT,9));
        carte.add(new Card(Card.Type.PLANT,8));
        carte.add(new Card(Card.Type.GAME,5));
        carte.add(new Card(Card.Type.FRAME,4));
        shelf.insert(carte,4);

        Assertions.assertTrue(obiettivo10.verify(shelf), "Common Objective Ten is verified on this shelf");
    }




    @Test
    void ComObjTenVerified_2() {
        CommonObjective10 obiettivo10=new CommonObjective10(4);
        carte.add(new Card(Card.Type.PLANT,0));
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.PLANT,19));
        carte.add(new Card(Card.Type.BOOK,0));
        carte.add(new Card(Card.Type.PLANT,20));
        carte.add(new Card(Card.Type.CAT,0));
        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT,2));
        carte.add(new Card(Card.Type.CAT,2));
        carte.add(new Card(Card.Type.GAME,1));
        carte.add(new Card(Card.Type.PLANT,1));
        carte.add(new Card(Card.Type.FRAME,1));
        carte.add(new Card(Card.Type.CAT,1));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT,5));
        carte.add(new Card(Card.Type.CAT,4));
        carte.add(new Card(Card.Type.PLANT,3));
        carte.add(new Card(Card.Type.PLANT,2));
        carte.add(new Card(Card.Type.PLANT,13));
        carte.add(new Card(Card.Type.FRAME,2));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,6));
        carte.add(new Card(Card.Type.PLANT,7));
        carte.add(new Card(Card.Type.BOOK,3));
        carte.add(new Card(Card.Type.GAME,3));
        carte.add(new Card(Card.Type.PLANT,6));
        carte.add(new Card(Card.Type.FRAME,3));
        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT,10));
        carte.add(new Card(Card.Type.FRAME,5));
        carte.add(new Card(Card.Type.PLANT,9));
        carte.add(new Card(Card.Type.PLANT,8));
        carte.add(new Card(Card.Type.GAME,5));
        carte.add(new Card(Card.Type.FRAME,4));
        shelf.insert(carte,4);

        Assertions.assertTrue(obiettivo10.verify(shelf), "Common Objective Ten is verified on this shelf");
    }



    @Test
    void ComObjTenVerified_3() {
        CommonObjective10 obiettivo10=new CommonObjective10(4);
        carte.add(new Card(Card.Type.PLANT,0));
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.PLANT,19));
        carte.add(new Card(Card.Type.BOOK,0));
        carte.add(new Card(Card.Type.CAT,20));
        carte.add(new Card(Card.Type.CAT,0));
        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT,2));
        carte.add(new Card(Card.Type.CAT,2));
        carte.add(new Card(Card.Type.GAME,1));
        carte.add(new Card(Card.Type.PLANT,1));
        carte.add(new Card(Card.Type.FRAME,1));
        carte.add(new Card(Card.Type.CAT,1));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT,5));
        carte.add(new Card(Card.Type.CAT,4));
        carte.add(new Card(Card.Type.PLANT,3));
        carte.add(new Card(Card.Type.PLANT,2));
        carte.add(new Card(Card.Type.PLANT,13));
        carte.add(new Card(Card.Type.FRAME,2));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,6));
        carte.add(new Card(Card.Type.PLANT,7));
        carte.add(new Card(Card.Type.BOOK,3));
        carte.add(new Card(Card.Type.GAME,3));
        carte.add(new Card(Card.Type.PLANT,6));
        carte.add(new Card(Card.Type.FRAME,3));
        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT,10));
        carte.add(new Card(Card.Type.FRAME,5));
        carte.add(new Card(Card.Type.CAT,9));
        carte.add(new Card(Card.Type.PLANT,8));
        carte.add(new Card(Card.Type.GAME,5));
        carte.add(new Card(Card.Type.FRAME,4));
        shelf.insert(carte,4);

        Assertions.assertFalse(obiettivo10.verify(shelf), "Common Objective Ten is not verified on this shelf");
    }




    //Commmon objective 11//
    //minimo otto tessere//


    @Test
    void ComObjElevenVerified_1() {
        CommonObjective11 obiettivo11=new CommonObjective11(4);
        carte.add(new Card(Card.Type.CAT,1));
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.TROPHY,0));
        carte.add(new Card(Card.Type.PLANT,0));
        carte.add(new Card(Card.Type.GAME,0));
        carte.add(new Card(Card.Type.CAT,0));
        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY,2));
        carte.add(new Card(Card.Type.CAT,2));
        carte.add(new Card(Card.Type.TROPHY,1));
        carte.add(new Card(Card.Type.PLANT,1));
        carte.add(new Card(Card.Type.FRAME,1));
        carte.add(new Card(Card.Type.GAME,1));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.GAME,5));
        carte.add(new Card(Card.Type.FRAME,4));
        carte.add(new Card(Card.Type.GAME,3));
        carte.add(new Card(Card.Type.CAT,3));
        carte.add(new Card(Card.Type.PLANT,3));
        carte.add(new Card(Card.Type.BOOK,0));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,5));
        carte.add(new Card(Card.Type.BOOK,1));
        carte.add(new Card(Card.Type.GAME,6));
        carte.add(new Card(Card.Type.TROPHY,3));
        carte.add(new Card(Card.Type.TROPHY,4));
        carte.add(new Card(Card.Type.CAT,4));
        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.GAME,10));
        carte.add(new Card(Card.Type.CAT,7));
        carte.add(new Card(Card.Type.PLANT,9));
        carte.add(new Card(Card.Type.TROPHY,8));
        carte.add(new Card(Card.Type.CAT,6));
        carte.add(new Card(Card.Type.BOOK,4));
        shelf.insert(carte,4);

        Assertions.assertTrue(obiettivo11.verify(shelf), "Common Objective Eleven is verified on this shelf");
    }




    @Test
    void ComObjElevenVerified_2() {
        CommonObjective11 obiettivo11=new CommonObjective11(4);
        carte.add(new Card(Card.Type.CAT,1));
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.TROPHY,0));
        carte.add(new Card(Card.Type.PLANT,0));
        carte.add(new Card(Card.Type.GAME,0));
        carte.add(new Card(Card.Type.CAT,0));
        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY,2));
        carte.add(new Card(Card.Type.CAT,2));
        carte.add(new Card(Card.Type.TROPHY,1));
        carte.add(new Card(Card.Type.PLANT,1));
        carte.add(new Card(Card.Type.FRAME,1));
        carte.add(new Card(Card.Type.GAME,1));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.GAME,5));
        carte.add(new Card(Card.Type.FRAME,4));
        carte.add(new Card(Card.Type.GAME,3));
        carte.add(new Card(Card.Type.CAT,3));
        carte.add(new Card(Card.Type.PLANT,3));
        carte.add(new Card(Card.Type.BOOK,0));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,5));
        carte.add(new Card(Card.Type.BOOK,1));
        carte.add(new Card(Card.Type.GAME,6));
        carte.add(new Card(Card.Type.TROPHY,3));
        carte.add(new Card(Card.Type.TROPHY,4));
        carte.add(new Card(Card.Type.CAT,4));
        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.GAME,10));
        carte.add(new Card(Card.Type.BOOK,7));
        carte.add(new Card(Card.Type.PLANT,9));
        carte.add(new Card(Card.Type.TROPHY,8));
        carte.add(new Card(Card.Type.CAT,6));
        carte.add(new Card(Card.Type.BOOK,4));
        shelf.insert(carte,4);

        Assertions.assertFalse(obiettivo11.verify(shelf), "Common Objective Eleven is not verified on this shelf");
    }





    @Test
    void ComObjElevenVerified_3() {
        CommonObjective11 obiettivo11=new CommonObjective11(4);
        carte.add(new Card(Card.Type.CAT,1));
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.CAT,11));
        carte.add(new Card(Card.Type.PLANT,0));
        carte.add(new Card(Card.Type.GAME,0));
        carte.add(new Card(Card.Type.CAT,0));
        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY,2));
        carte.add(new Card(Card.Type.CAT,2));
        carte.add(new Card(Card.Type.TROPHY,1));
        carte.add(new Card(Card.Type.PLANT,1));
        carte.add(new Card(Card.Type.FRAME,1));
        carte.add(new Card(Card.Type.GAME,1));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.GAME,5));
        carte.add(new Card(Card.Type.FRAME,4));
        carte.add(new Card(Card.Type.CAT,13));
        carte.add(new Card(Card.Type.CAT,3));
        carte.add(new Card(Card.Type.PLANT,3));
        carte.add(new Card(Card.Type.BOOK,0));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,5));
        carte.add(new Card(Card.Type.BOOK,1));
        carte.add(new Card(Card.Type.GAME,6));
        carte.add(new Card(Card.Type.TROPHY,3));
        carte.add(new Card(Card.Type.TROPHY,4));
        carte.add(new Card(Card.Type.CAT,4));
        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,10));
        carte.add(new Card(Card.Type.CAT,7));
        carte.add(new Card(Card.Type.CAT,19));
        carte.add(new Card(Card.Type.TROPHY,8));
        carte.add(new Card(Card.Type.CAT,6));
        carte.add(new Card(Card.Type.BOOK,4));
        shelf.insert(carte,4);

        Assertions.assertTrue(obiettivo11.verify(shelf), "Common Objective Eleven is verified on this shelf");
    }



    @Test
    void ComObjElevenVerified_4() {
        CommonObjective11 obiettivo11=new CommonObjective11(4);
        carte.add(new Card(Card.Type.BOOK,0));
        carte.add(new Card(Card.Type.FRAME,2));
        carte.add(new Card(Card.Type.FRAME,1));
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.TROPHY,0));
        carte.add(new Card(Card.Type.PLANT,0));
        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.GAME,3));
        carte.add(new Card(Card.Type.FRAME,4));
        carte.add(new Card(Card.Type.GAME,1));
        carte.add(new Card(Card.Type.GAME,0));
        carte.add(new Card(Card.Type.FRAME,3));
        carte.add(new Card(Card.Type.CAT,0));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK,5));
        carte.add(new Card(Card.Type.GAME,5));
        carte.add(new Card(Card.Type.BOOK,4));
        carte.add(new Card(Card.Type.BOOK,3));
        carte.add(new Card(Card.Type.TROPHY,1));
        carte.add(new Card(Card.Type.GAME,4));
        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.GAME,8));
        carte.add(new Card(Card.Type.BOOK,7));
        carte.add(new Card(Card.Type.GAME,7));
        carte.add(new Card(Card.Type.GAME,6));
        carte.add(new Card(Card.Type.TROPHY,2));
        carte.add(new Card(Card.Type.BOOK,6));
        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK,10));
        carte.add(new Card(Card.Type.FRAME,8));
        carte.add(new Card(Card.Type.FRAME,9));
        carte.add(new Card(Card.Type.TROPHY,8));
        carte.add(new Card(Card.Type.FRAME,7));
        carte.add(new Card(Card.Type.BOOK,9));
        shelf.insert(carte,4);
        Assertions.assertTrue(obiettivo11.verify(shelf), "Common Objective Eleven is verified on this shelf");
    }




    @Test
    void ComObjElevenVerified_5() {
        CommonObjective11 obiettivo11=new CommonObjective11(4);
        carte.add(new Card(Card.Type.CAT,1));
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.TROPHY,0));
        carte.add(new Card(Card.Type.PLANT,0));
        carte.add(new Card(Card.Type.GAME,0));
        carte.add(new Card(Card.Type.CAT,0));
        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY,2));
        carte.add(new Card(Card.Type.CAT,2));
        carte.add(new Card(Card.Type.TROPHY,1));
        carte.add(new Card(Card.Type.PLANT,1));
        carte.add(new Card(Card.Type.FRAME,1));
        carte.add(new Card(Card.Type.GAME,1));
        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.GAME,5));
        carte.add(new Card(Card.Type.FRAME,4));
        carte.add(new Card(Card.Type.GAME,3));
        carte.add(new Card(Card.Type.CAT,3));
        carte.add(new Card(Card.Type.PLANT,3));

        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,5));
        carte.add(new Card(Card.Type.BOOK,1));
        carte.add(new Card(Card.Type.GAME,6));
        carte.add(new Card(Card.Type.TROPHY,3));
        carte.add(new Card(Card.Type.TROPHY,4));
        carte.add(new Card(Card.Type.CAT,4));
        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.GAME,10));
        carte.add(new Card(Card.Type.CAT,7));
        carte.add(new Card(Card.Type.PLANT,9));
        carte.add(new Card(Card.Type.CAT,8));

        shelf.insert(carte,4);
        Assertions.assertTrue(obiettivo11.verify(shelf), "Common Objective Eleven is verified on this shelf");
    }






    //Commmon objective 12//



    @Test
    void ComObjTwelveVerified_1() {
        CommonObjective12 obiettivo12=new CommonObjective12(4);
        carte.add(new Card(Card.Type.CAT,1));
        carte.add(new Card(Card.Type.CAT,0));
        carte.add(new Card(Card.Type.CAT,2));
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.BOOK,0));

        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY,2));
        carte.add(new Card(Card.Type.PLANT,2));
        carte.add(new Card(Card.Type.CAT,3));
        carte.add(new Card(Card.Type.PLANT,1));

        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.GAME,5));
        carte.add(new Card(Card.Type.CAT,4));
        carte.add(new Card(Card.Type.TROPHY,3));

        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.GAME,6));
        carte.add(new Card(Card.Type.FRAME,7));

        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.CAT,10));

        shelf.insert(carte,4);

        Assertions.assertTrue(obiettivo12.verify(shelf), "Common Objective Twelve is verified on this shelf");
    }




    @Test
    void ComObjTwelveVerified_2() {
        CommonObjective12 obiettivo12=new CommonObjective12(4);
        carte.add(new Card(Card.Type.GAME,0));


        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT,0));
        carte.add(new Card(Card.Type.CAT,0));


        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.PLANT,1));
        carte.add(new Card(Card.Type.BOOK,0));

        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.GAME,5));
        carte.add(new Card(Card.Type.CAT,1));
        carte.add(new Card(Card.Type.PLANT,2));
        carte.add(new Card(Card.Type.BOOK,2));

        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY,10));
        carte.add(new Card(Card.Type.PLANT,5));
        carte.add(new Card(Card.Type.GAME,6));
        carte.add(new Card(Card.Type.CAT,2));
        carte.add(new Card(Card.Type.PLANT,3));

        shelf.insert(carte,4);

        Assertions.assertTrue(obiettivo12.verify(shelf), "Common Objective Twelve is verified on this shelf");
    }



    @Test
    void ComObjTwelveVerified_3() {
        CommonObjective12 obiettivo12=new CommonObjective12(4);
        carte.add(new Card(Card.Type.GAME,0));


        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT,0));
        carte.add(new Card(Card.Type.CAT,0));


        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.PLANT,1));
        carte.add(new Card(Card.Type.BOOK,0));

        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.GAME,5));
        carte.add(new Card(Card.Type.CAT,1));
        carte.add(new Card(Card.Type.PLANT,2));
        carte.add(new Card(Card.Type.BOOK,2));

        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY,10));
        carte.add(new Card(Card.Type.PLANT,5));
        carte.add(new Card(Card.Type.GAME,6));
        carte.add(new Card(Card.Type.CAT,2));


        shelf.insert(carte,4);

        Assertions.assertFalse(obiettivo12.verify(shelf), "Common Objective Twelve is not verified on this shelf");
    }



    @Test
    void ComObjTwelveVerified_4() {
        CommonObjective12 obiettivo12=new CommonObjective12(4);
        carte.add(new Card(Card.Type.GAME,0));


        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.PLANT,0));
        carte.add(new Card(Card.Type.CAT,0));


        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.PLANT,1));
        carte.add(new Card(Card.Type.BOOK,0));

        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.GAME,5));
        carte.add(new Card(Card.Type.CAT,1));


        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY,10));
        carte.add(new Card(Card.Type.PLANT,5));
        carte.add(new Card(Card.Type.GAME,6));
        carte.add(new Card(Card.Type.CAT,2));
        carte.add(new Card(Card.Type.PLANT,3));

        shelf.insert(carte,4);

        Assertions.assertFalse(obiettivo12.verify(shelf), "Common Objective Twelve is not verified on this shelf");
    }




    @Test
    void ComObjTwelveVerified_5() {
        CommonObjective12 obiettivo12=new CommonObjective12(4);
        carte.add(new Card(Card.Type.CAT,1));
        carte.add(new Card(Card.Type.CAT,0));
        carte.add(new Card(Card.Type.CAT,2));
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.BOOK,0));

        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.TROPHY,2));
        carte.add(new Card(Card.Type.PLANT,2));
        carte.add(new Card(Card.Type.CAT,3));
        carte.add(new Card(Card.Type.PLANT,4));

        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.GAME,5));
        carte.add(new Card(Card.Type.CAT,4));
        carte.add(new Card(Card.Type.TROPHY,3));

        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.GAME,6));
        carte.add(new Card(Card.Type.FRAME,7));

        shelf.insert(carte,3);



        Assertions.assertFalse(obiettivo12.verify(shelf), "Common Objective Twelve is not verified on this shelf");
    }



    @Test
    void ComObjTwelveVerified_6() {
        CommonObjective12 obiettivo12=new CommonObjective12(4);
        carte.add(new Card(Card.Type.GAME,1));
        carte.add(new Card(Card.Type.FRAME,0));
        carte.add(new Card(Card.Type.PLANT,0));
        carte.add(new Card(Card.Type.GAME,0));
        carte.add(new Card(Card.Type.CAT,0));

        shelf.insert(carte,0);
        carte.clear();
        carte.add(new Card(Card.Type.GAME,2));
        carte.add(new Card(Card.Type.TROPHY,2));
        carte.add(new Card(Card.Type.BOOK,1));
        carte.add(new Card(Card.Type.TROPHY,1));

        shelf.insert(carte,1);
        carte.clear();
        carte.add(new Card(Card.Type.BOOK,5));
        carte.add(new Card(Card.Type.TROPHY,4));
        carte.add(new Card(Card.Type.CAT,3));

        shelf.insert(carte,2);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME,5));
        carte.add(new Card(Card.Type.BOOK,6));
        carte.add(new Card(Card.Type.TROPHY,5));
        carte.add(new Card(Card.Type.GAME,4));

        shelf.insert(carte,3);
        carte.clear();
        carte.add(new Card(Card.Type.FRAME,10));
        carte.add(new Card(Card.Type.TROPHY,8));
        carte.add(new Card(Card.Type.CAT,6));
        carte.add(new Card(Card.Type.PLANT,2));
        carte.add(new Card(Card.Type.FRAME,7));

        shelf.insert(carte,4);
        Assertions.assertFalse(obiettivo12.verify(shelf), "Common Objective Twelve is not verified on this shelf");
    }

}