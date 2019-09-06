package ui.filter.enums;

public enum ContactsEnums {
	 ID("ID"), FIRSTNAME("First Name"), LASTNAME("Last Name"), STREET("Street"), PHONENUMBER("Phone Number"),
	    EMAIL("Email");

	    private final String columnHeadder;

	    ContactsEnums(String columnHeadder)
	    {
		this.columnHeadder = columnHeadder;
	    }

	    public String getColumn()
	    {
		return columnHeadder;
	    }
	    
	   
	}