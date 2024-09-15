package ge.tbc.itacademy.exceptions;

public class NoSuchSectionException extends RuntimeException{
    public NoSuchSectionException(String message) {
        super(message);
        System.out.println(message);
    }
}
