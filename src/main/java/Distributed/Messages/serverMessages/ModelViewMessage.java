package Distributed.Messages.serverMessages;

import Distributed.Client;

import Model.ModelViewData;
import View.View;

public class ModelViewMessage extends ServerMessageAbs{

    ModelViewData modelView;

    public ModelViewMessage(ModelViewData modelView, String player) {
        super(player);
        this.modelView = modelView;
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
     * It calls the method used to initialize a modelView on the View
     *
     * @param view is the View used to execute the message
     */
    @Override
    public void execute(View view) {
        view.initialiseModelView(modelView);
    }
}
