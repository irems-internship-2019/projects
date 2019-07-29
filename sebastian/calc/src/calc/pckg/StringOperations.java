package calc.pckg;
//import java.math.BigDecimal;
//import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

class StringOperations extends StackOperations
{
	private static String[] infix;
	private static int i=0,j=0;

	private static List<String> multipleDigitArray = new ArrayList<>();

	Verify newVerify = new Verify();

	String[] complexNumbers() 
	{
		removeFromStack(0);
		pushStack(null);

		int count = stackSize();

		infix = new String[count];

		for (i = 0; i < count; i++) 
		{
			if (newVerify.isNumeric(getStack(i))) 			
				multipleDigitArray.add(getStack(i));
			else 
				addComplexNumbersToInfix();
		}

		j=0;
		return infix;
	}

	private  void addComplexNumbersToInfix()
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
			infix[j] = getStack(i);
			j++;
		}
	}


	String[] removeNull(String[] operandsStack, int count) 
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

	String[] checkIfDecimal(String[] operandsStack, int count) 
	{
		for (i = 0; i < count; i++) 
			if (newVerify.isNotNull(operandsStack[i])&& newVerify.isNotNull(operandsStack[i+1])&& newVerify.isNotNull(operandsStack[i+2])) 
				if (newVerify.isNumeric(operandsStack[i])) 
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
