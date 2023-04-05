package Model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PrivateObjective {
	private int corrispondence;
	private int[] coordinates;
	private CardsType[] object;

	private List<Integer> points;


	public PrivateObjective(int ObjID) {
		try {
			Object file = new JSONParser().parse(new FileReader("src/main/resources/Model/config.json"));
			JSONObject jsonObject0 = (JSONObject) file;
			JSONObject jsonObject = (JSONObject) jsonObject0.get("privateObjectivesConfig");
			JSONArray patterns = (JSONArray) jsonObject.get("patterns");
			JSONArray coordinateJson = (JSONArray) ((JSONObject) patterns.get(ObjID)).get("objectiveCoordinates");
			JSONArray objectiveJson = (JSONArray) ((JSONObject) patterns.get(ObjID)).get("objectiveItems");
			JSONArray points = (JSONArray) jsonObject.get("points");

			if (points.size() < objectiveJson.size() || objectiveJson.size() * 2 != coordinateJson.size()) {
				System.err.println("Bad JSON format: private objectives points and pattern mismatch");
				System.exit(-1);
			}

			coordinates = new int[coordinateJson.size()];
			for (int i = 0; i < coordinateJson.size(); i++) {
				coordinates[i] = ((Long) coordinateJson.get(i)).intValue();
			}
			object = new CardsType[objectiveJson.size()];
			for (int i = 0; i < objectiveJson.size(); i++) {
				try {
					object[i] = CardsType.valueOf((String) objectiveJson.get(i));
				}
				catch (IllegalArgumentException e) {
					System.err.println("Bad JSON format: private objectives patterns contains invalid card type");
					System.exit(-1);
				}
			}
			this.points = new ArrayList<>();
			for (Object point : points) {
				this.points.add(((Long) point).intValue());
			}

		} catch (IOException | ParseException | NullPointerException | ClassCastException e) {
			System.err.println("Bad JSON format: invalid entries for private objective object");
			System.exit(-1);
		}
		corrispondence = 0;
	}

	public int verify(Library library) {
		int row;
		int col;
		Cards[][] libraryCopy = library.getAsMatrix();
		for (int i = 0; i < 2 * object.length; i += 2) {
			row = coordinates[i];
			col = coordinates[i + 1];
			if (libraryCopy[row][col] != null && libraryCopy[row][col].getType() == (object[i / 2])) {
				corrispondence++;
			}
		}
		return points.get(corrispondence);
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