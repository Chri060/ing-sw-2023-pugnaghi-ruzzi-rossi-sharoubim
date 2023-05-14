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

public class ServerStub implements Server {

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

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

    @Override
    public void register(Client client, String name) throws RemoteException {
        try {
            outputStream.writeObject(new JoinMessage(name));
        } catch (IOException e) {
            throw new RemoteException("Unable to update the server (registration)");
        }
    }

    @Override
    public void leave(Client client, String name) throws RemoteException {
        try {
            outputStream.writeObject(new LeaveMessage(name));
        } catch (IOException e) {
            throw new RemoteException("Unable to update the server (leave)");
        }
    }

    @Override
    public void update(Client client, ClientMessage message) throws RemoteException {
        try {
            outputStream.writeObject(message);
        } catch (IOException e) {
            throw new RemoteException("Unable to update the server");
        }
    }

    public void receive(Client client) {
        new Thread(() -> {
            while (true) {
                try {
                    ServerMessage serverMessage = (ServerMessage) inputStream.readObject();
                    client.update(serverMessage);
                } catch (IOException e) {
                    System.err.println("Error while receiving");
                    return;
                } catch (ClassNotFoundException e) {
                    System.err.println("Error while receiving: not a message");
                }
            }
        }).start();
    }
}

