package util;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Vector;

/**
 * Class used to extend other classes and make them observable
 */
public class Observable<Arg extends Object>  {


    private boolean changed = false;
    private Vector<Observer<? extends Observable<Arg>, Arg>> obs;

    /**
     * Constructor of Observable
     */
    public Observable() {
        obs = new Vector<>();
    }

    /**
     * Adds an observer to a given object

     * @param o is the observer to add
     */
    public synchronized void addObserver(Observer<? extends Observable<Arg>, Arg> o) {
        if (o == null)
            throw new NullPointerException();
        if (!obs.contains(o)) {
            obs.addElement(o);
        }
    }

    /**
     * Removes an observer to a given object
     *
     * @param o is the observer to remove
     */
    public synchronized void deleteObserver(Observer<? extends Observable<Arg>, Arg> o) {
        obs.removeElement(o);
    }

    /**
     * Notifies all the observer connected
     */
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
        for (int i = arrLocal.length-1; i>=0; i--) ((Observer<Observable<Arg>, Arg>)arrLocal[i]).update(this, arg);
    }

    /**
     * Sets the flag changed to true
     */
    protected synchronized void setChanged() {
        changed = true;
    }

    /**
     * Sets the flag changed to false
     */
    protected synchronized void clearChanged() {
        changed = false;
    }

    /**
     * When the flag changed is updated it notifies the observers
     *
     * @param message is the message to send to the observer
     */
    public void setChangedAndNotifyObservers(Arg message) {
        setChanged();
        notifyObservers(message);
    }
}