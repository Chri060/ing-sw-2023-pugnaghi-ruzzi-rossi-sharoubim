package Distributed.Events;

import View.View;

public class WaitResponseEvent implements Event{
    @Override
    public void update(View view) {
        view.waitResponse();
    }
}
