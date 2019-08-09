package addressbook.persons;

import java.util.ArrayList;

public class Contact 
{
	private int id;
	private String firstName;
	private String lastName;
	private Address address;
	private String phoneNumber;
	private String emailAddress;

	public static int index = 1;

	public Contact(String firstName, String lastName, Address address, String phoneNumber, String emailAddress) 
	{
		super();
		this.id = index++;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
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

	public void setAddress(Address address) 
	{
		this.address = address;
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

	public enum ContactElements 
	{
		INSTANCE;

		private ArrayList<Contact> contacts = new ArrayList<Contact>();

		private ContactElements() 
		{
			contacts.add(new Contact("Ion", "Bala", new Address("Romania", "Cluj", "Iancu.23", "3478902"), "0462347",
					"ion09"));
			contacts.add(new Contact("Maurice", "Hit", new Address("Franta", "Paris", "King.45", "zt4702145"),
					"0323671", "mau00"));
			contacts.add(new Contact("Krisnov", "Bor", new Address("Bulgaria", "Sofia", "Krus.21", "89to456"),
					"1690365", "borkr01"));
			contacts.add(new Contact("Jussepe", "Pir", new Address("Italia", "Torino", "Pacetti.13", "gh093"),
					"7613290", "juse11"));
		}

		public ArrayList<Contact> getContacts() 
		{
			return contacts;
		}
	}
}