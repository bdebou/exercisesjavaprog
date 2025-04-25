package be.abis.exercise.exception;

public class CompanyNotFoundException extends RepositoryException {
    public CompanyNotFoundException() {
        super("This company is not found");
    }

    public CompanyNotFoundException(String message) {
        super(message);
    }
}
