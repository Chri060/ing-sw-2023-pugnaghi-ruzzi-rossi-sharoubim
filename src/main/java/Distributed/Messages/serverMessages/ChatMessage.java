package Distributed.Messages.serverMessages;

import Distributed.Client;
import View.View;

import java.rmi.RemoteException;
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

    @Override
    public void execute(Client client) {
    }

    @Override
    public void execute(View view) {
        view.showChatMessage(sender, message);
    }
}
