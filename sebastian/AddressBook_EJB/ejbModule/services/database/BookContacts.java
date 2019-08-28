package services.database;

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
@Table(name = "bookContacts", schema = "addressbook")
public class BookContacts
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long contact_id;
    private String first_name;
    private String last_name;
    private String phone_number;
    private String email;
    
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="address_fk",referencedColumnName = "address_id")
    private BookAddresses address;

    @Override
    public String toString()
    {
	return "contact";
    }

    public Long getContacts_id()
    {
	return contact_id;
    }

    public void setContacts_id(Long contact_id)
    {
	this.contact_id = contact_id;
    }

    public String getFirst_name()
    {
	return first_name;
    }

    public void setFirst_name(String first_name)
    {
	this.first_name = first_name;
    }

    public String getLast_name()
    {
	return last_name;
    }

    public void setLast_name(String last_name)
    {
	this.last_name = last_name;
    }

    public String getPhone_number()
    {
	return phone_number;
    }

    public void setPhone_number(String phone_number)
    {
	this.phone_number = phone_number;
    }

    public String getEmail()
    {
	return email;
    }

    public void setEmail(String email)
    {
	this.email = email;
    }

    public BookAddresses getAddress()
    {
        return address;
    }

    public void setAddress(BookAddresses address)
    {
        this.address = address;
    }

}