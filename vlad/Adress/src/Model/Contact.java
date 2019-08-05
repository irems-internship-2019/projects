package Model;

public class Contact {

	private int id;
	private String firstName;
	private String lastName;
	private Address adress;
	private String phoneNumber;
	private String emailAdress;
	private static int contactCounter = 0;

	public Contact() {

	}

	public Contact(String firstName, Address adress, String lastName, String phoneNumber, String emailAdress) {
		this.id = contactCounter++;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.emailAdress = emailAdress;
		this.adress = adress;
	}

	public String getId() {
		return Integer.toString(id);
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

	public String getphoneNumber() {
		return phoneNumber;
	}

	public String getEmailAdress() {
		return emailAdress;
	}

	public String getStreet() {
		// TODO Auto-generated method stub
		return adress.getStreet();
	}

}
