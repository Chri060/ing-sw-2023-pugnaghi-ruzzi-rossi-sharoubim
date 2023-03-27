package Model;

import Exceptions.PlayerAlreadyInLobby;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

public class PreMatchTest extends TestCase {

    @Test
    void createAndUse() throws PlayerAlreadyInLobby {
        PreMatch prelobby = new PreMatch();

        prelobby.addPlayer("Marco");
        prelobby.print();
        prelobby.addPlayer("Lucia");
        prelobby.print();
        prelobby.addPlayer("Matteo");
        prelobby.addPlayer("Antonio");
        prelobby.togglePlayer("Marco");
        prelobby.print();
        printAllReady(prelobby);
        prelobby.togglePlayer("Antonio");
        prelobby.togglePlayer("Lucia");
        prelobby.togglePlayer("Matteo");

        prelobby.print();

        printAllReady(prelobby);


    }

    @Test
    void printAllReady(PreMatch preMatch) {
        System.out.println(preMatch.allReady());
    }


}