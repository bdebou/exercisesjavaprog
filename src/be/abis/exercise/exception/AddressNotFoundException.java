package be.abis.exercise.exception;

public class AddressNotFoundException extends RepositoryException {
    public AddressNotFoundException(){super("This address is not found");}
    public AddressNotFoundException(String message) {
        super(message);
    }
}
