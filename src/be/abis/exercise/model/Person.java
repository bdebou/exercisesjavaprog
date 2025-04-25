package be.abis.exercise.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Person implements Comparable<Person> {

    public static int counter = 0;

    private final int personNumber;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private String password;
    private String languageCode;
    private Company company;

    public Person() {
        personNumber = ++counter;
    }


    public Person(String firstName, String lastName, LocalDate birthDate, String email, String password, String languageCode) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
        this.languageCode = languageCode;
    }

    public Person(String firstName, String lastName, LocalDate birthDate, String email,
                  String password, String languageCode, Company company) {
        this(firstName, lastName, birthDate, email, password, languageCode);
        this.company = company;
    }


    public int getPersonNumber() {
        return personNumber;
    }

//    public void setPersonNumber(int personNumber) {
//        this.personNumber = personNumber;
//    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fName) {
        firstName = fName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lName) {
        lastName = lName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public static int getNumberOfPersons() {
        return counter;
    }

    @Override
    public String toString() {
        return "Person " + personNumber + ": " + this.firstName + " " + this.lastName;
    }


    @Override
    public int compareTo(Person o) {
        int result = this.getLastName().compareTo(o.getLastName());
        if (result == 0) {
            return this.getFirstName().compareTo(o.getFirstName());
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(birthDate, person.birthDate) && Objects.equals(email, person.email) && Objects.equals(company, person.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, birthDate, email, company);
    }

    public int calculateAge() {
        return Period.between(this.getBirthDate(), LocalDate.now()).getYears();
    }
}