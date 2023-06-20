package Distributed.Events;

import View.View;

/**
 * Class that implements the event of waiting for the response
 */
public class WaitResponseEvent implements Event {

    /**
     * Update method override to change the View when the client is needed to wait for a response
     *
     * @param view is the View to update
     */
    @Override
    public void update(View view) {
        view.waitResponse();
    }
}