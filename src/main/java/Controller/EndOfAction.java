package Controller;

import Exceptions.*;
import Model.Action;
import Model.CommonObjective;
import Model.Match;

import java.util.List;

public class EndOfAction extends PlayerAction {


	/*
	 * Creates a new PlayerAction for the ENDPHASE phase
	 *
	 * @param player 		is the name of the player that is trying to do the action
	 */
	public EndOfAction(String player) {
		super(player);
		setAction(Action.ENDPHASE);
	}

	/*
	 * Check if the action is possible and eventually executes it
	 *
	 * @param model 			is the model connected to this controller
	 *
	 * @throws exceptions based on the problem found
	 */


	@Override
	public void execute(Match model) {
		List<Integer> objectivesPoints;
		//TODO: se siamo nella fase finale bisogna terminare la partita altrimenti si cambia giocatore

		try {
			model.refill();
		} catch (BagEmptyException e) {}

		model.isLastTurn();

		try {
			objectivesPoints = model.getCommonObjectivesPoints(getCurrPlayer());
		}
		catch (PlayerNotFoundException e) {
			throw new RuntimeException("Player " + getCurrPlayer() + " not found while checking for its common points");
		}
		//TODO: obiettivo comune
		model.nextPlayer();
	}
}