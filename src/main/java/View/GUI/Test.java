package View.GUI;

import Distributed.ClientImpl;
import Distributed.ClientImplGUI;
import Distributed.Messages.clientMessages.JoinMessage;
import Distributed.Messages.clientMessages.LeaveMessage;
import Distributed.Messages.clientMessages.SetRoomSizeMessage;
import Distributed.Server;
import Distributed.Socket.ServerStub;
import Model.ModelView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.InputMismatchException;

public abstract class Test extends Application {

    TestView testView;

    ClientImplGUI clientImplGUI;

    Parameters parameters;

    Thread receiver;
    Thread view;

    @Override
    public void init() throws Exception {
        super.init();

        parameters = getParameters();

        testView = new TestView();
        clientImplGUI = new ClientImplGUI(testView);

        try {
            ServerStub serverStub = new ServerStub(parameters.getRaw().get(0), 55555);
            receiver = new Thread(() -> serverStub.receive(clientImplGUI));
            clientImplGUI.init(serverStub);
            view = new Thread(() -> clientImplGUI.run());
        } catch (RemoteException e) {
            throw new RemoteException("Server Socket KO");
        }
    }

    Stage stage;

    @Override
    public void start(Stage stage) throws RemoteException {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Welcome.fxml"));

            Scene scene = new Scene(loader.load());

            Button btn = (Button) scene.lookup("#ButtonJoin");

            TextField txt = (TextField) scene.lookup("#UsernameField");

            btn.setOnAction(event -> {
                String name = txt.getText();
                testView.setModelName(name);
            });

            stage.setScene(scene);
            stage.setTitle("MY SHELFIE");
            stage.show();

           // try {

                Platform.runLater(view);
                Platform.runLater(receiver);
               // receiver.start();
                //view.start();
                //receiver.join();
                //view.join();
           // } catch (InterruptedException e) {
           // }

        } catch (IOException e) {
            e.printStackTrace();
        }
        /*try {

            while (testView.getModelState() != ModelView.State.STARTED) {
                testView.waitEvent();
                if (testView.getModelState() == ModelView.State.SETTINGSIZE) {
                    roomLeaderEvent();
                }
            }
            //System.out.println("Game Started");
            //readCommand();
            if (*//*la partita è finita*//* true) {
               // endGame();
            }

        } catch (Exception e) {
            testView.setChangedAndNotifyObservers(new LeaveMessage(testView.getModelName()));
        }*/
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
        new Thread(() -> testView.setChangedAndNotifyObservers(new SetRoomSizeMessage(testView.getModelName(), finalRoomSize))).start();
        testView.requestModelSent();
    }

    protected void readViewName() {
        testView.readName();
    }

    public void setViewNameOutcome() {
        testView.setNameOutcome();
    }

    public void showViewLobby() {
        testView.showLobby();
    }

    public void roomViewLeaderEvent() {
        testView.roomLeaderEvent();
    }

    public void readViewCommand() {
        testView.readCommand();
    }

    public void endViewGame() {
        testView.endGame();
    }

    public void updateView() {
        testView.update();
    }

    public void showViewChatMessage(String sender, String message) {
        testView.showChatMessage(sender, message);
    }


}
