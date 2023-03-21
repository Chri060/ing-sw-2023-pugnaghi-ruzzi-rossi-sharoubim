package Exceptions;

public class ColumFullException extends Throwable {
    public  ColumFullException(int col) {
        System.err.println("Cannot insert in " +  "column " + col);
    }

}
