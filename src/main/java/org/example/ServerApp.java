package org.example;

import Distributed.*;
import Distributed.Socket.ClientStub;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerApp {
    public static void main(String[] args) throws RemoteException {

        Server server = new ServerImpl();

        try {
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("server", server);
        } catch (RemoteException e) {
            System.err.println("RMI KO");
        }
        new Thread(() -> runSocket(server)).start();


   }

   public static void runSocket(Server server) {
       try {
           ServerSocket serverSocket = new ServerSocket(55555);
           while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    ClientStub clientStub = new ClientStub(socket);
                    new Thread(() -> clientStub.receive(server)).start();
                } catch (IOException e) {
                    System.err.println("Socket client disconnected");
                    return;
                }
           }
       } catch (IOException e) {
           System.out.println("Socket KO");
       }
   }
}
