package calc.pckg;

class Operations {

	static boolean IsNumber(String input) {
		try {
			Float.parseFloat(input);
		} catch (NumberFormatException | NullPointerException nfe) {
			return false;
		}
		return true;
	}

	static Float allOperations(String operation, float data, float secondOperand) {

		float result = 0;
		switch (operation) {
		case "addition":
			result = data + secondOperand;
			return result;

		case "substraction":
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
