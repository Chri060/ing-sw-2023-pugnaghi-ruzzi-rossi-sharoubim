package Distributed.Messages.serverMessages;

import Distributed.Client;
import View.View;

/**
 * Class that implements a RoomSizeSettedMessage
 */
public class RoomSizeSettedMessage extends ServerMessageAbs {

    private int roomSize;

    /**
     * Construct a message that indicates that the room size had been set
     *
     * @param roomSize is the room size
     */
    public RoomSizeSettedMessage(int roomSize) {
        super();
        this.roomSize = roomSize;
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
     * It calls the method used to set the room size on the View
     *
     * @param view is the View used to execute the message
     */
    @Override
    public void execute(View view) {
        view.model.setTargetRoomSize(roomSize);
        view.model.setReceived();
    }
}