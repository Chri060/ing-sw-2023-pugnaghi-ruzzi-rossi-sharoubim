package Distributed.Messages.clientMessages;

import Controller.Controller;
import Distributed.Client;
import Distributed.Server;

import java.rmi.RemoteException;

public class InsertMessage extends ClientMessageAbs{

    private int column;

    public InsertMessage(String name, int column) {
        super(name);
        this.column = column;
    }

    @Override
    public void execute(Server server, Client client) {
    }

    @Override
    public void execute(Controller controller) {
        controller.insert(name, column);
    }
}
