package Distributed.Messages.serverMessages;

import Distributed.Client;
import View.View;

public class RoomSizeSettedMessage extends ServerMessageAbs{

    private int roomSize;

    public RoomSizeSettedMessage(int roomSize) {
        super();
        this.roomSize = roomSize;
    }

    @Override
    public void execute(Client client) {
    }

    @Override
    public void execute(View view) {
        view.model.setTargetRoomSize(roomSize);
        view.model.setReceived();
    }
}
