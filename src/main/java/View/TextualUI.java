package View;

import Distributed.Messages.clientMessages.*;
import Model.entities.Card;
import Model.viewEntities.CommonObjectiveView;
import Model.viewEntities.PlayerView;
import util.PlanarCoordinate;

import java.util.ArrayList;
import java.util.List;
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
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String choice = scanner.nextLine();
            switch (choice) {
                case ("size") -> {
                    System.out.print("Set room size: ");
                    int roomSize = scanner.nextInt();
                    setChangedAndNotifyObservers(new SetRoomSizeMessage(name, roomSize));
                }
                case ("w") -> setChangedAndNotifyObservers(new WithdrawMessage(readCords() ,name));
                case ("i") -> setChangedAndNotifyObservers(new InsertMessage(name, readColumn()));
                case ("o") -> setChangedAndNotifyObservers(new OrderMessage(name, readIntList()));
                case ("l") -> setChangedAndNotifyObservers(new LeaveMessage(name));
                case ("j") -> setChangedAndNotifyObservers(new JoinMessage(name));
                case ("n") -> name = (scanner.nextLine());
            }
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

}
