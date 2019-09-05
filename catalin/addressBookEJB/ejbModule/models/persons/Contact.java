package models.persons;

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
@Table(name = "contact", schema = "jpabook")
public class Contact implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private long contactid;
    private String firstname;
    private String lastname;
    private String phonenumber;
    private String emailaddress;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_fk", referencedColumnName = "addressid")
    private Address address;
 
    public Contact(long contactid, String firstname, String lastname, Address address, String phonenumber, String emailaddress) {
		this.contactid = contactid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phonenumber = phonenumber;
		this.emailaddress = emailaddress;
		this.address = address;
	}

	public Contact() {
		// TODO Auto-generated constructor stub
	}

	public long getContactid()
    {
        return contactid;
    }

    public void setContactid(long contactid)
    {
        this.contactid = contactid;
    }
    
    public String getFirstName()
    {
	return firstname;
    }

    public void setFirstname(String firstname)
    {
	this.firstname = firstname;
    }

    public String getLastName()
    {
	return lastname;
    }

    public void setLastName(String lastname)
    {
	this.lastname = lastname;
    }

    public String getPhoneNumber()
    {
	return phonenumber;
    }

    public void setPhoneNumber(String phonenumber)
    {
	this.phonenumber = phonenumber;
    }

    public String getEmailAddress()
    {
	return emailaddress;
    }

    public void setEmailAddress(String emailaddress)
    {
	this.emailaddress = emailaddress;
    }
    
    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }
}