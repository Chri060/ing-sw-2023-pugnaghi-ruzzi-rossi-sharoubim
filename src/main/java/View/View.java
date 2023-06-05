package View;

import Distributed.Messages.clientMessages.ClientMessage;
import Distributed.Messages.clientMessages.JoinMessage;
import Model.ModelView;
import Model.ModelViewData;
import Model.entities.Card;
import util.Observable;

import java.util.Scanner;

public abstract class View extends Observable<ClientMessage> implements Runnable {

    String name;
    public ModelView model;

    public int getCardsNum() {
        return cardsNum;
    }

    public void setCardsNum(int cardsNum) {
        this.cardsNum = cardsNum;
    }

    public enum State{
        SELECTNAME,
        NAMEREQUESTSENT,
        INLOBBY
    }

    private int cardsNum;
    private State state = State.NAMEREQUESTSENT;

    public View() {
        name = "";
        model = new ModelView();
    }

    public State getState () {
        return state;
    }

    public String getName() {
        return name;
    }

    public void setName() {
        synchronized (state) {
            while (state != State.INLOBBY) {
                System.out.println("Write your username and then press enter.");
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

    public void initialiseModelView(ModelViewData modelData) {
        this.model = new ModelView(modelData);
    }

    public void updateDashboard(Card[][] dashBoard) {
        this.model.updateDashboard(dashBoard);
    }

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
                System.out.println("The username is already selected, please try with a new one.");
            }
        }
    }


    public void joinUpdate(String playerName) {
        model.addPlayerName(playerName);
    }

    public void leaveUpdate(String playerName) {
        model.removePlayerName(playerName);
    }

    public void showChatMessage(String sender, String message) {
        System.out.println("[" + sender + "]:" + message);
    }


}
