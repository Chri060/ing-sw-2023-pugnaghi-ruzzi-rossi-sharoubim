package Model.CommonObjectives;

import Exceptions.MatchException;
import Model.Shelf;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class CommonObjective {

	private List<String> completedBy;
	List<Integer> Points;
	int objID;



	public CommonObjective (int numOfPlayers) throws MatchException{
		Points = new ArrayList<>();
		completedBy = new ArrayList<>();
		try {
			Object file = new JSONParser().parse(new FileReader("src/main/resources/Model/config.json"));
			JSONObject jsonObject = (JSONObject) file;
			JSONArray jsonArray0 = (JSONArray) jsonObject.get("commonPoints");

			if (jsonArray0.size() <= numOfPlayers - 2) {
				throw new MatchException("Bad JSON format: Common Points not defined for "+ numOfPlayers +" players");
			}

			JSONArray jsonArray = (JSONArray) jsonArray0.get(numOfPlayers - 2);
			if (jsonArray.size() < numOfPlayers) {
				throw new MatchException("Bad JSON format: Common Points not correctly defined for "+ numOfPlayers +" players");
			}
			for (int i = 0; i < jsonArray.size(); i++) {
				Points.add(((Long) jsonArray.get(i)).intValue());
			}
		}catch (IOException | ParseException | NullPointerException | ClassCastException e) {
			throw new MatchException("Bad JSON format: invalid entries for common objective object");
		}
	}
	public List<String> getCompletion () {
		return completedBy;
	}



	abstract public boolean verify (Shelf shelf);

	public int checkMaxAvaiablePoints() {
		if (Points.isEmpty()){
			return  0;
		}
		return Points.get(0);
	}

	public int getMaxAvaiblePoints() {
		if (Points.isEmpty()) {
			return 0;
		}
		return Points.remove(0);
	}


	public int getObjID () {
		return objID;
	}
}
