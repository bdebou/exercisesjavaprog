package be.abis.exercise.exception;

public class PersonNotFoundException extends RepositoryException {
    public PersonNotFoundException() {
        super("This Person is not found");
    }

    public PersonNotFoundException(String message) {
        super(message);
    }
}
