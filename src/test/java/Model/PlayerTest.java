package Model;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayerTest extends TestCase {

    @Test
    void constructorTest() {
        List<Integer> pObjID = new ArrayList<>();
        Random rand = new Random();
        Player player;

        pObjID.add(rand.nextInt(12));

        player = new Player("Giocatore", pObjID);

        player.getObjPattern();

    }

}