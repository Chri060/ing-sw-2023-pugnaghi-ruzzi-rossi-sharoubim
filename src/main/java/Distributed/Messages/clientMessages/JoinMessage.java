package Distributed.Messages.clientMessages;

import Controller.Controller;
import Distributed.Client;
import Distributed.Server;

import java.rmi.RemoteException;

public class JoinMessage extends ClientMessageAbs{
    public JoinMessage(String name) {
        super(name);
    }

    @Override
    public void execute(Server server, Client client) {
        try {
        server.register(client, getAuth());
    } catch (RemoteException e) {}
    }

    @Override
    public void execute(Controller controller) {
    }

}
