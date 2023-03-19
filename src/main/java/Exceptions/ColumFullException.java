package Exceptions;

public class ColumFullException extends Throwable {
    public  ColumFullException(int col) {
        System.err.println("Cannot insert in " + (col + 1) + "° column");
    }

}
