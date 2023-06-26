package Distributed;

import View.GUI.Test;
import View.View;
import javafx.application.Application;

import java.rmi.RemoteException;

public class ClientImplGUI extends ClientImpl{

    public ClientImplGUI(View view) throws RemoteException {
        super(view);
    }
}
