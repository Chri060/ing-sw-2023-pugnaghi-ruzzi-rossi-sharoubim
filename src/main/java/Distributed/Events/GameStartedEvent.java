package Distributed.Events;

import View.View;

/**
 * Class that implements the event of game started
 */
public class GameStartedEvent implements Event {

    /**
     * Update method override to change the View when the game starts
     *
     * @param view is the View to update
     */
    @Override
    public void update(View view) {
        view.update();
        view.model.setReceived();
    }
}