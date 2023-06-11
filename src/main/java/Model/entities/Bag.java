package Model.entities;

import Exceptions.InvalidActionException;
import util.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class used to represent the bag used to draw the cards to put in the dashboard
 */
public class Bag {

    private List<Card> cards;
    private int numberOfCardsOfEachType;

    /**
     * Construct a bag, filling it with the correct number of card from each type
     */
    public Bag() {
        numberOfCardsOfEachType = Config.getNumberOfCardsOfEachType();
        cards = new ArrayList<>();
        for (Card.Type type : Card.Type.values()) {
            for (int i = 0; i < numberOfCardsOfEachType; i++) {
                cards.add(new Card(type, i));
            }
        }
    }

    /**
     * @return a card and removes it from the bag
     *
     * @throws InvalidActionException on invalid action
     */
    public Card getCard() throws InvalidActionException {
        if (this.getNumberOfCardsLeft() == 0) {
            throw new InvalidActionException("Draw cards from the bag ", "the bag being empty");
        }
        Random random = new Random();
        return cards.remove(random.nextInt(cards.size()));
    }

    /**
     * @return the number of cards left in the bag
     */
    public int getNumberOfCardsLeft() {
        return cards.size();
    }

    /**
     * The value of numberOfCardsOfEachType is fixed at the start of the game
     *
     * @return the number of cards of each type
     */
    public int getNumberOfCardsOfEachType() {
        return numberOfCardsOfEachType;
    }
}