package be.abis.exercise.repository;

import be.abis.exercise.exception.AddressAlreadyExistsException;
import be.abis.exercise.exception.AddressNotFoundException;
import be.abis.exercise.model.Address;

import java.util.ArrayList;
import java.util.List;

public class AddressCommonRepository implements AddressRepository {
    public AddressCommonRepository() {
    }

    private final List<Address> addresses = new ArrayList<>();

    @Override
    public List<Address> findAllAddresses() {
        return this.addresses;
    }

    @Override
    public Address findByID(int id) throws AddressNotFoundException {
        return this.addresses.stream()
                .filter(a -> a.getAddressNumber() == id)
                .findFirst()
                .orElseThrow(AddressNotFoundException::new);
    }

    @Override
    public void addAddress(Address address) throws AddressAlreadyExistsException {
        if (this.addresses.contains(address)) {
            throw new AddressAlreadyExistsException();
        }
        this.addresses.add(address);
    }

    @Override
    public void removeAddress(Address address) throws AddressNotFoundException {
        if (this.addresses.contains(address)) {
            this.addresses.remove(address);
        } else {
            throw new AddressNotFoundException();
        }
    }

    @Override
    public long getNumberOfAddress() {
        return this.addresses.size();
    }
}
