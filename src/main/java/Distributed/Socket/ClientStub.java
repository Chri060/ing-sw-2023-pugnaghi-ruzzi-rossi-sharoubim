package Distributed.Socket;

import Distributed.Client;
import Distributed.Messages.clientMessages.ClientMessage;
import Distributed.Messages.serverMessages.ServerMessage;
import Distributed.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;

/**
 * Class used to implement the client stub
 */
public class ClientStub implements Client {

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    /**
     * Construct a new client stub
     *
     * @param socket is the Socket selected
     *
     * @throws RemoteException on connection problems
     */
    public ClientStub(Socket socket) throws RemoteException{
        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            throw new RemoteException("Failed to open streams");
        }
    }

    /**
     * Sends a server message
     *
     * @param message is the ServerMessage to send
     *
     * @throws RemoteException on connection problems
     */
    @Override
    public void update(ServerMessage message) throws RemoteException {
        try {
            outputStream.writeObject(message);
        } catch (IOException e) {
            //System.out.println("Unable to update a client");
            throw new RemoteException("Unable to update a client");
        }
    }

    /**
     * Let the server receive a ClientMessage
     *
     * @param server is the Server to send the message to
     */
    public void receive(Server server) {
        new Thread(() -> {
            while (true) {
                try {
                    ClientMessage clientMessage = (ClientMessage) inputStream.readObject();
                    server.update(this, clientMessage);
                }
                catch (IOException e) {
                    //System.err.println("Client disconnected");
                    return;
                }
                catch (ClassNotFoundException e) {
                    System.err.println("Received an unknown message");
                }
            }
        }).start();
    }
}