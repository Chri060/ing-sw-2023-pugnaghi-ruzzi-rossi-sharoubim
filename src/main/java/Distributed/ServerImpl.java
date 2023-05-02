package Distributed;

import Controller.Controller;
import Distributed.Messages.clientMessages.ClientMessage;
import Exceptions.InvalidArgumentException;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl extends UnicastRemoteObject implements Server{

    Controller controller;

    ClientAssociator associator;

    public ServerImpl() throws RemoteException {
    }

    protected ServerImpl(int port) throws RemoteException {
        super(port);
    }

    protected ServerImpl(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
    }


    @Override
    public void register(Client client, String name) {
        if (!associator.isNameAvailable(name)) {
            //Notifies name has already been taken
        }
        associator.add(client, name);
    }


    @Override
    public void update(Client client, ClientMessage message) {
        try {
            String name = associator.getName(client);
            message.injectName(name);
            message.execute(controller);
        } catch (InvalidArgumentException e) {
            //TODO notifies client that we don't know who he is
        }
    }
}
