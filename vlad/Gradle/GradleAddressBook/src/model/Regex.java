package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

	String emailRegex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
	String phoneNumberRegex = "^\\d{9,9}$";

	public boolean isEmailValid(String Email) {
		Pattern emailPattern = Pattern.compile(emailRegex);
		Matcher matcher = emailPattern.matcher(Email);
		if (matcher.matches())
			return true;
		else
			return false;
	}

	public boolean isPhoneNumberValid(String phoneNumber) {
		Pattern phoneNumberPattern = Pattern.compile(phoneNumberRegex);
		Matcher matcher = phoneNumberPattern.matcher(phoneNumber);
		if (matcher.matches())
			return true;
		else
			return false;
	}
}
