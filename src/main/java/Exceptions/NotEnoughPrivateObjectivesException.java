package Exceptions;

public class NotEnoughPrivateObjectivesException extends Throwable{
    public NotEnoughPrivateObjectivesException() {
        super("Not enough private objective for all players");
    }
}
