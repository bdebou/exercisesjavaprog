package be.abis.exercise.repository;

import be.abis.exercise.exception.PersonAlreadyExistsException;
import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.model.Person;
import be.abis.exercise.test.TestMain5;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PersonCommonRepository implements PersonRepository {
    private final List<Person> persons = new ArrayList<Person>();

    @Override
    public List<Person> findAllPersons() {
        return persons;
    }

    @Override
    public Person findPersonById(int id) throws PersonNotFoundException {
        return this.persons.stream()
                .filter(p -> p.getPersonNumber() == id)
                .findFirst()
                .orElseThrow(() -> new PersonNotFoundException("No person found with <" + id + "> as ID"));
    }

    @Override
    public Person findPersonByEmailAndPassword(String email, String password) throws PersonNotFoundException {
        Optional<Person> result = this.persons.stream()
                .filter(p -> p.getEmail().equalsIgnoreCase(email) && p.getPassword().equals(password))
                .findFirst();
        if (result.isPresent()) {
            TestMain5.ROOT_LOGGER.info("Person found with <" + email + "> as eMail");
            return result.get();
        } else {
            throw new PersonNotFoundException("No person found with <" + email + "> as eMail");
        }
    }

    @Override
    public List<Person> findPersonsForCompany(String companyName) throws PersonNotFoundException {
        List<Person> lstResult = this.persons.stream()
                .filter(p -> p.getCompany() != null)
                .filter(p -> p.getCompany().getName().equalsIgnoreCase(companyName))
                .collect(Collectors.toList());

        if (lstResult.isEmpty()) {
            throw new PersonNotFoundException("No person works for <" + companyName + "> as company");
        }

        return lstResult;
    }

    @Override
    public void addPerson(Person p) throws PersonAlreadyExistsException {
        if (this.persons.contains(p)) {
            throw new PersonAlreadyExistsException("<" + p.getLastName() + "> <" + p.getFirstName() + "> is already existing");
        }
        this.persons.add(p);
    }
}
