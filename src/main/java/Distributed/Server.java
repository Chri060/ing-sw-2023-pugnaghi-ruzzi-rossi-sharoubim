package Distributed;

import Distributed.Messages.NetworkMessage;
import Distributed.Messages.clientMessages.ClientMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Server extends Remote {

    void register(Client client, String name) throws RemoteException;
    void update(Client client, ClientMessage message)  throws RemoteException;

}
