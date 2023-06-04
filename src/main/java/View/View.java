package View;

import Distributed.Messages.clientMessages.ClientMessage;
import Distributed.Messages.clientMessages.JoinMessage;
import Model.ModelView;
import util.Observable;

import java.io.Serializable;
import java.util.Scanner;
import java.util.concurrent.*;

public abstract class View extends Observable<ClientMessage> implements Runnable {

    String name;
    ModelView model;

    public enum State{
        SELECTNAME,
        NAMEREQUESTSENT,
        INLOBBY
    }

    private State state = State.NAMEREQUESTSENT;

    public View() {
        name = "";
    }

    public String getName() {
        return name;
    }

    public void setName() {
        synchronized (state) {
            while (state != State.INLOBBY) {
                System.out.println("Insert your name:");
                name = new Scanner(System.in).nextLine();
                state = State.NAMEREQUESTSENT;
                new Thread(() -> setChangedAndNotifyObservers(new JoinMessage(name))).start();
                try {
                    state.wait();
                }
                catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void print(String s) {
        System.out.println(s);
    }

    public void setModel(ModelView model) {
        this.model = model;
    }

    abstract public void printDashboard();
    abstract public void printMyShelf();
    abstract public void printAllShelfs();
    abstract public void printCommonObjectives();
    abstract public void update();
    abstract public void setRoomSize();
    public void setNameAvailable(boolean nameAvailable) {
        synchronized (state) {
            state.notify();
            if (nameAvailable) {
                state = State.INLOBBY;
            }
            else {
                state = State.SELECTNAME;
                System.out.println("Name is not available");
            }
        }
    }

    public void showChatMessage(String sender, String message) {
        System.out.println("[" + sender + "]:" + message);
    }


}
