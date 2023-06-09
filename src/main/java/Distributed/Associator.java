package Distributed;

import Exceptions.InvalidArgumentException;
import util.Observer;

import java.util.ArrayList;
import java.util.List;

public class Associator {
    private List<String> nameList;
    private List<Client> clientList;
    private List<Observer> observerList;
    private List<Thread> threadList;

    public Associator() {
        nameList = new ArrayList<>();
        clientList = new ArrayList<>();
        observerList = new ArrayList<>();
        threadList = new ArrayList<>();

    }

    public void add(String name, Client client, Observer observer, Thread thread) throws InvalidArgumentException, NullPointerException {
        if (name == null || client == null || observer == null) {
            throw new NullPointerException();
        }
        if (nameList.contains(name)) {
            throw new InvalidArgumentException("Name already taken");
        }
        this.nameList.add(name);
        this.clientList.add(client);
        this.observerList.add(observer);
        this.threadList.add(thread);

    }

    public String getName(Client client) {
        int index = clientList.indexOf(client);
        if (index == - 1) {
            return null;
        }
        return nameList.get(index);
    }

    public Observer getObserver(Client client) {
        int index = clientList.indexOf(client);
        if (index == - 1) {
            return null;
        }
        return observerList.get(index);
    }

    public boolean contains(Client client) {
        return clientList.contains(client);
    }

    public Client getClient(String name) throws NullPointerException{
        int index = nameList.indexOf(name);
        if (index == - 1) {
            throw new NullPointerException("No such name");
        }
        return clientList.get(index);
    }

    public boolean isNameAvailable(String name) throws NullPointerException {
        if (name == null) {
            throw new NullPointerException();
        }
        return !nameList.contains(name);
    }

    public void delete(Client client) {
        int index = clientList.indexOf(client);
        if (index != - 1) {
            nameList.remove(index);
            clientList.remove(index);
            observerList.remove(index);
            threadList.remove(index);
        }
    }
}
