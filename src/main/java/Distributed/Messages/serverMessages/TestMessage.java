package Distributed.Messages.serverMessages;

import Distributed.Client;
import View.View;

import java.rmi.RemoteException;
import java.util.List;

public class TestMessage extends ServerMessageAbs{

    private String s;

    public TestMessage(String s) {
        super();
        this.s = s;
    }
    public TestMessage(String s, String receiver) {
        super(receiver);
        this.s = s;
    }
    public TestMessage(String s, List<String> receivers) {
        super(receivers);
        this.s = s;
    }

    @Override
    public void execute(Client client) {
    }

    @Override
    public void execute(View view) {
        view.print(s);
    }
}
