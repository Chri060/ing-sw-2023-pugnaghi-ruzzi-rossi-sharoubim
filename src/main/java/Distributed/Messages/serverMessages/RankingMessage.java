package Distributed.Messages.serverMessages;

import Distributed.Client;
import Model.viewEntities.PlayerView;
import View.View;

import java.util.List;

public class RankingMessage extends ServerMessageAbs{

    List<PlayerView> playerViewList;

    public RankingMessage(List<PlayerView> playerViewList) {
        super();
        this.playerViewList = playerViewList;
    }

    @Override
    public void execute(Client client) {

    }

    @Override
    public void execute(View view) {
        //TODO
    }
}
