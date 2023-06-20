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

    /**
     * Executes the message on the client
     *
     * @param client is the Client used to execute the message
     */
    @Override
    public void execute(Client client) {

    }

    /**
     * Executes the message on the view.
     * It calls the method used to show the shelf update on the View
     *
     * @param view is the View used to execute the message
     */
    @Override
    public void execute(View view) {
        view.model.updatePlayerShelf(playerName, shelf);
    }
}