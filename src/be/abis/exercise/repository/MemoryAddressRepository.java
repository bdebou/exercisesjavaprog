package be.abis.exercise.repository;

import be.abis.exercise.exception.AddressAlreadyExistsException;
import be.abis.exercise.model.Address;

public class MemoryAddressRepository extends AddressCommonRepository {

    public MemoryAddressRepository() throws AddressAlreadyExistsException {
        super();
        this.addAddress(new Address("Diestsevest", "32/4b", "3000", "Leuven", "België", "BE"));
        this.addAddress(new Address("Sint-Lazaruslaan", "10", "1210", "Saint-Josse-Ten-Noode", "Belgique", "BE"));
        this.addAddress(new Address("Avenue du Bourget", "42", "1130", "Brussels", "Belgium", "BE"));
        this.addAddress(new Address("Amsterdamseweg", "55", "1182GP", "Amstelveen", "Nederland", "NL"));
    }
}
