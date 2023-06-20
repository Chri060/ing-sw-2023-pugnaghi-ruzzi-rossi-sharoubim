package Distributed.Messages.clientMessages;

import Controller.Controller;
import Distributed.Client;
import Distributed.Server;

import java.rmi.RemoteException;
/**
 * Class that implements a JoinMessage
 */
public class JoinMessage extends ClientMessageAbs {

    /**
     * Construct a message for a new player who is joining
     *
     * @param name is the name of the player who is joining
     */
    public JoinMessage(String name) {
        super(name);
    }

    /**
     * Executes the request from the client on the server.
     * It trys to register a new Client on the Server
     *
     * @param server is the Server called
     * @param client is the Client that sent the message
     */
    @Override
    public void execute(Server server, Client client) {
        try {
            server.register(client, getAuth());
        } catch (RemoteException e) {
            /*do nothing*/
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