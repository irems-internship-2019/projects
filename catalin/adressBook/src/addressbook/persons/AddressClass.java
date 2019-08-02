package addressbook.persons;

import java.util.ArrayList;

public class AddressClass {
	private String country;
	private String city;
	private String street;
	private String postal_code;
	
	public AddressClass(String country, String city, String street, String postal_code) {
		super();
		this.country = country;
		this.city = city;
		this.street = street;
		this.postal_code = postal_code;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}
	
	public enum Address {
	    INSTANCE;

		private ArrayList<AddressClass> adressList = new ArrayList<AddressClass>();

	    private Address() {
	    	adressList.add(new AddressClass("Romania", "Cluj", "Iancu", "3478902"));
	    	adressList.add(new AddressClass("Franta", "Paris", "King", "zt4702145"));
	    	adressList.add(new AddressClass("Bulgaria", "Sofia", "Krus", "89to456"));
	    	adressList.add(new AddressClass("Italia", "Torino", "Pacetti", "gh093"));
	    }

	    public ArrayList<AddressClass> getPersons() {
	        return adressList;
	    }

	}
}
