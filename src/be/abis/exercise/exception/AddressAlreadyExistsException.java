package be.abis.exercise.exception;

public class AddressAlreadyExistsException extends RepositoryException {
    public AddressAlreadyExistsException(){super("This address already exists");}
    public AddressAlreadyExistsException(String message) {
        super(message);
    }
}
