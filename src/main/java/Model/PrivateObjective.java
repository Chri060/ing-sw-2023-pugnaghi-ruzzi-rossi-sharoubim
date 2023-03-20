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
	private Cards[] object;




	public PrivateObjective(int obiettivo) {

		try {
			Object file = new JSONParser().parse(new FileReader("src/main/resources/Model/PrivateObjective.json"));
			JSONArray jsonArray = (JSONArray) file;
			JSONObject jsonObject = (JSONObject) jsonArray.get(obiettivo);

			JSONArray coordinateJson = (JSONArray) jsonObject.get("objectiveCoordinates");
			JSONArray objectiveJson = (JSONArray) jsonObject.get("objectiveItems");

			coordinates = new int[coordinateJson.size()];
			for (int i = 0; i < coordinateJson.size(); i++) {
				coordinates[i] = ((Long) coordinateJson.get(i)).intValue();
			}
			object = new Cards[objectiveJson.size()];
			for (int i = 0; i < objectiveJson.size(); i++) {
				object[i] = Cards.valueOf((String) objectiveJson.get(i));
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
		Cards[][] libraryCopy = library.getAsMatrix();
		for (int i = 0; i < 2 * object.length; i += 2) {
			row = coordinates[i];
			col = coordinates[i + 1];
			if (libraryCopy[row][col] == (object[i / 2])) {
				corrispondence++;
			}
		}
		return corrispondence;
	}

	public Cards[][] getPattern() {
		System.out.println(Arrays.toString(this.coordinates));
		System.out.println(Arrays.toString(this.object));
		return null;
	}
}
