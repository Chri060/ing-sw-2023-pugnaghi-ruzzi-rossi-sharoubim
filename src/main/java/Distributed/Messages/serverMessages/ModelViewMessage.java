package Distributed.Messages.serverMessages;

import Distributed.Client;
import Model.ModelView;
import View.View;

import java.rmi.RemoteException;
import java.util.List;

public class ModelViewMessage extends ServerMessageAbs{

    ModelView modelView;

    public ModelViewMessage(ModelView modelView) {
        super();
        this.modelView = modelView;
    }


    @Override
    public void execute(Client client) {
    }

    @Override
    public void execute(View view) {
        view.setModel(this.modelView);
        view.update();
    }
}
