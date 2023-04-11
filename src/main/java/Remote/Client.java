package Remote;

import Model.Match;
import ModelView.EndMatchView;
import ModelView.MatchView;
import ModelView.UpdateView;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Client extends Remote {

    public void init(MatchView o) throws RemoteException;
    public void update(UpdateView o, Match.Event arg) throws RemoteException;
    public void finit(EndMatchView o) throws RemoteException;
}
