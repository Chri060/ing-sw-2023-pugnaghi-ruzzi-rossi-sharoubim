package Distributed.Messages.serverMessages;

import Model.ModelView;
import View.View;

import java.util.List;

public class ModelViewMessage extends ServerMessageAbs{

    ModelView modelView;

    public ModelViewMessage(ModelView modelView) {
        super();
        this.modelView = modelView;
    }

    public ModelViewMessage(boolean broadcast, String receiver) {
        super(broadcast, receiver);
    }

    public ModelViewMessage(boolean broadcast, List<String> receivers) {
        super(broadcast, receivers);
    }

    @Override
    public void execute(View view) {
        view.setModel(this.modelView);
        view.update();
    }
}
