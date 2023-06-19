package Distributed;

import Distributed.Messages.clientMessages.ClientMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface for the server
 */
public interface Server extends Remote {

    /**
     * Register a name-client on the server
     *
     * @param client is the Client to register
     * @param name is the name of the player
     *
     * @throws RemoteException on connection problems
     */
    void register(Client client, String name) throws RemoteException;

    /**
     * Removes a player from the server
     *
     * @param client is the Client to remove
     * @param name is the name of the player to remove
     *
     * @throws RemoteException on connection problems
     */
    void leave(Client client, String name) throws RemoteException;

    /**
     * Sends a message to the client
     *
     * @param client is the Client supposed to receive the update
     * @param message is the message to send
     *
     * @throws RemoteException on connection problems
     */
    void update(Client client, ClientMessage message)  throws RemoteException;
}