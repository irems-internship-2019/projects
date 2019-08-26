
package de.vogella.jpa.eclipselink.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import de.vogella.jpa.eclipselink.model.Address;

@Entity
@Table (name="Contact",schema="RCPpersistence")
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private String lastName;
	private Address address;
	private String phoneNumber;
	private String emailAdress;

	public String getId() {
		return Integer.toString(id);
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	 public void setAddress(Address address) {
	        this.address = address;
	    }

	@ManyToOne
	public Address getFamily() {
		return address;
	}

	public String getphoneNumber() {
		return phoneNumber;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmailAdress() {
		return emailAdress;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setEmailAddress(String email) {
		this.emailAdress = email;
	}

}
