package Distributed;

import Distributed.Messages.NetworkMessage;
import Distributed.Messages.serverMessages.ServerMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Client extends Remote {
    void update(ServerMessage message)  throws RemoteException;
}
