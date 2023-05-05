package Model.entities;

import Exceptions.InvalidArgumentException;
import org.junit.jupiter.api.Test;
import util.Config;
import util.PlanarCoordinate;

import java.util.ArrayList;
import java.util.List;

class ShelfTest {

    @Test
    void constructorTest() throws InvalidArgumentException {

        Config.initialise(2);

        Shelf shelf = new Shelf();

        assert (6 == shelf.getRows());
        assert (5 == shelf.getColumns());

        for(int i = 0; i < shelf.getRows(); i++) {
            for (int j = 0; j < shelf.getColumns(); j++) {
                assert (shelf.checkCell(new PlanarCoordinate(i, j)) == null);
            }
        }

    }

    @Test
    void insertTest() throws InvalidArgumentException {

        Config.initialise(4);

        Shelf shelf = new Shelf();

        List<Card> cardsList = new ArrayList<>();

        cardsList.add(new Card(Card.Type.CAT, 1));
        cardsList.add(new Card(Card.Type.TROPHY, 1));

        shelf.insert(cardsList, 0);

        assert (shelf.checkCell(new PlanarCoordinate(shelf.getRows() - 1, 0)).equalsType(new Card(Card.Type.CAT, 6) ));
        assert (shelf.checkCell(new PlanarCoordinate(shelf.getRows() - 2, 0)).equalsType(new Card(Card.Type.TROPHY, 69) ));

        assert (shelf.checkCell(new PlanarCoordinate(shelf.getRows() - 1, 0)).equalsType(new Card(Card.Type.CAT, 1) ));
        assert (shelf.checkCell(new PlanarCoordinate(shelf.getRows() - 2, 0)).equalsType(new Card(Card.Type.TROPHY, 1) ));


        shelf.insert(cardsList, 0);

        assert (shelf.checkCell(new PlanarCoordinate(shelf.getRows() - 3, 0)).equalsType(new Card(Card.Type.CAT, 6) ));
        assert (shelf.checkCell(new PlanarCoordinate(shelf.getRows() - 4, 0)).equalsType(new Card(Card.Type.TROPHY, 69) ));

        assert (shelf.checkCell(new PlanarCoordinate(shelf.getRows() - 3, 0)).equalsType(new Card(Card.Type.CAT, 1) ));
        assert (shelf.checkCell(new PlanarCoordinate(shelf.getRows() - 4, 0)).equalsType(new Card(Card.Type.TROPHY, 1) ));


    }

    @Test
    void fullTest() throws InvalidArgumentException {
        Config.initialise(2);

        Shelf shelf = new Shelf();

        List<Card> cardList = new ArrayList<>();

        cardList.add(new Card(Card.Type.CAT, 0));
        cardList.add(new Card(Card.Type.CAT, 0));
        cardList.add(new Card(Card.Type.CAT, 0));
        cardList.add(new Card(Card.Type.CAT, 0));
        cardList.add(new Card(Card.Type.CAT, 0));
        cardList.add(new Card(Card.Type.CAT, 0));
        assert (!shelf.isFull());

        shelf.insert(cardList, 0);
        assert (!shelf.isFull());

        shelf.insert(cardList, 1);
        assert (!shelf.isFull());

        shelf.insert(cardList, 2);
        assert (!shelf.isFull());

        shelf.insert(cardList, 3);
        assert (!shelf.isFull());

        shelf.insert(cardList, 4);
        assert (shelf.isFull());



    }

    @Test
    void freeSpaceTest() throws InvalidArgumentException
    {
        Config.initialise(2);

        Shelf shelf = new Shelf();

        List<Card> cardList = new ArrayList<>();

        Card cat = new Card(Card.Type.CAT, 0);
        Card trophy =new Card(Card.Type.TROPHY, 0);
        Card plant = new Card(Card.Type.PLANT, 0);
        Card book = new Card(Card.Type.BOOK, 0);
        Card game = new Card(Card.Type.GAME, 0);
        Card frame = new Card(Card.Type.FRAME, 0);

        assert (shelf.maxFreeSpace()==6);
        assert (!shelf.canInsert(cardList, 0));

        cardList.add(cat);
        cardList.add(plant);
        cardList.add(trophy);

        assert (shelf.maxFreeSpace()==6);
        shelf.insert(cardList,0);
        assert (shelf.maxFreeSpace()==6);
        assert (shelf.canInsert(cardList, 0));

        cardList.add(cat);
        assert (!shelf.canInsert(cardList, 0));
        assert (!shelf.canInsert(cardList, -1));
        assert (!shelf.canInsert(cardList, 5));
        assert (shelf.canInsert(cardList, 4));
        shelf.insert(cardList,4);
        assert (shelf.maxFreeSpace()==6);

        cardList.clear();
        cardList.add(frame);
        assert (shelf.canInsert(cardList, 4));
        assert (shelf.canInsert(cardList, 0));
        assert (shelf.canInsert(cardList, 1));
        shelf.insert(cardList,1);
        assert (shelf.canInsert(cardList, 1));
        assert (shelf.maxFreeSpace()==6);

        cardList.add(game);
        assert (shelf.canInsert(cardList, 2));
        shelf.insert(cardList,2);
        assert (shelf.canInsert(cardList, 2));
        assert (shelf.maxFreeSpace()==6);

        cardList.clear();
        cardList.add(book);
        assert (shelf.canInsert(cardList, 3));
        shelf.insert(cardList,3);
        assert (shelf.canInsert(cardList, 3));
        assert (shelf.maxFreeSpace()==5);

        cardList.add(plant);
        assert (shelf.canInsert(cardList, 3));
        assert (shelf.canInsert(cardList, 4));
        shelf.insert(cardList,4);
        assert (!shelf.canInsert(cardList, 4));
        shelf.insert(cardList,3);
        assert (shelf.maxFreeSpace()==5);

        cardList.clear();
        cardList.add(book);
        assert (shelf.canInsert(cardList, 1));
        shelf.insert(cardList,1);
        assert (shelf.canInsert(cardList, 1));
        assert (shelf.maxFreeSpace()==4);

        cardList.add(trophy);
        assert (shelf.canInsert(cardList, 0));
        shelf.insert(cardList,0);
        assert (!shelf.canInsert(cardList, 0));

        assert (shelf.canInsert(cardList, 1));
        shelf.insert(cardList,1);
        assert (shelf.canInsert(cardList, 1));
        assert (shelf.maxFreeSpace()==4);

        assert (!shelf.canInsert(cardList, 0));
        assert (!shelf.canInsert(cardList, 4));
        assert (shelf.canInsert(cardList, 3));
        shelf.insert(cardList,3);
        assert (!shelf.canInsert(cardList, 3));
        assert (shelf.maxFreeSpace()==4);

        cardList.clear();
        cardList.add(cat);
        assert (shelf.canInsert(cardList, 0));
        shelf.insert(cardList,0);
        assert (!shelf.canInsert(cardList, 0));
        assert (shelf.maxFreeSpace()==4);
        assert (shelf.canInsert(cardList, 2));
        shelf.insert(cardList,2);
        assert (shelf.maxFreeSpace()==3);

        cardList.add(frame);
        assert (!shelf.canInsert(cardList, 3));
        assert (shelf.canInsert(cardList, 1));
        assert (shelf.canInsert(cardList, 2));
        shelf.insert(cardList,2);
        assert (!shelf.canInsert(cardList, 2));
        assert (shelf.maxFreeSpace()==2);
        assert (!shelf.isFull());

        assert (shelf.canInsert(cardList, 1));
        shelf.insert(cardList,1);
        assert (!shelf.canInsert(cardList, 1));
        assert (shelf.maxFreeSpace()==1);
        assert (!shelf.isFull());

        cardList.clear();
        assert (!shelf.canInsert(cardList, 0));
        assert (!shelf.canInsert(cardList, 1));
        assert (!shelf.canInsert(cardList, 2));
        assert (!shelf.canInsert(cardList, 3));
        assert (!shelf.canInsert(cardList, 4));
        assert (shelf.maxFreeSpace()==1);
        assert (!shelf.isFull());

        cardList.add(game);
        assert (shelf.canInsert(cardList, 2));
        shelf.insert(cardList,2);
        assert (!shelf.canInsert(cardList, 2));
        assert (shelf.maxFreeSpace()==1);
        assert (!shelf.isFull());

        assert (!shelf.canInsert(cardList, 0));
        assert (!shelf.canInsert(cardList, 1));
        assert (!shelf.canInsert(cardList, 2));
        assert (shelf.canInsert(cardList, 3));
        assert (!shelf.canInsert(cardList, 4));
        assert (shelf.maxFreeSpace()==1);
        assert (!shelf.isFull());

        cardList.add(plant);
        assert (!shelf.canInsert(cardList, 3));
        cardList.clear();
        cardList.add(frame);
        assert (shelf.canInsert(cardList, 3));
        shelf.insert(cardList,3);
        assert (!shelf.canInsert(cardList, 3));
        cardList.clear();
        assert (!shelf.canInsert(cardList, 3));
        assert (shelf.maxFreeSpace()==0);
        assert (shelf.isFull());


    }





}