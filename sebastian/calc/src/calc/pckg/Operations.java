package calc.pckg;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Operations 
{
//	final static CalculatorCharacters plus = CalculatorCharacters.ADDITION;
//	final static CalculatorCharacters minus = CalculatorCharacters.SUBSTRACTION;
//	final static CalculatorCharacters multiply = CalculatorCharacters.MULTIPLICATION;
//	final static CalculatorCharacters division = CalculatorCharacters.DIVISION;
    private static final String plus = "+";
	private static final String minus = "-";
	private static final String multiply = "*";
	private static final String divide = "/";
	private static String[] infix;
	private static int i=0,j=0;

	
	private static List<String> multipleDigitArray = new ArrayList<>();

	public static String[] complexNumbers(Stack<String> operandsStack, int count) 
	{
		operandsStack.remove(0);
		operandsStack.push(null);
		
		infix = new String[count];
		for (i = 0; i < count; i++) 
		{
			if (Verify.isNumeric(operandsStack.get(i))) 			
				multipleDigitArray.add(operandsStack.get(i));
			else 
				addToInfix(operandsStack);
		}
		j=0;
		return infix;
	}
	
	private static void addToInfix(Stack<String> operandsStack)
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
	
	
	public static String[] removeNull(String[] operandsStack, int count) 
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
		numberOfCharacters=0;
		return infixFinal;
	}

	public static String[] checkIfDecimal(String[] operandsStack, int count) 
	{
		for (i = 0; i < count; i++) 
			if (Verify.isNotNull(operandsStack[i])&& Verify.isNotNull(operandsStack[i+1])&& Verify.isNotNull(operandsStack[i+2])) 
				if (Verify.isNumeric(operandsStack[i])) 
					if (operandsStack[i + 1].equals("."))
					{
						operandsStack[i] = operandsStack[i] + "" + operandsStack[i + 1] + ""
								+ operandsStack[i + 2].toString()
								.replace(",", "").replace(" ", "").replace("[", "").replace("]", "");

						operandsStack[i + 1] = null;
						operandsStack[i + 2] = null;

						i += 2;
					}
		return operandsStack;
	}

	public static double postFixCalculator(String[] postFixString) 
	{
		Stack<Double> operandsStack = new Stack<Double>();

		for (String str : postFixString) 
		{
			// The loop will run until it finds a null, because of a "(" or ")" character
			if (str != null)
				switch (str) {
				// If there is one of these characters ID'd, the loop will pop last 2 operators
				case plus:
				case minus:
				case multiply:
				case divide:
					double stackPeek = operandsStack.pop();
					double beforePeek = operandsStack.pop();
					double value = 0;
					
					switch (str) 
					{
						case plus:
							value = beforePeek + stackPeek;
							break;
							
						case minus:
							value = beforePeek - stackPeek;
							break;
							
						case multiply:
							value = beforePeek * stackPeek;
							break;
						case divide:
							value = beforePeek / stackPeek;
							break;
							
						default:
							break;
					}
					// after the case break the value of the new operand will be added to the LIFO
					// stack
					operandsStack.push(value);
					break;
				default:
					// if no case is triggered than the value, of the operand will pe addes to the
					// stack
					operandsStack.push(Double.parseDouble(str));
					break;
				}
		}
		// returns the result in a Double form
		return operandsStack.pop();
	}

	static int priority(String operator) 
	{
		switch (operator) 
		{
		case plus:
		case minus:
			return 1;

		case multiply:
		case divide:
			return 2;
		}
		return -1;
	}

	// The main method that converts given infix expression
	// to postfix expression.
	public static String[] infixToPostfixConverter(String[] inFixString, int nrOfElemnts)
	{
		int i = 0;
		// initializing empty String for result
		String[] resultString = new String[nrOfElemnts];

		// initializing empty stack
		Stack<String> temporaryStack = new Stack<>();

		for (String str : inFixString) 
		{
			// If the scanned string is an operand, add it to output.
			if (Verify.isNumeric(str)) 
			{
				resultString[i] = str;
				i++;
			}
			// If the scanned string is an '(', push it to the stack.
			else if (str.equals("(")) 
				temporaryStack.push(str);
			// If the scanned String is an ')', pop and output from the stack
			// until an '(' is encountered.
			else if (str.equals(")")) {
				while (!temporaryStack.isEmpty() && !temporaryStack.peek().equals("(")) 
				{
					resultString[i] = temporaryStack.pop();
					i++;
				}

				if (!temporaryStack.isEmpty() && !temporaryStack.peek().equals("("))
					return null; // invalid expression
				else
					temporaryStack.pop();
			} 
			else // an operator is encountered
			{
				while (!temporaryStack.isEmpty() && priority(str) <= priority(temporaryStack.peek())) 
				{
					if (temporaryStack.peek().equals("(")) 
					{
						return null;
					} else {
						resultString[i] = temporaryStack.pop();
						i++;
					}
				}
				temporaryStack.push(str);
			}
		}

		// pop all the operators from the stack aka the higher priority operands
		while (!temporaryStack.isEmpty()) 
		{

			if (temporaryStack.peek().equals("(")) 
				return null;
			else
			{
				resultString[i] = temporaryStack.pop();
				i++;
			}
		}
		return resultString;
	}
}
