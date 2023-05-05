package Distributed.Messages.clientMessages;

public abstract class ClientMessageAbs implements ClientMessage{
    String name;

    public ClientMessageAbs(String name) {
        this.name = name;
    }

    @Override
    public String getAuth() {
        return name;
    }
}
