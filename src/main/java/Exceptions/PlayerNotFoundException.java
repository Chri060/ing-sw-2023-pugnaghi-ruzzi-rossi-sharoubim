package Exceptions;

public class PlayerNotFoundException extends Throwable{
    public PlayerNotFoundException(String name) {
        super(name + "Player not found in this lobby");
    }
}