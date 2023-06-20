package Distributed.Messages.serverMessages;

import Distributed.Client;
import View.View;
import Distributed.Messages.NetworkMessage;

import java.util.List;

/**
 * Interface for a message sent from the server
 */
public interface ServerMessage extends NetworkMessage {

    /**
     * Checks if a message is for all clients or for a particular one
     *
     * @return true if the message is for everyone, false otherwise
     */
    boolean isBroadcast();

    /**
     * @return the list of the names of the player who is going to receive the message
     */
    List<String> getReceiver();

    /**
     * Executes the message on the client
     *
     * @param client is the Client used to execute the message
     */
    void execute(Client client);

    /**
     * Executes the message on the view
     *
     * @param view is the View used to execute the message
     */
    void execute(View view);
}