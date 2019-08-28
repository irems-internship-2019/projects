package model.enums;

public enum EditorLabelEnum
{
    FIRSTNAME("First Name: "), LASTNAME("Last Name: "), COUNTRY("Country: "), CITY("City: "), STREET("Street :"),
    POSTALCODE("Postal Code: "), PHONENUMBER("Phone Number: "), EMAIL("Email");

    private final String labelText;

    EditorLabelEnum(String labelText)
    {
	this.labelText = labelText;
    }

    public String getText()
    {
	return labelText;
    }
}
