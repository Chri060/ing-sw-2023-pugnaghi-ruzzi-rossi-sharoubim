package Distributed.Messages.serverMessages;

import Distributed.Client;
import Model.Model;
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

    @Override
    public void execute(Client client) {
    }

    @Override
    public void execute(View view) {
        view.model.setPlayerNames(playerNames);
        view.model.setState(state);
        view.model.setReceived();
    }
}
