package Distributed.Messages.serverMessages;

import Distributed.Client;
import View.View;

import java.util.List;

/**
 * Class that implements a ChatMessage
 */
public class ChatMessage extends ServerMessageAbs {

    private String sender;
    private List<String> receivers;
    private String message;

    /**
     * Construct a ChatMessage with sender and receivers
     *
     * @param sender is the name of the person who sent the message
     * @param receivers is the list of people that will receive the message
     * @param message is the message sent
     */
    public ChatMessage(String sender, List<String> receivers, String message) {
        super(receivers);
        this.sender = sender;
        this.receivers = receivers;
        this.message = message;
    }

    /**
     * Construct a ChatMessage with only sender
     *
     * @param sender is the name of the person who sent the message
     * @param message is the message to send
     */
    public ChatMessage(String sender, String message) {
        super();
        this.sender = sender;
        this.message = message;
    }

    /**
     * Executes the message on the client
     *
     * @param client is the Client used to execute the message
     */
    @Override
    public void execute(Client client) {

    }

    /**
     * Executes the message on the view.
     * It calls the method used to show a chat message on the View
     *
     * @param view is the View used to execute the message
     */
    @Override
    public void execute(View view) {
        view.showChatMessage(sender, message);
    }
}