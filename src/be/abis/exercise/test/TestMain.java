package be.abis.exercise.test;

import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.model.Course;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.CourseRepository;
import be.abis.exercise.repository.PersonRepository;
import be.abis.exercise.services.UnitOfWork;

import java.util.List;

public class TestMain {

    public static void main(String[] args) {

        UnitOfWork unitOfWork = new UnitOfWork();

        PersonRepository pr = unitOfWork.getPersonRepository();
        List<Person> persons = pr.findAllPersons();

        System.out.println("All persons:");
        persons.forEach(System.out::println);

        CourseRepository cr = unitOfWork.getCourseRepository();
        List<Course> courses = cr.findAllCourses();

        System.out.println("\nAll courses:");
        courses.forEach(System.out::println);
        System.out.println("---------------------------");
        try {
            System.out.println(pr.findPersonById(1));
            System.out.println(pr.findPersonById(4054));
        } catch (PersonNotFoundException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        try {
            System.out.println(pr.findPersonByEmailAndPassword("blemarcq@abis.be", "somepass4"));
            System.out.println(pr.findPersonByEmailAndPassword("bleBADmarcq@abis.be", "somepass4"));
        } catch (PersonNotFoundException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        try {
            System.out.println(pr.findPersonsForCompany("ABIS"));
            System.out.println(pr.findPersonsForCompany("AbiS"));
            System.out.println(pr.findPersonsForCompany("ING"));
        } catch (PersonNotFoundException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
