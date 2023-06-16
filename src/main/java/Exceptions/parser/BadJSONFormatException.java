package Exceptions.parser;

/**
 * Exception for problems with the parsing to the JSON configs
 */
public class BadJSONFormatException extends Throwable{

    /**
     * Construct the exception
     *
     * @param message is a string with the error message
     */
    public BadJSONFormatException(String message) {
        super(message);
    }
}