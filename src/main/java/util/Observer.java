package util;

/**
 * Interface for the observer class
 */
public interface Observer<ObservedObject extends Observable<Arg>, Arg extends Object> {

    /**
     * Sends the update to the observed objects
     *
     * @param o is the ObservedObject to update
     * @param message is the message to send to them
     */
    void update(ObservedObject o, Arg message);
}