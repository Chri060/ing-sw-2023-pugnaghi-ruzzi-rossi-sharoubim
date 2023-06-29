package Distributed.Messages.clientMessages;

import Controller.Controller;
import Distributed.Client;
import Distributed.Server;

public class PingServerMessage extends ClientMessageAbs{

    /**
     * Sets the name of the client who sent the message
     *
     * @param name is the name of the player who sent the request
     */
    public PingServerMessage(String name) {
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

    }

    /**
     * Executes the request from the server on the controller.
     *
     * @param controller is the Controller linked to the Model that is going to execute the action
     */
    @Override
    public void execute(Controller controller) {

    }
}
