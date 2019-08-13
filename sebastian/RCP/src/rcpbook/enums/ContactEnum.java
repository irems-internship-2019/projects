package rcpbook.enums;

public enum ContactEnum
{
    ID("ID"), FIRSTNAME("First Name"), LASTNAME("Last Name"), STREET("Street"), PHONENUMBER("Phone Number"),
    EMAIL("Email");

    private final String columnHeadder;

    ContactEnum(String columnHeadder)
    {
	this.columnHeadder = columnHeadder;
    }

    public String getColumn()
    {
	return columnHeadder;
    }
    
    private static ContactEnum[] vals = values();
    
    public ContactEnum next()
    {
        return vals[(this.ordinal()+1) % vals.length];
    }
}
