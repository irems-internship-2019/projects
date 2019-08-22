package ui.filter.enums;

public enum AdressEnums {
	 ID("ID"), FIRSTNAME("First Name"), LASTNAME("Last Name"), COUNTRY("country"), CITY("city"),
	 STREET("street"), POSTAL_CODE("Postal Code");

	    private final String columnHeadder;

	    AdressEnums(String columnHeadder)
	    {
		this.columnHeadder = columnHeadder;
	    }

	    public String getColumn()
	    {
		return columnHeadder;
	    }
}
