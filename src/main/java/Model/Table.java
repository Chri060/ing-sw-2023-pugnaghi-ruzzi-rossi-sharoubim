package Model;


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
	int DASHBOARDDIM;
	private Cards[][] dashboard;
	private boolean[][] taps;

	public Table(int playerNum) {

		try {
			Object file = new JSONParser().parse(new FileReader("src/main/resources/Model/Table.json"));
			JSONObject jsonObject = (JSONObject) file;
			DASHBOARDDIM = ((Long) jsonObject.get("DashboardDimension")).intValue();
			JSONArray patterns = (JSONArray) jsonObject.get("patterns");
			JSONArray pattern = (JSONArray) patterns.get(playerNum - 2);

			dashboard = new Cards[DASHBOARDDIM][DASHBOARDDIM];
			taps = new boolean[DASHBOARDDIM][DASHBOARDDIM];

			for (int i = 0; i < pattern.size(); i++) {
				taps[i / DASHBOARDDIM][i % DASHBOARDDIM] = ((Long) (pattern.get(i))).intValue() == 1;
			}

		} catch (FileNotFoundException | ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	public void refill(Bag bag) {
		for (int i = 0; i < DASHBOARDDIM; i++) {
			for (int j = 0; j < DASHBOARDDIM; j++) {
				if (taps[i][j] && dashboard[i][j] == null) {
					dashboard[i][j] = bag.getCard();
				}
			}
		}
	}

	public Cards checkout(int row, int col) {
		return dashboard[row][col];
	}
	
	//TODO: deve essere private
	public Cards getCard(int row, int col) {
		Cards c = dashboard[row][col];
		dashboard[row][col] = null;
		return c;
	}

	public List<Cards> withdraw(List<Integer> coordinates) {
		List<Cards> withdrawnCards = new ArrayList<>();

		int i = 0;
		int row, col;

		while (i < coordinates.size()) {
			row = coordinates.get(i);
			col = coordinates.get(i + 1);
			if (checkout(row, col) != null) {
				if (row == 0 || row == (DASHBOARDDIM - 1) || col == 0 || col == (DASHBOARDDIM - 1)) { i += 2; }
				else if (checkout(row + 1, col) == null) { i += 2; }
				else if (checkout(row - 1, col) == null) { i += 2; }
				else if (checkout(row, col + 1) == null) { i += 2; }
				else if (checkout(row, col - 1) == null) { i += 2; }
				else { return withdrawnCards; }
			}
			else { return withdrawnCards; }
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

		for(int i = 0; i < DASHBOARDDIM * DASHBOARDDIM; i++) {

			int row = i / DASHBOARDDIM;
			int col = i % DASHBOARDDIM;

			if (checkout(row, col) != null) {
				if (col > 0 && checkout(row, col - 1) != null) { return false;}
				else if (col < DASHBOARDDIM - 1 && checkout(row, col + 1) != null)  { return false;}
				else if (row > 0 && checkout(row - 1, col) != null)  { return false;}
				else if (row < DASHBOARDDIM - 1 && checkout(row +1, col) != null)  { return false;}
			}
		}
		return true;
	}

	//test only
	public void printTaps() {
		for(int i = 0; i < DASHBOARDDIM; i++) {
			for(int j = 0; j < DASHBOARDDIM; j++) {
				System.out.print(taps[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	//test only
	public void printCards() {
		for(int i = 0; i < DASHBOARDDIM; i++) {
			for(int j = 0; j < DASHBOARDDIM; j++) {
				System.out.print(dashboard[i][j] + "\t");
			}
			System.out.println();
		}
	}

}
