package be.abis.exercise.exception;

public class CourseNotFoundException extends RepositoryException {
    public CourseNotFoundException() {
        super("This course is not found");
    }

    public CourseNotFoundException(String message) {
        super(message);
    }
}
