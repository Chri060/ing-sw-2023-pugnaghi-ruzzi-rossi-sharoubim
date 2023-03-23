package Model;

import java.util.List;

public abstract class CommonObjective {

	private List<Integer> objID;

	private List<String> completedBy;


	public CommonObjective (){
		//TODO: pesca due numeri casuali da 1 a 12
	};

	public void chosenObjective (List<Integer> objID) {
		//TODO: costruisce gli oggetti relativi ai due obiettivi pescati
	}


}
