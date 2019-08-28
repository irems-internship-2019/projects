package model.enums;

public enum DetailesEnum
{
    ID("ID"), FIRSTNAME("First Name"), LASTNAME("Last Name"), COUNTRY("Country"), CITY("City"), STREET("Street"),
    POSTALCODE("Postal Code");

    private final String columnHeadder;

    DetailesEnum(String columnHeadder)
    {
	this.columnHeadder = columnHeadder;
    }

    public String getColumn()
    {
	return columnHeadder;
    }

    private static DetailesEnum[] vals = values();

    public DetailesEnum next()
    {
	return vals[(this.ordinal() + 1) % vals.length];
    }
}
