package Distributed.Messages.clientMessages;

import Controller.Controller;
import Distributed.Client;
import Distributed.Server;
import util.PlanarCoordinate;

import java.rmi.RemoteException;
import java.util.List;

public class WithdrawMessage extends ClientMessageAbs{

    List<PlanarCoordinate> planarCoordinateList;

    public WithdrawMessage(List<PlanarCoordinate> planarCoordinateList, String name) {
        super(name);
        this.planarCoordinateList = planarCoordinateList;
    }

    @Override
    public void execute(Server server, Client client) {
    }

    @Override
    public void execute(Controller controller) {
        controller.withdraw(name, planarCoordinateList);
    }

}
