package Exceptions;

public class BagEmptyException extends Throwable{
    public BagEmptyException() {
        System.err.println("Bag Empty");
    }
}
