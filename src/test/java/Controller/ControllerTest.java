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

    /**
     * Constructs the controller with its model connected
     */
    @BeforeEach
    void setup() {
        Config.initialise(2);
        Model model = new Model();
        controller = new Controller(model);
    }

    @Test
    void general () {

        controller.join("playerOne");
        controller.join("playerTwo");
        controller.setRoomSize(1, "playerTwo");
        controller.leave("playerTwo");
        controller.join("playerTwo");

        List<String> player = new ArrayList<>();
        player.add("playerTwo");
        controller.chatMessage("playerOne", player, "hello world");

        List<PlanarCoordinate> coordinates = new ArrayList<>();
        controller.withdraw("playerOne",coordinates);
        List<Integer> order = new ArrayList<>();
        controller.changeOrderOfCards(order);
        controller.insert("playerOne", 0);

        controller.endgame();
    }
}