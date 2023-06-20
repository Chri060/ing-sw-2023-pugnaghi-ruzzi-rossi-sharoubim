package Distributed.Messages.clientMessages;

import Controller.Controller;
import Distributed.Client;
import Distributed.Server;

import java.util.List;

/**
 * Class that implements a ChatMessage
 */
public class ChatMessage extends ClientMessageAbs {

    private List<String> receivers;
    private String message;

    /**
     * Construct the message with sender, receivers and the message
     *
     * @param name is the name of the person who sent the message
     * @param receivers is a list of player that will receive the message
     * @param message is the actual message
     */
    public ChatMessage(String name, List<String> receivers, String message) {
        super(name);
        this.receivers = receivers;
        this.message = message;
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
     * It calls the function to send chat messages on the controller.
     *
     * @param controller is the Controller linked to the Model that is going to execute the action
     */
    @Override
    public void execute(Controller controller) {
        controller.chatMessage(name, receivers, message);
    }
}