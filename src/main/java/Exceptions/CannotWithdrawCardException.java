package Exceptions;

import java.util.List;

public class CannotWithdrawCardException extends  Throwable{
    public CannotWithdrawCardException(List<Integer> coordinates) {
        super("Cannot withdraw in" + coordinates);
    }
}
