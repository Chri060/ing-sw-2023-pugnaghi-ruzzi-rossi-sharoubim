package Distributed.Events;

import View.View;

/**
 * Class that implements the event of joining lobby
 */
public class JoinLeaveLobbyEvent implements Event {

    /**
     * Update method override to change the View when someone joins the lobby
     *
     * @param view is the View to update
     */
    @Override
    public void update(View view) {
        view.showLobby();
    }
}