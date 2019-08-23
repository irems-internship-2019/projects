package model.enums;

public enum ErrorsEnum
{

    CONNECTION("Connection"), INSERT("Insert"), UPDATE("Update"), DELETE("Delete"), LOAD("Load"),BAD("Bad");

    private final String labelText;

    ErrorsEnum(String labelText)
    {
	this.labelText = labelText;
    }

    public String getText()
    {
	return labelText;
    }
}
