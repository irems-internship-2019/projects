package services.server;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address", schema = "jpabook")
public class AddressTable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private long addressid;
    private String country;
    private String city;
    private String street;
    private String postalcode;
    
    @OneToOne(mappedBy = "address")
    private ContactTable contact;
    
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
	return postalcode;
    }

    public void setPostalCode(String postalcode)
    {
	this.postalcode = postalcode;
    }
}
