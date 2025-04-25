package be.abis.exercise.repository;

import be.abis.exercise.exception.AddressAlreadyExistsException;
import be.abis.exercise.exception.AddressNotFoundException;
import be.abis.exercise.model.Address;
import be.abis.exercise.model.Company;

import java.util.List;

public interface AddressRepository {
    List<Address> findAllAddresses();

    Address findByID(int id) throws AddressNotFoundException;

    void addAddress(Address address) throws AddressAlreadyExistsException;

    void removeAddress(Address address) throws AddressNotFoundException;
    long getNumberOfAddress();
}
