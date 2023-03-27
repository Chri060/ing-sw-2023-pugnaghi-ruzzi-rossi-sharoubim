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

import static java.lang.Math.abs;

public class Table {
	int dashDim;
	private Cards[][] dashboard;
	private boolean[][] taps;

	public Table(int playerNum) {

		try {
			Object file = new JSONParser().parse(new FileReader("src/main/resources/Model/config.json"));
			JSONObject jsonObject0 = (JSONObject) file;
			JSONObject jsonObject = (JSONObject) jsonObject0.get("tableConfig");
			dashDim = ((Long) jsonObject.get("DashboardDimension")).intValue();
			JSONArray patterns = (JSONArray) jsonObject.get("patterns");
			JSONArray pattern = (JSONArray) patterns.get(playerNum - 2);

			dashboard = new Cards[dashDim][dashDim];
			taps = new boolean[dashDim][dashDim];

			for (int i = 0; i < pattern.size(); i++) {
				taps[i / dashDim][i % dashDim] = ((Long) (pattern.get(i))).intValue() == 1;
			}

		} catch (FileNotFoundException | ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	public void refill(Bag bag) throws BagEmptyException {
		for (int i = 0; i < dashDim; i++) {
			for (int j = 0; j < dashDim; j++) {
				if (taps[i][j] && dashboard[i][j] == null) {
					dashboard[i][j] = bag.getCard();
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

	//TODO: deve essere private: done
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

	public List<Cards> withdraw(List<Integer> coordinates) throws CannotWithdrawCardException, InvalidPickException {

		if (coordinates == null || coordinates.size() == 0 || coordinates.size() % 2 != 0){
			throw new InvalidPickException();
		}

		int i = 0;
		int row, col;
		List<Cards> withdrawnCards = new ArrayList<>();

		boolean vertical = false;
		List<Integer> rows;
		boolean orizzontal = false;
		List<Integer> cols;


		if (coordinates.size() != 2) {
			for (int j = 0; j < ((coordinates.size() / 2) - 1); j++) {
				if (coordinates.get(2 * j) != coordinates.get(2 * j + 2)) {vertical = true;}
				if (coordinates.get(2 * j + 1) != coordinates.get(2 * j + 3)) {orizzontal = true;}
				if ((vertical && orizzontal) || (!vertical && !orizzontal)) {
					throw new CannotWithdrawCardException(coordinates);
				}
			}
			if (vertical) {
				rows  = new ArrayList<Integer>();
				for (int j = 0; j < ((coordinates.size() / 2)); j++) {
					rows.add(coordinates.get(2 * j));
				}
				Collections.sort(rows);
				for (int j = 0; j < ((coordinates.size() / 2) - 1); j++) {
					if (rows.get(j) - rows.get(j + 1) != - 1) {
						throw new CannotWithdrawCardException(coordinates);
					}
				}
			}
			if (orizzontal) {
				cols  = new ArrayList<Integer>();
				for (int j = 0; j < ((coordinates.size() / 2)); j++) {
					cols.add(coordinates.get(2 * j + 1));
				}
				Collections.sort(cols);
				for (int j = 0; j < ((coordinates.size() / 2) - 1); j++) {
					if (cols.get(j) - cols.get(j + 1) != - 1) {
						throw new CannotWithdrawCardException(coordinates);
					}
				}
			}
		}

		boolean outOfBounds = coordinates.stream().anyMatch( x -> {
			if(x < 0) return true;
			if(x >= dashDim) return true;
			return false;
		});
		if(outOfBounds) throw new CannotWithdrawCardException(coordinates);

		while (i < coordinates.size()) {
			row = coordinates.get(i);
			col = coordinates.get(i + 1);
			if (checkout(row, col) != null) {
				if (row == 0 || row == (dashDim - 1) || col == 0 || col == (dashDim - 1)) { i += 2; }
				else if (checkout(row + 1, col) == null) { i += 2; }
				else if (checkout(row - 1, col) == null) { i += 2; }
				else if (checkout(row, col + 1) == null) { i += 2; }
				else if (checkout(row, col - 1) == null) { i += 2; }
				else {
					throw new CannotWithdrawCardException(coordinates); }
			}
			else {
				throw new CannotWithdrawCardException(coordinates); }
		}

		i = 0;
		while (i < coordinates.size()) {
			row = coordinates.get(i);
			col = coordinates.get(i + 1);
			withdrawnCards.add(getCard(row, col));
			i += 2;
		}
		return withdrawnCards;
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
			}catch (InvalidPickException e) {}
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
				}
				else {
					System.out.print("null" + "\t");
				}
			}
			System.out.println();
		}
	}

}
