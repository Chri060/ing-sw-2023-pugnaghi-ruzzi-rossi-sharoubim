package Distributed.Messages.serverMessages;

import Distributed.Client;
import View.View;

import java.util.List;

public class LeaveLobbyMessage extends ServerMessageAbs{

    List<String> playerNames;
    public LeaveLobbyMessage(List<String> playerNames) {
        this.playerNames = playerNames;
    }

    @Override
    public void execute(Client client) {

    }

    @Override
    public void execute(View view) {
        view.model.setPlayerNames(playerNames);
    }
}
