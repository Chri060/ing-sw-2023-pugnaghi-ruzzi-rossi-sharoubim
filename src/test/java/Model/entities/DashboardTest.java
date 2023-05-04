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

    @Test
    void withdrawTest() throws InvalidActionException {
        Config.initialise(2);

        Dashboard dashboard = new Dashboard();

        assert(dashboard.needsRefill());

        dashboard.refill(new Bag());

        assert (!dashboard.needsRefill());

        List<PlanarCoordinate> testerList = new ArrayList<>();
        List<Card> cardWithdrawn= new ArrayList<>();

        PlanarCoordinate a =new PlanarCoordinate(1,3);
        PlanarCoordinate b =new PlanarCoordinate(1,4);
        PlanarCoordinate c =new PlanarCoordinate(2,3);
        PlanarCoordinate d =new PlanarCoordinate(2,4);
        PlanarCoordinate e =new PlanarCoordinate(2,5);
        PlanarCoordinate f =new PlanarCoordinate(7,4);
        PlanarCoordinate g =new PlanarCoordinate(7,5);
        PlanarCoordinate h =new PlanarCoordinate(6,4);
        PlanarCoordinate invalid =new PlanarCoordinate(-1,5);

        testerList.add(a);
        testerList.add(b);
        assert(dashboard.canWithdraw(testerList));
        cardWithdrawn = dashboard.withdraw(testerList);
        assert(!dashboard.canWithdraw(testerList));
        assert (cardWithdrawn.size()==2);

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
        assert (cardWithdrawn.size()==2);

        testerList.clear();
        cardWithdrawn.clear();
        testerList.add(h);
        assert (dashboard.canWithdraw(testerList));
        cardWithdrawn = dashboard.withdraw(testerList);
        assert (!dashboard.canWithdraw(testerList));
        assert (cardWithdrawn.size()==1);


        testerList.clear();
        cardWithdrawn.clear();
        testerList.add(invalid);
        assert (!dashboard.canWithdraw(testerList));

        Config.initialise(4);

        Dashboard dashboard4 = new Dashboard();

        assert(dashboard4.needsRefill());

        dashboard4.refill(new Bag());

        assert (!dashboard4.needsRefill());

        PlanarCoordinate i =new PlanarCoordinate(4,0);
        PlanarCoordinate l =new PlanarCoordinate(3,8);
        PlanarCoordinate m =new PlanarCoordinate(4,8);
        PlanarCoordinate n =new PlanarCoordinate(4,6);
        PlanarCoordinate o =new PlanarCoordinate(4,7);

        testerList.clear();
        cardWithdrawn.clear();
        testerList.add(i);
        assert (dashboard4.canWithdraw(testerList));
        cardWithdrawn = dashboard4.withdraw(testerList);
        assert (!dashboard4.canWithdraw(testerList));
        assert (cardWithdrawn.size()==1);

        testerList.clear();
        cardWithdrawn.clear();
        testerList.add(l);
        testerList.add(m);
        assert (dashboard4.canWithdraw(testerList));
        cardWithdrawn = dashboard4.withdraw(testerList);
        assert (!dashboard4.canWithdraw(testerList));
        assert (cardWithdrawn.size()==2);

        testerList.clear();
        cardWithdrawn.clear();
        testerList.add(n);
        testerList.add(o);
        assert (!dashboard4.canWithdraw(testerList));
        cardWithdrawn = dashboard4.withdraw(testerList);
        assert (!dashboard4.canWithdraw(testerList));
        assert (cardWithdrawn.size()==2);



    }



}