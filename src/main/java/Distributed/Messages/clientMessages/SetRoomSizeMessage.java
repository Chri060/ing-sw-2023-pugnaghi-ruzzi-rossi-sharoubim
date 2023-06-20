package Distributed.Messages.clientMessages;

import Controller.Controller;
import Distributed.Client;
import Distributed.Server;

/**
 * Class that implements a SetRoomSizeMessage
 */
public class SetRoomSizeMessage extends ClientMessageAbs {

    private int roomSize;

    /**
     * Construct a message to set the room size
     *
     * @param name is the name of the player that is trying to set the room size
     * @param roomSize is the room size requested by the player
     */
    public SetRoomSizeMessage(String name, int roomSize) {
        super(name);
        this.roomSize = roomSize;
    }

    /**
     * Executes the request from the client on the server
     *
     * @param server is the Server called
     * @param client is the Client that sent the message
     */
    @Override
    public void execute(Server server, Client client) {

    }

    /**
     * Executes the request from the server on the controller.
     * It calls the function to set the size of the room on the controller.
     *
     * @param controller is the Controller linked to the Model that is going to execute the action
     */
    @Override
    public void execute(Controller controller) {
        controller.setRoomSize(roomSize, name);
    }
}