package Exceptions;

import java.util.List;

public class CannotWIthdrowCardException extends  Throwable{
    public  CannotWIthdrowCardException(List<Integer> coordinates) {
        super("Cannot withdraw in" + coordinates);
    }

}
