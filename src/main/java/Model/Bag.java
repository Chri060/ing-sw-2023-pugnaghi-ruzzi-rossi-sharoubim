package Model;

import java.util.HashMap;
import java.util.Random;

public class Bag {
	final int NUMBEROFCARDS = 22;
	private HashMap<Cards, Integer> cardsLeft;
	private Random rand;
	public Bag() {
		cardsLeft = new HashMap<Cards, Integer>();
		for (Cards c : Cards.values()) {
			cardsLeft.put(c, NUMBEROFCARDS);
		}
		rand = new Random();
	}

	public int getNumberOf(Cards card) {
		return cardsLeft.get(card);
	}

	private boolean containsSomething() {
		for (Cards c : Cards.values()) {
			if (getNumberOf(c) > 0) {
				return true;
			}
		}
		return false;
	}

	private void remove(Cards card) {
		cardsLeft.replace(card, cardsLeft.get(card) - 1);
	}

	public Cards getCard() {
		int j;
		Cards card;

		while (containsSomething()) {
			j = rand.nextInt(Cards.values().length);
			card = Cards.values()[j];
			if (this.getNumberOf(card) > 0) {
				remove(card);
				return card;
			}
		}
		return null;
	}

}
