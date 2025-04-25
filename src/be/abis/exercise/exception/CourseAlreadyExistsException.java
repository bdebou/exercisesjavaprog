package be.abis.exercise.exception;

public class CourseAlreadyExistsException extends  RepositoryException{
    public CourseAlreadyExistsException(){super("This course already exists");}
    public CourseAlreadyExistsException(String message) {
        super(message);
    }
}
