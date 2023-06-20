package Distributed.Messages.clientMessages;

import Controller.Controller;
import Distributed.Client;
import Distributed.Server;

import java.util.List;

/**
 * Class that implements a OrderMessage
 */
public class OrderMessage extends ClientMessageAbs {

    List<Integer> orderList;

    /**
     * Construct a message for ordering the Cards
     *
     * @param name is the player who is trying to order the Cards
     * @param orderList is the new desired order
     */
    public OrderMessage(String name, List<Integer> orderList) {
        super(name);
        this.orderList = orderList;
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
     * It calls the function to change the order of cards on the controller.
     *
     * @param controller is the Controller linked to the Model that is going to execute the action
     */
    @Override
    public void execute(Controller controller) {
        controller.changeOrderOfCards(orderList);
    }
}