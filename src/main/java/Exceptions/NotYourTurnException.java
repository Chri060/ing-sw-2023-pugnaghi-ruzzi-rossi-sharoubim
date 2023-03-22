package Exceptions;

public class NotYourTurnException extends Throwable{
    public NotYourTurnException(String name) {
        System.out.println("It's " + name + " turn");
    }
}
