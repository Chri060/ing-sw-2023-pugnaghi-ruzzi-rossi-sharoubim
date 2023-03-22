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




	public PrivateObjective(int obiettivo) {

		try {
			Object file = new JSONParser().parse(new FileReader("src/main/resources/Model/PrivateObjective.json"));
			JSONObject jsonObject = (JSONObject) file;
			JSONArray patterns = (JSONArray) jsonObject.get("patterns");

			JSONArray coordinateJson = (JSONArray) ((JSONObject) patterns.get(obiettivo)).get("objectiveCoordinates");
			JSONArray objectiveJson = (JSONArray) ((JSONObject) patterns.get(obiettivo)).get("objectiveItems");

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
	/*retruns number of coincidences*/
	public int verify(Library library) {
		int row;
		int col;

		try {
			Object file = new JSONParser().parse(new FileReader("src/main/resources/Model/PrivateObjective.json"));
			JSONObject jsonObject = (JSONObject) file;
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
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}

	}

	//TODO: valutare come viene restituito alla view, eventualmente creare nuovo tipo oggetto oppure usare matrice
	public Cards[][] getPattern() {
		System.out.println(Arrays.toString(this.coordinates));
		System.out.println(Arrays.toString(this.object));
		return null;
	}
}
