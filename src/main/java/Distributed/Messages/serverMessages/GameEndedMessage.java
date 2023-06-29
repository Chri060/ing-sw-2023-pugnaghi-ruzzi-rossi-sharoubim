package Distributed.Messages.serverMessages;

import Distributed.Client;
import Model.ModelView;
import View.View;

/**
 * Class that implements a GameEndedMessage
 */
public class GameEndedMessage extends ServerMessageAbs {

    boolean forfeit;

    /**
     * Construct a GameEndedMessage
     */
    public GameEndedMessage(boolean forfeit) {
        super();
        this.forfeit = forfeit;
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
     * It calls the method used set the state of ENDED on the View
     *
     * @param view is the View used to execute the message
     */
    @Override
    public void execute(View view) {
        view.model.setState(ModelView.State.ENDED);
        view.model.setWinByForfeit(forfeit);
        view.model.setReceived();
    }
}