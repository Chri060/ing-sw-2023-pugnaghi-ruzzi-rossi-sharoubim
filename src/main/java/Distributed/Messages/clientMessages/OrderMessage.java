package Distributed.Messages.clientMessages;

import Controller.Controller;
import Distributed.Client;
import Distributed.Server;

import java.rmi.RemoteException;
import java.util.List;

public class OrderMessage extends ClientMessageAbs{

    List<Integer> orderList;

    public OrderMessage(String name, List<Integer> orderList) {
        super(name);
        this.orderList = orderList;
    }

    @Override
    public void execute(Server server, Client client) {
    }

    @Override
    public void execute(Controller controller) {
        controller.changeOrderOfCards(orderList);
    }

}
