package Distributed.Events;

import View.View;

public class ResponseReceivedEvent implements Event{
    @Override
    public void update(View view) {
        view.giveResponse();
    }
}
