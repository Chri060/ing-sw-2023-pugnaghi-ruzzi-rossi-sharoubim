package Model;

import Exceptions.*;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MatchTest extends TestCase {

    @Test
    Match constructionTest() throws NotEnoughPrivateObjectivesException, IncorrectPlayersNumberException {

        List<String> players = new ArrayList<>();

        players.add("Christian");
        players.add("Carlo");
        players.add("Alessandro");
        //players.add("Gianluca");
            Match match = new Match(players);
            return match;

    }
    @Test
    void fluxControl() throws NotEnoughPrivateObjectivesException, IncorrectPlayersNumberException {
        List<Cards> cardsToWithdraw = new ArrayList<Cards>();
        List<Integer> coordinates = new ArrayList<Integer>();
        Random rand = new Random();
        int numberOfCardsWithdrawn;
        boolean done = false;
        boolean inserted = false;

            Match match = constructionTest();
            boolean bagEmpty = false;


        while (!match.endMatch()) {
            done = false;
            if (match.needsRefill() && !bagEmpty) {
                try {
                    System.out.println("refilling:");
                    match.refill();
                    System.out.println("cards left ib bag: " + match.getCardsLeft());
                }
                catch (BagEmptyException e) {
                    bagEmpty = true;
                }
            }
            match.printDashboard();

            while (!done) {
                numberOfCardsWithdrawn = rand.nextInt(2);
                for (int i = 0; i < 2; i++) {
                    coordinates.add(rand.nextInt(9));
                }/*
                coordinates.add(1);
                coordinates.add(3);*/
                try {
                    cardsToWithdraw =  match.withdraw(coordinates, match.getCurrentPlayer());
                    match.nextAction();
                    while (!inserted) {
                        try {
                            match.insert(cardsToWithdraw, rand.nextInt(5), match.getCurrentPlayer());
                            inserted = true;
                        }
                        catch (ColumFullException e) {
                        }
                    }
                    inserted = false;
                    System.out.println(coordinates);
                    try {
                        System.out.println(match.getCurrentPlayer() + "'s library:");
                        printLibrary(match.getPlayerLibrary(match.getCurrentPlayer()));
                        match.nextPlayer();
                        done = true;
                    }
                    catch (PlayerNotFoundException e) {};
                } catch (CannotWIthdrowCardException | InvalidPickException | NotYourTurnException e) {
                    coordinates.clear();
                }

            }

        }
        System.out.println();
        System.out.println("-----------------------------------------------------------");
        System.out.println();
        match.printPlayerPoints();

    }




    @Test
    void printLibrary(Library l) {
        Cards[][] c = l.getAsMatrix();
        for (int i = l.LIBRARYROWS - 1; i >= 0; i--) {
            for (int j = 0; j < l.LIBRARYCOLUMNS; j++) {
                if (c[i][j] != null) {
                    System.out.print(c[i][j].getType() + "\t");
                }
                else {
                    System.out.print("null" + "\t");
                }
            }
            System.out.println();

        }
    }

}