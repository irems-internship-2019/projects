package calc.pckg;
//import java.math.BigDecimal;
//import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class StringOperations 
{
	private static String[] infix;
	private static int i=0,j=0;

	private static List<String> multipleDigitArray = new ArrayList<>();

	static String[] complexNumbers(Stack<String> operandsStack, int count) 
	{
		operandsStack.remove(0);
		operandsStack.push(null);

		infix = new String[count];
		for (i = 0; i < count; i++) 
		{
			if (Verify.isNumeric(operandsStack.get(i))) 			
				multipleDigitArray.add(operandsStack.get(i));
			else 
				addComplexNumbersToInfix(operandsStack);
		}
		j=0;
		return infix;
	}

	static void addComplexNumbersToInfix(Stack<String> operandsStack)
	{
		if (!multipleDigitArray.isEmpty()) {
			infix[j] = multipleDigitArray.toString().replace(",", "").replace(" ", "").replace("[", "")
					.replace("]", "");
			j++;
			i--;

			multipleDigitArray.clear();
		} else {
			infix[j] = operandsStack.get(i);
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
