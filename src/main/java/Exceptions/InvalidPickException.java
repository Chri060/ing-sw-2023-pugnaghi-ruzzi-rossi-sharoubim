package Exceptions;

public class InvalidPickException extends Throwable{
    public InvalidPickException() {
        System.out.println("Coordinates for card pick from dashbord are invalid");
    }
}
