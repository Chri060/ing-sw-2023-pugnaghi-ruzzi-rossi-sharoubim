package org.example;

import Distributed.ClientImpl;
import Distributed.Server;
import Distributed.Socket.ServerStub;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import static util.AnsiColor.*;

/**
 * Class that starts a client application, where the user can select between CLI or GUI and Socket or RMI
 */
public class ClientApp {

    /**
     * Is the main method that is used to select the connection protocol
     */
    public static void main(String[] args) {
        System.out.println();
        System.out.println("███╗   ███╗██╗   ██╗    ███████╗██╗  ██╗███████╗██╗     ███████╗██╗███████╗");
        System.out.println("████╗ ████║╚██╗ ██╔╝    ██╔════╝██║  ██║██╔════╝██║     ██╔════╝██║██╔════╝");
        System.out.println("██╔████╔██║ ╚████╔╝     ███████╗███████║█████╗  ██║     █████╗  ██║█████╗  ");
        System.out.println("██║╚██╔╝██║  ╚██╔╝      ╚════██║██╔══██║██╔══╝  ██║     ██╔══╝  ██║██╔══╝  ");
        System.out.println("██║ ╚═╝ ██║   ██║       ███████║██║  ██║███████╗███████╗██║     ██║███████╗");
        System.out.println("╚═╝     ╚═╝   ╚═╝       ╚══════╝╚═╝  ╚═╝╚══════╝╚══════╝╚═╝     ╚═╝╚══════╝");
        System.out.println();
        System.out.println("Choose the protocol you want to use whit command "
                            + ANSI_GREEN + "RMI" + ANSI_RESET +
                            " or "
                            + ANSI_GREEN + "socket" + ANSI_RESET +
                            ". If you want to exit use "
                            + ANSI_GREEN + "exit" + ANSI_RESET +
                            ".");
        while (true) {
            String choice = ((new Scanner(System.in)).nextLine()).toLowerCase();
            switch (choice) {
                case "rmi" -> {
                    try {
                        runRMI(args[0]);
                    } catch (RemoteException e) {
                        System.out.println("RMI might not be available, you can still retry with "
                                            + ANSI_GREEN + "RMI" + ANSI_RESET +
                                            " or change to "
                                            + ANSI_GREEN + "socket" + ANSI_RESET +
                                            ".");
                    }
                }
                case "socket" ->{
                    try {
                        runSocket(args[0]);
                    }
                    catch (RemoteException e) {
                        System.out.println("Socket might not be available, you can still retry with "
                                            + ANSI_GREEN + "socket" + ANSI_RESET +
                                            " or change to "
                                            + ANSI_GREEN + "RMI" + ANSI_RESET +
                                            ".");
                    }
                }
                case "exit" -> { return; }
                default -> System.out.println("Wrong command, please retry.");
            }
        }
    }

    /**
     * Tries to start the socket connection to the server if the user selects socket
     *
     * @param ip is the ip of the server that is selected by the user
     *
     * @throws RemoteException on connection problems
     */
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

    /**
     * Tries to start the RMI connection to the server if the user selects RMI
     *
     * @param ip is the ip of the server that is selected by the user
     *
     * @throws RemoteException on connection problems
     */
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