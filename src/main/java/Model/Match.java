package Model;

public class Match {

	private String chair;

	private int playerNum;

	private Table dashboard;

	private Bag bag;

	private CommonObjective[] commonObjectives;

	private Player[] players;

	private Turn turn;

	private Match() {

	}

	public void nextAction() {

	}

	public void nextPlayer() {

	}

	public String getChairPlayer() {
		return null;
	}

	public int PersonalObjPoints(String playerName) {
		return 0;
	}

	public Cards getPlayerObjPattern(String playerName) {
		return null;
	}

	public boolean needsRefill() {
		return false;
	}

	public void refill() {

	}

}
