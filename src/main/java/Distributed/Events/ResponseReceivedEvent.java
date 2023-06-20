package Distributed.Events;

import View.View;

/**
 * Class that implements the event of response received
 */
public class ResponseReceivedEvent implements Event {

    /**
     * Update method override to change the View when the client receive the response
     *
     * @param view is the View to update
     */
    @Override
    public void update(View view) {
        view.giveResponse();
    }
}