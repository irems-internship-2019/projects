package models.persons;

public class Contact
{
    private long id;
    private String firstName;
    private String lastName;
    private Address address;
    private String phoneNumber;
    private String emailAddress;

    public Contact(long id, String firstName, String lastName, Address address, String phoneNumber, String emailAddress)
    {
	super();
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.address = address;
	this.phoneNumber = phoneNumber;
	this.emailAddress = emailAddress;
    }

    public long getId()
    {
	return id;
    }

    public void setId(long id)
    {
	this.id = id;
    }

    public String getFirstName()
    {
	return firstName;
    }

    public void setFirstName(String firstName)
    {
	this.firstName = firstName;
    }

    public String getLastName()
    {
	return lastName;
    }

    public void setLastName(String lastName)
    {
	this.lastName = lastName;
    }

    public Address getAddress()
    {
	return address;
    }

    public String getPhoneNumber()
    {
	return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
	this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress()
    {
	return emailAddress;
    }

    public void setEmailAddress(String emailAddress)
    {
	this.emailAddress = emailAddress;
    }
}