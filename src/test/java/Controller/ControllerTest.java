package Controller;

import Exceptions.*;
import Model.*;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class ControllerTest extends TestCase {



    @Test
    public void cont() throws NotEnoughPrivateObjectivesException, IncorrectPlayersNumberException, PlayerNotFoundException, InvalidPickException, NotYourTurnException, CannotWithdrawCardException, Exception, BagEmptyException, ColumFullException {
        List<String> players = new ArrayList<>();
        players.add("Christian");
        players.add("Carlo");
        Match model = new Match(players);
        Controller controller = Controller.createGame(players, players.size(), model);
        if(model.needsRefill()) {
            model.refill();
        }


        List<Integer> coordinates = new ArrayList<>();
        coordinates.add(1);
        coordinates.add(4);
        PlayerAction action = new DrawTile(coordinates , "Carlo");
        controller.doAction(action);
        System.out.println(model.getCurrentAction());

        List<Cards> cards = new ArrayList<>();
        cards.add(new Cards(CardsType.CATS, 10));
        cards.add(new Cards(CardsType.PLAN, 11));
        action = new InsertTile(1, cards, "Carlo");
        controller.doAction(action);
        System.out.println(model.getCurrentAction());

        action = new EndOfAction("Carlo");
        controller.doAction(action);
        System.out.println(model.getCurrentAction());
        System.out.println(model.getCurrentPlayer());
    }
}