package Distributed.Messages.serverMessages;

import Distributed.Client;
import Model.entities.Card;
import View.View;



public class DashboardUpdateMessage extends ServerMessageAbs{

    private Card[][] dashBoard;

    public DashboardUpdateMessage(Card[][] dashBoard) {
        super();
        this.dashBoard = dashBoard;
    }


    @Override
    public void execute(Client client) {
    }

    @Override
    public void execute(View view) {
        view.updateDashboard(dashBoard);
    }
}
