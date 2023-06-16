package Distributed.Events;

import View.View;

public class JoinLeaveLobbyEvent implements Event{
    @Override
    public void update(View view) {
        view.showLobby();
    }
}
