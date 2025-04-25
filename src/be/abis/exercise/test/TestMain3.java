package be.abis.exercise.test;

import be.abis.exercise.exception.RepositoryException;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.CourseRepository;
import be.abis.exercise.repository.PersonRepository;
import be.abis.exercise.services.UnitOfWork;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class TestMain3 {
    public static void main(String[] args) {
        try {
            System.setProperty("file.encoding", "UTF-8");
            System.out.println("Symbole €");
            UnitOfWork unitOfWork = new UnitOfWork();
            CourseRepository courseRepository = unitOfWork.getCourseRepository();

            System.out.println("----- 1 -----");
            System.out.println(courseRepository.findByID(1).formatCSV());
            System.out.println("----- 2 -----");
            System.out.println(courseRepository.printAllCourses());
            System.out.println("----- 3 -----");
            PersonRepository personRepository = unitOfWork.getPersonRepository();
            for (Person person : personRepository.findAllPersons()) {
                System.out.println(person.getFirstName() + " " + person.getLastName() + " is " + person.calculateAge() + " years old.");
            }

            System.out.println("----- 4a -----");
            LocalDate localDate = LocalDate.now();
            Period durationToAdd = Period.of(3, 2, 15);
            System.out.println(LocalDate.now().plus(durationToAdd).getDayOfWeek());
            System.out.println("----- 4b -----");
            LocalDate dob = LocalDate.of(1980, 10, 15);
            System.out.println(dob.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.FRENCH));
            System.out.println("----- 4c -----");
            LocalDate nextBD = dob.withYear(2025);
            long diff = ChronoUnit.DAYS.between(localDate, nextBD);
            System.out.println("Again " + diff + " days before my Birthday");
            System.out.println("----- 4d -----");
            System.out.println("I lived " + ChronoUnit.DAYS.between(dob, localDate) + " days.");
            System.out.println("----- 5 -----");
            System.out.println(courseRepository.printAllCourses(new Locale("nl","NL")));
            System.out.println(courseRepository.printAllCourses(Locale.FRANCE));
            System.out.println(courseRepository.printAllCourseAsTable());
            System.out.println(courseRepository.printAllCourseAsTable(Locale.FRANCE));
            System.out.println(courseRepository.printAllCourseAsTable(Locale.ENGLISH));
            System.out.println(courseRepository.printAllCourseAsTable(Locale.of("es")));
            System.out.println(courseRepository.printAllCourseAsTable(Locale.of("it")));
            System.out.println(courseRepository.printAllCourseAsTable(Locale.of("nl","BE")));
            courseRepository.printAllCourseAsTable(Path.of("C:\\temp\\printout.txt").toFile());
        } catch (RepositoryException e) {
            System.out.println(e.getMessage());
        }

    }
}
