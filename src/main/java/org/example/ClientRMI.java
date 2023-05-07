package org.example;

import Distributed.ClientImpl;
import Distributed.Messages.clientMessages.JoinMessage;
import Distributed.Messages.clientMessages.SetRoomSizeMessage;
import Distributed.Messages.clientMessages.WithdrawMessage;
import Distributed.Server;
import Distributed.WelcomeServer;
import Distributed.WelcomeServerImpl;
import util.PlanarCoordinate;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientRMI {
    public static void main(String[] args) throws RemoteException, NotBoundException {

        Registry registry = LocateRegistry.getRegistry();
        WelcomeServer welcomeServerImpl = (WelcomeServer) registry.lookup("welcomeServer");

        Server server = welcomeServerImpl.getRoom(new Scanner(System.in).nextLine());

        ClientImpl client = new ClientImpl(server);

        client.view.setChangedAndNotifyObservers(new JoinMessage(client.view.getName()));
        client.view.setChangedAndNotifyObservers(new SetRoomSizeMessage(client.view.getName(), 2));
        List<PlanarCoordinate> list = (new ArrayList<>());
        list.add(new PlanarCoordinate(1,3));
        client.view.setChangedAndNotifyObservers(new WithdrawMessage(list, client.view.getName()));

    }
}
