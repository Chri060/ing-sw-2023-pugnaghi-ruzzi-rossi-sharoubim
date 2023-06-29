package Model;

import Exceptions.InvalidActionException;
import Exceptions.InvalidArgumentException;
import Model.entities.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Config;
import util.PlanarCoordinate;

import java.util.ArrayList;
import java.util.List;

class ModelTest {

    Model model;

    /**
     * Creates the model
     */
    @BeforeEach
    void setup() {
        Config.initialise(2);
        model = new Model();
    }

    @Test
    void general() throws InvalidActionException {
        model.setTargetRoomSize(3);
        assert (model.getTargetRoomSize() == 3);
        model.setTargetRoomSize(5);
        assert (model.getTargetRoomSize() == 3);
        assert (model.getRoomSize() == 0);
        assert (model.getRoomLeader() == null);

        assert (model.canJoin("playerOne"));
        model.joinPlayer("playerOne");
        assert (model.canJoin("playerTwo"));
        model.joinPlayer("playerTwo");
        assert (model.isInGame("playerOne"));
        assert (model.getRoomSize() == 2);
        assert (model.getRoomLeader().equals("playerOne"));
        model.removePlayer("playerOne");
        assert (model.getRoomSize() == 1);
        assert (model.getRoomLeader().equals("playerTwo"));
        model.joinPlayer("playerOne");
        assert (model.getRoomSize() == 2);
        assert (model.getRoomLeader().equals("playerTwo"));
        assert (model.isInGame("playerOne"));
        assert (model.isInGame("playerTwo"));

        assert (model.getGameStatus() == Model.GameStatus.PREMATCH);
        model.setGameStatus(Model.GameStatus.PAUSED);
        assert (model.getGameStatus() != Model.GameStatus.PREMATCH);
        assert (model.getCurrentPlayer() == null);

        assert (!model.isLastTurn());
        //model.setTurnStatus(Model.TurnStatus.ENDED);
        //assert (model.getTurnStatus() == Model.TurnStatus.ENDED);
        model.forfeit();
    }

    @Test
    void matchSimulation () throws InvalidActionException, InvalidArgumentException {
        model.setTargetRoomSize(2);
        model.joinPlayer("playerOne");
        model.joinPlayer("playerTwo");
        model.start();
        model.setPlayerOffline("playerOne");
        assert (model.getOnlinePlayersCount() == 1);

        model.setPlayerOnline("playerTwo");
        assert (model.getNextPlayer() != null);

        model.forfeit();
        assert (model.getCurrentPlayer() != null);
        assert (model.getChairPlayer() != null);
        assert (!model.canInsert("playerOne", 1));
        assert (!model.canInsert("playerTwo", 1));

        model.insert("playerOne",3);
        assert (model.getPlayerMaxFreeSpace("playerOne") == 6);
        assert (!model.isLastTurn());
        assert (!model.endGame());
        assert (model.getCommonObjectivesID() != null);
        assert (!model.verifyCommonObj("playerOne",2));
        assert (!model.verifyCommonObj("playerTwo",2));
        assert (model.verifyCommonObj("playerOne") != null);

        model.givePoint("playerOne", new Point(2,"final point"));
        model.giveShelfPoints();
        model.givePrivatePoints();
        assert (model.getPlayerPoints("playerOne").get(0).getValue() == 2);
        assert (!model.needsRefill());

        model.refill();
        List<PlanarCoordinate> cards = new ArrayList<>();
        cards.add(new PlanarCoordinate(1,3));
        assert (model.canWithdraw(cards));

        model.withdraw(cards);
        assert (!model.canWithdraw(cards));

        List<Integer> order = new ArrayList<>();
        List<String> receivers = new ArrayList<>();
        order.add(0);
        receivers.add("playerTwo");
        model.setCurrentPlayer("playerOne");
        model.sortWithdrawnCards(order);
        model.sendChatMessage("playerOne",receivers,"hello");
        model.sortWinners();
        model.sendModelViewData();
        assert (model.getModelviewData("playerOne") != null);
    }
}