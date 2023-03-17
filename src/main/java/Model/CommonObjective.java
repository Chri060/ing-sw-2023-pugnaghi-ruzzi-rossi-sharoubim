package Model;

public abstract class CommonObjective {

	private int type;

	private String completedBy;

	private CommonObjective() {

	}

	public boolean obliquePattern(int len) {
		return false;
	}

	public boolean verticalPattern(int len) {
		return false;
	}

	public boolean orizontalPattern(int len) {
		return false;
	}

	public boolean xPattern() {
		return false;
	}

	public boolean verify(Library library) {
		return false;
	}

}
