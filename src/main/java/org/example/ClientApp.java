package org.example;

import Distributed.*;
import Distributed.Socket.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import static View.TextualUI.*;
import static util.AnsiColor.*;

public class ClientApp {
    public static void main(String[] args) {

        System.out.println(                                                                "\n" +
                "███╗   ███╗██╗   ██╗    ███████╗██╗  ██╗███████╗██╗     ███████╗██╗███████╗\n" +
                "████╗ ████║╚██╗ ██╔╝    ██╔════╝██║  ██║██╔════╝██║     ██╔════╝██║██╔════╝\n" +
                "██╔████╔██║ ╚████╔╝     ███████╗███████║█████╗  ██║     █████╗  ██║█████╗  \n" +
                "██║╚██╔╝██║  ╚██╔╝      ╚════██║██╔══██║██╔══╝  ██║     ██╔══╝  ██║██╔══╝  \n" +
                "██║ ╚═╝ ██║   ██║       ███████║██║  ██║███████╗███████╗██║     ██║███████╗\n" +
                "╚═╝     ╚═╝   ╚═╝       ╚══════╝╚═╝  ╚═╝╚══════╝╚══════╝╚═╝     ╚═╝╚══════╝\n" );


        System.out.println("Choose the protocol you want to use whit command " + ANSI_GREEN + "RMI" + ANSI_RESET +
                " or " + ANSI_GREEN + "socket" + ANSI_RESET + ". If you want to exit use "+ ANSI_GREEN + "exit" + ANSI_RESET + ".");
        while (true) {
            String choice = ((new Scanner(System.in)).nextLine()).toLowerCase();
            switch (choice) {
                case "rmi" -> {
                    try {
                        runRMI(args[0]);
                    } catch (RemoteException e) {
                        System.out.println("RMI might not be available, you can still retry with " + ANSI_GREEN + "RMI" + ANSI_RESET +
                                           " or change to " + ANSI_GREEN + "socket" + ANSI_RESET + ".");
                    }
                }
                case "socket" ->{
                    try {
                        runSocket(args[0]);
                    }
                    catch (RemoteException e) {
                        System.out.println("Socket might not be available, you can still retry with " + ANSI_GREEN + "socket" + ANSI_RESET +
                                " or change to " + ANSI_GREEN + "RMI" + ANSI_RESET + ".");
                    }
                }
                case "exit" -> {
                    return;
                }
            }
        }
    }

    public static void runSocket(String ip) throws RemoteException {

        ClientImpl client = new ClientImpl();
        try {
            ServerStub serverStub = new ServerStub(ip, 55555);
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
    public static void runRMI(String ip) throws RemoteException {

        ClientImpl client = new ClientImpl();

        try {
            Registry registry = LocateRegistry.getRegistry(ip, 44444);
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
