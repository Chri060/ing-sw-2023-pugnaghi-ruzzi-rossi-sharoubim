package Model;

import Controller.DrawTile;
import Controller.EndOfAction;
import Exceptions.BagEmptyException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bag {
	private int numOfCards;
	private List<Cards> cardsLeft;
	private Random rand;



	public Bag() {
		rand = new Random();
		cardsLeft = new ArrayList<Cards>();
		try {
			Object file = new JSONParser().parse(new FileReader("src/main/resources/Model/config.json"));
			JSONObject jsonObject = (JSONObject) file;
			numOfCards = ((Long) jsonObject.get("numberOfCardsOfEachType")).intValue();
		}
		catch (IOException | ParseException | NullPointerException | ClassCastException e) {
			System.err.println("Bad JSON format: invalid entries for Bag object");
			System.exit(-1);
		}
		if (numOfCards <= 0) {
			System.err.println("Bad JSON format: numberOfCardsOfEachType must be more than 0");
			System.exit(-1);
		}
		for (CardsType c : CardsType.values()) {
			for (int i = 0; i < numOfCards; i++) {
				cardsLeft.add(new Cards(c, i));
			}
		}

	}

	public int getNumOfCards() {
		return numOfCards;
	}

	public int cardsLeft(){
		return cardsLeft.size();
	}

	public Cards getCard() throws BagEmptyException {
		int j;
		if (cardsLeft.size() > 0) {
			j = rand.nextInt(cardsLeft.size());
			return cardsLeft.remove(j);
		}
		else {
			throw new BagEmptyException() ;
		}
	}
}
