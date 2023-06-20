package Distributed.Events;

import View.View;

/**
 * Class that implements the event of update of the playerView
 */
public class ModelViewUpdateEvent implements Event {

    /**
     * Update method override to change the View when the ModelView needs to be updated
     *
     * @param view is the View to update
     */
    @Override
    public void update(View view) {
        view.update();
    }
}