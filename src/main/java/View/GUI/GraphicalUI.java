package View.GUI;

import Distributed.Messages.clientMessages.*;
import Model.ModelView;
import View.View;
import javafx.scene.control.*;

import java.util.*;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static util.AnsiColor.*;

public class GraphicalUI extends View {

    private Stage stage;


    public void start (Stage stage) throws IOException{
        try {
            setName();

            while (model.getState() != ModelView.State.STARTED) {
                model.waitEvent();
                if (model.getState() == ModelView.State.SETTINGSIZE) {
                    roomLeaderEvent();
                }
            }
            //System.out.println("Game Started");
            readCommand();
            if (/*la partita è finita*/ true) {
                endGame();
            }

        } catch (Exception e) {
            setChangedAndNotifyObservers(new LeaveMessage(model.getName()));
        }
    }


    @Override
    public void setName() {
        try {
            try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/FXML/Welcome.fxml"));
            Scene scene= new Scene(loader.load());
            Button btn = (Button) scene.lookup("ButtonJoin");
            TextField txt = (TextField ) scene.lookup("UsernameField");
            btn.setOnAction(event -> {
                String name=txt.getText();
                model.setName(name);
            });
            stage.setScene(scene);
            stage.setTitle("MY SHELFIE");
            stage.showAndWait();
            } catch (IOException e){e.printStackTrace();}
            do {
                new Thread(() -> setChangedAndNotifyObservers(new JoinMessage(model.getName()))).start();
                model.requestSent();
                setNameOutcome();
            } while (model.getState() != ModelView.State.INLOBBY && model.getState() != ModelView.State.SETTINGSIZE && model.getState() != ModelView.State.STARTED);
        } catch (NoSuchFieldError e) {}
    }

    @Override
    protected void readName() {
        //useless, maybe can be integrated in setName
    }


    public void setNameOutcome() {
        if (model.getState() == ModelView.State.WAITINGLEADER){
            Dialog<String> dialog=new Dialog<String>();
            dialog.setTitle("Lobby unavailable");
            ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("Waiting leader to set rooms size");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        }
        else if (model.getState() == ModelView.State.SELECTNAME) {
            Dialog<String> dialog=new Dialog<String>();
            dialog.setTitle("Name unavailable");
            ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("Name was not available, please try again");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        }
        else if (model.getState() == ModelView.State.NOTJOINABLE) {

            Dialog<String> dialog=new Dialog<String>();
            dialog.setTitle("Game already started");
            ButtonType yesType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
            ButtonType noType = new ButtonType("No", ButtonBar.ButtonData.NO);
            dialog.setContentText("The game already started\nClose [Yes/No]?");
            dialog.getDialogPane().getButtonTypes().add(noType);
            dialog.getDialogPane().getButtonTypes().add(yesType);
            Button button=(Button) dialog.getDialogPane().lookupButton(noType);
            button.setOnAction(actionEvent -> {System.exit(0);});
            dialog.showAndWait();
        }
        else if (model.getState() == ModelView.State.INLOBBY) {
        }
    }

    String roomSizeString;
    public void roomLeaderEvent() {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/SizePopup.fxml"));
            Scene scene= new Scene(loader.load());
            Button btn = (Button) scene.lookup("NumConfirm");
            TextField txt = (TextField ) scene.lookup("NumPlayer");
            btn.setOnAction(event -> {
                roomSizeString=txt.getText();

            });
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        roomLeader();
    }

    public void roomLeader() {
        int roomSize;
        do {
            try {
                roomSize=Integer.parseInt(roomSizeString);
                if (roomSize >=2 && roomSize <= 4){ break;}
                else
                {
                    Dialog<String> dialog=new Dialog<String>();
                    dialog.setTitle("Wrong Value");
                    ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                    dialog.setContentText("Wrong value, it must be between 2 and 4, please retry.");
                    dialog.getDialogPane().getButtonTypes().add(type);
                    dialog.showAndWait();
                }
            } catch (InputMismatchException e) {

                Dialog<String> dialog=new Dialog<String>();
                dialog.setTitle("Wrong Value");
                ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                dialog.setContentText("Wrong value, it must be between 2 and 4, please retry.");
                dialog.getDialogPane().getButtonTypes().add(type);
                dialog.showAndWait();
            }
        } while (true);
        int finalRoomSize = roomSize;
        new Thread(() -> setChangedAndNotifyObservers(new SetRoomSizeMessage(model.getName(), finalRoomSize))).start();
        model.requestSent();
    }


    public void showLobby() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Lobby.fxml"));
            Scene scene = new Scene(loader.load());
            LobbyController controller=loader.getController();
            List<String> players =model.getPlayerNames();
            int size=model.getTargetRoomSize();
            controller.addPlayersToLobby(players);
            controller.UpdateNumber(players,size);
            stage.setScene(scene);
            stage.show();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isYourTurn() {
        if (model.getCurrentPlayer().equals(model.getName())) {
            return true;
        }
        Dialog<String> dialog=new Dialog<String>();
        dialog.setTitle("Not your turn");
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.setContentText("This is not your turn, please wait yours");
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait(); //maybe just dialog.show() is enough
        return false;
    }


    public void readCommand() {}/*
        try {
            int drawnCards = 0;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GameLayout.fxml"));
            Scene scene = new Scene(loader.load());
            LobbyController controller=loader.getController();
            Button leavebtn= (Button)scene.lookup("LeaveButton");
            stage.setScene(scene);
            stage.show();

            leavebtn.setOnAction(actionEvent -> {
                setChangedAndNotifyObservers(new LeaveMessage(model.getName()));
                System.exit(0);
            });

            Button sendMessage=(Button) scene.lookup("ChatSend");
            sendMessage.setOnAction(actionEvent -> {
                setChangedAndNotifyObservers(getMessage());
            });


            while (model.getState() != ModelView.State.ENDED) {
                if (model.getState() != ModelView.State.PAUSED) {


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
/*
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
    }*/
    public void endGame() {
        System.out.print("The final rank is the following:\n");
        //TODO: while that prints the players in descendent order
        for (int i = 0; i == 4; i++) {
            if (i == 0) { System.out.print(ANSI_YELLOW + "1. " + ANSI_RESET); }
            else { System.out.print((i + 1) + ". "); }
            System.out.print("name");
        }
        System.out.println("The game has ended");
    }/*


*/
    @Override
    public void update() {

       // printDashboard();
       // printCommonObjectives();
       // printMyShelf();
       // printAllShelves();
    }
/*

*/
    public void showChatMessage(String sender, String message) {
        System.out.println("[" + sender + "]:" + message);
    }/*

    ChatMessage getMessage() {
        System.out.println("Write the username of the receiver and then press enter.");
        String receiver = (scanner.nextLine()).toLowerCase();
        System.out.println("Write the message that you want to send and then press enter.");
        String message = (scanner.nextLine()).toLowerCase();
        List<String> receivers = new ArrayList<>();
        receivers.add(receiver);
        return new ChatMessage(model.getName(), receivers, message);
    }
*/
}

