package Distributed.Messages.serverMessages;

import Distributed.Client;
import View.View;

import java.util.List;

public class ChatMessage extends ServerMessageAbs{

    private String sender;
    private List<String> receivers;
    private String message;

    public ChatMessage(String sender, List<String> receivers, String message) {
        super(receivers);
        this.sender = sender;
        this.receivers = receivers;
        this.message = message;
    }

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