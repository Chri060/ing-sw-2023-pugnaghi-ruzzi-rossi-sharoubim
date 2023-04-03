package Controller;

import Exceptions.*;
import Model.Action;
import Model.Match;

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
	public void execute(Match model) throws BagEmptyException {
		//TODO: se siamo nella fase finale bisogna terminare la partita altrimenti si cambia giocatore
		if(model.needsRefill()) { model.refill(); }
		//TODO: obiettivo comune
		model.nextPlayer();
	}
}