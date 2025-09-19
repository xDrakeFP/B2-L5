package federicopini.exceptions;

public class DuplicateIdException extends RuntimeException {
    public DuplicateIdException(int id) { super("Esiste già un gioco con ID " + id); }
}
