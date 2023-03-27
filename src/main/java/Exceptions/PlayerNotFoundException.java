package Exceptions;

public class PlayerNotFoundException extends Throwable{
    public PlayerNotFoundException() {
        super("Player not found in this lobby");
    }
}