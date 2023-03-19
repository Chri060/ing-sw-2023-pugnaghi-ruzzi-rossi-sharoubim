package Model;

public class Turn {

	private String currentPlayer;

	private Action currentAction;

	private Turn(String firstPlayer) {
		currentPlayer = firstPlayer;
		currentAction = Action.WITHDRAW;
	}

	public void changePlayer(String next) {
		currentPlayer = next;
		currentAction = Action.WITHDRAW;
	}

	public void nextAction() {
		currentAction = Action.INSERT;
	}

}
