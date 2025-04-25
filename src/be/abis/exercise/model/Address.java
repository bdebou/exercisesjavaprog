package be.abis.exercise.model;

import java.util.Objects;

public class Address implements Comparable<Address> {

    public static int counter = 0;

    private final int addressNumber;
    private String street;
    private String nr;
    private String zipCode;
    private String town;
    private String country;
    private String countryCode;

    public Address() {
        addressNumber=++counter;
    }

    public Address(String street, String nr, String zipCode, String town, String country, String countryCode) {
        this();
        this.street = street;
        this.nr = nr;
        this.zipCode = zipCode;
        this.town = town;
        this.country = country;
        this.countryCode = countryCode;
    }

    public int getAddressNumber() {
        return addressNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public int compareTo(Address o) {
        int result = this.getCountry().compareTo(o.getCountry());
        if (result == 0) {
            result = this.getTown().compareTo(o.getTown());
            if (result == 0) {
                result = this.getZipCode().compareTo(o.getZipCode());
                if (result == 0) {
                    result = this.getStreet().compareTo(o.getStreet());
                    if (result == 0) {
                        return this.getNr().compareTo(o.getNr());
                    }
                }
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return addressNumber == address.addressNumber && Objects.equals(street, address.street) && Objects.equals(nr, address.nr) && Objects.equals(zipCode, address.zipCode) && Objects.equals(town, address.town) && Objects.equals(country, address.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressNumber, street, nr, zipCode, town, country);
    }
}
