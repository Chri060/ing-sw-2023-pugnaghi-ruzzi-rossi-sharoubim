package View;

import Distributed.Messages.clientMessages.ClientMessage;
import Model.ModelView;
import util.Observable;

import java.io.Serializable;
import java.util.Scanner;

public abstract class View extends Observable<ClientMessage> implements Runnable {

    String name;
    ModelView model;

    public View() {
        name = "";
    }

    public String getName() {
        return name;
    }

    public void setName() {
        do {
            System.out.println("Insert your name");
            name = new Scanner(System.in).nextLine();
        } while (name.equals(""));
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
    abstract public void setRoomSize ();

}
