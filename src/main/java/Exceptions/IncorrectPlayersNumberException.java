package Exceptions;

public class IncorrectPlayersNumberException extends Throwable{
    public IncorrectPlayersNumberException() {
        System.out.println("Only 2 up to 4 player supported");
    }
}
