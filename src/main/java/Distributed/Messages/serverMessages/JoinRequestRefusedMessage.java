package Distributed.Messages.serverMessages;

import Distributed.Client;
import Model.ModelView;
import View.View;

public class JoinRequestRefusedMessage extends ServerMessageAbs{

    ModelView.State state;

    public JoinRequestRefusedMessage(ModelView.State state) {
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
     * It calls the methods used to show the join request on the View
     *
     * @param view is the View used to execute the message
     */
    @Override
    public void execute(View view) {
        view.model.setState(state);
        view.model.setReceived();
    }
}
