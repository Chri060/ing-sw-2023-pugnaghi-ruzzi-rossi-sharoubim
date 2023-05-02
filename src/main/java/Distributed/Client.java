package Distributed;

import Distributed.Messages.NetworkMessage;
import Distributed.Messages.serverMessages.ServerMessage;

import java.rmi.Remote;

public interface Client extends Remote {

    void update(ServerMessage message);
}
