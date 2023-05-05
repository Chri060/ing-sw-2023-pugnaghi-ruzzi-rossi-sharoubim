package Distributed;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class WelcomeServerImpl extends UnicastRemoteObject implements WelcomeServer{
    Map<String, Server> rooms;

    public WelcomeServerImpl() throws RemoteException {
        super();
        rooms = new HashMap<>();
    }

    public Server getRoom(String roomName) throws RemoteException {
        Server server = rooms.get(roomName);
        if (server == null) {
            server = new ServerImpl();
            rooms.put(roomName, server);
        }
        return server;
    }
}
