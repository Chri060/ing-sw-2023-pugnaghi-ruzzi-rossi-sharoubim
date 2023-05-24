package Distributed.Messages.serverMessages;

import View.View;

import java.util.ArrayList;
import java.util.List;

public abstract class ServerMessageAbs implements  ServerMessage {

    private boolean broadcast;
    private List<String> receivers;

    public ServerMessageAbs() {
        this.broadcast = true;
    }

    public ServerMessageAbs(String receiver) {
        this.broadcast = false;
        this.receivers = new ArrayList<>();
        this.receivers.add(receiver);
    }

    public ServerMessageAbs(List<String> receivers) {
        this.broadcast = false;
        this.receivers = new ArrayList<>(receivers);
    }

    @Override
    public boolean isBroadcast() {
        return broadcast;
    }

    @Override
    public List<String> getReceiver() {
        return receivers;
    }

}
