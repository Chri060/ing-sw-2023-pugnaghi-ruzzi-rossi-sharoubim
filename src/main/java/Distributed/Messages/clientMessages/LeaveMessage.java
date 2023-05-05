package Distributed.Messages.clientMessages;

import Controller.Controller;

public class LeaveMessage extends ClientMessageAbs{
    String name;

    public LeaveMessage(String name) {
        super(name);
    }

    @Override
    public void execute(Controller controller) {
        controller.leave(name);
    }

}
