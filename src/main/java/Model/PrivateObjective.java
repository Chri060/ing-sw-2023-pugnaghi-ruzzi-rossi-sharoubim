package Model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PrivateObjective {
	private int corrispondence;
	private int[] coordinates;
	private CardsType[] object;



	public PrivateObjective(int ObjID) {
		try {
			Object file = new JSONParser().parse(new FileReader("src/main/resources/Model/config.json"));
			JSONObject jsonObject0 = (JSONObject) file;
			JSONObject jsonObject = (JSONObject) jsonObject0.get("privateObjectivesConfig");
			JSONArray patterns = (JSONArray) jsonObject.get("patterns");
			JSONArray coordinateJson = (JSONArray) ((JSONObject) patterns.get(ObjID)).get("objectiveCoordinates");
			JSONArray objectiveJson = (JSONArray) ((JSONObject) patterns.get(ObjID)).get("objectiveItems");
			coordinates = new int[coordinateJson.size()];
			for (int i = 0; i < coordinateJson.size(); i++) {
				coordinates[i] = ((Long) coordinateJson.get(i)).intValue();
			}
			object = new CardsType[objectiveJson.size()];
			for (int i = 0; i < objectiveJson.size(); i++) {
				object[i] = CardsType.valueOf((String) objectiveJson.get(i));
			}
		}
		catch (FileNotFoundException | ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		corrispondence = 0;
	}

	public int verify(Library library) {
		int row;
		int col;
		try {
			Object file = new JSONParser().parse(new FileReader("src/main/resources/Model/config.json"));
			JSONObject jsonObject0 = (JSONObject) file;
			JSONObject jsonObject = (JSONObject) jsonObject0.get("privateObjectivesConfig");
			JSONArray points = (JSONArray) jsonObject.get("points");
			Cards[][] libraryCopy = library.getAsMatrix();
			for (int i = 0; i < 2 * object.length; i += 2) {
				row = coordinates[i];
				col = coordinates[i + 1];
				if (libraryCopy[row][col] != null && libraryCopy[row][col].getType() == (object[i / 2])) {
					corrispondence++;
				}
			}
			return ((Long) points.get(corrispondence)).intValue();
		} catch (IOException | ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public Cards[][] getPattern() {
		Library l = new Library();
		Cards[][] pattern = l.getAsMatrix();
		l = null;
		for (int i = 0; i < object.length; i++) {
			pattern[coordinates[2 * i]][coordinates[2 * i + 1]]= new Cards(object[i], 0);
		}
		System.out.println(Arrays.toString(coordinates));
		System.out.println(Arrays.toString(object));
		return pattern;
	}
}