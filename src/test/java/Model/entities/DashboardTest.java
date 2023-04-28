package Model.entities;

import Exceptions.InvalidActionException;
import org.junit.jupiter.api.Test;
import util.Config;
import util.PlanarCoordinate;

import java.util.ArrayList;
import java.util.List;

class DashboardTest {

    @Test
    void constructorTest() {
        Config.initialise(2);
        Dashboard dashboard = new Dashboard();
        int columns = dashboard.getColumns();

        int i = 0;

        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;

        assert (true == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (true == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;

        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;


        assert (true == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (true == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (true == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;


        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;

        assert (true == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (true == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (true == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (true == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (true == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (true == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;

        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;

        assert (true == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (true == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (true == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (true == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (true == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (true == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (true == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;

        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;

        assert (true == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (true == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (true == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (true == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (true == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (true == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;

        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;

        assert (true == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (true == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (true == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;

        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;

        assert (true == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (true == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;

        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;
        assert (false == dashboard.isCellAvailable(new PlanarCoordinate(i / columns, i % columns)));
        i++;

        assert(i == dashboard.getColumns()*dashboard.getRows());
    }


    @Test
    void drawTest() throws InvalidActionException {
        Config.initialise(2);
        Dashboard dashboard = new Dashboard();
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

    @Test
    void refillTest() throws InvalidActionException {
        Config.initialise(2);

        Dashboard dashboard = new Dashboard();

        assert(dashboard.needsRefill());

        dashboard.refill(new Bag());

        assert (!dashboard.needsRefill());

        List<PlanarCoordinate> testerList = new ArrayList<>();




    }




}