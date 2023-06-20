package Distributed.Messages.serverMessages;

import Distributed.Client;
import View.View;

import java.util.List;

public class LeaveLobbyMessage extends ServerMessageAbs{

    List<String> playerNames;
    public LeaveLobbyMessage(List<String> playerNames) {
        this.playerNames = playerNames;
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
     * It calls the method used to set a name of a player the View
     *
     * @param view is the View used to execute the message
     */
    @Override
    public void execute(View view) {
        view.model.setPlayerNames(playerNames);
    }
}
