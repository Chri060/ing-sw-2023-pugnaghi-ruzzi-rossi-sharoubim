package org.example;

import Distributed.WelcomeServerImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class WelcomeServerApp {
    public static void main(String[] args) throws RemoteException {
        WelcomeServerImpl server = new WelcomeServerImpl();

        Registry registry = LocateRegistry.getRegistry();
        registry.rebind("server", server);
    }
}
