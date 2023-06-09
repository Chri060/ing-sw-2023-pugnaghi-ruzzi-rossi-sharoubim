package Distributed.Messages.serverMessages;

import Distributed.Client;
import Model.Model;
import Model.ModelView;
import View.View;

public class TurnUpdateMessage extends ServerMessageAbs{

    private String playerName;
    private Model.TurnStatus state;

    public TurnUpdateMessage(String playerName, Model.TurnStatus state) {
        this.playerName = new String(playerName);
        this.state = state;
    }

    @Override
    public void execute(Client client) {
    }
    @Override
    public void execute(View view) {
        view.model.setCurrentPlayer(playerName);
        view.model.setTurnState(state);
        view.model.setReceived();
    }
}
