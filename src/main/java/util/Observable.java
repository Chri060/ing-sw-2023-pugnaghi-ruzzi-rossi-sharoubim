package util;

import Distributed.Messages.NetworkMessage;

import java.util.Vector;

public class Observable<Arg extends NetworkMessage> {

    private boolean changed = false;
    private Vector<Observer<? extends Observable<Arg>, Arg>> obs;

    public Observable() {
        obs = new Vector<>();
    }

    public synchronized void addObserver(Observer<? extends Observable<Arg>, Arg> o) {
        if (o == null)
            throw new NullPointerException();
        if (!obs.contains(o)) {
            obs.addElement(o);
        }
    }

    public synchronized void deleteObserver(Observer<? extends Observable<Arg>, Arg> o) {
        obs.removeElement(o);
    }



    public void notifyObservers(Arg arg) {
        /*
         * a temporary array buffer, used as a snapshot of the state of
         * current Observers.
         */
        Object[] arrLocal;

        synchronized (this) {
            /* We don't want the Observer doing callbacks into
             * arbitrary code while holding its own Monitor.
             * The code where we extract each Observable from
             * the Vector and store the state of the Observer
             * needs synchronization, but notifying observers
             * does not (should not).  The worst result of any
             * potential race-condition here is that:
             * 1) a newly-added Observer will miss a
             *   notification in progress
             * 2) a recently unregistered Observer will be
             *   wrongly notified when it doesn't care
             */
            if (!changed)
                return;
            arrLocal = obs.toArray();
            clearChanged();
        }

        for (int i = arrLocal.length-1; i>=0; i--)
            ((Observer<Observable<Arg>, Arg>)arrLocal[i]).update(this, arg);
    }

    protected synchronized void setChanged() {
        changed = true;
    }


    protected synchronized void clearChanged() {
        changed = false;
    }

    public void setChangedAndNotifyObservers(Arg message) {
        setChanged();
        notifyObservers(message);
    }
}
