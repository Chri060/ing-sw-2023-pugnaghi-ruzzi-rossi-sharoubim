package Distributed.Messages.serverMessages;

import Distributed.Client;
import View.View;

import java.rmi.RemoteException;
import java.util.List;

public class TestMessage extends ServerMessageAbs{

    private String s;

    public TestMessage(String s) {
        super(true, (String) null);
        this.s = s;
    }
    public TestMessage(String s, String receiver) {
        super(false, receiver);
        this.s = s;
    }
    public TestMessage(String s, List<String> receivers) {
        super(false, receivers);
        this.s = s;
    }

    @Override
    public void execute(Client client) {
        try {
            client.executeOnView(this);
        } catch (RemoteException e) {}
    }

    @Override
    public void execute(View view) {
        view.print(s);
    }
}
