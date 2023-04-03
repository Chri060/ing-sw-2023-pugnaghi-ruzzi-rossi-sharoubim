package Controller;

import Exceptions.*;
import Model.*;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


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

    @Test
    public void fluxTest() throws NotEnoughPrivateObjectivesException, IncorrectPlayersNumberException, BagEmptyException {
        List<String> players = new ArrayList<>();
        players.add("Christian");
        players.add("Carlo");
        Match model = new Match(players);
        List<Integer> coordinates = new ArrayList<>();
        Controller controller = Controller.createGame(players, players.size(), model);
        Random rand = new Random();
        PlayerAction action;
        List<Cards> withdrawn;

        boolean draw = true;
        boolean inserted = false;

        while (true) {
            try {
                while (draw) {
                    //Pick randomly cards coordinates in the dashboard until they're valid
                    try {
                        for (int i = 0; i < 2; i++) {
                            coordinates.add(rand.nextInt(9));
                        }
                            action = new DrawTile(coordinates, model.getCurrentPlayer());
                            try {
                                controller.doAction(action);
                                draw = false;
                                inserted = false;
                                System.out.println("Coordinates withdrawn: " + coordinates);
                                model.printDashboard();
                            } catch (PlayerNotFoundException e) {}
                    } catch (InvalidPickException | NotYourTurnException | CannotWithdrawCardException |
                             ColumFullException e) {
                        coordinates.clear();
                    }
                }
                while (!inserted) {
                    try {
                        action = new InsertTile(rand.nextInt(5), model.getCardsToInsert(), model.getCurrentPlayer());
                        controller.doAction(action);
                        inserted = true;
                        draw = true;

                        System.out.println(model.getCurrentPlayer() + "'s library");
                        model.printLibrary(model.getPlayerLibrary(model.getCurrentPlayer()));

                    } catch (InvalidPickException | NotYourTurnException | CannotWithdrawCardException |
                             PlayerNotFoundException | ColumFullException e) {
                    }
                }
                try {
                    action = new EndOfAction(model.getCurrentPlayer());
                    controller.doAction(action);
                } catch (InvalidPickException | NotYourTurnException | CannotWithdrawCardException |
                         PlayerNotFoundException | ColumFullException e) {
                }

            } catch (RuntimeException e) {
                System.err.println(e);
                break;
            }
        }
    }
}