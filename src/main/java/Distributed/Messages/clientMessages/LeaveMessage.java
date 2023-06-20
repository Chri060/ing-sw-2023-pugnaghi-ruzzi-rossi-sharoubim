package Distributed.Messages.clientMessages;

import Controller.Controller;
import Distributed.Client;
import Distributed.Server;

import java.rmi.RemoteException;

/**
 * Class that implements a LeaveMessage
 */
public class LeaveMessage extends ClientMessageAbs {

    /**
     * Construct a message for a player who left
     *
     * @param name is the name og the player who left
     */
    public LeaveMessage(String name) {
        super(name);
    }

    /**
     * Executes the request from the client on the server
     *
     * @param server is the Server called
     * @param client is the Client that sent the message
     */
    @Override
    public void execute(Server server, Client client) {
        try {
            server.leave(client, getAuth());
        } catch (RemoteException e) {
            System.err.println("Can't ask to leave");
        }
    }

    /**
     * Executes the request from the server on the controller
     *
     * @param controller is the Controller linked to the Model that is going to execute the action
     */
    @Override
    public void execute(Controller controller) {

    }
}