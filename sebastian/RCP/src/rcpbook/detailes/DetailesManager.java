package rcpbook.detailes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rcpbook.contacts.AddressManager;
import rcpbook.contacts.ContactsManager;

public class DetailesManager {

	private String ID;
	private String first;
	private String second;
	private String country;
	private String city;
	private String street;
	private String postalCode;

	public DetailesManager(String ID, String first, String second, String country, String city, String street, String postalCode) {
		// super();
		this.ID = ID;
		this.first = first;
		this.second = second;
		this.country = country;
		this.city = city;
		this.street = street;
		this.postalCode = postalCode;
	}
	
	public DetailesManager createNewDetailes(String ID, String first, String second, String country, String city, String street, String postalCode){
		return new  DetailesManager(ID, first, second, country, city, street, postalCode);
	}

	public String getID() {
		return ID;
	}

	public String getFirst() {
		return first;
	}

	public String getSecond() {
		return second;
	}

	public String getCountry() {
		return country;
	}

	public String getCity() {
		return city;
	}

	public String getStreet() {
		return street;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public void setSecond(String second) {
		this.second = second;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
}
