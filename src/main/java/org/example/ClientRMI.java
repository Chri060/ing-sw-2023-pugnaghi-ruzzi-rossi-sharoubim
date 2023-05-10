package org.example;

import Distributed.*;
import Distributed.Messages.clientMessages.SetRoomSizeMessage;
import Distributed.Messages.clientMessages.WithdrawMessage;
import util.PlanarCoordinate;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

public class ClientRMI {
    public static void run() throws RemoteException, NotBoundException {

        Registry registry = LocateRegistry.getRegistry();
        Server server = (Server) registry.lookup("server");

        while (true) {

            ClientImpl client = new ClientImpl();
            client.view.setName();

            client.init(server);

            Thread viewThread = new Thread(() -> client.run());
            viewThread.start();
            try {
                viewThread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

        //server.leave(client, client.view.getName());
        /*List<PlanarCoordinate> list = (new ArrayList<>());
        list.add(new PlanarCoordinate(1,3));
        client.view.setChangedAndNotifyObservers(new WithdrawMessage(list, client.view.getName()));
*/
    }
}
