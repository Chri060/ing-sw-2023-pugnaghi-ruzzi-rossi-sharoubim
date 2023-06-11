package Model.entities;

import java.io.Serializable;

/**
 *  Class used to represent the possible cards types
 */
public class Card implements Serializable {

    /**
     * Enumeration that contains all type of card possible
     */
    public enum Type{
        CAT,
        BOOK,
        GAME,
        FRAME,
        TROPHY,
        PLANT
    }
    private int id;
    private Type type;

    /**
     * Construct a single card with is type and a unique ID (for the type)
     *
     * @param type is the actual type of the card (a value from the enumeration)
     * @param id is the integer used to identify the card (unique for each type)
     */
    public Card (Card.Type type, int id) {
        this.type = type;
        this.id = id;
    }

    /**
     * @return the type of the card in this
     */
    public Card.Type getType() {
        return this.type;
    }

    /**
     * @return the ID of the card in this
     */
    public int getId() {
        return this.id;
    }

    /**
     * Compare this with another card (compares both type and ID)
     *
     * @param card is the card to compare to this
     *
     * @return true if the card is equal to this.card, false otherwise
     */
    public boolean equals(Card card) {
        return (this.equalsType(card) && this.id == card.id);
    }

    /**
     * Compare this with another card (compares only type)
     *
     * @param card is the card to compare to this
     *
     * @return true if the card is equal to this.card, false otherwise
     */
    public boolean equalsType(Card card) {
        return (this.type == card.type);
    }

    /**
     * Compare this with another card (compares only type)
     *
     * @param type is the type of the card to compare to this
     *
     * @return true if the card is equal to this.card, false otherwise
     */
    public boolean equalsType(Card.Type type) {
        return (this.type == type);
    }
}