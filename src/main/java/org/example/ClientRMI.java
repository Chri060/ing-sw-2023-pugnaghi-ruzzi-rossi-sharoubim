package org.example;

import Distributed.ClientImpl;
import Distributed.Messages.clientMessages.JoinMessage;
import Distributed.Messages.clientMessages.SetRoomSizeMessage;
import Distributed.Server;
import Distributed.WelcomeServer;
import Distributed.WelcomeServerImpl;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClientRMI {
    public static void main(String[] args) throws RemoteException, NotBoundException {

        Registry registry = LocateRegistry.getRegistry();
        WelcomeServer welcomeServerImpl = (WelcomeServer) registry.lookup("server");

        Server server = welcomeServerImpl.getRoom(new Scanner(System.in).nextLine());

        ClientImpl client = new ClientImpl(server);

        client.view.setChangedAndNotifyObservers(new JoinMessage(client.view.getName()));
        client.view.setChangedAndNotifyObservers(new SetRoomSizeMessage(client.view.getName(), 3));
    }
}
