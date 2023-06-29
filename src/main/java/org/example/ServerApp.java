package org.example;

import Distributed.*;
import Distributed.Socket.ClientStub;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Class that starts a server application, where the user can select between CLI or GUI and Socket or RMI
 */
public class ServerApp {
    /**
     * Starts the server with both RMI and socket connection
     *
     * @throws RemoteException on connection problems
     */
    public static void main(String[] args) throws RemoteException {
        Server server = new ServerImpl();
        Registry registry;
        try {
            try {
                registry = LocateRegistry.createRegistry(44444);
            } catch (RemoteException e) {
                registry = LocateRegistry.getRegistry(44444);
            }
            registry.rebind("server", server);
        } catch (RemoteException e) {
            System.err.println("RMI KO");
        }
        new Thread(() -> runSocket(server)).start();
   }

    /**
     * Starts the socket connections on the client
     *
     * @param server is the server selected
     */
   public static void runSocket(Server server) {
       try {
           ServerSocket serverSocket = new ServerSocket(55555);
           while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    ClientStub clientStub = new ClientStub(socket);
                    new Thread(() -> clientStub.receive(server)).start();
                } catch (IOException e) {
                    System.err.println("Socket error, connection discarded");
                }
           }
       } catch (IOException e) {
           System.out.println("Socket KO");
       }
   }
}