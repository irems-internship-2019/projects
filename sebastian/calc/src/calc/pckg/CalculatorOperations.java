package calc.pckg;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;

public class CalculatorOperations {

	//	final static CalculatorCharacters plus = CalculatorCharacters.ADDITION;
	//	final static CalculatorCharacters minus = CalculatorCharacters.SUBSTRACTION;
	//	final static CalculatorCharacters multiply = CalculatorCharacters.MULTIPLICATION;
	//	final static CalculatorCharacters division = CalculatorCharacters.DIVISION;

	private static final String plus = "+";
	private static final String minus = "-";
	private static final String multiply = "*";
	private static final String divide = "/";
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
			else if (infixString[j].equals("(")) 
				temporaryStack.push(infixString[j]);
			// If the scanned String is an ')', pop and output from the stack
			// until an '(' is encountered.
			else if (infixString[j].equals(")")) {
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

	// an operator is encountered
	private static boolean  anOperatorIsEncountered(String infixElement) 
	{
		while (!temporaryStack.isEmpty() && operatorPriority(infixElement) <= operatorPriority(temporaryStack.peek())) 
		{
			if (temporaryStack.peek().equals("(")) 
				return false;
			else {
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

			if (temporaryStack.peek().equals("(")) 
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
			Verify.fixBracketCount();

			String[] infixDecimalDouble = StringOperations.checkIfDecimal(
					StringOperations.complexNumbers(Calculator.inputStack, Calculator.inputStack.size()), Calculator.inputStack.size());
			String[] infixFinal = StringOperations.removeNull(infixDecimalDouble, Calculator.inputStack.size());
			int infixCount = infixFinal.length;
			Double finalValue = postFixCalculator(infixToPostfixConverter(infixFinal, infixCount));
			Calculator.calculatorDisplay.setText(Calculator.calculatorDisplay.getText() + "=" + finalValue);

			Calculator.inputStack.clear();
			Calculator.inputStack.push("null");
			//Calculator.inputStack.push(Double.toString(finalValue));

		} catch (Exception exception) 
		{
			Calculator.inputStack.clear();
			Calculator.inputStack.push("null");
			Calculator.calculatorDisplay.setText("- ERROR -");
			//System.out.println(exception);
		}
	}

}
