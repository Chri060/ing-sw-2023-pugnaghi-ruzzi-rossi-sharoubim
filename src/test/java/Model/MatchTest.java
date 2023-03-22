package Model;

import Exceptions.*;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MatchTest extends TestCase {

    @Test
    Match constructionTest() throws NotEnoughPrivateObjectivesException, NotEnoughPlayersException {

        List<String> players = new ArrayList<>();

        players.add("Christian");
        players.add("Carlo");
        players.add("Alessandro");
        players.add("Gianluca");
            Match match = new Match(players);
            return match;

    }
    @Test
    void fluxControl() throws NotEnoughPrivateObjectivesException, NotEnoughPlayersException {
        List<Cards> cardsToWithdraw = new ArrayList<Cards>();
        List<Integer> coordinates = new ArrayList<Integer>();
        Random rand = new Random();
        int numberOfCardsWithdrawn;
        boolean done = false;

            Match match = constructionTest();


        while (!match.endMatch()) {
            done = false;
            if (match.needsRefill()) {
                try {
                    match.refill();
                }
                catch (BagEmptyException e) {
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
                    cardsToWithdraw =  match.withdraw(coordinates);
                    match.nextAction();
                    match.insert(cardsToWithdraw, rand.nextInt(5), match.getCurrentPlayer());
                    try {
                        System.out.println(match.getCurrentPlayer() + "'s library:");
                        printLibrary(match.getPlayerLibrary(match.getCurrentPlayer()));
                        match.nextPlayer();
                        done = true;
                    }
                    catch (PlayerNotFoundException e) {};
                } catch (CannotWIthdrowCardException | InvalidPickException | ColumFullException e) {
                    coordinates.clear();
                }
            }

        }

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