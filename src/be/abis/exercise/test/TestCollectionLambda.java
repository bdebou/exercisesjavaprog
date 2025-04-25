package be.abis.exercise.test;

import be.abis.exercise.exception.CourseAlreadyExistsException;
import be.abis.exercise.model.Course;
import be.abis.exercise.repository.CourseRepository;
import be.abis.exercise.repository.MemoryCourseRepository;
import org.w3c.dom.ls.LSOutput;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestCollectionLambda {
    public static void main(String[] args) {
        CourseRepository cr = null;
        try {
            cr = new MemoryCourseRepository();
        } catch (CourseAlreadyExistsException e) {
            TestMain5.EXCEPTION_LOGGER.fatal(e);
            throw new RuntimeException(e);
        }
        List<Course> courses = cr.findAllCourses();

        Comparator<Course> comparatorByTitle = Comparator.comparing(Course::getTitle);
        Comparator<Course> comparatorByDurationAndPrice = (c1, c2) -> {
            int result = c1.getDays() - c2.getDays();
            if (result == 0) {
                return (int) (c1.getDailyPrice() - c2.getDailyPrice());
            }
            return result;
        };
        System.out.println("----------with Comparable<Course>-----------");
        courses.sort(Course::compareTo);
        courses.forEach(System.out::println);
        System.out.println("----------with Lambda function-----------");
        courses.sort(comparatorByTitle);
        courses.forEach(System.out::println);
        System.out.println("----------with lambda function-----------");
        courses.sort(comparatorByDurationAndPrice);
        courses.forEach(System.out::println);
        System.out.println("----------Remove the course with less than 3 days");
        courses.removeIf(c -> c.getDays() < 3);
        courses.forEach(System.out::println);
        System.out.println("----------Map<String,Double>-------------");
        Map<String, Double> result = new HashMap<>();
        courses.forEach(c -> result.put(c.getTitle(), c.getDailyPrice()));
        for (Course course:courses){
            result.computeIfPresent(course.getTitle(),(k, v) -> v * 1.1);
        }
        result.forEach((k, v) -> System.out.println("<" + k + "> <" + v + ">"));
    }
}
