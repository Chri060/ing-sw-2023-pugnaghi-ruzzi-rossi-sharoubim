package Distributed.Messages.serverMessages;

import View.View;
import Distributed.Messages.NetworkMessage;

import java.util.List;


public interface ServerMessage extends NetworkMessage {
    boolean isBroadcast();
    List<String> getReceiver();
    void execute(View view);
}
