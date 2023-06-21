package Distributed.Messages.serverMessages;

import Distributed.Client;
import Model.viewEntities.PlayerView;
import View.View;

import java.util.List;

/**
 * Class that implements a RankingMessage
 */
public class RankingMessage extends ServerMessageAbs {

    List<PlayerView> playerViewList;

    /**
     * Construct a message used to send the final rankings
     *
     * @param playerViewList is the list of PlayerView
     */
    public RankingMessage(List<PlayerView> playerViewList) {
        super();
        this.playerViewList = playerViewList;
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
     * It calls the method used to show the final rankings on the View
     *
     * @param view is the View used to execute the message
     */
    @Override
    public void execute(View view) {
        //TODO
    }
}