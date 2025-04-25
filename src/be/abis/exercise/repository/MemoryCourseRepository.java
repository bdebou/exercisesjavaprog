package be.abis.exercise.repository;

import be.abis.exercise.exception.CourseAlreadyExistsException;
import be.abis.exercise.model.Course;

import java.time.LocalDate;

public class MemoryCourseRepository extends CourseCommonRepository {
    public MemoryCourseRepository() throws CourseAlreadyExistsException {
        super();
        this.addCourse(new Course("DB2, an overview", 5, 550.0, LocalDate.of(1986, 4, 30)));
        this.addCourse(new Course("Workshop SQL", 2, 475.0, LocalDate.of(1990, 1, 9)));
        this.addCourse(new Course("Java Programming", 5, 500.0, LocalDate.of(1997, 5, 27)));
        this.addCourse(new Course("Maven", 1, 450.0, LocalDate.of(2007, 6, 11)));
        this.addCourse(new Course("Programming with Spring", 3, 525.0, LocalDate.of(2008, 3, 21)));
    }

}
