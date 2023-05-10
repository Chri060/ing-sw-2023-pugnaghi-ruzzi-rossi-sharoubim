package Distributed.Messages.clientMessages;

import Controller.Controller;
import Distributed.Client;
import Distributed.Messages.NetworkMessage;
import Distributed.Messages.serverMessages.ServerMessage;
import Distributed.Server;

import java.io.Serializable;

public interface ClientMessage extends NetworkMessage{

    void execute(Server server, Client client);
    void execute(Controller controller);
    String getAuth();
}
