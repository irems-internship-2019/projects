package model;

public class Contact {

	private long id;
	private String firstName;
	private String lastName;
	private Address adress;
	private String phoneNumber;
	private String emailAdress;
	public static int contactCounter = 0;

	public Contact() {

	}

	public Contact(long id,String firstName, Address adress, String lastName, String phoneNumber, String emailAdress) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.emailAdress = emailAdress;
		this.adress = adress;
	}

	public String getId() {
		return Long.toString(id);
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
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

	public Address getAddress() {

		return adress;
	}

	public long getIdInt() {
		// TODO Auto-generated method stub
		return id;
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
