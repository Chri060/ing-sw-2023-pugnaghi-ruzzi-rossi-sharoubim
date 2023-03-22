package Exceptions;

public class IncorrectPlayersNumberException extends Throwable{
    public IncorrectPlayersNumberException() {
        super("Only 2 up to 4 player supported");
    }
}
