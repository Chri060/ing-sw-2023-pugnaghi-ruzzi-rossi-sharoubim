package Exceptions.parser;

public class BadJSONFormatException extends Throwable{
    public BadJSONFormatException(String message) {
        super(message);
    }
}
