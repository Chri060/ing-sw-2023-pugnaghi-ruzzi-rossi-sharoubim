package Exceptions;

public class NotYourTurnException extends Throwable{
    public NotYourTurnException(String name) {
        super("It's " + name + " turn");
    }
}