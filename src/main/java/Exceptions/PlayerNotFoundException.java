package Exceptions;

public class PlayerNotFoundException extends Throwable{
    public PlayerNotFoundException() {
        System.out.println("Player not found in this lobby");
    }
}
