package Distributed.Messages.serverMessages;

import Distributed.Client;
import Model.entities.Card;
import View.View;

import java.util.List;

public class WithdrawUpdateMessage extends ServerMessageAbs{

    private Card[][] dashBoard;
    private List<Card> withdrawnCards;

    public WithdrawUpdateMessage(Card[][] dashBoard, List<Card> withdrawnCards) {
        super();
        this.dashBoard = dashBoard;
        this.withdrawnCards = withdrawnCards;
    }
    @Override
    public void execute(Client client) {

    }

    @Override
    public void execute(View view) {
        view.model.setWithdrawnCards(withdrawnCards);
        view.model.setDashboard(dashBoard);
        view.update();
        view.model.setReceived();
    }
}
