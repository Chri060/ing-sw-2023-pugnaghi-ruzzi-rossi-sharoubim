package Model.entities;

import java.io.Serial;
import java.io.Serializable;

public class Card implements Serializable {

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

    public Card(Card.Type type, int id) {
        this.type = type;
        this.id = id;
    }

    /**
     * getter method for card type
     * */
    public Card.Type getType() {
        return this.type;
    }

    /**
     * getter method for card id*/
    public int getId() {
        return id;
    }

    public boolean equals(Card card) {
        return (this.equalsType(card) && this.id == card.id);
    }

    public boolean equalsType(Card card) {
        return (this.type == card.type);
    }

    public boolean equalsType(Card.Type type) {
        return (this.type == type);
    }

}
