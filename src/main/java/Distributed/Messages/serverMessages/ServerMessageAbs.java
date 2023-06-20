package Distributed.Messages.serverMessages;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for a ServerMessage
 */
public abstract class ServerMessageAbs implements  ServerMessage {

    private boolean broadcast;
    private List<String> receivers;

    /**
     * Construct a generic ServerMessage
     */
    public ServerMessageAbs() {
        this.broadcast = true;
    }

    /**
     * Construct a generic server message with a specific receiver
     *
     * @param receiver is the name of the player that is going to receive the message
     */
    public ServerMessageAbs(String receiver) {
        this.broadcast = false;
        this.receivers = new ArrayList<>();
        this.receivers.add(receiver);
    }

    /**
     * Construct a generic server message with a specific receivers
     *
     * @param receivers is the list of the names of the player that is going to receive the message
     */
    public ServerMessageAbs(List<String> receivers) {
        this.broadcast = false;
        this.receivers = new ArrayList<>(receivers);
    }

    /**
     * Checks if a message is for everyone or not
     *
     * @return true if it is broadcast, false otherwise
     */
    @Override
    public boolean isBroadcast() {
        return broadcast;
    }

    /**
     * @return the list of the receivers
     */
    @Override
    public List<String> getReceiver() {
        return receivers;
    }
}