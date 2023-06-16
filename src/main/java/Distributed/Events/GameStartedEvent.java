package Distributed.Events;

import View.View;

public class GameStartedEvent implements Event{
    @Override
    public void update(View view) {
        view.update();
        view.model.setReceived();
    }
}
