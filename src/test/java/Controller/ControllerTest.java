package Controller;

import Model.Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Config;
import util.PlanarCoordinate;

import java.util.ArrayList;
import java.util.List;


class ControllerTest {

    Controller controller;

    Model model;

    /**
     * Constructs the controller with its model connected
     */
    @BeforeEach
    void setup() {
        Config.initialise(2);
        model = new Model();
        controller = new Controller(model);
    }

    @Test
    void general () {

        controller.join("playerOne");
        controller.join("playerTwo");
        controller.join(null);
        controller.leave(null);
        controller.setRoomSize(5,"playerOne");
        controller.setRoomSize(1, "playerTwo");
        controller.setRoomSize(2,"playerOne");
        controller.leave("playerTwo");
        controller.join("playerTwo");
        model.setGameStatus(Model.GameStatus.RUNNING);
        controller.join("playerThree");
        controller.leave("playerThree");

        List<String> player = new ArrayList<>();
        player.add("playerTwo");
        controller.chatMessage("playerOne", player, "hello world");
        controller.chatMessage(null, player, "hello world");
        controller.chatMessage("playerOne", null, "hello world");
        controller.chatMessage("playerOne", player, null);

        List<PlanarCoordinate> coordinates = new ArrayList<>();
        model.setGameStatus(Model.GameStatus.PAUSED);
        controller.withdraw("playerOne",coordinates);
        model.setGameStatus(Model.GameStatus.RUNNING);
        controller.withdraw("playerOne",coordinates);
        controller.withdraw("playerTwo",coordinates);

        PlanarCoordinate coordPlan=new PlanarCoordinate(1,3);
        coordinates.add(0,coordPlan);
        controller.withdraw("playerOne",coordinates);

        List<Integer> order = new ArrayList<>();
        controller.changeOrderOfCards(order);
        controller.insert("playerOne", 0);

        model.setTurnStatus(Model.TurnStatus.INSERTING);
        controller.insert("playerTwo",1);
        controller.insert("playerOne",1);
        
        controller.endgame();
    }
}