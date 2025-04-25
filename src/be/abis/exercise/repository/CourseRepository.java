package be.abis.exercise.repository;

import be.abis.exercise.exception.CourseAlreadyExistsException;
import be.abis.exercise.exception.CourseNotFoundException;
import be.abis.exercise.model.Course;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;

public interface CourseRepository {

    List<Course> findAllCourses();

    void addCourse(Course c) throws CourseAlreadyExistsException;

    Course findByName(String name) throws CourseNotFoundException;

    Course findByID(int id) throws CourseNotFoundException;

    String formatCourse(Course course);

    String printAllCourses();

    String printAllCourses(Locale locale);

    String printAllCourses(Locale locale, String datePattern);

    String printAllCourseAsTable();

    void printAllCourseAsTable(File targetFile);

    String printAllCourseAsTable(Locale locale);
}
