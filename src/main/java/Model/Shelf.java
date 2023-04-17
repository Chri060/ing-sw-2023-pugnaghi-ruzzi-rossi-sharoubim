package Model;

import Exceptions.NotEnoughSpaceInColumnException;
import Exceptions.InvalidPickException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Shelf {
	private int librarycols;
	private int libraryrows;
	private Cards[][] library;



	public Shelf() {
		try {
			Object file = new JSONParser().parse(new FileReader("src/main/resources/Model/config.json"));
			JSONObject jsonObject0 = (JSONObject) file;
			JSONObject jsonObject = (JSONObject) jsonObject0.get("libraryConfig");
			librarycols = ((Long) jsonObject.get("numberOfColoumns")).intValue();
			libraryrows = ((Long) jsonObject.get("numberOfRows")).intValue();
			if (libraryrows <= 0 || librarycols <= 0) {
				System.err.println("Bad JSON format: rows and columns must be more than 0");
				System.exit(-1);
			}
			library = new Cards[libraryrows][librarycols];
		}
		catch (IOException | ParseException | NullPointerException | ClassCastException e) {
			System.err.println("Bad JSON format: invalid entries for Library object");
			System.exit(-1);
		}
	}

	public Shelf(Shelf l) {
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

	public void insert(List<Cards> cardsList, int col) throws NotEnoughSpaceInColumnException, InvalidPickException {
		int row = libraryrows - 1;
		if (canInsertIn(cardsList.size(), col)) {
			while (row > 0 && library[row][col] != null) {
				row--;
			}
			for (int i = 0; i < cardsList.size(); i++) {
				library[row - i][col] = cardsList.get(i);
			}
		}
		else {
			throw new NotEnoughSpaceInColumnException(col);
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

	public boolean canInsertIn(int nCards, int col) throws InvalidPickException{
		if (col < 0 || col > librarycols) {
			throw new InvalidPickException();
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
