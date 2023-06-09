package View;

import Distributed.Messages.clientMessages.ClientMessage;
import Distributed.Messages.clientMessages.JoinMessage;
import Distributed.Messages.clientMessages.LeaveMessage;
import Model.ModelView;
import Model.ModelViewData;
import util.Observable;

import java.util.Scanner;

public abstract class View extends Observable<ClientMessage> implements Runnable {

    public ModelView model;
    Scanner scanner;


    public View() {
        model = new ModelView();
        model.setName("");
        this.scanner = new Scanner(System.in);
        model.addObserver((o, message) -> message.update(View.this));
    }


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

    protected abstract void readName();
    abstract public void setNameOutcome();

    public abstract void showLobby();

    abstract public void roomLeaderEvent();

    abstract public void readCommand();
    abstract public void endGame();

    public void waitEvent() {
        synchronized (model) {
            try {
                model.wait();
            } catch (InterruptedException e) {}
        }
    }
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
    public void giveResponse() {
        synchronized (model) {
            model.notify();
        }
    }


    public void print(String s) {
        System.out.println(s);
    }
    public void initialiseModelView(ModelViewData modelData) {
        this.model.initialise(modelData);
    }
    abstract public void update();
    public abstract void showChatMessage(String sender, String message);


}
