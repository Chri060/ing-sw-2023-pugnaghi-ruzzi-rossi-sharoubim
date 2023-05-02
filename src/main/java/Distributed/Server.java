package Distributed;

import Distributed.Messages.NetworkMessage;
import Distributed.Messages.clientMessages.ClientMessage;

import java.rmi.Remote;

public interface Server extends Remote {

    void register(Client client, String name);

    void update(Client client, ClientMessage message);

}
