package Model;

import Exceptions.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Match {
	private String chair;
	private Table dashboard;
	private Bag bag;
	private List<CommonObjective> commonObjectives;
	private List<Player> players;
	private Turn turn;
	private String firstToFinish;

	public Match(List<String> playerList) throws NotEnoughPrivateObjectivesException, IncorrectPlayersNumberException {
		int pObjNum;
		int cObjNum;
		int x;
		int numberpObj;

		Collections.shuffle(playerList);

		if (playerList.size() < 2 || playerList.size() > 4) {
			throw new IncorrectPlayersNumberException();
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

	public void insert(List<Cards> cardsList, int col, String playerName) throws ColumFullException, NotYourTurnException {
		if (playerName.equals(turn.getCurrentPlayer())) {
			for (int i = 0; i < players.size(); i++) {
				if (playerName.equals(players.get(i).getName())) {
					players.get(i).insert(cardsList, col);
				}
			}
		}
		else {
			throw new NotYourTurnException(turn.getCurrentPlayer());
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
				firstToFinish = players.get(i).getName();
				return true;
			}
		}
		return false;
	}

	public List<Cards> withdraw(List<Integer> coordinates, String playerName) throws CannotWithdrawCardException, InvalidPickException, NotYourTurnException {
		if (playerName.equals(turn.getCurrentPlayer()))
		return dashboard.withdraw(coordinates);
		else {
			throw new NotYourTurnException(turn.getCurrentPlayer());
		}
	}

	public void setCommonObjectives (List<CommonObjective> objectives) {
		commonObjectives = objectives;
	}


	public List<CommonObjective>  getCommonObjectives () {
		return commonObjectives;
	}



	//test Only
	public void printDashboard() {
		System.out.println("Turn n°: " + turn.getTurn());
		dashboard.printCards();
	}

	//Test only
	public int getCardsLeft(){
		return bag.cardsLeft();
	}

	//test only
	public void printPlayerPoints() {
		System.out.println(firstToFinish + " finisched first, will get an extra point");
		for (int i = 0; i < players.size(); i++) {
			System.out.println(players.get(i).getName() + "'s library:");
			printLibrary(players.get(i).getLibrary());
			System.out.println(players.get(i).getName() + "'s private obj points:\nPattern is:");
			players.get(i).getObjPattern();
			System.out.print("Points: " + players.get(i).personalObjPoints());
			System.out.println();
			System.out.println();
		}
	}

	//test only
	void printLibrary(Library l) {
		Cards[][] c = l.getAsMatrix();
		for (int i = 0; i < l.getLibraryrows(); i++) {
			for (int j = 0; j < l.getLibrarycols(); j++) {
				if (c[i][j] != null) {
					System.out.print(c[i][j].getType() + "\t");
				}
				else {
					System.out.print("null" + "\t");
				}
			}
			System.out.println();
		}
	}


}
