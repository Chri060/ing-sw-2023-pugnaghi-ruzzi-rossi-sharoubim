package View.GUI;
import Model.ModelView;
import Model.ModelViewData;
import View.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class LobbyController {

    @FXML
    Label PlayerNeeded;

    @FXML
    Label placeholder_user1;

    @FXML
    Label placeholder_user2;

    @FXML
    Label placeholder_user3;

    @FXML
    Label placeholder_user4;

    @FXML
    List<Label>placeholder_list=new ArrayList<>();

    @FXML
    public void createList(){
        placeholder_list.add(placeholder_user1);
        placeholder_list.add(placeholder_user2);
        placeholder_list.add(placeholder_user3);
        placeholder_list.add(placeholder_user4);
    }

    @FXML
    public void addPlayersToLobby(List<String>stringList){
        createList();
        for (int i=0; i<stringList.size(); i++){
            placeholder_list.get(i).setText(stringList.get(i));
        }

    }

    public void UpdateNumber(List<String>stringList, int roomSize){

        Integer result=(roomSize- stringList.size());
        PlayerNeeded.setText(result.toString());

    }

}
