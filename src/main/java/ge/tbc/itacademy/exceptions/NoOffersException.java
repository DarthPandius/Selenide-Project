package ge.tbc.itacademy.exceptions;

public class NoOffersException extends RuntimeException{
    public NoOffersException(String message) {
        super(message);
        System.out.println(message);
    }
}
