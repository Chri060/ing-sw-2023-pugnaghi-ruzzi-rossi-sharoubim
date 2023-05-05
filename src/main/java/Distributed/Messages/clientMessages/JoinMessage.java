package Distributed.Messages.clientMessages;

import Controller.Controller;

public class JoinMessage extends ClientMessageAbs{

    public JoinMessage(String name) {
        super(name);
    }

    @Override
    public void execute(Controller controller) {
        controller.join(getAuth());
    }

}
