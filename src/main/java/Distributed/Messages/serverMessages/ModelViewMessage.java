package Distributed.Messages.serverMessages;

import Distributed.Client;
import Model.ModelView;
import Model.ModelViewData;
import View.View;

import java.rmi.RemoteException;
import java.util.List;

public class ModelViewMessage extends ServerMessageAbs{

    ModelViewData modelView;

    public ModelViewMessage(ModelViewData modelView, String player) {
        super(player);
        this.modelView = modelView;
    }


    @Override
    public void execute(Client client) {
    }

    @Override
    public void execute(View view) {
        view.initialiseModelView(modelView);
        view.update();
    }
}
