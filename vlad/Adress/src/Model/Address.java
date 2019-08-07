package Model;

import java.util.ArrayList;

public class Address {
	
	private String country; 
	private String city;
	private String street;
	private String postalCode;
	
	private ArrayList<Address> adressList=new ArrayList<Address>();
	
	public Address(String country, String city, String street, String postalCode) {
		super();
		this.country = country; 
		this.city = city;
		this.street = street;
		this.postalCode = postalCode;
	}
	
	public void setStreet(String street) {
		this.street=street;
	}

	public ArrayList<Address> getAddresses() {
		return adressList;
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

	public String getPostal_code() {
		return postalCode;
	}

	public void setCountry(String country) {
		// TODO Auto-generated method stub
		  this.country=country;
	}

	
	
}
