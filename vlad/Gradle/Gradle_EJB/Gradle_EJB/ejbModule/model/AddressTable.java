package model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address", schema = "jpa")
public class AddressTable implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private long addressid;
    private String country;
    private String city;
    private String street;
    private String postalCode;
    
    @OneToOne(mappedBy = "address")
    private ContactsTable contact;
    
    public AddressTable(String country, String city, String street, String postalCode) {
    	this.country = country;
		this.city = city;
		this.street = street;
		this.postalCode = postalCode;	}
    public AddressTable()
    {
    	
    }

	public String getCountry()
    {
	return country;
    }

    public void setCountry(String country)
    {
	this.country = country;
    }

    public String getCity()
    {
	return city;
    }

    public void setCity(String city)
    {
	this.city = city;
    }

    public String getStreet()
    {
	return street;
    }

    public void setStreet(String street)
    {
	this.street = street;
    }

    public String getPostalCode()
    {
	return postalCode;
    }

    public void setPostalCode(String postalcode)
    {
	this.postalCode = postalcode;
    }
}