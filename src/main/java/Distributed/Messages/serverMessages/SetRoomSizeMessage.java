package Distributed.Messages.serverMessages;

import Distributed.Client;
import View.View;

public class SetRoomSizeMessage extends ServerMessageAbs{

    String roomLeader;

    public SetRoomSizeMessage(String roomLeader) {
        super(roomLeader);
        this.roomLeader = roomLeader;
    }

    @Override
    public void execute(Client client) {
    }

    @Override
    public void execute(View view) {
        view.model.setRoomLeader(roomLeader);
    }
}