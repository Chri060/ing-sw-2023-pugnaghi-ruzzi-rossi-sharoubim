package Distributed.Messages.clientMessages;

import Controller.Controller;
import Distributed.Messages.NetworkMessage;

import java.io.Serializable;

public interface ClientMessage extends NetworkMessage{

    void execute(Controller controller);
    String getAuth();
}
