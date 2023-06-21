package Distributed.Messages.serverMessages;

import Distributed.Client;
import View.View;

import java.util.List;

/**
 * Class that implements a TestMessage
 */
public class TestMessage extends ServerMessageAbs {

    private String s;

    /**
     * Construct a test message with a String
     *
     * @param s is the String sent in the message
     */
    public TestMessage(String s) {
        super();
        this.s = s;
    }

    /**
     * Construct a test message with a String and a receiver
     *
     * @param s is the String sent in the message
     * @param receiver is the player who is going to receive the message
     */
    public TestMessage(String s, String receiver) {
        super(receiver);
        this.s = s;
    }

    /**
     * Construct a test message with a String and a list of receivers
     *
     * @param s is the String sent in the message
     * @param receivers is a list the players who are going to receive the message
     */
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
