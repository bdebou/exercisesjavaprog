package be.abis.exercise.test;

import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.PersonRepository;
import be.abis.exercise.services.UnitOfWork;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestMain5 {
    public static final Logger EXCEPTION_LOGGER = LogManager.getLogger("exceptionLogger");
    public static final Logger ROOT_LOGGER = LogManager.getRootLogger();

    public static void main(String[] args) {
        UnitOfWork unitOfWork = new UnitOfWork();
        EXCEPTION_LOGGER.error("test bruno");
        ROOT_LOGGER.info("infor de bruno");

        PersonRepository personRepository = unitOfWork.getPersonRepository();
        try {
            Person resultOK = personRepository.findPersonByEmailAndPassword("gindesteege@abis.be", "somepass3");
            Person resultNOK = personRepository.findPersonByEmailAndPassword("gindesteege@abis.be", "somepass");
        } catch (PersonNotFoundException e) {
            EXCEPTION_LOGGER.error(e);
        }
    }
}
