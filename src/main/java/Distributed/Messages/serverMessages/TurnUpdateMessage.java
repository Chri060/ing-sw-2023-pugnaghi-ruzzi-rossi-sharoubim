package Distributed.Messages.serverMessages;

import Distributed.Client;
import Model.Model;
import View.View;

/**
 * Class that implements a TurnUpdateMessage
 */
public class TurnUpdateMessage extends ServerMessageAbs {

    private String playerName;
    private Model.TurnStatus state;

    /**
     * Construct a message that updates the status of the turn
     *
     * @param playerName is the name of the player's client to update
     * @param state is the new state to set
     */
    public TurnUpdateMessage(String playerName, Model.TurnStatus state) {
        this.playerName = new String(playerName);
        this.state = state;
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
     * It calls the methods used to update the state of the turn on the View
     *
     * @param view is the View used to execute the message
     */
    @Override
    public void execute(View view) {
        view.model.setCurrentPlayer(playerName);
        view.model.setTurnState(state);
        view.model.setReceived();
    }
}