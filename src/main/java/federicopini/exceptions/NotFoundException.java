package federicopini.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(int id) { super("Nessun elemento con ID " + id); }
}
