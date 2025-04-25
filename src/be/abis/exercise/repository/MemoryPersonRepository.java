package be.abis.exercise.repository;


import be.abis.exercise.exception.CompanyNotFoundException;
import be.abis.exercise.exception.PersonAlreadyExistsException;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Person;

import java.time.LocalDate;

public class MemoryPersonRepository extends PersonCommonRepository {

    public MemoryPersonRepository(CompanyRepository companyRepository) throws CompanyNotFoundException, PersonAlreadyExistsException {
        super();
        Company c1 = companyRepository.findByName("ABIS");
        Company c2 = companyRepository.findByName("BNP Paribas Fortis");
        Company c3 = companyRepository.findByName("IBM");
        Company c4 = companyRepository.findByName("KLM");

        this.addPerson(new Person("Sandy", "Schillebeeckx", LocalDate.of(1978, 4, 10), "sschillebeeckx@abis.be", "somepass1", "nl", c1));
        this.addPerson(new Person("Koen", "De Backer", LocalDate.of(1962, 11, 25), "kdebacker@abis.be", "somepass2", "nl", c1));
        this.addPerson(new Person("Gie", "Indesteege", LocalDate.of(1958, 8, 19), "gindesteege@abis.be", "somepass3", "nl", c1));
        this.addPerson(new Person("Bart", "Lemarcq", LocalDate.of(1976, 2, 12), "blemarcq@abis.be", "somepass4", "fr", c1));
        this.addPerson(new Person("Michel", "Dupont", LocalDate.of(1980, 5, 5), "michel.dupont@bnpparibasfortis.com", "somepass5", "fr", c2));
        this.addPerson(new Person("Anne", "Van der Meulen", LocalDate.of(1984, 9, 30), "anne.vandermeulen@bnpparibasfortis.com", "somepass6", "nl", c2));
        this.addPerson(new Person("Bob", "Miles", LocalDate.of(1967, 3, 11), "bob.miles@ibm.com", "somepass7", "en", c3));
        this.addPerson(new Person("Willem-Alexander", "Janssen", LocalDate.of(1971, 1, 18), "willemalexander.janssen@klm.nl", "somepass8", "nl", c4));
        this.addPerson(new Person("Jef", "Smits", LocalDate.of(1988, 10, 10), "jefke@yahoo.com", "somepass9", "nl"));
    }


}
