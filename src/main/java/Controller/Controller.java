package Controller;

import Exceptions.*;
import Model.Match;
import java.util.List;
import java.util.Objects;

public class Controller {
	private Match model;
	private PlayerAction playerAction;



	/*
	* Creates a new Controller object linked to the model Match
	* Used for testing
	*
	* @param model 		is an instance of the class Model.ModelWrapper
	*/
	public Controller(Match model) {
		Objects.requireNonNull(model);
		this.model = model;
	}

	/*
	* This checks if the inserted values for the players are correct
	* If it is true, it creates the Controller and link it to the correct Match class
	*
	* @param players 	is the list of the names of people who are playing
	* @param playerNum 	is the actual number of player that are needed to start the game
	* @param model 		is the model linked to this Controller
	*/
	public static Controller createGame(List<String> players, int playerNum, Match model) {
		Objects.requireNonNull(players);
		if (playerNum == players.size()) {
			return new Controller(model);
		}
		else {
			//TODO: RuntimeException
			throw new RuntimeException("The number of players in the list is different from the number inserted by lobby leader.");
		}
	}

	/*
	* This method check if the requested action is correct and executes it
	* If the action is invalid it gives exceptions based on the problem found
	*
	* @param action 	is the PlayerAction that someone is trying
	*/
	public void doAction(PlayerAction action) throws PlayerNotFoundException, InvalidPickException, NotYourTurnException, CannotWithdrawCardException, ColumFullException, BagEmptyException {
		action.validate(model, action);
		action.execute(model);
		model.nextAction();
	}
}