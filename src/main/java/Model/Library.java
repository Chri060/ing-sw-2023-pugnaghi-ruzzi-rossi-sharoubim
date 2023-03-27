package Model;

import Exceptions.ColumFullException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Library {
	private int librarycols;
	private int libraryrows;
	private Cards[][] library;



	public Library() {
		try {
			Object file = new JSONParser().parse(new FileReader("src/main/resources/Model/config.json"));
			JSONObject jsonObject0 = (JSONObject) file;
			JSONObject jsonObject = (JSONObject) jsonObject0.get("libraryConfig");
			librarycols = ((Long) jsonObject.get("numberOfColoumns")).intValue();
			libraryrows = ((Long) jsonObject.get("numberOfRows")).intValue();
			library = new Cards[libraryrows][librarycols];
		}
		catch (FileNotFoundException | ParseException e) {
			e.printStackTrace();
		} catch (
				IOException e) {
			throw new RuntimeException(e);
		}
	}

	public Library(Library l) {
		this();
		for (int i = 0; i < libraryrows; i++) {
			for (int j = 0; j < librarycols; j++) {
				this.library[i][j] =  l.library[i][j];
			}
		}
	}

	public Cards[][] getAsMatrix() {
		Cards[][] result = new Cards[libraryrows][librarycols];
		for (int i = 0; i < libraryrows; i++) {
			for (int j = 0; j < librarycols; j++) {
				result[i][j] = library[i][j];
			}
		}
		return result;
	}

	public void insert(List<Cards> cardsList, int col) throws ColumFullException {
		int row = libraryrows - 1;
		if (canInsertIn(cardsList.size(), col)) {
			while (row > 0 && library[row][col] != null) {
				row--;
			}
			for (int i = 0; i < cardsList.size(); i++) {
				library[row - i][col] = cardsList.get(i);
			}
		}
		//TODO: gestire caso di indici non validi
		else {
			throw new ColumFullException(col);
		}
	}

	public boolean isFull() {
		return (maxFreeSpace() == 0);
	}

	public int maxFreeSpace() {
		int row = 0;
		int space = 0;
		for (int i = 0; i < librarycols; i++) {
			row = 0;
			while (row < libraryrows && library[row][i] == null ) { row++; }
			if (space < row) { space = row; }
		}
		return space;
	}

	public boolean  canInsertIn(int nCards, int col) {
		if (col < 0 || col > librarycols) {
			return false;
		}
		int row = 0;
		while (row < libraryrows && library[row][col] == null) {
			row++;
		}
		return (row >= nCards);
	}

	public int getLibraryCols() {
		return librarycols;
	}

	public int getLibraryRows() {
		return libraryrows;
	}
}
