package Distributed;

import Controller.Controller;
import Distributed.Messages.clientMessages.ClientMessage;
import Distributed.Messages.serverMessages.*;
import Exceptions.InvalidArgumentException;
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

    Associator associator;
    Map<String, Client> clientNames;
    Map<String, Observer<Observable<ServerMessage>, ServerMessage>> observers;

    public ServerImpl() throws RemoteException {
        super();
        this.model = new Model();
        this.controller = new Controller(model);
        observers = new HashMap<>();
        clientNames = new HashMap<>();
        associator = new Associator();
    }

    protected ServerImpl(int port) throws RemoteException {
        super(port);
    }

    protected ServerImpl(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
    }

    @Override
    public void register(Client client, String name) throws RemoteException {
        try {
            if (!associator.isNameAvailable(name)) {
                client.update(new TestMessage("Name already in use"));
                return;
            }
            if (name.equals("")) {
                client.update(new TestMessage("Name cannot be empty"));
                return;
            }
            if (associator.contains(client)) {
                client.update(new TestMessage("You are already in this lobby"));
                return;
            }
        } catch (NullPointerException e) {
            client.update(new TestMessage("Name cannot be null"));
        }
        Observer<Observable<ServerMessage>, ServerMessage> obs = getObserver(client, name);
        model.addObserver(obs);
        controller.join(name);
        try {
            boolean spectator;
            if (model.getGameStatus() != Model.GameStatus.PREMATCH) {
                spectator = !model.isInGame(name);
            }
            else {
                spectator = false;
            }
            Pinger pinger;
            if (!spectator) {
                pinger = getPinger(client);
                pinger.start();
            }
            else {
                pinger = null;
                client.update(new TestMessage("You are a spectator", name));
            }
            associator.add(spectator, name, client, obs, pinger);
        } catch (InvalidArgumentException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void leave(Client client, String name) throws RemoteException {
        if (name.equals(associator.getName(client))) {
            model.deleteObserver(associator.getObserver(client));
            controller.leave(name);
            associator.delete(client);
            client.update(new TestMessage("Lobby left"));
        }
    }

    @Override
    public void update(Client client, ClientMessage message) throws RemoteException {
        System.out.println("Received a new message: " + message.getClass());
        if (message.getAuth().equals(associator.getName(client))) {
            message.execute(controller);
        }
        message.execute(this, client);
    }

    private Observer<Observable<ServerMessage>, ServerMessage> getObserver(Client client, String name) {
        return new Observer<>() {
            @Override
            public void update(Observable<ServerMessage> o, ServerMessage message) {
                if (message.isBroadcast() || message.getReceiver().contains(name)) {
                    try {
                        client.update(message);
                    } catch (RemoteException e) {
                        System.err.println("Error while notify client");
                        if (associator.isSpectator(client)) {
                            model.deleteObserver(associator.getObserver(client));
                        } else {
                            model.notifyObservers(new TestMessage(associator.getName(client) + "Disconnected"));
                        }
                    }
                }
            }

        };
    }
    private Pinger getPinger(Client client) {
        return new Pinger() {
            @Override
            public void run() {
                synchronized (this) {
                    while (!needsToStop()) {
                        try {
                            client.update(new PingClientMessage());
                            wait(1000);
                        } catch (RemoteException e) {
                            model.deleteObserver(associator.getObserver(client));
                            model.setChangedAndNotifyObservers(new TestMessage(associator.getName(client) + " Disconnected"));
                            System.out.println(associator.getName(client) + " disconnected");
                            controller.leave(associator.getName(client));
                            associator.delete(client);
                            return;
                        } catch (InterruptedException e) {
                        }
                    }
                }
            }
        };
    }


}
