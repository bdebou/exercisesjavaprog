package be.abis.exercise.repository;

import be.abis.exercise.exception.CompanyAlreadyExistsException;
import be.abis.exercise.exception.CompanyNotFoundException;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Course;

import java.util.List;

public interface CompanyRepository {
    List<Company> findAllCompanies();
    Company findByName(String name) throws CompanyNotFoundException;
    Company findByID(int id) throws CompanyNotFoundException;
    void addCompany(Company company) throws CompanyAlreadyExistsException;
    void removeCompany(Company company) throws CompanyNotFoundException;
    long getNumberOfCompanies();
}
