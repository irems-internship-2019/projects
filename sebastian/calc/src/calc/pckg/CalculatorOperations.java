package calc.pckg;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;

public class CalculatorOperations {

	//	final static String addition = CalculatorCharacters.ADDITION.getName();
	//	final static CalculatorCharacters minus = CalculatorCharacters.SUBSTRACTION;
	//	final static CalculatorCharacters multiply = CalculatorCharacters.MULTIPLICATION;
	//	final static CalculatorCharacters division = CalculatorCharacters.DIVISION;

	private static final String PLUS = "+";
	private static final String MINUS = "-";
	private static final String MULTIPLY = "*";
	private static final String DIVIDE = "/";
	private static final String OPENBRACKET ="(";
	private static final String CLOSEDBRACKET =")";
	private static final String NULL= "null";

	private static int i = 0, j = 0;

	private static String[] resultString;
	private static Stack<String> temporaryStack = new Stack<>();

	private static double postFixCalculator(String[] postFixString) 
	{
		Stack<Double> operandsStack = new Stack<Double>();

		for (String str : postFixString) 
		{
			// The loop will run until it finds a null, because of a "(" or ")" character
			if (str != null)
				switch (str) {
				// If there is one of these characters ID'd, the loop will pop last 2 operators
				case PLUS:
				case MINUS:
				case MULTIPLY:
				case DIVIDE:
					double stackPeek = operandsStack.pop();
					double beforePeek = operandsStack.pop();
					double value = 0;

					switch (str) 
					{
					case PLUS:
						value = beforePeek + stackPeek;
						break;

					case MINUS:
						value = beforePeek - stackPeek;
						break;

					case MULTIPLY:
						value = beforePeek * stackPeek;
						break;

					case DIVIDE:
						value = beforePeek / stackPeek;
						break;

					default:
						break;
					}

					operandsStack.push(value);
					break;

				default:
					operandsStack.push(Double.parseDouble(str));
					break;
				}
		}
		return BigDecimal.valueOf(operandsStack.pop()).setScale(3, RoundingMode.HALF_UP).doubleValue();
	}

	private static int operatorPriority(String operator) 
	{
		switch (operator) 
		{
		case PLUS:
		case MINUS:
			return 1;

		case MULTIPLY:
		case DIVIDE:
			return 2;
		}
		return -1;
	}

	// The main method that converts given infix expression
	// to postfix expression.
	private static String[] infixToPostfixConverter(String[] infixString, int nrOfElemnts)
	{
		resultString = new String[nrOfElemnts];

		for (j =0; j < nrOfElemnts; j++) 
		{
			// If the scanned string is an operand, add it to output.
			if (Verify.isNumeric(infixString[j])) 
			{
				resultString[i] = infixString[j];
				i++;
			}
			// If the scanned string is an '(', push it to the stack.
			else if (infixString[j].equals(OPENBRACKET)) 
				temporaryStack.push(infixString[j]);
			// If the scanned String is an ')', pop and output from the stack
			// until an '(' is encountered.
			else if (infixString[j].equals(CLOSEDBRACKET)) 
			{
				if(!aBracketIsEncountered()) 
					return null;
			} 
			else 
			{
				if(anOperatorIsEncountered(infixString[j]))
					temporaryStack.push(infixString[j]);
				else 
					return null;
			}
		}

		if(popAllOperatorFromTheStack()) 
		{
			i=0;
			return resultString;
		}
		else
			return null;
	}


	private static boolean aBracketIsEncountered() 
	{
		while (!temporaryStack.isEmpty() && !temporaryStack.peek().equals(OPENBRACKET)) 
		{
			resultString[i] = temporaryStack.pop();
			i++;
		}

		if (!temporaryStack.isEmpty() && !temporaryStack.peek().equals(OPENBRACKET))
			return false; // invalid expression
		else
			temporaryStack.pop();
		return true;
	}

	// an operator is encountered
	private static boolean  anOperatorIsEncountered(String infixElement) 
	{
		while (!temporaryStack.isEmpty() && operatorPriority(infixElement) <= operatorPriority(temporaryStack.peek())) 
		{
			if (temporaryStack.peek().equals(OPENBRACKET)) 
				return false;
			else 
			{
				resultString[i] = temporaryStack.pop();
				i++;
			}
		}
		return true;
	}

	// pop all the operators from the stack aka the higher priority operands
	private static boolean popAllOperatorFromTheStack() 
	{
		while (!temporaryStack.isEmpty()) 
		{
			if (temporaryStack.peek().equals(OPENBRACKET)) 
				return false;
			else
			{
				resultString[i] = temporaryStack.pop();
				i++;
			}
		}
		return true;
	}

	static void calculateFinalValue() 
	{
		try {
			//int size = StackOperations.stackSize();
			Verify.fixBracketCount();

			String[] infixDecimalDouble = StringOperations.checkIfDecimal(
					StringOperations.complexNumbers(), StackOperations.stackSize());

			String[] infixFinal = StringOperations.removeNull(infixDecimalDouble, StackOperations.stackSize());

			Double finalValue = postFixCalculator(infixToPostfixConverter(infixFinal, infixFinal.length));

			TextWidget.setDisplayFinalValue(finalValue);

			StackOperations.clearStack();
			StackOperations.pushStack(NULL);
			//CreateCalculatorUi.inputStack.push(Double.toString(finalValue));

		} catch (Exception exception) 
		{
			StackOperations.clearStack();
			StackOperations.pushStack(NULL);
			TextWidget.setDisplayToError();
			//System.out.println(exception);
		}
	}

}
