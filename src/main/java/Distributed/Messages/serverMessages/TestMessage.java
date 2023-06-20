package Distributed.Messages.serverMessages;

import Distributed.Client;
import View.View;

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

    /**
     * Executes the message on the client
     *
     * @param client is the Client used to execute the message
     */
    @Override
    public void execute(Client client) {

    }

    /**
     * Executes the message on the view.
     * It calls the method used to show a test message on the View
     *
     * @param view is the View used to execute the message
     */
    @Override
    public void execute(View view) {
        view.print(s);
    }
}
