package Controller;

import Exceptions.*;
import Model.Action;
import Model.Model;

import java.util.List;

public class EndOfAction extends PlayerAction {


	/*
	 * Creates a new PlayerAction for the ENDPHASE phase
	 *
	 * @param player 		is the name of the player that is trying to do the action
	 */
	public EndOfAction(String player) {
		super(player);
		this.action = Action.ENDPHASE;
	}

	/*
	 * Check if the action is possible and eventually executes it
	 *
	 * @param model 			is the model connected to this controller
	 *
	 * @throws exceptions based on the problem found
	 */


	@Override
	public void execute(Model model) {
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
			objectivesPoints = null;
		}
		//TODO: obiettivo comune
		model.nextPlayer();
	}
}