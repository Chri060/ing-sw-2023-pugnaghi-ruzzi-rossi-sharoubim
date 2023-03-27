package Model;

import Exceptions.*;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.min;

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
    void constructionTest1() throws NotEnoughPrivateObjectivesException, IncorrectPlayersNumberException {

        List<String> players = new ArrayList<>();

        players.add("Christian");
        players.add("Carlo");
        //players.add("Alessandro");
       // players.add("Gianluca");
        Match match = new Match(players);
    }

    @Test
    void fluxControl() throws NotEnoughPrivateObjectivesException, IncorrectPlayersNumberException, PlayerNotFoundException {

        List<String> players = new ArrayList<>();

        players.add("Christian");
        players.add("Carlo");
        players.add("Alessandro");
        players.add("Gianluca");
        Match match = new Match(players);



        List<Cards> cardsToWithdraw = new ArrayList<Cards>();
        LibraryTest printer = new LibraryTest();
        List<Integer> coordinates = new ArrayList<Integer>();
        Random rand = new Random();
        int numberOfCardsWithdrawn;
        boolean done = false;
        boolean inserted = false;


        boolean bagEmpty = false;


        while (!match.endMatch() || !match.getChairPlayer().equals(match.getCurrentPlayer())) {
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
                numberOfCardsWithdrawn = rand.nextInt(min(3, match.getPlayerLibrary(match.getCurrentPlayer()).maxFreeSpace())) + 1;
                for (int i = 0; i < 2 * numberOfCardsWithdrawn; i++) {
                    coordinates.add(rand.nextInt(9));
                }
                try {
                    cardsToWithdraw =  match.withdraw(coordinates, match.getCurrentPlayer());
                    match.nextAction();
                    while (!inserted) {
                        try {
                            match.insert(cardsToWithdraw, rand.nextInt(match.getPlayerLibrary(match.getCurrentPlayer()).getLibraryCols()), match.getCurrentPlayer());
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
                } catch (CannotWithdrawCardException | InvalidPickException | NotYourTurnException e) {
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
        for (int i = 0; i < l.getLibraryRows(); i++) {
            for (int j = 0; j < l.getLibraryCols(); j++) {
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