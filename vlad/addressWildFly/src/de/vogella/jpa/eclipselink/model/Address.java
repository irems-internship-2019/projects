package de.vogella.jpa.eclipselink.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="Address",schema="RCPpersistence")

public class Address {
	 @Id
	 
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String country;
	private String city;
	private String street;
	private String postalCode;
	
	 public void setCountry(String country)
	    {
	        this.country = country;
	    }

	    public void setCity(String city)
	    {
	        this.city = city;
	    }

	    public void setStreet(String street)
	    {
	        this.street = street;
	    }

	    public void setPostalCode(String postalCode)
	    {
	        this.postalCode = postalCode;
	    }

	    public String getCity()
	    {
		return city;
	    }

	    public String getStreet()
	    {
		return street;
	    }

		public String getCountry() {
			// TODO Auto-generated method stub
			return country;
		}

		public String getPostalCode() {
			// TODO Auto-generated method stub
			return postalCode;
		}

}
