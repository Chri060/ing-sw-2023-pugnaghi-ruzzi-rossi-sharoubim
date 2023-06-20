package Distributed.Messages.serverMessages;

import Distributed.Client;
import View.View;

public class SetRoomSizeMessage extends ServerMessageAbs{

    String roomLeader;

    public SetRoomSizeMessage(String roomLeader) {
        super(roomLeader);
        this.roomLeader = roomLeader;
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
     * It calls the method used to request the dimension of the room on the View
     *
     * @param view is the View used to execute the message
     */
    @Override
    public void execute(View view) {
        view.model.setRoomLeader(roomLeader);
    }
}