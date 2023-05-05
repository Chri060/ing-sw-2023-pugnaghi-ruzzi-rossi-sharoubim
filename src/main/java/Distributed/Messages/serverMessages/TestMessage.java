package Distributed.Messages.serverMessages;

import View.View;

public class TestMessage implements ServerMessage{

    private String s;

    public TestMessage(String s) {
        this.s = s;
    }

    @Override
    public void execute(View view) {
        view.print(s);
    }
}
