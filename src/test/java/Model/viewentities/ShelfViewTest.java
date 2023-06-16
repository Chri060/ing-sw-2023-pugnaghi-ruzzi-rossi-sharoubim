package Model.viewentities;

import Model.entities.Card;
import Model.viewEntities.ShelfView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Config;

import java.util.ArrayList;
import java.util.List;

class ShelfViewTest {

    ShelfView shelfView;

    /**
     * Creates the PlayerView
     */
    @BeforeEach
    void setup() {
        Config.initialise(2);
        Card[][] card = new Card[5][6];
        shelfView = new ShelfView(card);
    }

    @Test
    void general() {
        List<Card> card = new ArrayList<>();
        card.add(new Card(Card.Type.CAT,0));
        assert (shelfView.canInsert(card, 1));

        assert (shelfView.maxFreeSpace() == 5);
        assert (shelfView.getShelf() != null);
    }
}