package addressbook.enums;

public enum AddressBookDetailsEnum
{
    ID("ID"), FIRSTNAME("First Name"), LASTNAME("Last Name"), COUNTRY("Country"), CITY("City"),
    STREET("Street"), POSTALCODE("Postal Code");

    private final String columnHeader;

    AddressBookDetailsEnum(String columnHeadder)
    {
	this.columnHeader = columnHeadder;
    }

    public String getColumn()
    {
	return columnHeader;
    }
    
    private static AddressBookDetailsEnum[] vals = values();
    
    public AddressBookDetailsEnum next()
    {
        return vals[(this.ordinal()+1) % vals.length];
    }
}