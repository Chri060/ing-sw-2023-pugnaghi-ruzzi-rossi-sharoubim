package Model.viewentities;

import Model.entities.Card;
import Model.entities.Point;
import Model.viewEntities.PlayerView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Config;

import java.util.ArrayList;
import java.util.List;

class PlayerViewTest {

    PlayerView playerView;

    /**
     * Creates the PlayerView
     */
    @BeforeEach
    void setup() {
        Config.initialise(2);
        playerView = new PlayerView();
    }

    @Test
    void general() {
        playerView.setName("player");
        assert (playerView.getName().equals("player"));

        List<Point> point = new ArrayList<>();
        point.add(new Point(1, "test"));
        playerView.setPoint(point);
        assert (playerView.getPoint().size() == 1);
        assert (playerView.getTotalPoint().getValue() == 1);

        Card[][] card = new Card[5][6];
        playerView.setShelf(card);
        assert (playerView.getShelf() != null);
    }
}