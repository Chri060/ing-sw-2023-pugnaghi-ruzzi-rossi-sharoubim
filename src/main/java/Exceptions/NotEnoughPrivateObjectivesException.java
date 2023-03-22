package Exceptions;

public class NotEnoughPrivateObjectivesException extends Throwable{
    public NotEnoughPrivateObjectivesException() {
        System.out.println("Not enough private objective for all players");
    }
}
