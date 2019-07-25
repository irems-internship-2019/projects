package calc.pckg;
import java.util.Stack;

public class ConditionsAndVerification {

	// if the String is "("
	public static boolean isOpenB(String strNum) {
		if (strNum.equals("(")) {
			return true;
		} else {
			return false;
		}
	}

	// if the String is ")"
	public static boolean isCloseB(String strNum) {
		if (strNum.equals(")")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNumeric(String strNum) {
		try {
			Double.parseDouble(strNum);
		} catch (NumberFormatException | NullPointerException nfe) {
			return false;
		}
		return true;
	}

	public static boolean Verify(String right, String left) {

		// The order for these conditions, is critical
		// the ones that are specific, have to be on top
		// the ones that are not specific, eg. !isNumeric MUST be LOWER
		// Specifics

		if (left.equals("dorel") && isNumeric(right)) {
			return true;
		} // dorel 1
		else if (left.equals("dorel") && isOpenB(right)) {
			return true;
		} // dorel (
		else if (left.equals("dorel") && isCloseB(right)) {
			return false;
		} // dorel )
		else if (left.equals("dorel") && !isNumeric(right)) {
			return false;
		} // dorel +
		else if (isNumeric(left) && isNumeric(right)) {
			return true;
		} // 1 1
		else if (isNumeric(left) && isCloseB(right)) {
			return true;
		} // 1 )
		else if (isNumeric(left) && isOpenB(right)) {
			return false;
		} // 1 (
		else if (isOpenB(left) && isCloseB(right)) {
			return false;
		} // ( )
		else if (isCloseB(left) && isOpenB(right)) {
			return false;
		} // ) (
		else if (isCloseB(left) && isNumeric(right)) {
			return false;
		} // ) 1
		else if (left.equals("/") && right.equals("0")) {
			return false;
		} // / 0
		else if (isOpenB(left) && isNumeric(right)) {
			return true;
		} // ( 1
		else if (isOpenB(left) && isOpenB(right)) {
			return true;
		} // ( (
		else if (isCloseB(left) && isCloseB(right)) {
			return true;
		} // ) )

		// non Specifics
		else if (isCloseB(left) && !isNumeric(right)) {
			return true;
		} // ) +
		else if (isNumeric(left) && !isNumeric(right)) {
			return true;
		} // 1 +
		else if (!isNumeric(left) && isOpenB(right)) {
			return true;
		} // + (
		else if (!isNumeric(left) && isNumeric(right)) {
			return true;
		} // + 1
		else if (!isNumeric(left) && isCloseB(right)) {
			return false;
		} // + )
		else if (isOpenB(left) && !isNumeric(right)) {
			return false;
		} // ( +
		else if (!isNumeric(left) && !isNumeric(right)) {
			return false;
		} // + +
		return false;
		// should add more conditions
		// with 3 parameters eg. 1.1. or
	}
	
	public static int checkNumberOfBrackets(Stack<String> infixStack) {
		int numberOfOpenBrackets = 0;
		int numberOfClosedBrackets = 0;
		for (String str : infixStack) {
			if (str.equals("(")) {
				numberOfOpenBrackets++;
			}
		}

		for (String str : infixStack) {
			if (str.equals(")")) {
				numberOfClosedBrackets++;
			}
		}
		return numberOfOpenBrackets - numberOfClosedBrackets;
	}
}
