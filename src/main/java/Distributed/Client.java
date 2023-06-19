package Distributed;

import Distributed.Messages.serverMessages.ServerMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface for the Client
 */
public interface Client extends Remote {

    /**
     * Sends a message to the Server
     *
     * @param message is the ServerMessage to send
     *
     * @throws RemoteException on connection problems
     */
    void update(ServerMessage message)  throws RemoteException;
 }