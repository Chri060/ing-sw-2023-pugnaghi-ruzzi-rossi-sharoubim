package Model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class CommonObjective {
	private List<String> completedBy;

	List<Integer> Points;

	int objID;

	public CommonObjective (int numOfPlayers) {
		Points = new ArrayList<>();
		try {
			Object file = new JSONParser().parse(new FileReader("src/main/resources/Model/config.json"));
			JSONObject jsonObject = (JSONObject) file;
			JSONArray jsonArray0 = (JSONArray) jsonObject.get("commonPoints");
			JSONArray jsonArray = (JSONArray) jsonArray0.get(numOfPlayers - 2);
			for (int i = 0; i < jsonArray.size(); i++) {
				Points.add(((Long) jsonArray.get(i)).intValue());
			}
		} catch (FileNotFoundException | ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public List<String> getCompletion () {
		return completedBy;
	}

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
