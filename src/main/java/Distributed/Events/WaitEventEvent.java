package Distributed.Events;

import View.View;

public class WaitEventEvent implements Event{
    @Override
    public void update(View view) {
        view.waitEvent();
    }
}
