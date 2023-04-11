package Remote;

import Model.Cards;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Server extends Remote {
    public void register(Client client) throws RemoteException;

    public void getCards(Client client, List<Integer> coordinates) throws RemoteException;

    public void insertCards(Client client, List<Cards> cards, int col) throws RemoteException;


}
