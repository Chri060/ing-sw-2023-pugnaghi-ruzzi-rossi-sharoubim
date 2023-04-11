package Exceptions;

public class InvalidPickException extends Throwable{
    public InvalidPickException() {
        super("Coordinates are invalid");
    }
}