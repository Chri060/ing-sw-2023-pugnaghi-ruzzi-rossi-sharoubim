package org.example;

import Distributed.Server;
import Distributed.ServerImpl;
import Distributed.Socket.ClientStub;
import Distributed.WelcomeServerImpl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WelcomeServerApp {

    static private WelcomeServerImpl server;

    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) {

        try {
            server = new WelcomeServerImpl();
        } catch (RemoteException e) {
            System.err.println("Fatal Server Error");
            System.exit(-1);
        }

        Thread RMIStarter = new Thread(() ->
        { try {
            runRMI();
        } catch (RemoteException e) {
            System.err.println("Error while starting RMI: " + e.getMessage());
            System.err.println("RMI connections won't be available");
        }});

        Thread SocketStarter = new Thread(() -> runSocket());

        RMIStarter.start();
        SocketStarter.start();

        System.out.println("Server ready");

        try {
            RMIStarter.join();
            SocketStarter.join();
        } catch (InterruptedException e) {
            System.err.println("Everything Broke :(");
        }
    }

    public static void runRMI() throws RemoteException {
        Registry registry = LocateRegistry.getRegistry();
        registry.rebind("welcomeServer", server);
    }

    public static void runSocket() {
        try {
            ServerSocket serverSocket = new ServerSocket(5555);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                socketClientDispatcher(clientSocket);
                executorService.submit(()  -> socketClientDispatcher(clientSocket));
            }
        } catch (IOException e) {
            System.err.println("Error while starting Socket : " + e.getMessage());
            System.out.println("Socket connections won't be available");
        }
    }

    public static void socketClientDispatcher(Socket clientSocket) {
        try {
            ClientStub clientStub = new ClientStub(clientSocket);
            String roomName = clientStub.getString();
            if (roomName == null) {
                try {
                    System.err.println("Couldn't get a valid room name from the client closing connection");
                    clientSocket.close();
                } catch (IOException e) {
                    System.err.println("Error while closing client socket");
                    return;
                }
            }
            Server serverRoom = server.getRoom(roomName);
            String clientName = clientStub.getString();
            if (clientName == null) {
                try {
                    System.err.println("Couldn't get a valid name from the client closing connection");
                    clientSocket.close();
                } catch (IOException e) {
                    System.err.println("Error while closing client socket");
                }
                return;
            }
            serverRoom.register(clientStub, clientName);
            Thread receiver = new Thread(() -> {
                while (true) {
                    clientStub.startReceiving(serverRoom);
                }});
            receiver.start();
            receiver.join();
        } catch (IOException e) {
            System.err.println("Error while getting clientSocket: connection refused");
        } catch (InterruptedException e) {
            //TODO
        }
    }
}
