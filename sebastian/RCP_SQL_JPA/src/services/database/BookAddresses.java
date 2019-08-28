package services.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bookAddresses", schema = "addressbook")
public class BookAddresses
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long address_id;
    private String country;
    private String city;
    private String street;
    private String postal_code;
    
    @OneToOne(mappedBy = "address")
    private BookContacts contact;
    

    @Override
    public String toString()
    {
	return "address";
    }

    public Long getAddress_id()
    {
	return address_id;
    }

    public void setAddress_id(Long address_id)
    {
	this.address_id = address_id;
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

    public String getPostal_code()
    {
	return postal_code;
    }

    public void setPostal_code(String postal_code)
    {
	this.postal_code = postal_code;
    }

}