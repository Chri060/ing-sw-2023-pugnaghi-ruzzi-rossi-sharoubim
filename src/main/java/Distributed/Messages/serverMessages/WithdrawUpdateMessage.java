package Distributed.Messages.serverMessages;

import Distributed.Client;
import Model.entities.Card;
import View.View;

import java.util.List;

/**
 * Class that implements a WithdrawUpdateMessage
 */
public class WithdrawUpdateMessage extends ServerMessageAbs {

    private Card[][] dashBoard;
    private List<Card> withdrawnCards;

    /**
     * Construct a message for a withdrawal
     *
     * @param dashBoard is the new dashboard
     * @param withdrawnCards is the list of cards that had been withdrawn
     */
    public WithdrawUpdateMessage(Card[][] dashBoard, List<Card> withdrawnCards) {
        super();
        this.dashBoard = dashBoard;
        this.withdrawnCards = withdrawnCards;
    }

    /**
     * Executes the message on the client
     *
     * @param client is the Client used to execute the message
     */
    @Override
    public void execute(Client client) {

    }

    /**
     * Executes the message on the view.
     * It calls the methods used to withdraw on the View
     *
     * @param view is the View used to execute the message
     */
    @Override
    public void execute(View view) {
        view.model.setWithdrawnCards(withdrawnCards);
        view.model.setDashboard(dashBoard);
        view.model.setReceived();
    }
}