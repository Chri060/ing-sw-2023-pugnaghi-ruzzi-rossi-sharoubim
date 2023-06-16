package Distributed.Events;

import View.View;

public class RoomLeaderEvent implements Event{
    @Override
    public void update(View view) {
        view.roomLeaderEvent();
    }
}
