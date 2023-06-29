package Distributed;

import Exceptions.InvalidArgumentException;
import util.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used to link a client with his name, observer and pinger
 */
public class Associator {
    private List<String> nameList;
    private List<Client> clientList;
    private List<Observer> observerList;
    private List<Thread> threadList;

    /**
     * Construct a new empty Associator
     */
    public Associator() {
        nameList = new ArrayList<>();
        clientList = new ArrayList<>();
        observerList = new ArrayList<>();
        threadList = new ArrayList<>();
    }

    /**
     * Links a player with a client, a observer and a thread
     *
     * @param name is the name of the player
     * @param client is the client to lik to the player
     * @param observer is the Observer to link to the player
     * @param thread is the Thread to link to the player
     *
     * @throws InvalidArgumentException on invalid argument
     * @throws NullPointerException on null pointer
     */
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

    /**
     * Checks for the name linked to a given Client
     *
     * @param client is the Client to check
     *
     * @return the name of the player linked to the client
     */
    public String getName(Client client) {
        int index = clientList.indexOf(client);
        if (index == -1) {
            return null;
        }
        return nameList.get(index);
    }

    /**
     * Checks for the Observer linked to a given Client
     *
     * @param client is the Client to check
     *
     * @return the Observer of the player linked to the client
     */
    public Observer getObserver(Client client) {
        int index = clientList.indexOf(client);
        if (index == - 1) {
            return null;
        }
        return observerList.get(index);
    }

    /**
     * Checks if the list of Clients contains a given Client
     *
     * @param client is the Client to search in the list
     *
     * @return true if the Client is in the list, false otherwise
     */
    public boolean contains(Client client) {
        return clientList.contains(client);
    }

    /**
     * Checks for the Client linked to a given name
     *
     * @param name is the name to check
     *
     * @return the Client of the player linked to the name
     */
    public Client getClient(String name) throws NullPointerException{
        int index = nameList.indexOf(name);
        if (index == - 1) {
            throw new NullPointerException("No such name");
        }
        return clientList.get(index);
    }

    /**
     * Checks if the name is available to use
     *
     * @param name is the name to check
     *
     * @return true if the name is available, false otherwise
     *
     * @throws NullPointerException on null pointer
     */
    public boolean isNameAvailable(String name) throws NullPointerException {
        if (name == null) {
            throw new NullPointerException();
        }
        return !nameList.contains(name);
    }

    /**
     * Removes a client from the list of Clients
     *
     * @param client is the client to remove
     */
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