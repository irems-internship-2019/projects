package rcpbook.contacts;

public class ContactsManager 
{
	private static final String[]   FIRST = {"Favonius", "Tom", "Brad", "Scott", "Linda", "Maria"};
	private static final String[]   SECOND = {"Cruise", "Tempo", "Abbey", "Adam", "Albert", "Thomas","Afloare"};
	private static final String[]   PHONENUMBER = {"0756-591-561","0359-447-924","0746-777-767", "0770-198-445"};
	private static final String[]   EMAIL = {"sebi.paul121@gmail.com", "dorel44@outlook.com", "afloare-maria_1992@hotmail.com"};
	//private static final int[]      AGE = {12, 5, 9, 21, 23, 24, 25, 26, 33, 28, 19, 40};

	private static int index = 1;
	private int ID = 0;

//	private static Random random = new Random(System.currentTimeMillis());
	
	//AddressManager address1 = new AddressManager("USA", "New York", "Cattle Heaven", "415300");

	private String first;
	private String second;
	
	private AddressManager address;
	private String phoneNumber;
	private String email;

	public ContactsManager(String first, String second, AddressManager address, String phoneNumber, String email) 
	{
		//super();
		this.ID = index++;
		this.first = first;
		this.second = second;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public String getID() 
	{
		return Integer.toString(ID);
	}

	public int getIdForComparator() {
		return ID;
	}
	public String getFirst() {
		return first;
	}
	public String getSecond() {
		return second;
	}
	public AddressManager getAddress() {
		return address;
	}

	public String getPhone() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public void setSecond(String second) {
		this.second = second;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
