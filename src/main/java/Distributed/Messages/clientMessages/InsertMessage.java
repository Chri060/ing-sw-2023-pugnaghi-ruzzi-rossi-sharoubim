package Distributed.Messages.clientMessages;

import Controller.Controller;
import Distributed.Client;
import Distributed.Server;

/**
 * Class that implements a InsertMessage
 */
public class InsertMessage extends ClientMessageAbs {

    private int column;

    /**
     * Construct a new InsertMessage with the name of the player and the selected column
     *
     * @param name is the name of the player
     * @param column is the column where to insert the cards
     */
    public InsertMessage(String name, int column) {
        super(name);
        this.column = column;
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
     * It calls the function to insert the cards on the controller.
     *
     * @param controller is the Controller linked to the Model that is going to execute the action
     */
    @Override
    public void execute(Controller controller) {
        controller.insert(name, column);
    }
}