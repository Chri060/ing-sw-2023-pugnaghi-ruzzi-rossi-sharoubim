package Model;

import Exceptions.ColumFullException;

import java.util.List;

public class Library {

	final int LIBRARYCOLUMNS = 5;
	final int LIBRARYROWS = 6;

	private Cards[][] library;

	public Library() {
		library = new Cards[LIBRARYROWS][LIBRARYCOLUMNS];
	}

	public Library(Library l) {
		this();
		for (int i = 0; i < LIBRARYROWS; i++) {
			for (int j = 0; j < LIBRARYCOLUMNS; j++) {
				this.library[i][j] =  l.library[i][j];
			}
		}
	}

	public Cards[][] getAsMatrix() {
		Cards[][] result = new Cards[LIBRARYROWS][LIBRARYCOLUMNS];
		for (int i = 0; i < LIBRARYROWS; i++) {
			for (int j = 0; j < LIBRARYCOLUMNS; j++) {
				result[i][j] = library[i][j];
			}
		}
		return result;
	}

	public void insert(List<Cards> cardsList, int col) throws ColumFullException {
		int row = LIBRARYROWS - 1;
		if (canInsertIn(cardsList.size(), col)) {
			while (row > 0 && library[row][col] != null) {
				row--;
			}
			for (int i = 0; i < cardsList.size(); i++) {
				library[row - i][col] = cardsList.get(i);
			}
		}
		else { throw new ColumFullException(col);
		}
	}

	public boolean isFull() { return (maxFreeSpace() == 0); }

	public int maxFreeSpace() {
		int row = 0;
		int space = 0;

		for (int i = 0; i < LIBRARYCOLUMNS; i++) {
			row = 0;
			while (row < LIBRARYROWS && library[row][i] == null ) { row++; }
			if (space < row) { space = row; }
		}
		return space;
	}

	public boolean  canInsertIn(int nCards, int col) {
		int row = 0;

		while (row < LIBRARYROWS && library[row][col] == null) {
			row++;
		}
		return (row >= nCards);

	}

}
