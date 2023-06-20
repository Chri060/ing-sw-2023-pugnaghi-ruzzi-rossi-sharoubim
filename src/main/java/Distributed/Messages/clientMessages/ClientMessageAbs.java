package Distributed.Messages.clientMessages;

/**
 * Abstract class for a message from the client
 */
public abstract class ClientMessageAbs implements ClientMessage {
    String name;

    /**
     * Sets the name of the client who sent the message
     *
     * @param name is the name of the player who sent the request
     */
    public ClientMessageAbs(String name) {
        this.name = name;
    }

    /**
     * @return the name of the player who sent the request
     */
    @Override
    public String getAuth() {
        return name;
    }
}