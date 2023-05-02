package Distributed.Messages.serverMessages;

import View.View;
import Distributed.Messages.NetworkMessage;


public interface ServerMessage extends NetworkMessage {
    void execute(View view);
}
