package View.GUI;

import Distributed.Server;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class TestRMI extends Test{
    @Override
    public void init() throws Exception {
        super.init();

        try {
            Registry registry = LocateRegistry.getRegistry(parameters.getRaw().get(0), 44444);
            Server server = (Server) registry.lookup("server");
            clientImplGUI.init(server);
            Thread viewThread = new Thread(() -> clientImplGUI.run());
            viewThread.start();
            try {
                viewThread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } catch (RemoteException | NotBoundException e) {
            throw new RemoteException("Server RMI KO");
        }
    }
}
