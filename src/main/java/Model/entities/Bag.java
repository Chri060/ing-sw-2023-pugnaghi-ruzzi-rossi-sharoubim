package Model.entities;

import Exceptions.InvalidActionException;
import util.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bag {

    private List<Card> cards;
    private int numberOfCardsOfEachType;

    public Bag() {
        numberOfCardsOfEachType = Config.getNumberOfCardsOfEachType();
        cards = new ArrayList<>();
        for (Card.Type type : Card.Type.values()) {
            for (int i = 0; i < numberOfCardsOfEachType; i++) {
                cards.add(new Card(type, i));
            }
        }
    }

    public Card getCard() throws InvalidActionException {
        if (this.getNumberOfCardsLeft() == 0) {
            throw new InvalidActionException("Draw cards from the bag ", "the bag being empty");
        }
        Random random = new Random();
        return cards.remove(random.nextInt(cards.size()));
    }

    public int getNumberOfCardsLeft() {
        return cards.size();
    }

    public int getNumberOfCardsOfEachType() {
        return numberOfCardsOfEachType;
    }
}
