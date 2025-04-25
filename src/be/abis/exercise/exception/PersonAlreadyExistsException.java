package be.abis.exercise.exception;

public class PersonAlreadyExistsException extends RepositoryException {
    public PersonAlreadyExistsException(){super("This person already exists");}
    public PersonAlreadyExistsException(String message) {
        super(message);
    }
}
