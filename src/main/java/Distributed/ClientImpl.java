package Distributed;

import Distributed.Messages.serverMessages.ServerMessage;
import View.*;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

public class ClientImpl extends UnicastRemoteObject implements Client{

    public View view;
    private String token;

    public ClientImpl(Server server) throws RemoteException {
        super();
        view = new TextualIU();
        initialise(server);
    }

    protected ClientImpl(int port) throws RemoteException {
        super(port);
    }

    protected ClientImpl(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
    }

    public void setToken(String token) throws RemoteException {
        this.token = token;
    }

    public void initialise(Server server) throws RemoteException {
    server.register(this, view.getName());
    view.addObserver((o, message) -> {
        try {
            server.update(this, message);
        } catch (RemoteException e) {
            System.err.println("Couldn't update the server");
        }
    });
    }


    @Override
    public void update(ServerMessage message) {
        if (message.isBroadcast()) {
            message.execute(view);
        }
        else if (message.getReceiver().contains(view.getName())) {
            message.execute(view);
        }
    }
}
