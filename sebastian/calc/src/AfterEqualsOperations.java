import java.util.Stack;

public class AfterEqualsOperations {
	static Stack<String> multipleDigitStack = new Stack<>();

	public static String[] ComplexNumbers(Stack<String> operandsStack, int count) {
		operandsStack.remove(0);
		operandsStack.push(null);
		String[] infix = new String[count];
		int j = 0;
		for (int i = 0; i < count; i++) {
			if (ConditionsAndVerification.isNumeric(operandsStack.get(i))) {
				multipleDigitStack.push(operandsStack.get(i));
			} else {
				if (!multipleDigitStack.isEmpty()) {
					infix[j] = multipleDigitStack.toString().replace(",", "").replace(" ", "").replace("[", "")
							.replace("]", "");
					j++;
					i--;
					multipleDigitStack.clear();
				} else {
					infix[j] = operandsStack.get(i);
					j++;
				}
			}
		}
		return infix;
	}

	public static String[] RemoveNull(String[] operandsStack, int count) {
		int numberOfcharacters = 0;
		for (String str : operandsStack) {
			if (str != null && !str.isEmpty()) {
				numberOfcharacters++;
			}
		}

		String[] infixFinal = new String[numberOfcharacters];
		int FinalCount = 0;
		for (int i = 0; i < count; i++) {
			if (operandsStack[i] != null && !operandsStack[i].isEmpty()) {
				infixFinal[FinalCount] = operandsStack[i];
				FinalCount++;
			}
		}
		return infixFinal;
	}

	public static String[] CheckifDecimal(String[] operandsStack, int count) {
		for (int i = 0; i < count; i++) {
			if (operandsStack[i] != null && !operandsStack[i].isEmpty()) {
				if (ConditionsAndVerification.isNumeric(operandsStack[i])) {
					if (operandsStack[i + 1] != null && !operandsStack[i + 1].isEmpty()) {
						if (operandsStack[i + 1].equals(".")) {
							if (operandsStack[i + 2] != null && !operandsStack[i + 2].isEmpty()) {
								operandsStack[i] = operandsStack[i] + "" + operandsStack[i + 1] + ""
										+ operandsStack[i + 2].toString().replace(",", "").replace(" ", "")
												.replace("[", "").replace("]", "");
								operandsStack[i + 1] = null;
								operandsStack[i + 2] = null;
								i += 2;
							}
						}
					}
				}
			}
		}
		return operandsStack;
	}

	public static double PostFixCalculator(String[] postFixString) {
		Stack<Double> operandsStack = new Stack<Double>();

		for (String str : postFixString) {
			// The loop will run untill it finds a null, because of a "(" or ")" character
			if (str != null)
				switch (str) {
				// If there is one of these characters ID'd, the loop will pop last 2 operators
				case "+":
				case "-":
				case "*":
				case "/":
					double right = operandsStack.pop();
					double left = operandsStack.pop();
					double value = 0;
					switch (str) {
					case "+":
						value = left + right;
						break;
					case "-":
						value = left - right;
						break;
					case "*":
						value = left * right;
						break;
					case "/":
						value = left / right;
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

	static int Priority(String operator) {
		switch (operator) {
		case "+":
		case "-":
			return 1;

		case "*":
		case "/":
			return 2;
		}
		return -1;
	}

	// The main method that converts given infix expression
	// to postfix expression.
	public static String[] InfixToPostfixConverter(String[] inFixString, int nrOfElemnts) {
		int i = 0;
		// initializing empty String for result
		String[] resultString = new String[nrOfElemnts];

		// initializing empty stack
		Stack<String> temporaryStack = new Stack<>();

		for (String str : inFixString) {
			// If the scanned string is an operand, add it to output.
			if (ConditionsAndVerification.isNumeric(str)) {
				resultString[i] = str;
				i++;
			}
			// If the scanned string is an '(', push it to the stack.
			else if (str.equals("(")) {
				temporaryStack.push(str);
			}
			// If the scanned String is an ')', pop and output from the stack
			// until an '(' is encountered.
			else if (str.equals(")")) {
				while (!temporaryStack.isEmpty() && !temporaryStack.peek().equals("(")) {
					resultString[i] = temporaryStack.pop();
					i++;
				}

				if (!temporaryStack.isEmpty() && !temporaryStack.peek().equals("("))
					return null; // invalid expression
				else
					temporaryStack.pop();
			} else // an operator is encountered
			{
				while (!temporaryStack.isEmpty() && Priority(str) <= Priority(temporaryStack.peek())) {

					if (temporaryStack.peek().equals("(")) {
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
		while (!temporaryStack.isEmpty()) {

			if (temporaryStack.peek().equals("(")) {
				return null;
			} else {
				resultString[i] = temporaryStack.pop();
				i++;
			}
		}
		return resultString;
	}
}
