package be.abis.exercise.model;

public class Company implements Comparable<Company> {

	public static int counter = 0;

	private final int companyNumber;
	private String name;
	private Address address;

	public Company(){
		companyNumber=++counter;
	}

	public Company(String name, Address address) {
		this();
		this.name = name;
		this.address = address;
	}

	public int getCompanyNumber() {
		return companyNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Company{" +
				"name='" + name + '\'' +
				", address=" + address.getTown() +
				'}';
	}

	@Override
	public int compareTo(Company o) {
		return this.getName().compareTo(o.getName());
	}
}
