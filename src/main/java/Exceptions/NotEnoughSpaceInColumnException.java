package Exceptions;

public class NotEnoughSpaceInColumnException extends Throwable {
    public NotEnoughSpaceInColumnException(int col) {
        super("Cannot insert in column " + col);
    }
}