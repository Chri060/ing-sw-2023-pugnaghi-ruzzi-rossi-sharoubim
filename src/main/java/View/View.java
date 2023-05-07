package View;

import Distributed.Messages.clientMessages.ClientMessage;
import Model.ModelView;
import util.Observable;

import java.util.Scanner;

public abstract class View extends Observable<ClientMessage> {

    String name;
    ModelView model;

    public View() {
        name = new Scanner(System.in).nextLine();
    }

    public String getName() {
        return name;
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

}
