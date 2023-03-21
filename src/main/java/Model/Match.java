package Model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Match {

	private String chair;

	private int playerNum;

	private Table dashboard;

	private Bag bag;

	private CommonObjective[] commonObjectives;

	private List<Player> players;

	private Turn turn;

	public Match(List<String> playerList) {
		int pObjNum;
		int cObjNum;
		int x;
		int numberpObj;

		Random rand = new Random();
		List <Integer> chosenID = new ArrayList<>();
		List <Integer> chosenIDforPlayer = new ArrayList<>();
		try {
			Object file = new JSONParser().parse(new FileReader("src/main/resources/Model/Match.json"));
			JSONObject jsonObject = (JSONObject) file;
			pObjNum = ((Long) jsonObject.get("privateObjectives")).intValue();
			cObjNum = ((Long) jsonObject.get("commonObjectives")).intValue();
			Object fileObj = new JSONParser().parse(new FileReader("src/main/resources/Model/PrivateObjective.json"));
			JSONObject jsonObj = (JSONObject) file;
			numberpObj = ((Long) jsonObj.get("numberOfpOjectives")).intValue();
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
		} catch (Exception e) {
		}
	}

	public void nextAction() {

	}

	public void nextPlayer() {

	}

	public String getChairPlayer() {
		return null;
	}

	public int PersonalObjPoints(String playerName) {
		return 0;
	}

	public CardsType getPlayerObjPattern(String playerName) {
		return null;
	}

	public boolean needsRefill() {
		return false;
	}

	public void refill() {

	}

}
