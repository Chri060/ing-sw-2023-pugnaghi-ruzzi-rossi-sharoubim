package Model;

import java.util.Random;

public class Table {
	final int DASHBOARDDIM = 9;
	private Cards[][] dashboard;
	private boolean[][] taps;

	static int[] TwoPlayers = new int[] {
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


	static int[] ThreePlayers = new int[] {
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


	static int[] FourPlayers = new int[] {
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
			case 3:
				for(int i = 0; i<DASHBOARDDIM*DASHBOARDDIM; i++) {
					taps[i/DASHBOARDDIM][i%DASHBOARDDIM] = (ThreePlayers[i] == 1);
				}
				break;
			case 4:
				for(int i = 0; i<DASHBOARDDIM*DASHBOARDDIM; i++) {
					taps[i/DASHBOARDDIM][i%DASHBOARDDIM] = (FourPlayers[i] == 1);
				}
				break;
			case 2:
				for(int i = 0; i<DASHBOARDDIM*DASHBOARDDIM; i++) {
					taps[i/DASHBOARDDIM][i%DASHBOARDDIM] = (TwoPlayers[i] == 1);
				}
		}


	}
	public void refill(Bag bag) {
		for (int i = 0; i<DASHBOARDDIM; i++) {
			for (int j = 0; j<DASHBOARDDIM; j++) {
				if (taps[i][j] && dashboard[i][j] == null) {
					dashboard[i][j] = bag.getCard();
				}
			}
		}
	}

	public Cards checkout(int x, int y) {
		return dashboard[x][y];
	}

	private Cards take(int x, int y) {
		Cards c = dashboard[x][y];
		dashboard[x][y] = null;
		return  c;
	}

/*	public arrayList Cards withdraw() {
		list Cards l;
		for ...
			l.put(take(x,y));

		retunr l;
	}*/

	public boolean needsRefill() {
		return false;
	}

	//test only
	public void printTaps() {
		for(int i = 0; i<DASHBOARDDIM; i++) {
			for(int j = 0; j<DASHBOARDDIM; j++) {
				System.out.print(taps[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	//test only
	public void printCards() {
		for(int i = 0; i<DASHBOARDDIM; i++) {
			for(int j = 0; j<DASHBOARDDIM; j++) {
				System.out.print(dashboard[i][j] + "\t");
			}
			System.out.println();
		}
	}

}
