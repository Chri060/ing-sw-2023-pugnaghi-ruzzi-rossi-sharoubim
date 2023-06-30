package View;

import Distributed.Messages.clientMessages.*;
import Model.Model;
import Model.ModelView;
import Model.entities.Card;
import Model.entities.Point;
import Model.viewEntities.CommonObjectiveView;
import Model.viewEntities.PlayerView;
import util.PlanarCoordinate;

import java.util.*;

import static util.AnsiColor.*;

/**
 * Class that implements the textual user interface
 */
public class TextualUI extends View {

    /**
     * Requests the name and let the player insert the name.
     * If the name is valid it will be set in the Model
     */
    @Override
    protected void readName() {
        System.out.println("Insert your name");
        String name = scanner.nextLine();
        model.setName(name);
    }

    /**
     * Request the first player to join to set the room size
     */
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

    /**
     * Reads the input from the player and chooses the corresponding message
     */
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
                            if (model.getState() != ModelView.State.RUNNING) {break;}
                            if (isYourTurn() && isCurrentAction(Model.TurnStatus.DRAWING)) {
                                List<PlanarCoordinate> cords;
                                do {
                                    cords = readCords();
                                    if (model.getDashboard().canWithdraw(cords) && cords.size() <= model.getMyShelf().maxFreeSpace()) {
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
                            if (model.getState() != ModelView.State.RUNNING) {break;}
                            if (drawnCards == 1) {
                                System.out.println("You can't order a single card");
                                break;
                            }
                            if (isYourTurn() && isCurrentAction(Model.TurnStatus.INSERTING)) {
                                final List<Integer> cordsList = readIntList(drawnCards);
                                new Thread(() -> setChangedAndNotifyObservers(new OrderMessage(model.getName(), cordsList))).start();
                                //model.requestSent();
                                List<Card> newCards = new ArrayList<>();
                                cordsList.forEach(x -> newCards.add(model.getWithdrawnCards().get(x)));
                                model.setWithdrawnCards(newCards);
                                printWithdrawnCards();
                            }
                        }
                        case ("insert") -> {
                            if (model.getState() != ModelView.State.RUNNING) {break;}
                            if (isYourTurn() && isCurrentAction(Model.TurnStatus.INSERTING)) {
                                int column;
                                boolean ok;
                                do {
                                    column = readColumn();
                                    ok = model.getMyShelf().canInsert(model.getWithdrawnCards(), column);
                                    if (!ok) {
                                        System.out.println("There's not enough space in that column");
                                    }
                                } while(!ok);
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
            System.out.println(ANSI_RED + "Crash detected" + ANSI_RESET);
            System.exit(-10);
        }
    }

    /**
     * Checks if the player that is doing the action is the right one
     *
     * @return true if the player is correct, false otherwise
     */
    private boolean isYourTurn() {
        if (model.getCurrentPlayer().equals(model.getName())) {
            return true;
        }
        System.out.println("It's not your turn");
        return false;
    }

    /**
     * Checks that the current action is what the playr is supposed to do*/

    private boolean isCurrentAction(Model.TurnStatus action) {
        if (action != model.getTurnState()) {
            switch (action) {
                case DRAWING -> System.out.println("You already withdraw, order or insert");
                case INSERTING -> System.out.println("You need to withdraw first");
            }
            return false;
        }
        return true;
    }

    /**
     * When the game ends it prints the rankings and notifies the end
     */
    @Override
    public void endGame() {
        if (model.isWinByForfeit()) {
            System.out.println(ANSI_YELLOW + "You won by forfeit!"+ ANSI_RESET);
        }
        else {
            System.out.print("The final rank is the following:\n");
            int playerNum = model.getPlayerViews().size();
            for (int i = 0; i < playerNum; i++) {
                if (i == 0) {
                    System.out.print(ANSI_YELLOW + "1. " + ANSI_RESET);
                } else {
                    System.out.print((i + 1) + ". ");
                }
                PlayerView player = model.getPlayerViews().get(i);
                System.out.println(player.getName() + " Points " + player.getTotalPoint().getValue());
            }
            for (PlayerView player : model.getPlayerViews()) {
                System.out.println(". _ . _ . _ . _ . _ . _ . _ . _ . _ . _ . _ . _ . _ . _ . _ .");
                System.out.println(player.getName());
                for (Point point : player.getPoint()) {
                    System.out.println(point.getOrigin() + " : " + point.getValue());
                }
            }
        }
        System.out.println("Thanks for playing");
        System.exit(0);
    }

    /**
     * Prints the dashboard
     */
    public void printDashboard() {
        Card[][] dashboard = this.model.getDashboard().dashboard;
        printMatrix(dashboard);
    }

    /**
     * Prints the withdrawn cards
     */

    public void printWithdrawnCards() {
        System.out.print("Cards: ");
        model.getWithdrawnCards().forEach(x -> {
            printCard(x.getType());
            System.out.print(" ");
        });
        System.out.println();
    }


    /**
     * Prints the personal shelf
     */
    public void printMyShelf() {
        System.out.println("My Shelf, Points: " + model.getMyPoints().stream().mapToInt(x -> x.getValue()).sum());
        printMatrix(model.getMyShelf().shelf);
        System.out.println("My Objective");
        model.getMyPrivateObjectives().stream().forEach(o -> printMatrix(o));
    }

    /**
     * Prints all the shelves from other players
     */
    public void printAllShelves() {
        List<PlayerView> playerViewList = model.getPlayerViews();
        playerViewList.stream().filter(x -> !x.getName().equals(model.getName())).forEach(x -> {
            System.out.println(x.getName() +"'s Shelf " + " Points: " + x.getTotalPoint().getValue());
            printMatrix(x.getShelf());
        });
    }

    /**
     * Prints the CommonObjectives that are active in the actual match
     */
    public void printCommonObjectives() {
        List<CommonObjectiveView> objectiveViewList = model.getCommonObjectiveViews();
        objectiveViewList.forEach(x -> {
            System.out.println("Objective " + x.getID() + ": " + model.getDescriptionByID(x.getID()));
            System.out.println("Points available: " + x.getMaxPoint().getValue());
        });
    }

    /**
     * The server sets the State of the model after a request to set the name
     */
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

    /**
     * Prints all the players that are in the lobby when called
     */
    @Override
    public void showLobby() {
        System.out.println("Players in lobby:");
        model.getPlayerNames().stream().forEach(System.out:: println);
    }

    /**
     * Updates the player view with all the updated components
     */
    @Override
    public void update() {
        printDashboard();
        printCommonObjectives();
        printMyShelf();
        printAllShelves();
        if (model.getTurnState() == Model.TurnStatus.INSERTING) {
            printWithdrawnCards();
        }

        System.out.println("It's " + model.getCurrentPlayer() + "'s turn");
    }

    /**
     * Asks for the room leader
     */
    @Override
    public void roomLeaderEvent() {
        System.out.println("You are the first to join: set the room size (2 up to 4).");
        roomLeader();
    }

    /**
     * Prints a ChatMessage
     *
     * @param sender is the person who sent the message
     * @param message is the actual message
     */
    @Override
    public void showChatMessage(String sender, String message) {
        System.out.println("[" + sender + "]:" + message);
    }

    /**
     * Prints a generic array of Cards
     *
     * @param matrix is the bi-dimensional array to print
     */
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

    /**
     * Prints a generic array of Card.Type
     *
     * @param matrix is the bi-dimensional array to print
     */
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

    /**
     * Reds a list of integer
     *
     * @param cardsNum is the number of integers to read
     *
     * @return the list of integer that has been read
     */
    private List<Integer> readIntList(int cardsNum) {
        List<Integer> result = new ArrayList<>();
        System.out.println("Write the card number in the order that you want 1 -> " + cardsNum);
        for (int i = 0; i < cardsNum; i++) {
            try {
                int x = scanner.nextInt();
                if (result.contains(x - 1) || x < 1 || x > cardsNum) {
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

    /**
     * Reads the integers in couple and creates a PlanarCoordinate for each couple
     *
     * @return a list of PlanarCoordinate
     */
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

    /**
     * Checks if the list of Card can be inserted in a given column
     *
     * @return the column where to insert the Cards
     */
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

    /**
     * Print a single Card
     *
     * @param type is the Type of the Card
     */
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

    /**
     * Reads the message from the user interface
     *
     * @return a ChatMessage
     */
    ChatMessage getMessage() {
        System.out.println("Write the username of the receiver and then press enter. (Leave empty for a broadcast message)");
        String receiver = (scanner.nextLine());
        if (!model.getPlayerViews().stream().map(x -> x.getName()).anyMatch(x -> x.equals(receiver)) && !"".equals(receiver)) {
            System.out.println("No players found with this name, no one will receive this message");
        }
        if (("".equals(receiver))) {
            System.out.println("This message will be received by everyone");
        }
        System.out.println("Write the message that you want to send and then press enter.");
        String message = (scanner.nextLine());
        List<String> receivers = new ArrayList<>();
        receivers.add(receiver);
        return new ChatMessage(model.getName(), receivers, message);
    }


    /**
     * Prints on the output the given string
     * @param s is the string to print
     */
    @Override
    public void print(String s) {
        System.out.println(s);
    }
}