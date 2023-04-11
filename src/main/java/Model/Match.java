package Model;

import Exceptions.*;
import Exceptions.MatchException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;

public class Match {

	public enum Event {
		WITHDRAW, //Notifies client requested cards where withdrawn from thw board
		NOTWITHDRAW, //Notifies client requested cards couldn't be withdrawn from thw board
		INSERT, // Notifies client that withdrawn cards were inserted in the library
		NOTINSERT, // Notifies client that withdrawn cards couldn't be inserted in the library (not enough space in column)
		COMMONOBJECTIVE, // Notifies about a common objective
		END, // Notifies all clients that the match is ended
		REFILL, // Notifies that the board have been refilled

		ERROR // Notifies client something went wrong

	};
	private int playerNum;
	private String chair;
	private Table dashboard;
	private Bag bag;
	private List<CommonObjective> commonObjectives;
	private List<Player> players;
	private Turn turn;
	private String firstToFinish;

	private boolean lastTurn;

	private final int NUMOFCOMMONOBJECTIVES = 12;

	private List<Cards> cardsToInsert;

	public Match(List<String> playerList) throws MatchException {
		int pObjNum;
		int cObjNum;
		int x;
		int numberpObj;
		Collections.shuffle(playerList);

		Random rand = new Random();
		List <Integer> chosenID = new ArrayList<>();
		List <Integer> chosenIDforPlayer = new ArrayList<>();
		try {
			Object file = new JSONParser().parse(new FileReader("src/main/resources/Model/config.json"));
			JSONObject jsonObject = (JSONObject) file;


			//Checks that there's a table config for the current list of players
			int maxNumOfPlayers = ((JSONArray) ((JSONObject) jsonObject.get("tableConfig")).get("patterns")).size() + 1;
			if (playerList.size() < 2 || playerList.size() > maxNumOfPlayers) {
				throw new MatchException("Number of players not supported: only 2 up to " + maxNumOfPlayers);
			}

			pObjNum = ((Long) jsonObject.get("privateObjectives")).intValue();
			cObjNum = ((Long) jsonObject.get("commonObjectives")).intValue();
			JSONObject jsonObj = (JSONObject) jsonObject.get("privateObjectivesConfig");
			numberpObj = ((JSONArray) jsonObj.get("patterns")).size();
			if (numberpObj < pObjNum * playerList.size()) {
				throw new MatchException("Bad JSON format: Not enough Private Objectives for all players" +
						"\nAvailable: " + numberpObj + ", Requested for each player: " + pObjNum +
						" number of players: " + playerList.size());
			}
			Library l = new Library();
			if (playerList.size() * l.getLibraryRows() * l.getLibraryCols() - playerList.size() + 1 > (new Bag()).cardsLeft()) {
				throw new MatchException("Bad JSON format: Not enough cards to guarantee the match to mathematically end");
			}
			if (NUMOFCOMMONOBJECTIVES < cObjNum) {
				throw new MatchException("Bad JSON format: Not enough common objectives available");
			}
			players = new ArrayList<>();
			for (int i = 0; i < playerList.size(); i++) {
				chosenIDforPlayer.clear();
				for (int j = 0; j < pObjNum;) {
					x = rand.nextInt(numberpObj);
					if (!chosenID.contains(x)) {
						chosenID.add(x);
						chosenIDforPlayer.add(x);
						j++;
					}
				}
				players.add(new Player(playerList.get(i), chosenIDforPlayer));
			}
			chair = players.get(rand.nextInt(players.size())).getName();
			bag = new Bag();
			dashboard = new Table(players.size());
			//TODO rivedere il refill iniziale
			dashboard.refill(bag);
			turn = new Turn(chair);
			commonObjectives = new CommonObjectiveFactory().chosenObjective(playerList.size());
			firstToFinish = null;
			playerNum = players.size();
			lastTurn = false;
		} catch (ParseException | NullPointerException | IOException | BagEmptyException e) {
			System.err.println("File JSON not found or badly formatted");
			System.exit(-1);
		}
	}

	public void nextAction() {
		turn.nextAction();
	}

	public void nextPlayer() {
		int x = - 1;
		for (int i = 0; i < players.size(); i++) {
			if (turn.getCurrentPlayer().equals(players.get(i).getName())) {
				x = i + 1;
			}
		}
		turn.changePlayer(players.get(x % players.size()).getName());
	}

	public String getChairPlayer() {
		return chair;
	}

	public String getCurrentPlayer() {
		return turn.getCurrentPlayer();
	}

	public Library getPlayerLibrary(String playerName) throws PlayerNotFoundException{
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getName().equals(playerName)) {
				return players.get(i).getLibrary();
			}
		}
		throw new PlayerNotFoundException(playerName);
	}

	public void insert(List<Cards> cardsList, int col, String playerName) throws NotEnoughSpaceInColumnException, InvalidPickException {
		for (Player player : players) {
			if (playerName.equals(player.getName())) {
				player.insert(cardsList, col);
			}
		}
	}

	public int PersonalObjPoints(String playerName) throws PlayerNotFoundException {
		for (Player player : players) {
			if (playerName.equals(player.getName())) {
				return player.personalObjPoints();
			}
		}
		throw new PlayerNotFoundException(playerName);
	}

	public List<Cards[][]> getPlayerObjPattern(String playerName) throws PlayerNotFoundException{
		for (int i = 0; i < players.size(); i++) {
			if (playerName.equals(players.get(i).getName())) {
				players.get(i).getObjPattern();
				return null;
			}
		}
		throw new PlayerNotFoundException(playerName);
	}

	public boolean needsRefill() {
		return dashboard.needsRefill();
	}

	public void refill() throws BagEmptyException {
		dashboard.refill(bag);
	}

	public boolean isLastTurn() {
		if (!lastTurn) {
			for (int i = 0; i < players.size(); i++) {
				if (players.get(i).getLibrary().isFull()) {
					firstToFinish = players.get(i).getName();
					lastTurn = true;
				}
			}
		}
		return lastTurn;
	}

	public boolean endMatch() {
		return (lastTurn && getCurrentPlayer().equals(getChairPlayer()));
	}

	public void withdraw(List<Integer> coordinates, String playerName) throws CannotWithdrawCardException, InvalidPickException {
		cardsToInsert = dashboard.withdraw(coordinates);
	}

	public List<CommonObjective>  getCommonObjectives () {
		return commonObjectives;
	}

	public List<Integer> getCommonObjectivesPoints(String playerName) throws PlayerNotFoundException {

		Library l = getPlayerLibrary(playerName);

		List<Integer> result = new ArrayList<>();
		for (CommonObjective obj : commonObjectives) {
			if (obj.getCompletion().contains(playerName)) {
				result.add(0);
			} else {
				if (obj.verify(l)) {
					obj.getCompletion().add(playerName);
					result.add(obj.getMaxAvaiblePoints());
				}
				else {
					result.add(0);
				}
			}
		}
		return result;
	}


	//test Only
	public void printDashboard() {
		System.out.println("Turn n°: " + turn.getTurn());
		dashboard.printCards();
	}

	//test only
	public int getCardsLeft(){
		return bag.cardsLeft();
	}

	//test only
	public void printPlayerPoints() {
		System.out.println(firstToFinish + " finished first, will get an extra point");
		for (Player player : players) {
			System.out.println(player.getName() + "'s library:");
			printLibrary(player.getLibrary());
			System.out.println(player.getName() + "'s private obj points:\nPattern is:");
			player.getObjPattern();
			System.out.print("Points: " + player.personalObjPoints());
			System.out.println();
			System.out.println();
		}
	}

	//test only
	public void printLibrary(Library l) {
		Cards[][] c = l.getAsMatrix();
		for (int i = 0; i < l.getLibraryRows(); i++) {
			for (int j = 0; j < l.getLibraryCols(); j++) {
				if (c[i][j] != null) {
					System.out.print(c[i][j].getType() + "\t");
				} else {
					System.out.print("null" + "\t");
				}
			}
			System.out.println();
		}
	}

	public Action getCurrentAction() {
		return turn.getCurrentAction();
	}

	public int getTurn() {
		return turn.getTurn();
	}

	public int getPlayerNum() {
		return playerNum;
	}

	public List<Cards> getCardsToInsert() {
		return cardsToInsert;
	}
}