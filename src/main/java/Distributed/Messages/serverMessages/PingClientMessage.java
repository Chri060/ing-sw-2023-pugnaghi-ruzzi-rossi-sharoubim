package Distributed.Messages.serverMessages;

import Distributed.Client;
import View.View;

public class PingClientMessage extends ServerMessageAbs{

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
     * It calls the method used to ping a client on the View
     *
     * @param view is the View used to execute the message
     */
    @Override
    public void execute(View view) {
    }
}