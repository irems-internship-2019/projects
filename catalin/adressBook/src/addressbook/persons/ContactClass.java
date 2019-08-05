package addressbook.persons;

import java.util.ArrayList;

public class ContactClass {
	private int id;
	private String firstName;
	private String lastName;
	private AddressClass address;
	private int phoneNumber;
	private String emailAddress;
	
	public ContactClass(int id, String firstName, String lastName, AddressClass address, int phoneNumber,
			String emailAddress) {
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

	public AddressClass getAddress() {
		return address;
	}

	public void setAddress(AddressClass address) {
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
	
	public enum Contact {
	    INSTANCE;

		private ArrayList<ContactClass> contactList = new ArrayList<ContactClass>();

	    private Contact() {
	    	contactList.add(new ContactClass(1,"Ion","Bala",new AddressClass("Romania", "Cluj", "Iancu", "3478902"),0462347,"ion09"));
	    	contactList.add(new ContactClass(2,"Maurice","Hit",new AddressClass("Franta", "Paris", "King", "zt4702145"),0323671,"mau00"));
	    	contactList.add(new ContactClass(3,"Krisnov","Bor",new AddressClass("Bulgaria", "Sofia", "Krus", "89to456"),1690365,"borkr01"));
	    	contactList.add(new ContactClass(4,"Jussepe","Pir",new AddressClass("Italia", "Torino", "Pacetti", "gh093"),7613290,"borkr01"));
	    }

	    public ArrayList<ContactClass> getContacts() {
	        return contactList;
	    }
	}
}
