package Distributed.Events;

import View.View;

/**
 * Interface for the Event classes
 */
public interface Event {

    /**
     * Method used to update the View
     *
     * @param view is the View to update
     */
    void update(View view);
}