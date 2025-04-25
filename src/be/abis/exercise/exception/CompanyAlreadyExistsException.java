package be.abis.exercise.exception;

public class CompanyAlreadyExistsException extends RepositoryException {
    public CompanyAlreadyExistsException(){super("This company already exists");}
    public CompanyAlreadyExistsException(String message) {
        super(message);
    }
}
