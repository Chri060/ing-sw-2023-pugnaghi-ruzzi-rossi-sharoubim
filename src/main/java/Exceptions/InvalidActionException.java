package Exceptions;

public class InvalidActionException extends Throwable{
    public InvalidActionException(String action, String motivation) {
        super(action + " cannot be performed due to " + motivation);
    }
}
