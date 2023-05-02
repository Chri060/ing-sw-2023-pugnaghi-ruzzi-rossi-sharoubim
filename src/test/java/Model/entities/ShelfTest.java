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
}