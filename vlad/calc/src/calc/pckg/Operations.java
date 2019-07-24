package calc.pckg;

public class Operations {

	protected static boolean isNull(String input) {
		if (input != null && !input.isEmpty())
			return false;
		else
			return true;
	}

	protected static boolean IsNumber(String input) {
		try {
			Float.parseFloat(input);
		} catch (NumberFormatException | NullPointerException nfe) {
			return false;
		}
		return true;
	}

	public static Float allOperations(String operation, float data, float secondOperand) {

		float result = 0;
		switch (operation) {
		case "addition":
			result = data + secondOperand;

			return result;

		case "subtraction":
			result = data - secondOperand;
			return result;

		case "multiplication":
			result = data * secondOperand;
			return result;

		case "division":
			result = data / secondOperand;
			return result;

		default:
			break;
		}
		return result;

	}

}
