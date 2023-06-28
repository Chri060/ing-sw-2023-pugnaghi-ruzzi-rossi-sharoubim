package Distributed;

import Controller.Controller;
import Distributed.Messages.clientMessages.ClientMessage;
import Distributed.Messages.serverMessages.*;
import Exceptions.InvalidArgumentException;
import Model.Model;
import Model.ModelView;
import util.Observable;
import util.Observer;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

/**
 * Class that implements the server
 */
public class ServerImpl extends UnicastRemoteObject implements Server{

    private Controller controller;
    private Model model;
    Associator associator;
    Map<String, Client> clientNames;
    Map<String, Observer<Observable<ServerMessage>, ServerMessage>> observers;

    /**
     * resets the server linked Objects
     *
     * @param server is the Server to reset
     */
    public static void reset(ServerImpl server) {
        server.model = new Model();
        server.controller = new Controller(server.model);
    }

    /**
     * Construct a new Server with Model, View and Associator
     *
     * @throws RemoteException on connection problems
     */
    public ServerImpl() throws RemoteException {
        super();
        this.model = new Model();
        this.controller = new Controller(model);
        associator = new Associator();
    }

    /**
     * Sets a port to connect to
     *
     * @param port is the port to use
     *
     * @throws RemoteException on connection problems
     */
    protected ServerImpl(int port) throws RemoteException {
        super(port);
    }

    /**
     * Sets a port to connect to and socket and RMi
     *
     * @param port is the port to use
     * @param csf is the RMIClientSocketFactory
     * @param ssf is the RMIServerSocketFactory
     *
     * @throws RemoteException on connection problems
     */
    protected ServerImpl(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
    }

    /**
     * Register a client to the serve r
     *
     * @param client is the Client to register
     * @param name is the name of the player
     *
     * @throws RemoteException on connection problems
     */
    @Override
    public void register(Client client, String name) throws RemoteException {
        synchronized (model) {
            if (!model.canJoin(name)) {
                if (model.getGameStatus() != Model.GameStatus.PREMATCH) {
                    client.update(new JoinRequestRefusedMessage(ModelView.State.NOTJOINABLE));
                }
                client.update(new JoinRequestRefusedMessage(ModelView.State.WAITINGLEADER));
                return;
            }
        }
        synchronized (associator) {
            try {
                if (!associator.isNameAvailable(name)) {
                    client.update(new JoinRequestRefusedMessage(ModelView.State.SELECTNAME));
                    return;
                }
                if (name.equals("")) {
                    client.update(new JoinRequestRefusedMessage(ModelView.State.SELECTNAME));
                    return;
                }
                if (associator.contains(client)) {
                    client.update(new TestMessage("You are already in this lobby"));
                    return;
                }

            } catch (NullPointerException e) {
                client.update(new JoinRequestRefusedMessage(ModelView.State.SELECTNAME));
            }
        }
        Observer<Observable<ServerMessage>, ServerMessage> obs = getObserver(client, name);
        model.addObserver(obs);
        controller.join(name);
        Thread pinger = getPinger(client);
        pinger.start();
        try {
            associator.add(name, client, obs, pinger);
        } catch (InvalidArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Removes a Client connection from the server
     *
     * @param client is the Client to remove
     * @param name is the name of the player to remove
     *
     * @throws RemoteException on connection problems
     */
    @Override
    public void leave(Client client, String name) throws RemoteException {
        synchronized (model) {
            if (associator.getName(client).equals(name)) {
                model.deleteObserver(associator.getObserver(client));
                controller.leave(name);
                associator.delete(client);
                if (model.getGameStatus() != Model.GameStatus.PREMATCH && model.getOnlinePlayersCount() == 0) {
                    System.out.println("Reset");
                    reset(this);
                }
            }
        }
    }

    /**
     * Sends an update to the client selected
     *
     * @param client is the Client supposed to receive the update
     * @param message is the message to send
     *
     * @throws RemoteException on connection problems
     */
    @Override
    public void update(Client client, ClientMessage message) throws RemoteException {
        if (message.getAuth() == null) {
            return;
        }
        if (message.getAuth().equals(associator.getName(client))) {
            message.execute(controller);
        }
        message.execute(this, client);
    }

    /**
     * Return the observer linked to a Client
     *
     * @param client is the Client to check
     * @param name is the name of the player to check
     *
     * @return an Observer
     */
    private Observer<Observable<ServerMessage>, ServerMessage> getObserver(Client client, String name) {
        return (o, message) -> {
            if (message.isBroadcast() || message.getReceiver().contains(name)) {
                try {
                    client.update(message);
                } catch (RemoteException e) {
                    System.err.println("Error while notifying " + associator.getName(client));
                    }
                }
            };
    }

    /**
     * Checks the Pinger linked to a Client
     *
     * @param client is the Client to check
     *
     * @return a Thread
     */
    private Thread getPinger(Client client) {
        return new Thread() {
            @Override
            public void run() {
                synchronized (this) {
                    while (true) {
                        try {
                            client.update(new PingClientMessage());
                            wait(1000);
                        } catch (RemoteException e) {
                            try {
                                if (associator.getName(client) != null) {
                                    leave(client, associator.getName(client));
                                }
                            } catch (RemoteException remoteException) {}
                            return;
                        } catch (InterruptedException e) {
                        }
                    }
                }
            }
        };
    }
}