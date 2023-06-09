package Model.viewEntities.Events;

import View.View;

public class ModelViewUpdateEvent implements Event{
    @Override
    public void update(View view) {
        view.update();
    }
}
