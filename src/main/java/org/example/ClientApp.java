package org.example;

import Distributed.*;
import Distributed.Socket.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClientApp {
    public static void main(String[] args) {

        System.out.println("\n" +
                "\033[0;36m" +
                "=====================================================================\n" +
                "=  =====  ==  ====  ===      ===  ============  ====    =============\n" +
                "=   ===   ==   ==   ==  ====  ==  ============  ===  ==  ============\n" +
                "=  =   =  ===  ==  ===  ====  ==  ============  ===  ================\n" +
                "=  == ==  ===  ==  ====  =======  ======   ===  ==    =====  ===   ==\n" +
                "=  =====  ====    =======  =====    ===  =  ==  ===  ==========  =  =\n" +
                "=  =====  =====  ==========  ===  =  ==     ==  ===  ======  ==     =\n" +
                "=  =====  =====  =====  ====  ==  =  ==  =====  ===  ======  ==  ====\n" +
                "=  =====  =====  =====  ====  ==  =  ==  =  ==  ===  ======  ==  =  =\n" +
                "=  =====  =====  ======      ===  =  ===   ===  ===  ======  ===   ==\n" +
                "=====================================================================\n" +
                "\033[0m" +
                "\n");


        while (true) {
            System.out.println("Chose a protocol RMI or Socket. Use x to exit");
            String choice = (new Scanner(System.in)).nextLine();
            switch (choice) {
                case "RMI" -> {
                    try {
                        runRMI();
                    } catch (RemoteException e) {
                        System.out.println("RMI might not be available, can still retry");
                    }
                }
                case "Socket" ->{
                    try {
                        runSocket();
                    }
                    catch (RemoteException e) {
                        System.out.println("Socket might not be available, can still retry");
                    }
                }
                case "x" -> {
                    return;
                }
            }
        }
    }

    public static void runSocket() throws RemoteException {

        ClientImpl client = new ClientImpl();
        try {
            ServerStub serverStub = new ServerStub("carlosharu.ddns.net", 55555);
            Thread receiver = new Thread(() -> serverStub.receive(client));


            client.init(serverStub);

            Thread view = new Thread(() -> client.run());

            try {
                receiver.start();
                view.start();
                receiver.join();
                view.join();
            } catch (InterruptedException e) {
            }
        } catch (RemoteException e) {
            throw new RemoteException("Server Socket KO");
        }

    }
    public static void runRMI() throws RemoteException {

        ClientImpl client = new ClientImpl();

        try {
            Registry registry = LocateRegistry.getRegistry();
            Server server = (Server) registry.lookup("server");

            client.init(server);

            Thread viewThread = new Thread(() -> client.run());
            viewThread.start();
            try {
                viewThread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } catch (RemoteException | NotBoundException e) {
            throw new RemoteException("Server RMI KO");
        }
    }

}
