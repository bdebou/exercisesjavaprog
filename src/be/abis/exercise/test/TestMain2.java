package be.abis.exercise.test;

import be.abis.exercise.exception.*;
import be.abis.exercise.model.Course;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.CourseRepository;
import be.abis.exercise.repository.PersonRepository;
import be.abis.exercise.services.UnitOfWork;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

public class TestMain2 {
    public static void main(String[] args) {

        try {
            UnitOfWork unitOfWork = new UnitOfWork(new Faker(),5000,1000,100000);

            PersonRepository pr = unitOfWork.getPersonRepository();
            List<Person> persons = pr.findAllPersons();

            System.out.println("All persons:");
            persons.forEach(System.out::println);

            CourseRepository cr = unitOfWork.getCourseRepository();
            List<Course> courses = cr.findAllCourses();

            System.out.println("\nAll courses:");
            courses.forEach(System.out::println);

            System.out.println("----------------------");

            System.out.println("A- Select all persons whose last name starts with S. Sort them on alphabetical order of first name.");
            pr.findAllPersons().stream()
                    .filter(p -> p.getLastName().startsWith("S"))
                    .sorted(Comparator.comparing(Person::getFirstName))
                    .forEach(System.out::println);
            System.out.println("B- Print a list of all distinct companies.");
            pr.findAllPersons().stream()
                    .map(Person::getCompany)
                    .filter(Objects::nonNull)
                    .distinct()
                    .forEach(System.out::println);
            System.out.println("C- How many persons are there in the list that work in Leuven?");
            long result = pr.findAllPersons().stream()
                    .filter(p -> p.getCompany() != null)
                    .filter(p -> "Leuven".equalsIgnoreCase(p.getCompany().getAddress().getTown()))
                    .count();
            System.out.printf("There are %1$s persons working in Leuven%n", result);

            ToIntFunction<Person> toAge = s -> Period.between(s.getBirthDate(), LocalDate.now()).getYears();
            try {
                System.out.println("D- Who is the youngest person?");
                Function<Person, LocalDate> getBirthday = Person::getBirthDate;
                System.out.println(pr.findAllPersons().stream()
//                    .map(Person::getBirthDate)
                        .min(Comparator.comparingInt(toAge))
                        .orElseThrow(() -> new PersonNotFoundException("no youngest")));
            } catch (PersonNotFoundException e) {
                throw new RuntimeException(e);
            }

            System.out.println("E- Group all persons per company.");
            pr.findAllPersons().stream()
                    .filter(p -> p.getCompany() != null)
                    .collect(Collectors.groupingBy(Person::getCompany, Collectors.toList()))
                    .forEach((c, p) -> {
                        System.out.println(c.getName());
                        p.forEach(System.out::println);
                    });
            System.out.println("F- How many persons are there per company?");
            pr.findAllPersons().stream()
                    .filter(p -> p.getCompany() != null)
                    .collect(Collectors.groupingBy(Person::getCompany, Collectors.counting()))
                    .forEach((c, p) -> System.out.println(c.getName() + " --> " + p + " worker(s)"));
            System.out.println("G- What is the average number of employees per company?");
//        pr.findAllPersons().stream()
//                        .filter(p->p.getCompany()!=null)
//                                .collect(Collectors.groupingBy(Person::getCompany,Collectors.counting()))
//                                        .forEach();
//

            pr.findAllPersons().stream()
                    .filter(p -> p.getCompany() != null)
                    .collect(Collectors.groupingBy(Person::getCompany, Collectors.averagingInt(toAge)))
                    .forEach((c, p) -> System.out.println(c.getName() + " --> average age is " + p + " years"));
        } catch (AddressAlreadyExistsException | CourseAlreadyExistsException | PersonAlreadyExistsException |
                 CompanyAlreadyExistsException | AddressNotFoundException | CompanyNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
