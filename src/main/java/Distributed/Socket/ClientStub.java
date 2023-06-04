package Distributed.Socket;

import Distributed.Client;
import Distributed.Messages.clientMessages.ClientMessage;
import Distributed.Messages.clientMessages.LeaveMessage;
import Distributed.Messages.serverMessages.ServerMessage;
import Distributed.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;

public class ClientStub implements Client {

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public ClientStub(Socket socket) throws RemoteException{
        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            throw new RemoteException("Failed to open streams");
        }
    }

    @Override
    public void update(ServerMessage message) throws RemoteException {
        try {
            outputStream.writeObject(message);
        } catch (IOException e) {
            //System.out.println("Unable to update a client");
            throw new RemoteException("Unable to update a client");
        }
    }

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
