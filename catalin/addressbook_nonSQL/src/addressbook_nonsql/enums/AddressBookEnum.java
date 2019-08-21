package addressbook_nonsql.enums;

public enum AddressBookEnum
{
    ID("ID"), FIRSTNAME("First Name"), LASTNAME("Last Name"), STREET("Street"), PHONENUMBER("Phone Number"),
    EMAIL("Email");

    private final String columnHeader;

    AddressBookEnum(String columnHeader)
    {
	this.columnHeader = columnHeader;
    }

    public String getColumn()
    {
	return columnHeader;
    }
    
    private static AddressBookEnum[] vals = values();
    
    public AddressBookEnum next()
    {
        return vals[(this.ordinal()+1) % vals.length];
    }
}