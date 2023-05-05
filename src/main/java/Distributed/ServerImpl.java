package Distributed;

import Controller.Controller;
import Distributed.Messages.clientMessages.ClientMessage;
import Model.Model;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class ServerImpl extends UnicastRemoteObject implements Server{

    Controller controller;
    Model model;
    Map<Client, String> clientNames;

    public ServerImpl() throws RemoteException {
        super();
        this.model = new Model();
        this.controller = new Controller(model);
        clientNames = new HashMap<>();
    }

    protected ServerImpl(int port) throws RemoteException {
        super(port);
    }

    protected ServerImpl(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
    }


    @Override
    public void register(Client client, String auth) throws RemoteException{
        clientNames.put(client, auth);
        model.addObserver((o, message) -> {
            try {
                client.update(message);
            } catch (RemoteException e) {
                System.err.println("Couldn't contact client");
            }
        });
    }


    @Override
    public void update(Client client, ClientMessage message) throws RemoteException {
        if (message.getAuth().equals(clientNames.get(client))) {
            message.execute(this.controller);

        }
    }
}
