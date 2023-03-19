package Model;

public class Player {

	private String name;

	private int points;

	private PrivateObjective[] privateObj;

	private Library library;

	private Player() {

	}

	public String getName() {
		return name;
	}

	public int getPoints() {
		return points;
	}

	public Library getLibrary() {
		return new Library(this.library);
	}

	public int personalObjPoints() {
		return 0;
	}

	public Cards getObjPattern() {
		return null;
	}

}
