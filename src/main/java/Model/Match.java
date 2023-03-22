package Model;

import Exceptions.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Match {

	private String chair;
	private Table dashboard;
	private Bag bag;
	private List<CommonObjective> commonObjectives;
	private List<Player> players;
	private Turn turn;

	public Match(List<String> playerList) throws NotEnoughPrivateObjectivesException, NotEnoughPlayersException{
		int pObjNum;
		int cObjNum;
		int x;
		int numberpObj;

		if (playerList.size() < 2) {
			throw new NotEnoughPlayersException();
		}

		Random rand = new Random();
		List <Integer> chosenID = new ArrayList<>();
		List <Integer> chosenIDforPlayer = new ArrayList<>();
		try {
			Object file = new JSONParser().parse(new FileReader("src/main/resources/Model/Match.json"));
			JSONObject jsonObject = (JSONObject) file;
			pObjNum = ((Long) jsonObject.get("privateObjectives")).intValue();
			cObjNum = ((Long) jsonObject.get("commonObjectives")).intValue();
			Object fileObj = new JSONParser().parse(new FileReader("src/main/resources/Model/PrivateObjective.json"));
			JSONObject jsonObj = (JSONObject) fileObj;
			numberpObj = ((Long) jsonObj.get("numberOfpOjectives")).intValue();;
			if (numberpObj < pObjNum * playerList.size()) {
				throw new NotEnoughPrivateObjectivesException();
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
			turn = new Turn(chair);
		} catch (FileNotFoundException | ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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

	public String getCurrentPlayer() {return turn.getCurrentPlayer();}

	public Library getPlayerLibrary(String playerName) throws PlayerNotFoundException{
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getName().equals(playerName)) {
				return players.get(i).getLibrary();
			}
		}
		throw new PlayerNotFoundException();
	}

	public void insert(List<Cards> cardsList, int col, String playerName) throws ColumFullException {
		for (int i = 0; i < players.size(); i++) {
			if (playerName.equals(players.get(i).getName())) {
				players.get(i).insert(cardsList, col);
			}
		}
	}

	public int PersonalObjPoints(String playerName) throws PlayerNotFoundException {
		for (int i = 0; i < players.size(); i++) {
			if (playerName.equals(players.get(i).getName())) {
				return players.get(i).personalObjPoints();
			}
		}
		throw new PlayerNotFoundException();
	}

	//TODO: solito problema del pattern
	public List<Cards[][]> getPlayerObjPattern(String playerName) throws PlayerNotFoundException{
		for (int i = 0; i < players.size(); i++) {
			if (playerName.equals(players.get(i).getName())) {
				players.get(i).getObjPattern();
				return null;
			}
		}
		throw new PlayerNotFoundException();
	}

	public boolean needsRefill() {
		return dashboard.needsRefill();
	}

	public void refill() throws BagEmptyException {
		dashboard.refill(bag);
	}

	public boolean endMatch() {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getLibrary().isFull()) {
				return true;
			}
		}
		return false;
	}

	public List<Cards> withdraw(List<Integer> coordinates) throws CannotWIthdrowCardException, InvalidPickException {
		return dashboard.withdraw(coordinates);
	}

	//test Only
	public void printDashboard() {
		System.out.println("Turn n°: " + turn.getTurn());
		dashboard.printCards();
	}
}
