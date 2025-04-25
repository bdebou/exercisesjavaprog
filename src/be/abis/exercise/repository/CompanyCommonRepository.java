package be.abis.exercise.repository;

import be.abis.exercise.exception.CompanyAlreadyExistsException;
import be.abis.exercise.exception.CompanyNotFoundException;
import be.abis.exercise.model.Company;

import java.util.ArrayList;
import java.util.List;

public class CompanyCommonRepository implements CompanyRepository {
    private final List<Company> companies = new ArrayList<>();

    @Override
    public List<Company> findAllCompanies() {
        return this.companies;
    }

    @Override
    public Company findByName(String name) throws CompanyNotFoundException {
        return this.companies.stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(CompanyNotFoundException::new);
    }

    @Override
    public Company findByID(int id) throws CompanyNotFoundException {
        return this.companies.stream()
                .filter(c -> c.getCompanyNumber() == id)
                .findFirst()
                .orElseThrow(CompanyNotFoundException::new);
    }

    @Override
    public void addCompany(Company company) throws CompanyAlreadyExistsException {
        if (this.companies.contains(company)) {
            throw new CompanyAlreadyExistsException();
        }
        this.companies.add(company);
    }

    @Override
    public void removeCompany(Company company) throws CompanyNotFoundException {
        if (this.companies.contains(company)) {
            this.companies.remove(company);
        } else {
            throw new CompanyNotFoundException();
        }
    }
    public long getNumberOfCompanies(){
        return this.companies.size();
    }
}
