package Distributed.Messages.serverMessages;

import Distributed.Client;
import Model.ModelView;
import View.View;

public class GamePausedMessage extends ServerMessageAbs{

    public GamePausedMessage() {
        super();
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
     * It calls the method used set the state of PAUSED on the View
     *
     * @param view is the View used to execute the message
     */
    @Override
    public void execute(View view) {
        view.model.setState(ModelView.State.PAUSED);
    }
}