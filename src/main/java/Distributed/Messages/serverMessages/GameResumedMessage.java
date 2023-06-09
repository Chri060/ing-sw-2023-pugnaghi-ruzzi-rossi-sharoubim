package Distributed.Messages.serverMessages;

import Distributed.Client;
import Model.ModelView;
import View.View;

public class GameResumedMessage extends ServerMessageAbs{

    public GameResumedMessage() {
        super();
    }
    @Override
    public void execute(Client client) {

    }

    @Override
    public void execute(View view) {
        view.model.setState(ModelView.State.RUNNING);
    }
}
