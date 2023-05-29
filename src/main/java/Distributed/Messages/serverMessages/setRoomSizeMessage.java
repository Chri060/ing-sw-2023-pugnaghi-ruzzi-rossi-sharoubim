package Distributed.Messages.serverMessages;

import Distributed.Client;
import View.View;

public class setRoomSizeMessage extends ServerMessageAbs{
    @Override
    public void execute(Client client) {
    }

    @Override
    public void execute(View view) {
        view.setRoomSize();
    }
}