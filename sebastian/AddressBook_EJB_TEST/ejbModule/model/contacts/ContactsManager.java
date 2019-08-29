package model.contacts;

public class ContactsManager
{

       private long ID = 0;

    private String first;
    private String second;

    private AddressManager address;
    private String phoneNumber;
    private String email;

    public ContactsManager(long ID, String first, String second, AddressManager address, String phoneNumber, String email)
    {
	// super();
	this.ID = ID;
	this.first = first;
	this.second = second;
	this.address = address;
	this.phoneNumber = phoneNumber;
	this.email = email;
    }

    public String getID()
    {
	return Long.toString(ID);
    }

    public long getLongID()
    {
	return ID;
    }

    public String getFirstName()
    {
	return first;
    }

    public String getLastName()
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

    public void setFirstName(String first)
    {
	this.first = first;
    }

    public void setLastName(String second)
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
    
    public void setPrimaryKeyID(int iD) 
    {
	ID = iD;
    }

    public void setID(long iD)
    {
        ID = iD;
    }
}
