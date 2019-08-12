package rcpbook.contacts;

public class ContactsManager
{

    private static int index = 1;
    private int ID = 0;

    private String first;
    private String second;

    private AddressManager address;
    private String phoneNumber;
    private String email;

    public ContactsManager(String first, String second, AddressManager address, String phoneNumber, String email)
    {
	// super();
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

    public int getIntId()
    {
	return ID;
    }

    public String getFirst()
    {
	return first;
    }

    public String getSecond()
    {
	return second;
    }

    public AddressManager getAddress()
    {
	return address;
    }

    public String getPhone()
    {
	return phoneNumber;
    }

    public String getEmail()
    {
	return email;
    }

    public void setFirst(String first)
    {
	this.first = first;
    }

    public void setSecond(String second)
    {
	this.second = second;
    }

    public void setPhoneNumber(String phoneNumber)
    {
	this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email)
    {
	this.email = email;
    }

    public void setIDForDelete(int iD)
    {
	ID = iD;
	index = iD + 1;
    }

    public void setID(int iD)
    {
	ID = iD;
	// used to decrement index counter so there are no gaps in-between objects
	index--;
    }
}
