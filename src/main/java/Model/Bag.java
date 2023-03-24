package Model;

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
			Object file = new JSONParser().parse(new FileReader("src/main/resources/Model/Bag.json"));
			JSONObject jsonObject = (JSONObject) file;
			numOfCards = ((Long) jsonObject.get("numberOfCardsOfEachType")).intValue();
		}
		catch (FileNotFoundException | ParseException e) {
		e	.printStackTrace();
		} catch (
		IOException e) {
			throw new RuntimeException(e);
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

	public Cards getCard() throws BagEmptyException{
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
