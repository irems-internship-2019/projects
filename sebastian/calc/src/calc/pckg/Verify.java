package calc.pckg;

class Verify extends StackOperations {

	private final static String OPENBRACKET ="(";
	private final static String CLOSEDBRACKET =")";

	boolean isNotNull(String strNum)
	{
		return strNum != null;
	}

	// if the String is "("
	boolean isOpenBracket(String strNum) 
	{
		return strNum.equals(OPENBRACKET);
	}

	// if the String is ")"
	boolean isCloseBracket(String strNum) 
	{
		return strNum.equals(CLOSEDBRACKET);
	}

	boolean isNull(String strNum) 
	{
		return strNum == null;
	}
	boolean isNumeric(String strNum) 
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

	void fixBracketCount () 
	{
		int numberOfBrackets = 0;
		numberOfBrackets = checkNumberOfBrackets(/*CreateCalculatorUi.inputStack*/);

		if (numberOfBrackets != 0 && numberOfBrackets > 0) 
		{
			for (int i = 0; i < numberOfBrackets; i++) 
			{
				pushStack(CLOSEDBRACKET);
				TextWidget.setDisplayAfter(CLOSEDBRACKET);
			}
		} 
		else if (numberOfBrackets < 0) 
		{
			numberOfBrackets *= (-1);
			for (int i = 1; i <= numberOfBrackets; i++) 
			{
				addToStack(i, OPENBRACKET);
				TextWidget.setDisplayBefore(OPENBRACKET);
			}
		}
	}

	private  int checkNumberOfBrackets() 
	{


		int numberOfOpenBrackets = 0;
		int numberOfClosedBrackets = 0;

		for (int i =0; i < stackSize(); i++) 
		{
			if (isEqual(i, OPENBRACKET)) 
				numberOfOpenBrackets++;
			else if (isEqual(i, CLOSEDBRACKET)) 
				numberOfClosedBrackets++;
		}
		return numberOfOpenBrackets - numberOfClosedBrackets;
	}
}
