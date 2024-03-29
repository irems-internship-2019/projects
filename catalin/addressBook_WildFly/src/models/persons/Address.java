package models.persons;

public class Address
{
    private String country;
    private String city;
    private String street;
    private String postal_code;

    public Address(String country, String city, String street, String postal_code)
    {
	super();
	this.country = country;
	this.city = city;
	this.street = street;
	this.postal_code = postal_code;
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