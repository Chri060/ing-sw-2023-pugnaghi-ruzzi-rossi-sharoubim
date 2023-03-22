package Exceptions;

public class InvalidPickException extends Throwable{
    public InvalidPickException() {
        super("Coordinates for card pick from dashbord are invalid");
    }
}
