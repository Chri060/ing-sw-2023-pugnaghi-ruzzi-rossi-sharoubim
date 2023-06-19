package View;

import Distributed.Messages.clientMessages.ClientMessage;
import Distributed.Messages.clientMessages.JoinMessage;
import Distributed.Messages.clientMessages.LeaveMessage;
import Model.ModelView;
import Model.ModelViewData;
import util.Observable;

import java.util.Scanner;

/**
 * Abstract class used for both textual and graphical user interface
 */
public abstract class View extends Observable<ClientMessage> implements Runnable {

    public ModelView model;
    Scanner scanner;

    /**
     * Construct the view with a ModelView, an observer and a scanner
     */
    public View() {
        model = new ModelView();
        model.setName("");
        this.scanner = new Scanner(System.in);
        model.addObserver((o, message) -> message.update(View.this));
    }

    /**
     * Method used to initialize the lobby, every player needs to set the name and wait for the game to begin
     */
    @Override
    public void run() {
        try {
            setName();
            while (model.getState() != ModelView.State.STARTED) {
                model.waitEvent();
                if (model.getState() == ModelView.State.SETTINGSIZE) {
                    roomLeaderEvent();
                }
            }
            System.out.println("Game Started");
            readCommand();
            if (/*la partita è finita*/ true) {
                endGame();
            }
        } catch (Exception e) {
            setChangedAndNotifyObservers(new LeaveMessage(model.getName()));
        }
    }

    /**
     * Method that send the name set request to the server and sets the outcome of the request in the view
     */
    public void setName() {
        try {
            do {
                readName();
                new Thread(() -> setChangedAndNotifyObservers(new JoinMessage(model.getName()))).start();
                model.requestSent();
                setNameOutcome();
            } while (model.getState() != ModelView.State.INLOBBY && model.getState() != ModelView.State.SETTINGSIZE && model.getState() != ModelView.State.STARTED);
        } catch (NoSuchFieldError e) {}
    }

    /**
     * Reads the player's name
     */
    protected abstract void readName();

    /**
     * Sets the enumeration value based on server response
     */
    abstract public void setNameOutcome();

    /**
     * Shows all the players in lobby
     */
    public abstract void showLobby();

    /**
     * Only the roomLeader will receive this event
     * He needs to set the size of the room
     */
    abstract public void roomLeaderEvent();

    /**
     * Reads the command inserted by the player
     */
    abstract public void readCommand();

    /**
     * Used to finish the game
     */
    abstract public void endGame();

    /**
     * Let the client wait for an event from the server
     */
    public void waitEvent() {
        synchronized (model) {
            try {
                model.wait();
            } catch (InterruptedException e) {}
        }
    }

    /**
     * Let the client wait for a response from the server
     */
    public void waitResponse() {
        synchronized (model) {
            try {
                model.wait(4000);
                if (model.getRequestStatus() == ModelView.RequestStatus.SENT) {
                    System.err.println("Server did not respond\nHalting game");
                    System.exit(0);
                }
            } catch (InterruptedException e) {}
        }
    }

    /**
     * Sends a response to the server
     */
    public void giveResponse() {
        synchronized (model) {
            model.notify();
        }
    }

    /**
     * Print a string in the console
     *
     * @param s is the string to print
     */
    public void print(String s) {
        System.out.println(s);
    }

    /**
     * Used to initialize the ModelView
     *
     * @param modelData are the data used to initialize the ModelView
     */
    public void initialiseModelView(ModelViewData modelData) {
        this.model.initialise(modelData);
    }

    /**
     * Updates the ModelView
     */
    abstract public void update();

    /**
     * Prints a ChatMessage
     *
     * @param sender is the person who sent the message
     * @param message is the actual message
     */
    public abstract void showChatMessage(String sender, String message);
}