package calc.pckg;

public class Verify {

	static boolean isNotNull(String strNum)
	{
		return strNum != null;
	}

	// if the String is "("
	static boolean isOpenBracket(String strNum) 
	{
		return strNum.equals("(");
	}

	// if the String is ")"
	static boolean isCloseBracket(String strNum) 
	{
		return strNum.equals(")");
	}

	static boolean isNull(String strNum) 
	{
		return strNum == null;
	}
	static boolean isNumeric(String strNum) 
	{
		try 
		{
			Double.parseDouble(strNum);

			return true;
		} 
		catch (NumberFormatException | NullPointerException nfe) 
		{
			return false;
		}
	}
}
