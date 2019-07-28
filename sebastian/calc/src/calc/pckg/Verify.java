package calc.pckg;

class Verify {

	private final static String OPENBRACKET ="(";
	private final static String CLOSEDBRACKET =")";

	static boolean isNotNull(String strNum)
	{
		return strNum != null;
	}

	// if the String is "("
	static boolean isOpenBracket(String strNum) 
	{
		return strNum.equals(OPENBRACKET);
	}

	// if the String is ")"
	static boolean isCloseBracket(String strNum) 
	{
		return strNum.equals(CLOSEDBRACKET);
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

	static void fixBracketCount () 
	{
		int numberOfBrackets = 0;
		numberOfBrackets = checkNumberOfBrackets(/*CreateCalculatorUi.inputStack*/);

		if (numberOfBrackets != 0 && numberOfBrackets > 0) 
		{
			for (int i = 0; i < numberOfBrackets; i++) 
			{
				StackOperations.pushStack(CLOSEDBRACKET);
				TextWidget.setDisplayAfter(CLOSEDBRACKET);
			}
		} 
		else if (numberOfBrackets < 0) 
		{
			numberOfBrackets *= (-1);
			for (int i = 1; i <= numberOfBrackets; i++) 
			{
				StackOperations.addToStack(i, OPENBRACKET);
				TextWidget.setDisplayBefore(OPENBRACKET);
			}
		}
	}

	private static int checkNumberOfBrackets() 
	{


		int numberOfOpenBrackets = 0;
		int numberOfClosedBrackets = 0;

		for (int i =0; i < StackOperations.stackSize(); i++) 
		{
			if (StackOperations.isEqual(i, OPENBRACKET)) 
				numberOfOpenBrackets++;
			else if (StackOperations.isEqual(i, CLOSEDBRACKET)) 
				numberOfClosedBrackets++;
		}
		return numberOfOpenBrackets - numberOfClosedBrackets;
	}
}
