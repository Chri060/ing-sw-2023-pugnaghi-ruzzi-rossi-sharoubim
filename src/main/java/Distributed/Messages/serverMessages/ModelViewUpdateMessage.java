package Distributed.Messages.serverMessages;

import Distributed.Client;
import Model.ModelViewDataUpdate;
import View.View;

public class ModelViewUpdateMessage extends ServerMessageAbs{

    ModelViewDataUpdate modelViewDataUpdate;


    public ModelViewUpdateMessage(ModelViewDataUpdate modelViewDataUpdate) {
        super();
        this.modelViewDataUpdate = modelViewDataUpdate;
    }

    @Override
    public void execute(Client client) {

    }

    @Override
    public void execute(View view) {
        view.model.update(modelViewDataUpdate);
    }
}
