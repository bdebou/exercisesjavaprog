package be.abis.exercise.repository;

import be.abis.exercise.exception.AddressNotFoundException;
import be.abis.exercise.exception.CompanyAlreadyExistsException;
import be.abis.exercise.model.Company;

public class MemoryCompanyRepository extends CompanyCommonRepository {
    public MemoryCompanyRepository(AddressRepository addressRepository) throws AddressNotFoundException, CompanyAlreadyExistsException {
        super();
        this.addCompany(new Company("ABIS", addressRepository.findByID(1)));
        this.addCompany(new Company("BNP Paribas Fortis", addressRepository.findByID(2)));
        this.addCompany(new Company("IBM", addressRepository.findByID(3)));
        this.addCompany(new Company("KLM", addressRepository.findByID(4)));
    }
}
