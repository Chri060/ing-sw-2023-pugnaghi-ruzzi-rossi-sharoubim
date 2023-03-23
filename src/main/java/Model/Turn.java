package Model;

public class Turn {

	private String currentPlayer;
	private Action currentAction;
	private int turn;
	public String getCurrentPlayer() {return currentPlayer;}
	public Action getCurrentAction() {return currentAction;}

	public int getTurn() {return turn;}
	public Turn(String firstPlayer) {
		currentPlayer = firstPlayer;
		currentAction = Action.WITHDRAW;
		turn = 1;
	}
	public void changePlayer(String next) {
		currentPlayer = next;
		currentAction = Action.WITHDRAW;
		turn++;
	}
	public void nextAction() {
		currentAction = Action.INSERT;
	}

}
