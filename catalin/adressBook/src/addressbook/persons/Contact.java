package addressbook.persons;

import java.util.ArrayList;

public class Contact {
	private int id;
	private String firstName;
	private String lastName;
	private Address address;
	private int phoneNumber;
	private String emailAddress;

	public Contact(int id, String firstName, String lastName, Address address, int phoneNumber, String emailAddress) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public enum ContactElements {
		INSTANCE;

		private ArrayList<Contact> contactList = new ArrayList<Contact>();

		private ContactElements() {
			contactList.add(new Contact(1, "Ion", "Bala", new Address("Romania", "Cluj", "Iancu.23", "3478902"),
					0462347, "ion09"));
			contactList.add(new Contact(2, "Maurice", "Hit", new Address("Franta", "Paris", "King.45", "zt4702145"),
					0323671, "mau00"));
			contactList.add(new Contact(3, "Krisnov", "Bor", new Address("Bulgaria", "Sofia", "Krus.21", "89to456"),
					1690365, "borkr01"));
			contactList.add(new Contact(4, "Jussepe", "Pir", new Address("Italia", "Torino", "Pacetti.13", "gh093"),
					7613290, "juse11"));
		}

		public ArrayList<Contact> getContacts() {
			return contactList;
		}
	}
}
