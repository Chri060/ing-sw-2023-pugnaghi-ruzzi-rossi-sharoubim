package Model.viewEntities.Events;

import Model.ModelView;
import View.View;

public class GameStartedEvent implements Event{
    @Override
    public void update(View view) {
        view.update();
        view.model.setReceived();
    }
}
