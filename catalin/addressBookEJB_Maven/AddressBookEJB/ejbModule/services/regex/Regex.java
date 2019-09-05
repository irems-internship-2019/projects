package services.regex;

public class Regex
{
    public String isValidEmail(String email)
    {
	String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	if (email.matches(regex))
	    return email;
	else
	    return email = "";
    }

    public String isValidPhone(String phone)
    {
	String regex = "^[(]{0,1}[0-9]{3}[)]{0,1}[-\\s\\.]{0,1}[0-9]{3}[-\\s\\.]{0,1}[0-9]{4}$";
	if (phone.matches(regex))
	    return phone;
	else
	    return phone = "";
    }
}