package Controller;

import Exceptions.PlayerAlreadyInLobby;
import Model.PreMatch;

public class LobbyController {

    private PreMatch preMatch = new PreMatch();

    public void joinUpdate(String name/*, Client ID*/) {
        try {
            preMatch.addPlayer(name);
        }
        catch (PlayerAlreadyInLobby e) {
            //notify client
        }
    }

    public void leaveUpdate(String name/*, Client ID*/) {
        /*if (Client ID coincide con name) {
            preMatch.removePlayer(name);
        }*/
    }

    public void toggleUpdate(String name/*, Client ID*/) {
        /*if (Client ID coincide con name) {
            preMatch.togglePlayer(name);
        }*/
    }

    public boolean allReady() {
        return preMatch.allReady();
    }


}
