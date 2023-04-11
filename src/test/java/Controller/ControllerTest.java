package Controller;

import Exceptions.*;
import Exceptions.MatchException;
import Model.*;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ControllerTest extends TestCase {



    @Test
    public void cont() throws  MatchException, PlayerNotFoundException, InvalidPickException, NotYourTurnException, CannotWithdrawCardException, Exception, BagEmptyException, NotEnoughSpaceInColumnException {
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
    public void fluxTest() throws MatchException, BagEmptyException {
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
                             NotEnoughSpaceInColumnException e) {
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
                             PlayerNotFoundException | NotEnoughSpaceInColumnException e) {
                    }
                }
                try {
                    action = new EndOfAction(model.getCurrentPlayer());
                    controller.doAction(action);
                } catch (InvalidPickException | NotYourTurnException | CannotWithdrawCardException |
                         PlayerNotFoundException | NotEnoughSpaceInColumnException e) {
                }

            } catch (RuntimeException e) {
                System.err.println(e);
                break;
            }
        }
    }


    @Test
    public void endTest() {

        List<String> players = new ArrayList<>();
        players.add("Christian");
        players.add("Carlo");


        try {
            Match model = new Match(players);
            Controller controller = Controller.createGame(players, players.size(), model);
            try {
                for (int i = 0; i < (new Library()).getLibraryCols(); i++) { //fills chair's player shelf
                    List<Cards> cards = new ArrayList<>();
                    for (int j = 0; j < (new Library()).getLibraryRows(); j++) {
                        cards.add(new Cards(CardsType.CATS, 0));
                    }
                    model.insert(cards, i, model.getChairPlayer());
                }
                List<Integer> coordinates = new ArrayList<>();
                coordinates.add(1);
                coordinates.add(3);

                model.isLastTurn();


                try {
                    controller.doAction(new DrawTile(coordinates, model.getChairPlayer()));
                }
                catch (RuntimeException e) {
                    e.printStackTrace();
                    assert (false);
                }
            }
            catch (InvalidPickException | NotEnoughSpaceInColumnException e) {} catch (BagEmptyException |
                                                                                       NotYourTurnException |
                                                                                       CannotWithdrawCardException |
                                                                                       PlayerNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        catch (MatchException e) {}



    }



}