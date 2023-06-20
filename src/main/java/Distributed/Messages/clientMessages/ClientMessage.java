package Distributed.Messages.clientMessages;

import Controller.Controller;
import Distributed.Client;
import Distributed.Messages.NetworkMessage;
import Distributed.Server;

/**
 * Interface for a generic message from the client
 */
public interface ClientMessage extends NetworkMessage {

    /**
     * Executes the request from the client on the server
     *
     * @param server is the Server called
     * @param client is the Client that sent the message
     */
    void execute(Server server, Client client);

    /**
     * Executes the request from the server on the controller
     *
     * @param controller is the Controller linked to the Model that is going to execute the action
     */
    void execute(Controller controller);

    /**
     * @return the identifier of the client that sent the message
     */
    String getAuth();
}