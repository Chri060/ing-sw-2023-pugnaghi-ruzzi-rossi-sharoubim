package Distributed.Messages.serverMessages;

import Distributed.Client;
import Model.ModelView;
import View.View;

public class JoinRequestRefusedMessage extends ServerMessageAbs{

    ModelView.State state;

    public JoinRequestRefusedMessage(ModelView.State state) {
        this.state = state;
    }

    @Override
    public void execute(Client client) {
    }

    @Override
    public void execute(View view) {
        view.model.setState(state);
        view.model.setReceived();
    }
}
