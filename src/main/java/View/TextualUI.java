package View;

import Distributed.Messages.clientMessages.*;
import Model.ModelView;
import Model.entities.Card;
import Model.viewEntities.CommonObjectiveView;
import Model.viewEntities.PlayerView;
import util.PlanarCoordinate;

import java.util.*;

import static util.AnsiColor.*;

public class TextualUI extends View {


    @Override
    protected void readName() {
        System.out.println("Insert your name");
        String name = scanner.nextLine();
        model.setName(name);
    }

    public void roomLeader() {
        int roomSize;
        do {
            try {
                roomSize = scanner.nextInt();
                if (roomSize >=2 && roomSize <= 4) break;
                else System.out.println("Wrong value, it must be between 2 and 4, please retry.");
            } catch (InputMismatchException e) {
                System.out.println("Wrong value, it must be between 2 and 4, please retry.");
            }
        } while (true);
        int finalRoomSize = roomSize;
        new Thread(() -> setChangedAndNotifyObservers(new SetRoomSizeMessage(model.getName(), finalRoomSize))).start();
        model.requestSent();
    }

    @Override
    public void readCommand() {
        try {
            System.out.println("Use the command " + ANSI_GREEN + "withdraw" + ANSI_RESET + " if it's your turn, "
                    + ANSI_BLUE + "message" + ANSI_RESET + " or "
                    + ANSI_RED + "leave" + ANSI_RESET + ".");
            int drawnCards = 0;
            while (model.getState() != ModelView.State.ENDED) {
                if (model.getState() != ModelView.State.PAUSED) {
                    switch ((scanner.nextLine()).toLowerCase()) {
                        case ("leave") -> {
                            setChangedAndNotifyObservers(new LeaveMessage(model.getName()));
                            System.exit(0);
                        }
                        case ("message") -> setChangedAndNotifyObservers(getMessage());
                        case ("withdraw") -> {
                            if (isYourTurn()) {
                                List<PlanarCoordinate> cords;
                                do {
                                    cords = readCords();
                                    if (model.getDashboard().canWithdraw(cords)) {
                                        break;
                                    }
                                    System.out.println("you can't withdraw those cards");
                                } while (true);
                                final List<PlanarCoordinate> coordinates = cords;
                                drawnCards = cords.size();
                                new Thread(() -> setChangedAndNotifyObservers(new WithdrawMessage(coordinates, model.getName()))).start();
                                //model.requestSent();
                            }
                        }
                        case ("order") -> {
                            if (isYourTurn()) {
                                final List<Integer> cordsList = readIntList(drawnCards);
                                new Thread(() -> setChangedAndNotifyObservers(new OrderMessage(model.getName(), cordsList))).start();
                                //model.requestSent();
                            }
                        }
                        case ("insert") -> {
                            if (isYourTurn()) {
                                final int column = readColumn();
                                setChangedAndNotifyObservers(new InsertMessage(model.getName(), column));
                                //model.requestSent();
                            }
                        }
                    }
                }
                else {
                    System.out.println("Game is paused");
                    model.waitEvent();
                }
            }
        } catch (Exception e) {
            setChangedAndNotifyObservers(new LeaveMessage(model.getName()));
        }
    }

    private boolean isYourTurn() {
        if (model.getCurrentPlayer().equals(model.getName())) {
            return true;
        }
        System.out.println("It's not your turn");
        return false;
    }

    @Override
    public void endGame() {
        System.out.print("The final rank is the following:\n");
        //TODO: while that prints the players in descendent order
        for (int i = 0; i < 4; i++) {
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
        System.out.println("My Shelf Points " + model.getMyPoints().stream().mapToInt(x -> x.getValue()).sum());
        printMatrix(model.getMyShelf().shelf);
        System.out.println("My Objective");
        model.getMyPrivateObjectives().stream().forEach(o -> printMatrix(o));
    }

    public void printAllShelves() {
        List<PlayerView> playerViewList = model.getPlayerViews();
        playerViewList.stream().filter(x -> !x.getName().equals(model.getName())).forEach(x -> {
            System.out.println(x.getName() +"'s Shelf " + " Points: " + x.getTotalPoint().getValue());
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
    public void setNameOutcome() {
        if (model.getState() == ModelView.State.WAITINGLEADER){
            System.out.println("Waiting leader to set rooms size");
        }
        else if (model.getState() == ModelView.State.SELECTNAME) {
            System.out.println("Name not available");
        }
        else if (model.getState() == ModelView.State.NOTJOINABLE) {
            System.out.println("The game already started\nClose [y/n]?");
            while (true) {
                switch (scanner.nextLine().toLowerCase()) {
                    case "y" : System.exit(0);
                    case "n" : return;
                }
            }
        }
        else if (model.getState() == ModelView.State.INLOBBY) {
        }
    }

    @Override
    public void showLobby() {
        System.out.println("Players in lobby:");
        model.getPlayerNames().stream().forEach(System.out:: println);
    }

    @Override
    public void update() {
        printDashboard();
        printCommonObjectives();
        printMyShelf();
        printAllShelves();
        System.out.println("It's " + model.getCurrentPlayer() + "'s turn");
    }

    @Override
    public void roomLeaderEvent() {
        System.out.println("You are the first to join: set the room size (2 up to 4).");
        roomLeader();
    }

    @Override
    public void showChatMessage(String sender, String message) {
        System.out.println("[" + sender + "]:" + message);
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

    private List<Integer> readIntList(int cardsNum) {
        List<Integer> result = new ArrayList<>();
        System.out.println("Write the card number in the order that you want 1 -> " + cardsNum);
        for (int i = 0; i < cardsNum; i++) {
            try {
                int x = scanner.nextInt();
                if (result.contains(x) || x < 1 || x > cardsNum) {
                    System.out.println("Wrong input, insert the numbers again.");
                    result.clear();;
                    i = 0;
                }
                else {
                    result.add(x - 1);
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong input, insert the numbers again.");
                result.clear();
                i = 0;
            }
        }
        return result;
    }

    private List<PlanarCoordinate> readCords() {
        List<PlanarCoordinate> result;
        do {
            System.out.println("Write the coordinates (row column) of the card that you want to withdraw.");
            String input = scanner.nextLine();
            List<String> inputList = Arrays.stream(input.split("\\s")).toList();
            List<Integer> coordsList;
            try {
                coordsList = inputList.stream().map(Integer::parseInt).toList();
                result = new ArrayList<>();
                if (coordsList.size() % 2 != 0) {
                    throw new NumberFormatException();
                }
                for (int i = 0; i < coordsList.size() / 2; i++) {
                    result.add(new PlanarCoordinate(coordsList.get(2 * i), coordsList.get(2 * i + 1)));
                }
            } catch (NumberFormatException e) {
                System.out.println("Coordinates are not valid");
                result = null;
            }
        } while (result == null);
        return result;
    }

    private int readColumn() {
        System.out.println("Select the column where you want to insert the cards");
        int column;
        while (true) {
            try {
                column = scanner.nextInt();
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
        System.out.println("Write the username of the receiver and then press enter.");
        String receiver = (scanner.nextLine()).toLowerCase();
        System.out.println("Write the message that you want to send and then press enter.");
        String message = (scanner.nextLine()).toLowerCase();
        List<String> receivers = new ArrayList<>();
        receivers.add(receiver);
        return new ChatMessage(model.getName(), receivers, message);
    }


}