package Exceptions;

import java.util.List;

public class CannotWithdrawCardException extends  Throwable{
    public CannotWithdrawCardException(List<Integer> coordinates) {
        super("Cannot withdraw in" + coordinates);
    }
    public CannotWithdrawCardException(int maxSpace) {super("Not enough space in your shelf, max drawable cards: " + maxSpace);}
}
