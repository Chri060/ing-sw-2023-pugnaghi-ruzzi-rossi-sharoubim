package Model;

import Exceptions.BagEmptyException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bag {
	final int NUMBEROFCARDS = 22;
	private List<Cards> cardsLeft;
	private Random rand;

	public Bag() {
		rand = new Random();
		cardsLeft = new ArrayList<Cards>();
		for (CardsType c : CardsType.values()) {
			for (int i = 0; i < NUMBEROFCARDS; i++) {
				cardsLeft.add(new Cards(c, i));
			}
		}
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
