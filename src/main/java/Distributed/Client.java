package Distributed;

import Distributed.Messages.NetworkMessage;
import Distributed.Messages.clientMessages.ClientMessage;
import Distributed.Messages.serverMessages.ServerMessage;
import Distributed.Messages.serverMessages.ServerMessageAbs;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Client extends Remote {
    void update(ServerMessage message)  throws RemoteException;
 }
