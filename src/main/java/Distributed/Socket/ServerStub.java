package Distributed.Socket;

import Distributed.Client;
import Distributed.Messages.clientMessages.ClientMessage;
import Distributed.Messages.clientMessages.JoinMessage;
import Distributed.Messages.clientMessages.LeaveMessage;
import Distributed.Messages.serverMessages.ServerMessage;
import Distributed.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;

/**
 * Class used to implement the server stub
 */
public class ServerStub implements Server {

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    /**
     * Construct a server stub
     *
     * @param ip is the IP of the server
     * @param port is the port used by the server
     *
     * @throws RemoteException on connection problems
     */
    public ServerStub(String ip, int port) throws RemoteException{
        try {
            Socket socket = new Socket(ip, port);
            try {
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                inputStream = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                System.err.println("Failed to open socket streams");
            }
        } catch (IOException e) {
            throw new RemoteException("Failed to open streams");
        }
    }

    /**
     * Register a Client to the Server
     *
     * @param client is the Client to register
     * @param name is the name of the player
     *
     * @throws RemoteException on connection problems
     */
    @Override
    public void register(Client client, String name) throws RemoteException {
        try {
            outputStream.writeObject(new JoinMessage(name));
        } catch (IOException e) {
            throw new RemoteException("Unable to update the server (registration)");
        }
    }

    /**
     * Removes a Client from the server
     *
     * @param client is the Client to remove
     * @param name is the name of the player to remove
     *
     * @throws RemoteException on connection problems
     */
    @Override
    public void leave(Client client, String name) throws RemoteException {
        try {
            outputStream.writeObject(new LeaveMessage(name));
        } catch (IOException e) {
            throw new RemoteException("Unable to update the server (leave)");
        }
    }

    /**
     * Updates a client with a message
     *
     * @param client is the Client supposed to receive the update
     * @param message is the message to send
     *
     * @throws RemoteException on connection problems
     */
    @Override
    public void update(Client client, ClientMessage message) throws RemoteException {
        try {
            outputStream.writeObject(message);
        } catch (IOException e) {
            throw new RemoteException("Unable to update the server");
        }
    }

    /**
     * Receives a message from the server
     *
     * @param client is the Client that needs to receive the message
     */
    public void receive(Client client) {
        new Thread(() -> {
            while (true) {
                try {
                    ServerMessage serverMessage = (ServerMessage) inputStream.readObject();
                    client.update(serverMessage);
                } catch (IOException e) {
                    System.err.println("Error while receiving");
                    System.out.println(e.getMessage());
                    return;
                } catch (ClassNotFoundException e) {
                    System.err.println("Error while receiving: not a message");
                }
            }
        }).start();
    }
}