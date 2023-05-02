package Distributed;

import Exceptions.InvalidActionException;
import Exceptions.InvalidArgumentException;

import java.util.ArrayList;
import java.util.List;

public class ClientAssociator {

    List<Client> clientList;
    List<String> nameList;

    public ClientAssociator() {
        this.clientList =new ArrayList<>();
        this.nameList = new ArrayList<>();
    }

    public void add(Client client, String name) throws NullPointerException {
        if (client == null || name == null) {
            throw new NullPointerException();
        }
        clientList.add(client);
        nameList.add(name);
    }

    public String getName(Client client) throws InvalidArgumentException {
        if (!clientList.contains(client)) {
            throw new InvalidArgumentException("Requested client is not in this associator");
        }
        return nameList.get(clientList.indexOf(client));
    }

    public Client getClient(String name) throws InvalidArgumentException {
        if (!nameList.contains(name)) {
            throw new InvalidArgumentException("Requested name is not in this associator");
        }
        return clientList.get(nameList.indexOf(name));
    }

    public boolean isNameAvailable(String name) {
        return !nameList.contains(name);
    }
}
