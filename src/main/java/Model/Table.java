package Model;


import Exceptions.BagEmptyException;
import Exceptions.CannotWIthdrowCardException;
import Exceptions.InvalidPickException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Table {
	int dashDim;
	private Cards[][] dashboard;
	private boolean[][] taps;

	public Table(int playerNum) {

		try {
			Object file = new JSONParser().parse(new FileReader("src/main/resources/Model/Table.json"));
			JSONObject jsonObject = (JSONObject) file;
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

	public Cards checkout(int row, int col) {
		return dashboard[row][col];
	}

	//TODO: deve essere private: done
	private Cards getCard(int row, int col) {
		Cards c = dashboard[row][col];
		dashboard[row][col] = null;
		return c;
	}

	public List<Cards> withdraw(List<Integer> coordinates) throws  CannotWIthdrowCardException, InvalidPickException {

		if (coordinates == null || coordinates.size() == 0){
			throw new InvalidPickException();
		}

		List<Cards> withdrawnCards = new ArrayList<>();

		int i = 0;
		int row, col;

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
					throw new CannotWIthdrowCardException(row, col); }
			}
			else {
				throw new CannotWIthdrowCardException(row, col); }
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
			if (checkout(row, col) != null) {
				if (col > 0 && checkout(row, col - 1) != null) { return false;}
				else if (col < dashDim - 1 && checkout(row, col + 1) != null)  { return false;}
				else if (row > 0 && checkout(row - 1, col) != null)  { return false;}
				else if (row < dashDim - 1 && checkout(row +1, col) != null)  { return false;}
			}
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
