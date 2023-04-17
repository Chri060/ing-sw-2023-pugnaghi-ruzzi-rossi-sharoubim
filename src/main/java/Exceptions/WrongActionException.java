package Exceptions;

import Model.Action;

public class WrongActionException extends Throwable{
    public WrongActionException(Action a) {
        super("Actual action is " + a);
    }
}
