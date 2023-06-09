package Distributed.Messages.serverMessages;

import Distributed.Client;
import Model.entities.Card;
import View.View;

public class ShelfUpdateMessage extends  ServerMessageAbs{

    String playerName;
    Card[][] shelf;

    public ShelfUpdateMessage(String playerName, Card[][] shelf) {
        super();
        this.shelf = shelf;
        this.playerName = playerName;
    }

    @Override
    public void execute(Client client) {

    }

    @Override
    public void execute(View view) {
        view.model.updatePlayerShelf(playerName, shelf);
    }
}
