package be.abis.exercise.services;

import be.abis.exercise.exception.*;
import be.abis.exercise.model.Address;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Course;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.*;
import be.abis.exercise.test.TestMain5;
import com.github.javafaker.Faker;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Random;

public class UnitOfWork {
    private final AddressRepository addressRepository;
    private final CompanyRepository companyRepository;
    private final PersonRepository personRepository;
    private final CourseRepository courseRepository;

    public UnitOfWork() {
        try {
            this.addressRepository = new MemoryAddressRepository();
            this.companyRepository = new MemoryCompanyRepository(addressRepository);
            this.personRepository = new MemoryPersonRepository(this.companyRepository);
            this.courseRepository = new MemoryCourseRepository();
        } catch (RepositoryException e) {
            TestMain5.EXCEPTION_LOGGER.error(e);
            throw new RuntimeException(e);
        }

    }

    public UnitOfWork(Faker faker) throws AddressAlreadyExistsException, CourseAlreadyExistsException, AddressNotFoundException, CompanyAlreadyExistsException, CompanyNotFoundException, PersonAlreadyExistsException {
        this(faker, 100, 10, 1000);
    }

    public UnitOfWork(Faker faker, int nbAddress, int nbCompany, int nbPerson) throws AddressAlreadyExistsException, CourseAlreadyExistsException, AddressNotFoundException, CompanyAlreadyExistsException, CompanyNotFoundException, PersonAlreadyExistsException {
        this.addressRepository = new AddressCommonRepository();
        this.companyRepository = new CompanyCommonRepository();
        this.personRepository = new PersonCommonRepository();
        this.courseRepository = new CourseCommonRepository();

        Random random = new Random();

        var fakerAddress = faker.address();
        for (int i = 0; i < nbAddress; i++) {
            Address address = new Address(
                    fakerAddress.streetAddress(),
                    fakerAddress.buildingNumber(),
                    fakerAddress.zipCode(),
                    fakerAddress.city(),
                    fakerAddress.country(),
                    fakerAddress.countryCode());
            this.addressRepository.addAddress(address);
        }

        this.courseRepository.addCourse(new Course("DB2, an overview", 5, 550.0, LocalDate.of(1986, 4, 30)));
        this.courseRepository.addCourse(new Course("Workshop SQL", 2, 475.0, LocalDate.of(1990, 1, 9)));
        this.courseRepository.addCourse(new Course("Java Programming", 5, 500.0, LocalDate.of(1997, 5, 27)));
        this.courseRepository.addCourse(new Course("Maven", 1, 450.0, LocalDate.of(2007, 6, 11)));
        this.courseRepository.addCourse(new Course("Programming with Spring", 3, 525.0, LocalDate.of(2008, 3, 21)));

        for (int i = 0; i < nbCompany; i++) {
            this.companyRepository.addCompany(new Company(
                    faker.company().name(),
                    this.addressRepository.findByID(random.nextInt(1, (int) this.addressRepository.getNumberOfAddress()))
            ));
        }

        for (int i = 0; i < nbPerson; i++) {
            this.personRepository.addPerson(new Person(
                    faker.name().firstName(),
                    faker.name().lastName(),
                    LocalDate.ofInstant(faker.date().birthday(1, 100).toInstant(), ZoneId.systemDefault()),
                    faker.internet().emailAddress(),
                    faker.internet().password(),
                    faker.nation().language(),
                    companyRepository.findByID(random.nextInt(1, (int) companyRepository.getNumberOfCompanies()))));

        }
    }

    public UnitOfWork(Path coursePath) {
        try {
            this.addressRepository = new MemoryAddressRepository();
            this.companyRepository = new MemoryCompanyRepository(addressRepository);
            this.personRepository = new MemoryPersonRepository(this.companyRepository);
            this.courseRepository = new FileCourseRepository(coursePath);
        } catch (RepositoryException e) {
            TestMain5.EXCEPTION_LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }

    public AddressRepository getAddressRepository() {
        return addressRepository;
    }

    public CompanyRepository getCompanyRepository() {
        return companyRepository;
    }

    public PersonRepository getPersonRepository() {
        return personRepository;
    }

    public CourseRepository getCourseRepository() {
        return courseRepository;
    }
}
