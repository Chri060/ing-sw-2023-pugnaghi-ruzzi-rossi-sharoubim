package Distributed.Messages.serverMessages;

import Distributed.Client;
import View.View;

public class LeaveAck extends ServerMessageAbs{

    String leaver;
    public LeaveAck(String leaver) {
        this.leaver = leaver;
    }

    @Override
    public void execute(Client client) {

    }

    @Override
    public void execute(View view) {
        view.model.removePlayerName(leaver);
    }
}
