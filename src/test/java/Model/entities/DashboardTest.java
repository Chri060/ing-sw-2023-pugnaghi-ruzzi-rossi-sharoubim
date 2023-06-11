package Model.entities;

import Exceptions.InvalidActionException;
import Exceptions.InvalidArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Config;
import util.PlanarCoordinate;

import java.util.ArrayList;
import java.util.List;

class DashboardTest {

    Dashboard dashboard;

    /**
     * Creates the dashboard
     */
    @BeforeEach
    void setup() {
        Config.initialise(2);
        dashboard = new Dashboard();
    }

    /**
     * Checks if the created dashboard is correct or not and refilled correctly
     *
     * @throws InvalidActionException on invalid action
     * @throws InvalidActionException on invalid argument
     */
    @Test
    void constructorTest() throws InvalidActionException, InvalidArgumentException {
        int columns = dashboard.getColumns();
        assert (columns == 9);
        int rows = dashboard.getRows();
        assert (rows == 9);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                switch (i) {
                    case 1 -> {
                        if (j == 3 || j == 4) assert (dashboard.isCellAvailable(new PlanarCoordinate(i, j)));
                        else assert (!dashboard.isCellAvailable(new PlanarCoordinate(i, j)));
                    }
                    case 2, 6 -> {
                        if (j == 3 || j == 4 || j == 5) assert (dashboard.isCellAvailable(new PlanarCoordinate(i, j)));
                        else assert (!dashboard.isCellAvailable(new PlanarCoordinate(i, j)));
                    }
                    case 3 -> {
                        if (j == 0 || j == 1 || j == 8) assert (!dashboard.isCellAvailable(new PlanarCoordinate(i, j)));
                        else assert (dashboard.isCellAvailable(new PlanarCoordinate(i, j)));
                    }
                    case 4 -> {
                        if (j == 0 || j == 8) assert (!dashboard.isCellAvailable(new PlanarCoordinate(i, j)));
                        else assert (dashboard.isCellAvailable(new PlanarCoordinate(i, j)));
                    }
                    case 5 -> {
                        if (j == 0 || j == 7 || j == 8) assert (!dashboard.isCellAvailable(new PlanarCoordinate(i, j)));
                        else assert (dashboard.isCellAvailable(new PlanarCoordinate(i, j)));
                    }
                    case 7 -> {
                        if (j == 4 || j == 5) assert (dashboard.isCellAvailable(new PlanarCoordinate(i, j)));
                        else assert (!dashboard.isCellAvailable(new PlanarCoordinate(i, j)));
                    }
                    default -> {
                        assert (!dashboard.isCellAvailable(new PlanarCoordinate(i, j)));
                    }
                }
            }
        }

        assert (dashboard.needsRefill());
        dashboard.refill(new Bag());
        assert (!dashboard.needsRefill());

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                switch (i) {
                    case 1 -> {
                        if (j == 3 || j == 4) assert (dashboard.checkCell(new PlanarCoordinate(i, j)).equals(dashboard.asMatrix()[i][j]));
                        else assert (!dashboard.isCellAvailable(new PlanarCoordinate(i, j)));
                    }
                    case 2, 6 -> {
                        if (j == 3 || j == 4 || j == 5) assert (dashboard.checkCell(new PlanarCoordinate(i, j)).equals(dashboard.asMatrix()[i][j]));
                        else assert (!dashboard.isCellAvailable(new PlanarCoordinate(i, j)));
                    }
                    case 3 -> {
                        if (j == 0 || j == 1 || j == 8) assert (!dashboard.isCellAvailable(new PlanarCoordinate(i, j)));
                        else assert (dashboard.checkCell(new PlanarCoordinate(i, j)).equals(dashboard.asMatrix()[i][j]));
                    }
                    case 4 -> {
                        if (j == 0 || j == 8) assert (!dashboard.isCellAvailable(new PlanarCoordinate(i, j)));
                        else assert (dashboard.checkCell(new PlanarCoordinate(i, j)).equals(dashboard.asMatrix()[i][j]));
                    }
                    case 5 -> {
                        if (j == 0 || j == 7 || j == 8) assert (!dashboard.isCellAvailable(new PlanarCoordinate(i, j)));
                        else assert (dashboard.checkCell(new PlanarCoordinate(i, j)).equals(dashboard.asMatrix()[i][j]));
                    }
                    case 7 -> {
                        if (j == 4 || j == 5) assert (dashboard.checkCell(new PlanarCoordinate(i, j)).equals(dashboard.asMatrix()[i][j]));
                        else assert (!dashboard.isCellAvailable(new PlanarCoordinate(i, j)));
                    }
                    default -> {
                        assert (!dashboard.isCellAvailable(new PlanarCoordinate(i, j)));
                    }
                }
            }
        }

        assert (dashboard.getIterator() != null);
    }

    /**
     * Tries various combination of coordinates to check if they are correct or not
     *
     * @throws InvalidActionException on invalid action
     */
    @Test
    void drawTest() throws InvalidActionException {
        dashboard.refill(new Bag());
        List<PlanarCoordinate> testerList = new ArrayList<>();

        testerList.add(new PlanarCoordinate(0 ,0));
        assert (!dashboard.canWithdraw(testerList));

        testerList.clear();
        testerList.add(new PlanarCoordinate(1 ,3));
        testerList.add(new PlanarCoordinate(1 ,4));
        assert (dashboard.canWithdraw(testerList));

        testerList.add(new PlanarCoordinate(2 ,3));
        assert (!dashboard.canWithdraw(testerList));

        testerList.clear();
        testerList.add(new PlanarCoordinate(2 ,3));
        testerList.add(new PlanarCoordinate(2 ,4));
        assert (!dashboard.canWithdraw(testerList));

        testerList.clear();
        testerList.add(new PlanarCoordinate(3 ,3));
        testerList.add(new PlanarCoordinate(2 ,4));
        assert (!dashboard.canWithdraw(testerList));

        testerList.clear();
        testerList.add(new PlanarCoordinate(2 ,3));
        testerList.add(new PlanarCoordinate(2 ,5));
        assert (!dashboard.canWithdraw(testerList));

        testerList.clear();
        testerList.add(new PlanarCoordinate(2 ,4));
        assert (!dashboard.canWithdraw(testerList));

        testerList.clear();
        testerList.add(new PlanarCoordinate(1 ,3));
        testerList.add(new PlanarCoordinate(1 ,4));
        testerList.add(new PlanarCoordinate(1 ,3));
        assert (!dashboard.canWithdraw(testerList));
    }

    /**
     * Try to withdraw from various cells on the dashboard
     *
     * @throws InvalidActionException on invalid action
     */
    @Test
    void withdrawTest() throws InvalidActionException {

        assert(dashboard.needsRefill());

        dashboard.refill(new Bag());
        assert (!dashboard.needsRefill());

        List<PlanarCoordinate> testerList = new ArrayList<>();
        PlanarCoordinate a = new PlanarCoordinate(1,3);
        PlanarCoordinate b = new PlanarCoordinate(1,4);
        PlanarCoordinate c = new PlanarCoordinate(2,3);
        PlanarCoordinate d = new PlanarCoordinate(2,4);
        PlanarCoordinate e = new PlanarCoordinate(2,5);
        PlanarCoordinate f = new PlanarCoordinate(7,4);
        PlanarCoordinate g = new PlanarCoordinate(7,5);
        PlanarCoordinate h = new PlanarCoordinate(6,4);
        PlanarCoordinate invalid = new PlanarCoordinate(-1,5);
        testerList.add(a);
        testerList.add(b);
        assert(dashboard.canWithdraw(testerList));

        List<Card> cardWithdrawn = dashboard.withdraw(testerList);
        assert(!dashboard.canWithdraw(testerList));
        assert (cardWithdrawn.size() == 2);

        testerList.clear();
        cardWithdrawn.clear();
        testerList.add(c);
        testerList.add(d);
        testerList.add(e);
        assert (dashboard.canWithdraw(testerList));

        cardWithdrawn = dashboard.withdraw(testerList);
        assert (!dashboard.canWithdraw(testerList));
        assert (cardWithdrawn.size()==3);

        testerList.clear();
        cardWithdrawn.clear();
        assert (!dashboard.canWithdraw(testerList));

        testerList.add(f);
        testerList.add(g);
        assert (dashboard.canWithdraw(testerList));

        cardWithdrawn = dashboard.withdraw(testerList);
        assert (!dashboard.canWithdraw(testerList));
        assert (cardWithdrawn.size() == 2);

        testerList.clear();
        cardWithdrawn.clear();
        testerList.add(h);
        assert (dashboard.canWithdraw(testerList));

        cardWithdrawn = dashboard.withdraw(testerList);
        assert (!dashboard.canWithdraw(testerList));
        assert (cardWithdrawn.size() == 1);

        testerList.clear();
        cardWithdrawn.clear();
        testerList.add(invalid);
        assert (!dashboard.canWithdraw(testerList));

        Config.initialise(4);
        Dashboard dashboard4 = new Dashboard();
        assert(dashboard4.needsRefill());

        dashboard4.refill(new Bag());
        assert (!dashboard4.needsRefill());

        PlanarCoordinate i = new PlanarCoordinate(4,0);
        PlanarCoordinate l = new PlanarCoordinate(3,8);
        PlanarCoordinate m = new PlanarCoordinate(4,8);
        PlanarCoordinate n = new PlanarCoordinate(4,6);
        PlanarCoordinate o = new PlanarCoordinate(4,7);
        testerList.clear();
        cardWithdrawn.clear();
        testerList.add(i);
        assert (dashboard4.canWithdraw(testerList));

        cardWithdrawn = dashboard4.withdraw(testerList);
        assert (!dashboard4.canWithdraw(testerList));
        assert (cardWithdrawn.size() == 1);

        testerList.clear();
        cardWithdrawn.clear();
        testerList.add(l);
        testerList.add(m);
        assert (dashboard4.canWithdraw(testerList));

        cardWithdrawn = dashboard4.withdraw(testerList);
        assert (!dashboard4.canWithdraw(testerList));
        assert (cardWithdrawn.size() == 2);

        testerList.clear();
        cardWithdrawn.clear();
        testerList.add(n);
        testerList.add(o);
        assert (!dashboard4.canWithdraw(testerList));
        cardWithdrawn = dashboard4.withdraw(testerList);
        assert (!dashboard4.canWithdraw(testerList));
        assert (cardWithdrawn.size() == 2);
    }
}