package Distributed.Messages.clientMessages;

import Controller.Controller;
import Distributed.Client;
import Distributed.Server;

import java.util.List;

public class ChatMessage extends ClientMessageAbs {

    private List<String> receivers;
    private String message;

    public ChatMessage(String name, List<String> receivers, String message) {
        super(name);
        this.receivers = receivers;
        this.message = message;
    }

    @Override
    public void execute(Server server, Client client) {

    }

    @Override
    public void execute(Controller controller) {
        controller.chatMessage(name, receivers, message);
    }
}
