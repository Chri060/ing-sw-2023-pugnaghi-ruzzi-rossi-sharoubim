package View;

import Distributed.Messages.clientMessages.*;
import Model.entities.Card;
import Model.viewEntities.CommonObjectiveView;
import Model.viewEntities.PlayerView;
import util.PlanarCoordinate;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static util.AnsiColor.*;

public class TextualUI extends View {
    @Override
    public void run() {
        try {
            setName();
            if (/*roomLeader*/ true) {
                roomLeader();
            }

            if (/*partita iniziata e attiva*/ true) {
                command();
            }

            if (/*la partita è finita*/ true) {
                endGame();
            }

        } catch (Exception e) {
            setChangedAndNotifyObservers(new LeaveMessage(name));
        }
    }

    public void roomLeader () {
        int roomSize;
        do {
            try {
                Scanner scanner = new Scanner(System.in);
                roomSize = scanner.nextInt();
                if (roomSize >=2 && roomSize <= 4) break;
                else System.out.println("Wrong value, it must be between 2 and 4, please retry.");
            } catch (InputMismatchException e) {
                System.out.println("Wrong value, it must be between 2 and 4, please retry.");
            }
        } while (true);
        setChangedAndNotifyObservers(new SetRoomSizeMessage(name, roomSize));
    }

    public void command() {
        try {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Use the command " + ANSI_GREEN + "withdraw" + ANSI_RESET + " if it's your turn, "
                                   + ANSI_GREEN + "message" + ANSI_RESET + " or "
                                   + ANSI_GREEN + "leave" + ANSI_RESET + ".");
                switch ((scanner.nextLine()).toLowerCase()) {
                    case ("leave") -> {
                        setChangedAndNotifyObservers(new LeaveMessage(name));
                        System.exit(0);
                    }
                    case ("message") ->
                            setChangedAndNotifyObservers(getMessage());
                    case ("withdraw") -> {
                        setChangedAndNotifyObservers(new WithdrawMessage(readCords(), name));
                        setChangedAndNotifyObservers(new OrderMessage(name, readIntList()));
                        setChangedAndNotifyObservers(new InsertMessage(name, readColumn()));
                    }
                }
            }
        } catch (Exception e) {
            setChangedAndNotifyObservers(new LeaveMessage(name));
        }
    }

    public void endGame() {
        System.out.print("The final rank is the following:\n");
        //TODO: while that prints the players in descendent order
        for (int i = 0; i == 4; i++) {
            if (i == 0) { System.out.print(ANSI_YELLOW + "1. " + ANSI_RESET); }
            else { System.out.print((i + 1) + ". "); }
            System.out.print("name");
        }
        System.out.println("The game has ended");
    }

    public void printDashboard() {
        Card[][] dashboard = this.model.getDashboard().dashboard;
        printMatrix(dashboard);
    }

    public void printMyShelf() {
        List<PlayerView> playerViewList = model.getPlayerViews();
        System.out.println("My Shelf");
        playerViewList.stream().filter(x -> x.getName().equals(name)).forEach(x -> {
            printMatrix(x.getShelf());
            x.getPrivateObjectivePattern().forEach(y -> printMatrix(y));
        });
    }

    public void printAllShelves() {
        List<PlayerView> playerViewList = model.getPlayerViews();
        playerViewList.stream().filter(x -> !x.getName().equals(name)).forEach(x -> {
            System.out.println(x.getName() +"'s Shelf " + " Points: " + x.getPoint().getValue());
            printMatrix(x.getShelf());
        });
    }

    public void printCommonObjectives() {
        List<CommonObjectiveView> objectiveViewList = model.getCommonObjectiveViews();
        objectiveViewList.forEach(x -> {
            System.out.println("Objective " + x.getID() + ": ");
            System.out.println("Points available: " + x.getMaxPoint().getValue());
        });
    }

    @Override
    public void update() {
        printDashboard();
        printCommonObjectives();
        printMyShelf();
        printAllShelves();
    }

    private void printMatrix(Card[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;

        System.out.print(" ");
        for (int i = 0; i < columns; i++) {
            System.out.print(" " + i + "");
        }
        System.out.println();
        for (int i = 0; i < rows; i++) {
            System.out.print(i);
            for (int j = 0; j < columns; j++) {
                System.out.print("|");
                if (matrix[i][j] == null) {
                    System.out.print(" ");
                }
                else {
                    printCard(matrix[i][j].getType());
                }
            }
            System.out.println("|");
        }
    }
    private void printMatrix(Card.Type[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;

        System.out.print(" ");
        for (int i = 0; i < columns; i++) {
            System.out.print(" " + i + "");
        }
        System.out.println();
        for (int i = 0; i < rows; i++) {
            System.out.print(i);
            for (int j = 0; j < columns; j++) {
                System.out.print("|");
                printCard(matrix[i][j]);
            }
            System.out.println("|");
        }
    }

    private List<Integer> readIntList() {
        List<Integer> result = new ArrayList<>();
        System.out.println("Write the card number in the order that you want");
        for (int i = 0; i < getCardsNum(); i++) {
            try {
                Scanner scanner = new Scanner(System.in);
                int x = scanner.nextInt();
                if (result.contains(x) || x < 1 || x > getCardsNum()) {
                    System.out.println("Wrong input, insert the numbers again.");
                    result.clear();;
                    i = 0;
                }
                result.add(x);
            } catch (InputMismatchException e) {
                System.out.println("Wrong input, insert the numbers again.");
                result.clear();
                i = 0;
            }
        }
        return result;
    }

    private List<PlanarCoordinate> readCords() {
        List<PlanarCoordinate> result = new ArrayList<>();
        System.out.println("Insert the number of cards that you want to withdraw.");
        do {
            try {
                Scanner scanner = new Scanner(System.in);
                setCardsNum(scanner.nextInt());
            } catch (InputMismatchException e) {
                System.out.println("Wrong input, insert the name again.");
                setCardsNum(-1);
            }
        } while (getCardsNum() < 0 || getCardsNum() > 3);
        System.out.println("Write the coordinates (row column) of the card that you want to withdraw.");
        for (int i = 0; i < getCardsNum(); i++) {
            try {
                Scanner scanner = new Scanner(System.in);
                int row = scanner.nextInt();
                int column = scanner.nextInt();
                if (row < 0 || column < 0 || row > model.getDashboard().dashboard.length || column > model.getDashboard().dashboard[0].length) {
                    System.out.println("Wrong card selected, insert all the cards again.");
                    result.clear();
                    i = 0;
                }
                result.add(new PlanarCoordinate(row, column));
            }
            catch (InputMismatchException e) {
                System.out.println("Wrong card selected, insert all the cards again.");
                result.clear();
                i = 0;
            }
        }
        return result;
    }

    private int readColumn() {
        System.out.println("Select the column where you want to insert the cards");
        int column;
        while (true) {
            try {
                column = new Scanner(System.in).nextInt();
                if (column < 0 || column > model.getDashboard().dashboard[0].length) System.out.println("Wrong column selected, insert all the cards again.");
                else break;
            } catch (InputMismatchException e) {
                System.out.println("Wrong card selected, insert all the cards again.");
            }
        }
        return column;
    }

    void printCard(Card.Type type) {
        if (type == null) {
            System.out.print(" ");
            return;
        }
        switch (type) {
            case PLANT -> System.out.print(ANSI_RED + "P" + ANSI_RESET);
            case CAT -> System.out.print(ANSI_GREEN + "C"+ ANSI_RESET);
            case TROPHY -> System.out.print(ANSI_CYAN + "T" + ANSI_RESET);
            case BOOK -> System.out.print(ANSI_WHITE + "B"+ ANSI_RESET);
            case FRAME -> System.out.print(ANSI_BLUE + "F" + ANSI_RESET);
            case GAME -> System.out.print(ANSI_YELLOW + "G" + ANSI_RESET);
        }
    }

    ChatMessage getMessage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write the username of the receiver and then press enter.");
        String receiver = (scanner.nextLine()).toLowerCase();
        System.out.println("Write the message that you want to send and then press enter.");
        String message = (scanner.nextLine()).toLowerCase();
        List<String> receivers = new ArrayList<>();
        receivers.add(receiver);
        return new ChatMessage(name, receivers, message);
    }

    @Override
    public void setRoomSize() {
        System.out.println("You are the first to join: set the room size (2 up to 4).");
    }
}