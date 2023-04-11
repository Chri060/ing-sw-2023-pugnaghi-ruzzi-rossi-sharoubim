package Model;

import Exceptions.BagEmptyException;
import Exceptions.CannotWithdrawCardException;
import Exceptions.InvalidPickException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Table {
	int dashDim;

	//TODO rimettere privata
	public Cards[][] dashboard;
	public boolean[][] taps;


	public Table(int playerNum) {
		try {
			Object file = new JSONParser().parse(new FileReader("src/main/resources/Model/config.json"));
			JSONObject jsonObject0 = (JSONObject) file;
			JSONObject jsonObject = (JSONObject) jsonObject0.get("tableConfig");
			dashDim = ((Long) jsonObject.get("DashboardDimension")).intValue();
			if (dashDim < 2) {
				System.err.println("Dashboard dimension must be at least 3 x 3");
				System.exit(-1);
			}
			JSONArray patterns = (JSONArray) jsonObject.get("patterns");
			if (patterns.size() < playerNum - 1) {
				System.err.println("Dashboard pattern is not defined for " + playerNum + " of players");
				System.exit(-1);
			}

			JSONArray pattern = (JSONArray) patterns.get(playerNum - 2);
			if (dashDim * dashDim != pattern.size()) {
				System.err.println("Dashboard dimension must be at least 3 x 3");
				System.exit(-1);
			}
			dashboard = new Cards[dashDim][dashDim];
			taps = new boolean[dashDim][dashDim];
			for (int i = 0; i < pattern.size(); i++) {
				taps[i / dashDim][i % dashDim] = ((Long) (pattern.get(i))).intValue() > 0;
			}
		} catch (IOException | ParseException | NullPointerException | ClassCastException e) {
			System.err.println("Bad JSON format: invalid entries for Table object");
			System.exit(-1);
		}
	}

	public void refill(Bag bag) throws BagEmptyException {
		if (needsRefill()) {
			for (int i = 0; i < dashDim; i++) {
				for (int j = 0; j < dashDim; j++) {
					if (taps[i][j] && dashboard[i][j] == null) {
						dashboard[i][j] = bag.getCard();
					}
				}
			}
		}
	}

	public Cards checkout(int row, int col) throws InvalidPickException {
		if (row >= dashDim || row < 0 || col >= dashDim || col < 0) {
			throw new InvalidPickException();
		}
		return dashboard[row][col];
	}

	private Cards getCard(int row, int col) throws InvalidPickException{
		if (row >= dashDim || row < 0 || col >= dashDim || col < 0) {
			throw new InvalidPickException();
		}
		if (dashboard[row][col] == null) {
			throw new InvalidPickException();
		}
		Cards c = dashboard[row][col];
		dashboard[row][col] = null;
		return c;
	}

	public boolean[][] getTaps() {
		boolean[][] res = new boolean[dashDim][dashDim];
		for (int i = 0; i < dashDim; i++) {
			for (int j = 0; j < dashDim; j++) {
				res[i][j] = taps[i][j];
			}
		}
		return res;
	}

	public Cards[][] getTable() {
		Cards[][] res = new Cards[dashDim][dashDim];
		for (int i = 0; i < dashDim; i++) {
			for (int j = 0; j < dashDim; j++) {
				res[i][j] = dashboard[i][j];
			}
		}
		return res;
	}

	public int getDashDim() {
		return dashDim;
	}

	public List<Cards> withdraw(List<Integer> coordinates) throws CannotWithdrawCardException, InvalidPickException {
		int row;
		int col;
		List <Cards> withdrawnCards = new ArrayList<>();
		if (canWithdraw(coordinates)) {
			int i = 0;
			while (i < coordinates.size()) {
				row = coordinates.get(i);
				col = coordinates.get(i + 1);
				withdrawnCards.add(getCard(row, col));
				i += 2;
			}
		} else {
			throw new CannotWithdrawCardException(coordinates);
		}
		return withdrawnCards;
	}

	private boolean areNear(List<Integer> coordinates) {
		Collections.sort(coordinates);
		for (int i = 0; i < coordinates.size() - 1; i++) {
			if (coordinates.get(i) - coordinates.get(i + 1) != - 1) {
				return false;
			}
		}
		return true;
	}

	public boolean needsRefill() {
		for(int i = 0; i < dashDim * dashDim; i++) {
			int row = i / dashDim;
			int col = i % dashDim;
			try {
				if (checkout(row, col) != null) {
					if (col > 0 && checkout(row, col - 1) != null) {
						return false;
					} else if (col < dashDim - 1 && checkout(row, col + 1) != null) {
						return false;
					} else if (row > 0 && checkout(row - 1, col) != null) {
						return false;
					} else if (row < dashDim - 1 && checkout(row + 1, col) != null) {
						return false;
					}
				}
			} catch (InvalidPickException e) {}
		}
		return true;
	}

	//test only
	public void printTaps() {
		for(int i = 0; i < dashDim; i++) {
			for(int j = 0; j < dashDim; j++) {
				System.out.print(taps[i][j] + "\t");
			}
			System.out.println();
		}
	}

	//test only
	public void printCards() {
		System.out.print("\t");
		for(int i = 0; i < dashDim; i++) {
			System.out.print(+ i + "\t\t");
		}
		System.out.println();
		for(int i = 0; i < dashDim; i++) {
			System.out.print(+ i + "\t");
			for(int j = 0; j < dashDim; j++) {
				if (dashboard[i][j] != null) {
					System.out.print(dashboard[i][j].getType() + "\t");
				} else {
					System.out.print("null" + "\t");
				}
			}
			System.out.println();
		}
	}

	//TODO: sistemare

	/* Returns true only if cards can be withdrawn
	 * @param coordinates	List of coordinates of the cards in the board
	 * @throws InvalidPickException if coordinates is null, not a even set of numbers or if contains values out of
	 * table bounds
	 * */
	private boolean canWithdraw (List<Integer> coordinates) throws InvalidPickException {
		if (coordinates == null || coordinates.size() % 2 != 0) { //Checks that coordinates are in a valid format
			throw new InvalidPickException();
		}

		boolean outOfBounds = coordinates.stream().anyMatch( x -> { //Checks that coordinates aren't out of bounds
			if (x < 0 || x >= dashDim) return true;
			return false;
		});
		if (outOfBounds) throw new InvalidPickException();

		if (coordinates.size() > 2) { //If more than one card, Checks if coordinates are in row or in column
			List<Integer> rows = new ArrayList<>();
			List<Integer> cols = new ArrayList<>();

			for (int i = 0; i < coordinates.size(); i += 2) { //Creates rows and columns coordinates lists
				rows.add(coordinates.get(i));
				cols.add(coordinates.get(i + 1));
			}

			boolean vertical = (rows.stream().distinct().count() > 1);
			boolean horizontal = (cols.stream().distinct().count() > 1);

			if (vertical && horizontal) { //Cards are not in row or in column
				return false;
			}
			if (!vertical && !horizontal) { //The same card was chosen 2 times
				throw new InvalidPickException();
			}

			if (vertical) { //If vertical checks that the rows are adjacent
				if (!areNear(rows)) {
					return false;
				}
			} else { //If horizontal checks that the cols are adjacent
				if (!areNear(cols)) {
					return false;
				}
			}
		}


		int i = 0;
		int row, col;


		while (i < coordinates.size()) {
			row = coordinates.get(i);
			col = coordinates.get(i + 1);
			if (checkout(row, col) != null) {
				if (row == 0 || row == (dashDim - 1) || col == 0 || col == (dashDim - 1)) {
					i += 2;
				} else if (checkout(row + 1, col) == null) {
					i += 2;
				} else if (checkout(row - 1, col) == null) {
					i += 2;
				} else if (checkout(row, col + 1) == null) {
					i += 2;
				} else if (checkout(row, col - 1) == null) {
					i += 2;
				} else { //At least one card doesn't have a free adjacent free space
					return false;
				}
			} else { //An already taken card is being withdrawn
				throw new InvalidPickException();
			}
		}
		return true;
	}
}