package Distributed;

import View.TextualUI;

import java.rmi.RemoteException;

public class ClientImplTUI extends ClientImpl{
    public ClientImplTUI() throws RemoteException {
        super();

        view = new TextualUI();
    }
}
