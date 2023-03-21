package Model;

public abstract class CommonObjective {

	private int type;

	private String completedBy;


	public CommonObjective (){

	};

	public boolean verify(Library library) {
		return false;
	}

}
