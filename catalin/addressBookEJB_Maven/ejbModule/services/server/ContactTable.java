package services.server;

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
public class ContactTable
{
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
    private AddressTable address;

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
    
    public AddressTable getAddress()
    {
        return address;
    }

    public void setAddress(AddressTable address)
    {
        this.address = address;
    }
}