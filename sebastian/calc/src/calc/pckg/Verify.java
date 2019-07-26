package calc.pckg;

import java.util.Stack;

class Verify {

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

	static void fixBracketCount () 
	{
		int numberOfBrackets = 0;
		numberOfBrackets = checkNumberOfBrackets(Calculator.inputStack);
		if (numberOfBrackets != 0 && numberOfBrackets > 0) 
		{
			for (int i = 0; i < numberOfBrackets; i++) 
			{
				Calculator.inputStack.push(")");
				Calculator.calculatorDisplay.setText(Calculator.calculatorDisplay.getText() + ")");
			}
		} else if (numberOfBrackets < 0) {
			numberOfBrackets *= (-1);
			for (int i = 1; i <= numberOfBrackets; i++) 
			{
				Calculator.inputStack.add(i, "(");
				Calculator.calculatorDisplay.setText("(" + Calculator.calculatorDisplay.getText());
			}
		}
	}

	private static int checkNumberOfBrackets(Stack<String> infixStack) 
	{
		int numberOfOpenBrackets = 0;
		int numberOfClosedBrackets = 0;

		for (String str : infixStack) 
		{
			if (str.equals("(")) 
				numberOfOpenBrackets++;
		}

		for (String str : infixStack) {
			if (str.equals(")")) {
				numberOfClosedBrackets++;
			}
		}

		return numberOfOpenBrackets - numberOfClosedBrackets;
	}
}
