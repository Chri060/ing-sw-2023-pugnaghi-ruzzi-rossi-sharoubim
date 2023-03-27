package Exceptions;

public class ColumFullException extends Throwable {
    public  ColumFullException(int col) {
        super("Cannot insert in " +  "column " + col);
    }
}