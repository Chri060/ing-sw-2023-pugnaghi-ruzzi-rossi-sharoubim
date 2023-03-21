package Model;

public class Cards {
    CardsType type;
    int cardID;

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

