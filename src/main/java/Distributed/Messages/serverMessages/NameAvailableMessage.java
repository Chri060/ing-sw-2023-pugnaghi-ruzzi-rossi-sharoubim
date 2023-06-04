package Distributed.Messages.serverMessages;

import Distributed.Client;
import View.View;

public class NameAvailableMessage extends ServerMessageAbs {

    boolean nameAvailable;

    public NameAvailableMessage(boolean nameAvailable) {
        super();
        this.nameAvailable = nameAvailable;

    }
    @Override
    public void execute(Client client) {

    }

    @Override
    public void execute(View view) {
        view.setNameAvailable(nameAvailable);
    }
}
