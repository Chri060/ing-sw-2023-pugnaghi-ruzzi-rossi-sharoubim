package Controller;

import Exceptions.*;
import Model.Action;
import Model.Model;

import java.util.List;
import java.util.Objects;

public abstract class PlayerAction {
	private String currPlayer;
	Action action;


	/*
	* Used by the subclasses to initialize the name of the player which is trying to play
	*
	* @param player 	is the name of the player which is trying to play
	*/
	public PlayerAction(String player) {
		currPlayer = player;
	}

	/*
	* Checks all possible problem with the player, the phase and the queried action
	* If no check fails it performs the action using the method execute
	*
	* @param model 		is the class Match in the model linked to the current Controller
	* @param action 	is the current action
	*/
	public void validate(Model model) throws WrongActionException, NotYourTurnException {
		if (isGameRunning(model))
			//TODO partita finita
			isCorrectTurn(model);
	}

	/*
	* Checks if the game is still in progress, or it's ended
	*
	* @param model 		is the class Match in the model linked to the current Controller
	*
	* @throws exception when the game is ended
	*/
	public boolean isGameRunning(Model model) {
		return model.endMatch();
	}

	/*
	* Checks if the player is correct
	*
	* @param action 	is the current action
	* @param model 		is the class Match in the model linked to the current Controller
	*
	* @throws exception when the wrong player is trying to do an action
	*/
	public void isCorrectTurn(Model model) throws NotYourTurnException, WrongActionException {
		if (!Objects.equals(currPlayer, model.getCurrentPlayer())) {
			throw new NotYourTurnException(currPlayer);
		}
		if (getAction() != model.getCurrentAction()) {
			throw new WrongActionException(model.getCurrentAction());
		}
	}

	/*
	 * This method depend on how the current phase works
	 * When all is checked by the previous method it performs the action
	 *
	 * @param model 	is the class Match in the model linked to the current Controller
	 *
	 * @throws exception if it encounters problems in performing the action
	 */
	public abstract void execute(Model model) throws InvalidPickException, NotYourTurnException,
			CannotWithdrawCardException, NotEnoughSpaceInColumnException, BagEmptyException;

	/*
	* A getter for the player which is requesting to do the action
	*/
	public String getCurrPlayer() {
		return currPlayer;
	}

	/*
	 * A getter for the action which needs to be executed (used in subclasses)
	 */
	public Action getAction() {
		return action;
	}
}