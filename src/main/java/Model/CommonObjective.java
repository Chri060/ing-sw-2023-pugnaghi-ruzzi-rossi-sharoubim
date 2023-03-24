package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class CommonObjective {
	private List<String> completedBy;

	int objID;

	public CommonObjective () {

	}

	public List<String> getCompletion () {
		return completedBy;
	}

	public int getObjID () {
		return objID;
	}
}
