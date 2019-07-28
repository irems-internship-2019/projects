package calc.pckg;
//import java.math.BigDecimal;
//import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

class StringOperations 
{
	private static String[] infix;
	private static int i=0,j=0;

	private static List<String> multipleDigitArray = new ArrayList<>();

	static String[] complexNumbers() 
	{
		StackOperations.removeFromStack(0);
		StackOperations.pushStack(null);

		int count = StackOperations.stackSize();

		infix = new String[count];

		for (i = 0; i < count; i++) 
		{
			if (Verify.isNumeric(StackOperations.getStack(i))) 			
				multipleDigitArray.add(StackOperations.getStack(i));
			else 
				addComplexNumbersToInfix();
		}

		j=0;
		return infix;
	}

	private static void addComplexNumbersToInfix()
	{
		if (!multipleDigitArray.isEmpty()) 
		{
			infix[j] = multipleDigitArray.toString().replace(",", "").replace(" ", "").replace("[", "")
					.replace("]", "");
			j++;
			i--;

			multipleDigitArray.clear();

		} 
		else 
		{
			infix[j] = StackOperations.getStack(i);
			j++;
		}
	}


	static String[] removeNull(String[] operandsStack, int count) 
	{
		int numberOfCharacters = 0;

		for (String str : operandsStack) 
		{
			if (str != null && !str.isEmpty()) 
				numberOfCharacters++;
		}

		final String[] infixFinal = new String[numberOfCharacters];

		int FinalCount = 0;
		for (int i = 0; i < count; i++) 
		{
			if (operandsStack[i] != null && !operandsStack[i].isEmpty()) 
			{
				infixFinal[FinalCount] = operandsStack[i];
				FinalCount++;
			}
		}
		return infixFinal;
	}

	static String[] checkIfDecimal(String[] operandsStack, int count) 
	{
		for (i = 0; i < count; i++) 
			if (Verify.isNotNull(operandsStack[i])&& Verify.isNotNull(operandsStack[i+1])&& Verify.isNotNull(operandsStack[i+2])) 
				if (Verify.isNumeric(operandsStack[i])) 
					if (operandsStack[i + 1].equals("."))
					{
						operandsStack[i] = operandsStack[i] + "" + operandsStack[i + 1] + ""+ operandsStack[i + 2].toString().replace(",", "").replace(" ", "").replace("[", "").replace("]", "");
						operandsStack[i + 1] = null;
						operandsStack[i + 2] = null;

						i += 2;
					}
		return operandsStack;
	}
}
