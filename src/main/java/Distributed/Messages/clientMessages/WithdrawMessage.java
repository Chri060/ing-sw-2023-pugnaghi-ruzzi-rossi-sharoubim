package Distributed.Messages.clientMessages;

import Controller.Controller;
import Distributed.Client;
import Distributed.Server;
import util.PlanarCoordinate;

import java.util.List;

/**
 * Class that implements a WithdrawMessage
 */
public class WithdrawMessage extends ClientMessageAbs {

    List<PlanarCoordinate> planarCoordinateList;

    /**
     * Construct a WithdrawMessage with a list of coordinate and the name of the client
     *
     * @param planarCoordinateList is the list of PlayerCoordinate to withdraw
     * @param name is the name of the player who is trying to do the action
     */
    public WithdrawMessage(List<PlanarCoordinate> planarCoordinateList, String name) {
        super(name);
        this.planarCoordinateList = planarCoordinateList;
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
     * It calls the function to withdraw on the controller.
     *
     * @param controller is the Controller linked to the Model that is going to execute the action
     */
    @Override
    public void execute(Controller controller) {
        controller.withdraw(name, planarCoordinateList);
    }
}