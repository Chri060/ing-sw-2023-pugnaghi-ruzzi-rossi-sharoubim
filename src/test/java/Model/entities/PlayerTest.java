package Model.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Config;

import java.util.ArrayList;
import java.util.List;

class PlayerTest {

    /**
     * Creates the bag
     */
    @BeforeEach
    void setup() {
        Config.initialise(2);
    }

    /**
     * Creates two players and test every variable inside it with the possible updates
     */
    @Test
    void contructorTest() {
        List<PrivateObjective> playerOneObjective = new ArrayList<>();
        List<Integer> playerOneObjectiveInt = new ArrayList<>();
        playerOneObjective.add(new PrivateObjective(0));
        playerOneObjectiveInt.add(0);
        Player playerOne = new Player("playerOne", playerOneObjective);

        List<PrivateObjective> playerTwoObjective = new ArrayList<>();
        List<Integer> playerTwoObjectiveInt = new ArrayList<>();
        playerTwoObjective.add(new PrivateObjective(3));
        playerTwoObjectiveInt.add(3);
        playerTwoObjective.add(new PrivateObjective(7));
        playerTwoObjectiveInt.add(7);
        playerTwoObjective.add(new PrivateObjective(8));
        playerTwoObjectiveInt.add(8);
        Player playerTwo = new Player("playerTwo", playerTwoObjective);

        assert (playerOne.isConnected());
        assert (playerTwo.isConnected());
        playerOne.setOffline();
        assert (!playerOne.isConnected());
        assert (playerTwo.isConnected());
        playerTwo.setOffline();
        assert (!playerOne.isConnected());
        assert (!playerTwo.isConnected());
        playerOne.setOnline();
        playerTwo.setOnline();
        assert (playerOne.isConnected());
        assert (playerTwo.isConnected());

        assert (playerOne.getName().equals("playerOne"));
        assert (playerTwo.getName().equals("playerTwo"));
        assert (!playerTwo.equals(playerOne));
        assert (!playerOne.equals(playerTwo));
        assert (playerTwo.equals("playerTwo"));
        assert (playerOne.equals("playerOne"));

        assert (playerOne.getPrivateObjectivesID().equals(playerOneObjectiveInt));
        assert (playerTwo.getPrivateObjectivesID().equals(playerTwoObjectiveInt));

        assert (playerOne.getTotalPoints().getValue() == 0);
        assert (playerTwo.getTotalPoints().getValue() == 0);

        assert (playerOne.getPoints().size() == 0);
        assert (playerTwo.getPoints().size() == 0);

        assert (playerOne.getPrivateObjectivePattern() != null);
        assert (playerTwo.getPrivateObjectivePattern() != null);

        playerOne.givePoint(new Point(8, "playerOne's private objectives points"));
        playerOne.givePoint(new Point(6, "Common Objective 10"));
        playerOne.givePoint(new Point(1, "final point"));

        assert (playerOne.getPrivatePoint().size() == 1);
        assert (playerOne.getTotalPrivatePoints().getValue() == 0);

        assert (playerOne.getTotalPoints().getValue() == 15);
        assert (playerOne.hasAlreadyGotCommonPoints(10));
        assert (!playerOne.hasAlreadyGotCommonPoints(11));

        assert (playerOne.getShelf() != null);
        assert (playerTwo.getShelf() != null);
        assert (playerOne.getShelfGroups().size() == 0);
        assert (playerTwo.getShelfGroups().size() == 0);
    }
}