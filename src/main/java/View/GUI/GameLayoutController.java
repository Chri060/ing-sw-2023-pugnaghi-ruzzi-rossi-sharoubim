package View.GUI;

import Model.entities.Card;
import Model.viewEntities.CommonObjectiveView;
import Model.viewEntities.PlayerView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;


import java.util.List;

public class GameLayoutController {


    @FXML
    GridPane DashboardGrid;

    @FXML
    GridPane PlayerShelfGrid;

    @FXML
    GridPane OpponentShelfGrid;

    @FXML
    GridPane PrivateGoalGrid;

    @FXML
    Button NextOpShelf;

    @FXML
    Label OpponentName;

    @FXML
    Label CommonGoal1;

    @FXML
    Label CommonGoal2;

    @FXML
    Label PointsCommonGoal1;

    @FXML
    Label PointsCommonGoal2;

    @FXML
    ImageView CommonImage1;

    @FXML
    ImageView CommonImage2;

    @FXML
    ImageView FirstToEndDashboard;

    @FXML
    ImageView PlayerFirstToEnd;

    @FXML
    ImageView ChairPlaceholder;

    @FXML
    TextField ChatText;

    @FXML
    TextField ChatUser;

    @FXML
    Button ChatSend;

    @FXML
    private ImageView getPrivateGoal(Card.Type card){
        switch (card)
        {
            case CAT ->{return new ImageView("/Images/catPrivate.png");}
            case PLANT ->{return new ImageView("/Images/plantPrivate.png");}
            case TROPHY ->{return new ImageView("/Images/trophyPrivate.png");}
            case FRAME ->{return new ImageView("/Images/framePrivate.png");}
            case GAME ->{return new ImageView("/Images/gamePrivate.png");}
            case BOOK ->{return new ImageView("/Images/bookPrivate.png");}
            default ->{return null;}
        }

    }

    @FXML
    private void printPrivateGoal(Card.Type[][] matrix){

        PrivateGoalGrid.getChildren().clear();
        int rows = matrix.length;
        int columns = matrix[0].length;


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j]!=null)
                {
                    ImageView temp=getPrivateGoal(matrix[i][j]);
                    if (temp!=null)
                    {
                        //may need a remove before add
                        PrivateGoalGrid.add(temp,j,i);
                    }
                }
            }
        }
    }

    @FXML
    private void removeFirstToEndDashboard(){
        FirstToEndDashboard=null;
    }


    @FXML
    private void addPoints(){

    }


    @FXML
    private void addChair(){
        ChairPlaceholder= new ImageView("Images/chair.png");
    }


    @FXML
    private void printDashboard(Card[][] matrix)
    {
        DashboardGrid.getChildren().clear();
        int rows = matrix.length;
        int columns = matrix[0].length;


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++)
            {
                if (matrix[i][j]!=null)
                {
                    ImageView temp=getImageCard(matrix[i][j].getType(), matrix[i][j].getId());

                    if (temp!=null)
                    {
                        //may need a remove before add
                        DashboardGrid.add(temp,j,i);
                    }
                }
            }
        }
    }

    @FXML //function used during initialization
    public void printCommonObjectives(List <CommonObjectiveView> objectiveView) {

       CommonImage1=getImageCommon(objectiveView.get(0).getID());
       CommonImage2=getImageCommon(objectiveView.get(1).getID());
       CommonGoal1.setText(maxPointsCommon(objectiveView.get(0)));
       CommonGoal2.setText(maxPointsCommon(objectiveView.get(1)));
    }

    @FXML
    public void printCommonPoints(List <CommonObjectiveView> objectiveView) {

        CommonGoal1.setText(maxPointsCommon(objectiveView.get(0)));
        CommonGoal2.setText(maxPointsCommon(objectiveView.get(1)));
    }


    @FXML
    private String maxPointsCommon(CommonObjectiveView objectiveView) {
        int PointsAvailable = objectiveView.getMaxPoint().getValue();
        return Integer.toString(PointsAvailable);
    }

    @FXML
    private ImageView getImageCommon(int id){
        switch (id)
        {
            case 1->
            {
                return new ImageView("Images/1.jpg");
            }
            case 2->
            {
                return new ImageView("Images/2.jpg");
            }
            case 3->
            {
                return new ImageView("Images/3.jpg");
            }
            case 4->
            {
                return new ImageView("Images/4.jpg");
            }
            case 5->
            {
                return new ImageView("Images/5.jpg");
            }
            case 6->
            {
                return new ImageView("Images/6.jpg");
            }
            case 7->
            {
                return new ImageView("Images/7.jpg");
            }
            case 8->
            {
                return new ImageView("Images/8.jpg");
            }
            case 9->
            {
                return new ImageView("Images/9.jpg");
            }
            case 10->
            {
                return new ImageView("Images/10.jpg");
            }
            case 11->
            {
                return new ImageView("Images/11.jpg");
            }
            case 12->
            {
                return new ImageView("Images/12.jpg");
            }
            default -> {return null;}
        }

    }

    @FXML
    private void printOpShelf(Card[][] matrix) {

        OpponentShelfGrid.getChildren().clear();
        int rows = matrix.length;
        int columns = matrix[0].length;


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                if (matrix[i][j]!=null)
                {
                    ImageView temp = getImageCard(matrix[i][j].getType(), matrix[i][j].getId());
                    if (temp != null) {
                        //may need a remove before add
                        OpponentShelfGrid.add(temp, j, i);
                    }
                }
            }
        }
    }

    @FXML
    private void printShelfPlayer(Card[][] matrix) {

        PlayerShelfGrid.getChildren().clear();
        int rows = matrix.length;
        int columns = matrix[0].length;


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                if (matrix[i][j]!=null)
                {
                    ImageView temp = getImageCard(matrix[i][j].getType(), matrix[i][j].getId());
                    if (temp != null) {
                        //may need a remove before add
                        PlayerShelfGrid.add(temp, j, i);
                    }
                }
            }
        }
    }


    @FXML
    private ImageView getImageCard(Card.Type type, int id) {
        if(type==null){
            return null;
        }
        else{

            ImageView image=null;

            switch (type){
                case CAT ->
                {
                    switch (id)
                    {
                        case 1 -> image = new ImageView("Images/Cat1.1.png");
                        case 2 -> image = new ImageView("Images/Cat1.2.png");
                        case 3 -> image = new ImageView("Images/Cat1.3.png");
                        default -> {return null;}

                    }
                    return image;
                }
                case PLANT ->
                {
                    switch (id)
                    {
                        case 1 -> image=new ImageView("Images/Plant1.1.png");
                        case 2 -> image=new ImageView("Images/Plant1.2.png");
                        case 3 -> image=new ImageView("Images/Plant1.3.png");
                        default ->{return null;}

                    }
                    return image;
                }
                case TROPHY ->
                {
                    switch (id)
                    {
                        case 1 -> image=new ImageView("Images/Trophy1.1.png");
                        case 2 -> image=new ImageView("Images/Trophy1.2.png");
                        case 3 -> image=new ImageView("Images/Trophy1.3.png");
                        default ->{return null;}
                    }
                    return image;
                }
                case FRAME ->
                {
                    switch (id)
                    {
                        case 1 -> image=new ImageView("Images/Frame1.1.png");
                        case 2 -> image=new ImageView("Images/Frame1.2.png");
                        case 3 -> image=new ImageView("Images/Frame1.3.png");
                        default ->{return null;}
                    }
                    return image;
                }
                case GAME ->
                {
                    switch (id)
                    {
                        case 1 -> image=new ImageView("Images/Game1.1.png");
                        case 2 -> image=new ImageView("Images/Game1.2.png");
                        case 3 -> image=new ImageView("Images/Game1.3.png");
                        default ->{return null;}
                    }
                    return image;
                }
                case BOOK ->
                {
                    switch (id)
                    {
                        case 1 -> image=new ImageView("Images/Book1.1.png");
                        case 2 -> image=new ImageView("Images/Book1.2.png");
                        case 3 -> image=new ImageView("Images/Book1.3.png");
                        default ->{return null;}
                    }
                    return image;
                }
            }
            return null;
        }
    }



}
