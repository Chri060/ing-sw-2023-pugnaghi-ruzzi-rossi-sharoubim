package Distributed.Events;

import View.View;

/**
 * Class that implements the event for clients that needs to wait for the RoomLeader
 */
public class WaitEventEvent implements Event {

    /**
     * Update method override to change the View when the client is needed to wait for an event
     *
     * @param view is the View to update
     */
    @Override
    public void update(View view) {
        view.waitEvent();
    }
}