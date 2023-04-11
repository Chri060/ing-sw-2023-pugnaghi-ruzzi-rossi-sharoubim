package Controller;

import Exceptions.*;
import Model.Action;
import Model.Match;
import java.util.List;

public class DrawTile extends PlayerAction {
	private List<Integer> coordinates;


	/*
	 * Creates a new PlayerAction for the WITHDRAW phase
	 *
	 * @param coordinate 	contains the coordinates selected by the player
	 * @param player 		is the name of the player that is trying to do the action
	 */
	public DrawTile(List<Integer> coordinate, String player) {
		super(player);
		coordinates = coordinate;
		setAction(Action.WITHDRAW);
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
		try {
			model.withdraw(coordinates, getCurrPlayer());
		}
		catch (CannotWithdrawCardException | InvalidPickException e) {
			//TODO Chiedere nuovamente al client le coordinate
		}
	}
}