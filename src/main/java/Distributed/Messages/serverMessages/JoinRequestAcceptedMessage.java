package Distributed.Messages.serverMessages;

import Distributed.Client;
import Model.ModelView;
import View.View;

import java.util.List;

public class JoinRequestAcceptedMessage extends ServerMessageAbs{
    private List<String> playerNames;
    private ModelView.State state;

    public JoinRequestAcceptedMessage(List<String> playerNames, ModelView.State state) {
        this.playerNames = playerNames;
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
     * It calls the methods used to let a new player join on the View
     *
     * @param view is the View used to execute the message
     */
    @Override
    public void execute(View view) {
        view.model.setPlayerNames(playerNames);
        view.model.setState(state);
        view.model.setReceived();
    }
}
