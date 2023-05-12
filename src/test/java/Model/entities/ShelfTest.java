package Model.entities;

import Exceptions.InvalidArgumentException;
import Model.ModelView;
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


        assert(shelf.getGroupSize(shelf.asMatrix(), new PlanarCoordinate(0,0), shelf.asMatrix()[0][0].getType()) == 30);

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

    @Test
    void GroupTest() {
        Config.initialise(2);
        Shelf shelf = new Shelf();
        List<Card> carte = new ArrayList<>();

        carte.add(new Card(Card.Type.CAT, 0));
        carte.add(new Card(Card.Type.CAT, 0));
        carte.add(new Card(Card.Type.CAT, 0));

        shelf.insert(carte, 0);

        assert (shelf.getGroupSize(shelf.asMatrix(), new PlanarCoordinate(5, 0), shelf.asMatrix()[5][0].getType()) == 3);
        assert (shelf.getGroupSize(shelf.asMatrix(), new PlanarCoordinate(4, 0), shelf.asMatrix()[4][0].getType()) == 3);
        assert (shelf.getGroupSize(shelf.asMatrix(), new PlanarCoordinate(3, 0), shelf.asMatrix()[3][0].getType()) == 3);

        shelf = new Shelf();
        carte.clear();
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


        assert (shelf.getGroupSize(shelf.asMatrix(), new PlanarCoordinate(0, 0), Card.Type.PLANT) == 1);
        assert (shelf.getGroupSize(shelf.asMatrix(), new PlanarCoordinate(0, 1), Card.Type.TROPHY) == 2);
        assert (shelf.getGroupSize(shelf.asMatrix(), new PlanarCoordinate(0, 2), Card.Type.TROPHY) == 2);

        assert (shelf.getGroupSize(shelf.asMatrix(), new PlanarCoordinate(2, 2), Card.Type.GAME) == 7);

        assert (shelf.getGroupSize(shelf.asMatrix(), new PlanarCoordinate(5, 4), Card.Type.GAME) == 3);

        assert (shelf.getGroupSize(shelf.asMatrix(), new PlanarCoordinate(5, 4), Card.Type.TROPHY) == 0);

        assert (shelf.getGroupSize(shelf.asMatrix(), new PlanarCoordinate(5, 4), Card.Type.CAT) == 0);


        List<Integer> result = shelf.getAdjacentGroupsSizes();

        assert (result.size() == 19);

        assert (result.get(0) == 1);
        assert (result.get(1) == 2);
        assert (result.get(2) == 2);
        assert (result.get(3) == 1);
        assert (result.get(4) == 1);
        assert (result.get(5) == 7);
        assert (result.get(6) == 1);
        assert (result.get(7) == 1);
        assert (result.get(8) == 1);
        assert (result.get(9) == 1);
        assert (result.get(10) == 1);
        assert (result.get(11) == 1);
        assert (result.get(12) == 2);
        assert (result.get(13) == 1);
        assert (result.get(14) == 3);
        assert (result.get(15) == 1);
        assert (result.get(16) == 1);
        assert (result.get(17) == 1);
        assert (result.get(18) == 1);

        int[] points = Config.getCustomShelfPoints();

        assert (result.stream().mapToInt(x -> x).sum() == 30);


    }
}