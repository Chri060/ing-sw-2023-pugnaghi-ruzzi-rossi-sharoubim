package Model;

public class Cards {
    CardsType type;
    private int cardID;

    public Cards(CardsType type, int id) {
        this.type = type;
        this.cardID = id;
    }
    public int getCardID() {
        return cardID;
    }

    public CardsType getType() {
        return type;
    }
}

