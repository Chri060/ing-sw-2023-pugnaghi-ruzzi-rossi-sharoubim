package View;

import Distributed.Messages.clientMessages.*;
import Model.entities.Card;
import Model.viewEntities.CommonObjectiveView;
import Model.viewEntities.PlayerView;
import util.PlanarCoordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class TextualUI extends View {


    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\033[1;91m";
    public static final String ANSI_GREEN = "\033[1;92m";
    public static final String ANSI_YELLOW = "\033[1;93m";
    public static final String ANSI_BLUE = "\033[1;94m";
    public static final String ANSI_CYAN = "\033[1;96m";
    public static final String ANSI_WHITE = "\033[1;97m";


    @Override
    public void printDashboard() {
        Card[][] dashboard = this.model.getDashboard();
        printMatrix(dashboard);
    }

    @Override
    public void printMyShelf() {
        List<PlayerView> playerViewList = model.getPlayerViews();
        System.out.println("My Shelf");
        playerViewList.stream().filter(x -> x.getName().equals(name)).forEach(x -> {
            printMatrix(x.getShelf());
            x.getPrivateObjectivePattern().forEach(y -> printMatrix(y));
        });
    }

    @Override
    public void printAllShelfs() {
        List<PlayerView> playerViewList = model.getPlayerViews();
        playerViewList.stream().filter(x -> !x.getName().equals(name)).forEach(x -> {
            System.out.println(x.getName() +"'s Shelf " + " Points: " + x.getPoint().getValue());
            printMatrix(x.getShelf());
        });
    }

    @Override
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
        printAllShelfs();
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

    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(System.in);

            do {
                System.out.print("Set your username:\n");
                name = scanner.nextLine();
            } while (false /*TODO: nome già presente*/);


            String input;
            do {
                System.out.print("The username selected is available, write join to enter the lobby\n");
                input = scanner.nextLine();
            } while (!Objects.equals(input, "join"));
            setChangedAndNotifyObservers(new JoinMessage(name));




            while(true) {

            }
            /*

            while (true) {
                System.out.print("It's your turn, use the command 'withdraw':\n");
                input = scanner.nextLine();
                switch (input) {
                    case ("leave") -> setChangedAndNotifyObservers(new LeaveMessage(name));
                    case ("message") -> setChangedAndNotifyObservers(getMessage());
                    case ("withdraw") -> {
                        setChangedAndNotifyObservers(new WithdrawMessage(readCords(), name));
                        System.out.print("Select the order of the cards with the command 'order':\n");
                        setChangedAndNotifyObservers(new OrderMessage(name, readIntList()));
                        System.out.print("Select the column with the command 'column':\n");
                        setChangedAndNotifyObservers(new InsertMessage(name, readColumn()));
                    }
                }
            }
            */
        } catch (Exception e) {
            setChangedAndNotifyObservers(new LeaveMessage(name));
        }

    }

    private List<Integer> readIntList() {
        List<Integer> result = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many cards did you withdraw?");
        int cardsNum = scanner.nextInt();
        System.out.println("Set the new order");
        for (int i = 0; i < cardsNum; i++) {
            int x = scanner.nextInt();
            result.add(x);
        }
        return result;
    }

    private List<PlanarCoordinate> readCords() {
        List<PlanarCoordinate> result = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many cards do you want to withdraw?");
        int cardsNum = scanner.nextInt();
        for (int i = 0; i < cardsNum; i++) {
            int row = scanner.nextInt();
            int column = scanner.nextInt();
            result.add(new PlanarCoordinate(row, column));
        }
        return result;
    }

    private int readColumn() {
        System.out.println("Select a column");
        int column = new Scanner(System.in).nextInt();
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
        System.out.println("Message:");
        String message = scanner.nextLine();
        System.out.println("Receiver:");
        String receiver = scanner.nextLine();
        List<String> receivers = new ArrayList<>();
        receivers.add(receiver);
        return new ChatMessage(name, receivers, message);
    }

    @Override
    public void setRoomSize() {
        String input;
        Scanner scanner= new Scanner(System.in);
        System.out.print("Use the command size to set the room size:\n");
        input = scanner.nextLine();
        while (!Objects.equals(input, "size")) {
            if (Objects.equals(input, "leave")) {
                setChangedAndNotifyObservers(new LeaveMessage(name));
            }
            input = scanner.nextLine();
        }
        int roomSize;
        do {
            System.out.print("Insert the size of the room:\n");
            roomSize = scanner.nextInt();
        } while (roomSize < 2);
        setChangedAndNotifyObservers(new SetRoomSizeMessage(name, roomSize));
    }
}