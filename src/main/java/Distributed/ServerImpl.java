package Distributed;

import Controller.Controller;
import Distributed.Messages.NetworkMessage;
import Distributed.Messages.clientMessages.ClientMessage;
import Distributed.Messages.serverMessages.*;
import Model.Model;
import util.Observable;
import util.Observer;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class ServerImpl extends UnicastRemoteObject implements Server{

    Controller controller;
    Model model;
    Map<String, Client> clientNames;
    Map<String, Observer<Observable<ServerMessage>, ServerMessage>> observers;

    public ServerImpl() throws RemoteException {
        super();
        this.model = new Model();
        this.controller = new Controller(model);
        observers = new HashMap<>();
        clientNames = new HashMap<>();
    }

    protected ServerImpl(int port) throws RemoteException {
        super(port);
    }

    protected ServerImpl(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
    }


    @Override
    public void register(Client client, String name) throws RemoteException {
        System.out.println("Registering...");
        if (clientNames.containsKey(name)) {
            client.update(new TestMessage("Name already in use"));
            return;
        }
        clientNames.put(name, client);
        Observer<Observable<ServerMessage>, ServerMessage> obs = (o, message) -> {
            try {
                client.update(message);
            } catch (RemoteException e) {
                System.err.println("Error while notify client");
            }
        };
        model.addObserver(obs);
        observers.put(name, obs);
        controller.join(name);
    }


    @Override
    public void update(Client client, ClientMessage message) throws RemoteException {
        System.out.println("Received a new message");
        if (clientNames.get(message.getAuth()) != null && client.equals(clientNames.get(message.getAuth()))) {
            message.execute(controller);
            return;
        }
        message.execute(this, client);
        /*if (!clientNames.containsKey(message.getAuth())) {
            System.out.println("Executing a message from an unknown");
            message.execute(this, client);
            return;
        }
        if (!clientNames.get(message.getAuth()).equals(client)) {
            client.update(new TestMessage("Name not available"));
            return;
        }
        System.out.println("Executing a message from " + message.getAuth());
        System.out.println("Message Type: " + message);
        message.execute(controller);*/


    }
}
