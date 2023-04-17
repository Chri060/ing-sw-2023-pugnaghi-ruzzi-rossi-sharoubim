package Controller;

import Exceptions.*;
import Model.Action;
import Model.Model;
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
		this.action = Action.WITHDRAW;
	}

	/*
	* Check if the action is possible and eventually executes it
	*
	* @param model 			is the model connected to this controller
	*
	* @throws exceptions based on the problem found
	*/
	@Override
	public void execute(Model model) throws CannotWithdrawCardException, InvalidPickException {
		model.withdraw(coordinates, getCurrPlayer());
	}
}