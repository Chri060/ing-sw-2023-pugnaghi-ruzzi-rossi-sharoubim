package Model;


import java.util.ArrayList;
import java.util.List;

public class Table {
	final int DASHBOARDDIM = 9;
	private Cards[][] dashboard;
	private boolean[][] taps;

	final int[] TwoPlayers = new int[] {
			0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 1, 1, 0, 0, 0, 0,
			0, 0, 0, 1, 1, 1, 0, 0, 0,
			0, 0, 1, 1, 1, 1, 1, 1, 0,
			0, 1, 1, 1, 1, 1, 1, 1, 0,
			0, 1, 1, 1, 1, 1, 1, 0, 0,
			0, 0, 0, 1, 1, 1, 0, 0, 0,
			0, 0, 0, 0, 1, 1, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0
	};


	final int[] ThreePlayers = new int[] {
			0, 0, 0, 1, 0, 0, 0, 0, 0,
			0, 0, 0, 1, 1, 0, 0, 0, 0,
			0, 0, 1, 1, 1, 1, 1, 0, 0,
			0, 0, 1, 1, 1, 1, 1, 1, 1,
			0, 1, 1, 1, 1, 1, 1, 1, 0,
			1, 1, 1, 1, 1, 1, 1, 0, 0,
			0, 0, 1, 1, 1, 1, 1, 0, 0,
			0, 0, 0, 0, 1, 1, 0, 0, 0,
			0, 0, 0, 0, 0, 1, 0, 0, 0
	};


	final int[] FourPlayers = new int[] {
			0, 0, 0, 1, 1, 0, 0, 0, 0,
			0, 0, 0, 1, 1, 1, 0, 0, 0,
			0, 0, 1, 1, 1, 1, 1, 0, 0,
			0, 1, 1, 1, 1, 1, 1, 1, 1,
			1, 1, 1, 1, 1, 1, 1, 1, 1,
			1, 1, 1, 1, 1, 1, 1, 1, 0,
			0, 0, 1, 1, 1, 1, 1, 0, 0,
			0, 0, 0, 1, 1, 1, 0, 0, 0,
			0, 0, 0, 0, 1, 1, 0, 0, 0
	};



	public Table(int playerNum) {
		dashboard = new Cards[DASHBOARDDIM][DASHBOARDDIM];
		taps = new boolean[DASHBOARDDIM][DASHBOARDDIM];

		switch (playerNum) {
			case 2:
				for(int i = 0; i<DASHBOARDDIM*DASHBOARDDIM; i++) {
					taps[i/DASHBOARDDIM][i%DASHBOARDDIM] = (TwoPlayers[i] == 1);
				}
				break;
			case 3:
				for(int i = 0; i<DASHBOARDDIM*DASHBOARDDIM; i++) {
					taps[i/DASHBOARDDIM][i%DASHBOARDDIM] = (ThreePlayers[i] == 1);
				}
				break;
			case 4:
				for(int i = 0; i<DASHBOARDDIM*DASHBOARDDIM; i++) {
					taps[i/DASHBOARDDIM][i%DASHBOARDDIM] = (FourPlayers[i] == 1);
				}
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
				if (col < DASHBOARDDIM - 1 && checkout(row, col + 1) != null)  { return false;}
				if (row > 0 && checkout(row - 1, col) != null)  { return false;}
				if (row < DASHBOARDDIM - 1 && checkout(row +1, col) != null)  { return false;}
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
