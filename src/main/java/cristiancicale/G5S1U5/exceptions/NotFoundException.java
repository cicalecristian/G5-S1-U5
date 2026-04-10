package cristiancicale.G5S1U5.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {
        super("La risorsa con id " + id + " non è stata trovata!");
    }
}
