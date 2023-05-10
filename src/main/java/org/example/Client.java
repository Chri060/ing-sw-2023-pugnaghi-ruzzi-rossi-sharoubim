package org.example;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws NotBoundException, IOException {
        String choice = (new Scanner(System.in)).nextLine();
        switch (choice) {
            case "RMI" -> ClientRMI.run();
            case "Socket" -> ClientSocket.run();
        }
    }
}
