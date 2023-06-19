package Distributed;

import Distributed.Messages.serverMessages.ServerMessage;
import View.*;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

/**
 * Class used to implement the client
 */
public class ClientImpl extends UnicastRemoteObject implements Client, Runnable{

    public View view;

    /**
     * Creates a Textual UI Client
     *
     * @throws RemoteException on connection problems
     */
    public ClientImpl() throws RemoteException {
        super();
        view = new TextualUI();
    }

    /**
     * Sets a port to connect to
     *
     * @param port is the port to use
     *
     * @throws RemoteException on connection problems
     */
    protected ClientImpl(int port) throws RemoteException {
        super(port);
    }

    /**
     * Sets a port to connect to and socket and RMI
     *
     * @param port is the port to use
     * @param csf is the RMIClientSocketFactory
     * @param ssf is the RMIServerSocketFactory
     *
     * @throws RemoteException on connection problems
     */
    protected ClientImpl(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
    }

    /**
     * Tries to add an observer and send a message to the server
     *
     * @param server is the Server to connect to
     *
     * @throws RemoteException on connection problems
     */
    public void init(Server server) throws RemoteException {
        view.addObserver((o, message) -> {
            try {
                server.update(this, message);
            } catch (RemoteException e) {
                System.err.println("Error while notify Server");
            }
        });
        //server.register(this, view.getName());
    }

    /**
     * Updates the View linked to the Client
     *
     * @param message is the ServerMessage to send
     *
     * @throws RemoteException on connection problems
     */
    @Override
    public void update(ServerMessage message) throws RemoteException {
        message.execute(this);
        message.execute(view);
    }

    /**
     * Starts the view execution
     */
    @Override
    public void run() {
        view.run();
    }
}