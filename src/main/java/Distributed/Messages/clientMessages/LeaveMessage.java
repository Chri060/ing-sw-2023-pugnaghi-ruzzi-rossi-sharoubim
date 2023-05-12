package Distributed.Messages.clientMessages;

import Controller.Controller;
import Distributed.Client;
import Distributed.Server;

import java.rmi.RemoteException;

public class LeaveMessage extends ClientMessageAbs{
    public LeaveMessage(String name) {
        super(name);
    }

    @Override
    public void execute(Server server, Client client) {
        try {
            server.leave(client, getAuth());
        } catch (RemoteException e) {
            System.err.println("Can't ask to leave");
        }
    }

    @Override
    public void execute(Controller controller) {

    }
}
