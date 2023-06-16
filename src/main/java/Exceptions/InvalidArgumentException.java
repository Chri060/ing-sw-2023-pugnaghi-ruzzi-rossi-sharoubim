package Exceptions;

/**
 * Exception for problems with the argument used
 */
public class InvalidArgumentException extends Throwable{

    /**
     * Exception thrown when there is an invalid argument used
     *
     * @param message is the error message with the details
     */
    public InvalidArgumentException(String message) {
        super(message);
    }
}