package Exceptions;

/**
 * Exception for problems with the actions
 */
public class InvalidActionException extends Throwable{

    /**
     * Exception thrown if  someone does a wrong action
     *
     * @param action is the action that the player tried
     * @param motivation is the motivation why the action is invalid
     */
    public InvalidActionException(String action, String motivation) {
        super(action + " cannot be performed due to " + motivation);
    }
}