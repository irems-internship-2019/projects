package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "contact", schema = "jpa")
public class ContactsTable implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private long contactid;
   

	private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAdress;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_fk", referencedColumnName = "addressid")
    private AddressTable address;

    
  
    
    public ContactsTable(long id,String firstName, AddressTable adress, String lastName, String phoneNumber, String emailAdress) {
		this.contactid = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.emailAdress = emailAdress;
		this.address = adress;
	}
    
    public ContactsTable()
    {
    	
    }

	public long getContactid() {
		return contactid;
	}

	public void setContactid(long contactid) {
		this.contactid = contactid;
	}
    
    
    public String getFirstName()
    {
	return firstName;
    }

    public void setFirstName(String firstname)
    {
	this.firstName = firstname;
    }

    public String getLastName()
    {
	return lastName;
    }

    public void setLastName(String lastname)
    {
	this.lastName = lastname;
    }

    public String getPhoneNumber()
    {
	return phoneNumber;
    }

    public void setPhoneNumber(String phonenumber)
    {
	this.phoneNumber = phonenumber;
    }

    public String getEmailAddress()
    {
	return emailAdress;
    }

    public void setEmailAddress(String emailaddress)
    {
	this.emailAdress = emailaddress;
    }
    
    public AddressTable getAddress()
    {
        return address;
    }

    public void setAddress(AddressTable address)
    {
        this.address = address;
    }
}