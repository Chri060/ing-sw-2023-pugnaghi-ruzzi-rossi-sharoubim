package org.example;

import Distributed.ClientImpl;
import Distributed.Socket.ServerStub;

import java.io.IOException;

public class ClientSocket {

    public static void run() throws IOException {
        ClientImpl client = new ClientImpl();
        client.view.setName();

        ServerStub serverStub = new ServerStub("localhost", 5555);

        new Thread(() -> serverStub.receive(client)).start();

        client.init(serverStub);

        new Thread(() -> client.run()).start();

    }
}
