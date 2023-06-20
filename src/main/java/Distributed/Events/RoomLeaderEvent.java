package Distributed.Events;

import View.View;

/**
 * Class that implements the event or the roomLeader
 */
public class RoomLeaderEvent implements Event {

    /**
     * Update method override to change the View when the first player joins and is set to be the room leader
     *
     * @param view is the View to update
     */
    @Override
    public void update(View view) {
        view.roomLeaderEvent();
    }
}