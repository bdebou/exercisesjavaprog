package be.abis.exercise.exception;

public class MissingTokenException extends RepositoryException {
    public MissingTokenException() {
        super("Missing Token ! expected 3");
    }

    public MissingTokenException(String message) {
        super(message);
    }
}
