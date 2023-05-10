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
                    switch (matrix[i][j].getType()) {
                        case PLANT -> System.out.print("P");
                        case CAT -> System.out.print("C");
                        case TROPHY -> System.out.print("T");
                        case BOOK -> System.out.print("B");
                        case FRAME -> System.out.print("F");
                        case GAME -> System.out.print("G");
                    }
                }
            }
            System.out.println("|");
        }
    }    private void printMatrix(Card.Type[][] matrix) {
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
                    switch (matrix[i][j]) {
                        case PLANT -> System.out.print("P");
                        case CAT -> System.out.print("C");
                        case TROPHY -> System.out.print("T");
                        case BOOK -> System.out.print("B");
                        case FRAME -> System.out.print("F");
                        case GAME -> System.out.print("G");
                    }
                }
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

}
