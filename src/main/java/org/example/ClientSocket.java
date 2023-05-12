package org.example;

import Distributed.ClientImpl;
import Distributed.Socket.ServerStub;

import java.io.IOException;

public class ClientSocket {

    public static void run() throws IOException {
        ClientImpl client = new ClientImpl();
        client.view.setName();

        ServerStub serverStub = new ServerStub("5.95.187.51", 18301);

        new Thread(() -> serverStub.receive(client)).start();

        client.init(serverStub);

        new Thread(() -> client.run()).start();

    }
}
