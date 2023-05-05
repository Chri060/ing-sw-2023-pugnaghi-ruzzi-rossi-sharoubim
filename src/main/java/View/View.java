package View;

import Distributed.Messages.clientMessages.ClientMessage;
import util.Observable;

import java.util.Scanner;

public abstract class View extends Observable<ClientMessage> {

    String name;

    public View() {
        name = new Scanner(System.in).nextLine();
    }

    public String getName() {
        return name;
    }

    public void print(String s) {
        System.out.println(s);
    }

}
