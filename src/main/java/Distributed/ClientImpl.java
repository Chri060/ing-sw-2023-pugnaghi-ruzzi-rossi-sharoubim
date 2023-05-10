package Distributed;

import Distributed.Messages.serverMessages.ServerMessage;
import View.*;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

public class ClientImpl extends UnicastRemoteObject implements Client, Runnable{

    public View view;

    public ClientImpl() throws RemoteException {
        super();
        view = new TextualUI();
    }

    protected ClientImpl(int port) throws RemoteException {
        super(port);
    }

    protected ClientImpl(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
    }

    public void init(Server server) throws RemoteException {
        view.addObserver((o, message) -> {
            try {
                server.update(this, message);
            } catch (RemoteException e) {
                System.err.println("Error while notify Server");
            }
        });
        server.register(this, view.getName());
    }


    @Override
    public void update(ServerMessage message) throws RemoteException {
        message.execute(this);
    }

    @Override
    public void executeOnView(ServerMessage message) throws RemoteException {
        message.execute(view);
    }

    @Override
    public void run() {
        view.run();
    }
}
