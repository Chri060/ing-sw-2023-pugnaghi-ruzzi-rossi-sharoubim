package Distributed;

import Distributed.Messages.serverMessages.ServerMessage;
import View.View;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

public class ClientImpl extends UnicastRemoteObject implements Client{

    View view;

    public ClientImpl() throws RemoteException {
    }

    protected ClientImpl(int port) throws RemoteException {
        super(port);
    }

    protected ClientImpl(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
    }

    @Override
    public void update(ServerMessage message) {

    }
}
