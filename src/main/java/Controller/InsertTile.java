package Controller;

import Exceptions.*;
import Model.Action;
import Model.Cards;
import Model.Model;
import java.util.List;

public class InsertTile extends PlayerAction {
	private int column;
	private List<Cards> cards;


	/*
	 * Creates a new PlayerAction for the INSERT phase
	 *
	 * @param col			is the column selected by the player
	 * @param card			contains the ordered list of cards
	 * @param player 		is the name of the player that is trying to do the action
	 */
	public InsertTile(int col, List<Cards> card, String player) {
		super(player);
		column = col;
		cards = card;
		this.action = Action.INSERT;
	}

	/*
	 * Check if the action is possible and eventually executes it
	 *
	 * @param model 			is the model connected to this controller
	 *
	 * @throws exceptions based on the problem found
	 */
	public void execute(Model model) throws InvalidPickException, NotEnoughSpaceInColumnException {
		model.insert(cards, column, getCurrPlayer());
	}
}