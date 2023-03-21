package Exceptions;

public class CannotWIthdrowCardException extends  Throwable{
    public  CannotWIthdrowCardException(int row, int col) {
        System.err.println("Cannot withdraw in " + row + "° row, " + (col) + "° column");
    }

}
