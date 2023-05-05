package Distributed;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface WelcomeServer extends Remote {
    Server getRoom(String roomName) throws RemoteException;

}
