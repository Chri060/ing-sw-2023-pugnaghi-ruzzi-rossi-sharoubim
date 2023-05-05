package Distributed.Messages.clientMessages;

import Controller.Controller;

public class SetRoomSizeMessage extends ClientMessageAbs{

    private int roomSize;
    public SetRoomSizeMessage(String name, int roomSize) {
        super(name);
        this.roomSize = roomSize;
    }

    @Override
    public void execute(Controller controller) {
        controller.setRoomSize(roomSize, name);
    }
}
