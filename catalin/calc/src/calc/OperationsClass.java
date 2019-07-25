package calc;

import org.eclipse.swt.widgets.Text;

public class OperationsClass extends UserInterface {
	private int numberIndex;
	private double firstNumber;
	private double secondNumber;
	private double result;

	enum Operations {
		ADDITON("+"), SUBSTRACTION("-"), DIVISION("/"), MULTIPLICATION("x");

		private final String signOfOperation;

		Operations(String signOfOperation) {
			this.signOfOperation = signOfOperation;
		}

		public String getSignOfOperation() {
			return signOfOperation;
		}
	}

	private void plus(Text text) {
		numberIndex = textBox.indexOf("+");
		firstNumber = Double.parseDouble(textBox.get(numberIndex - 1));
		secondNumber = Double.parseDouble(textBox.get(numberIndex + 1));
		result = firstNumber + secondNumber;
		textBox.remove(numberIndex + 1);
		textBox.remove(numberIndex);
		textBox.set(numberIndex - 1, checkDouble(result));
		updateText(text);
	}

	private void minus(Text text) {
		numberIndex = textBox.indexOf("-");
		firstNumber = Double.parseDouble(textBox.get(numberIndex - 1));
		secondNumber = Double.parseDouble(textBox.get(numberIndex + 1));
		result = firstNumber - secondNumber;
		textBox.remove(numberIndex + 1);
		textBox.remove(numberIndex);
		textBox.set(numberIndex - 1, checkDouble(result));
		updateText(text);
	}

	private void multiple(Text text) {
		numberIndex = textBox.indexOf("x");
		firstNumber = Double.parseDouble(textBox.get(numberIndex - 1));
		secondNumber = Double.parseDouble(textBox.get(numberIndex + 1));
		result = firstNumber * secondNumber;
		textBox.remove(numberIndex + 1);
		textBox.remove(numberIndex);
		textBox.set(numberIndex - 1, checkDouble(result));
		updateText(text);
	}

	private void divide(Text text) {
		numberIndex = textBox.indexOf("/");
		firstNumber = Double.parseDouble(textBox.get(numberIndex - 1));
		secondNumber = Double.parseDouble(textBox.get(numberIndex + 1));
		result = firstNumber / secondNumber;
		textBox.remove(numberIndex + 1);
		textBox.remove(numberIndex);
		textBox.set(numberIndex - 1, checkDouble(result));
		updateText(text);
	}

	private void multipleConditions(Text text) {
		if (textBox.contains(Operations.MULTIPLICATION.getSignOfOperation())
				&& (textBox.indexOf(Operations.MULTIPLICATION.getSignOfOperation()) <= textBox
						.indexOf(Operations.DIVISION.getSignOfOperation())
						|| textBox.indexOf(Operations.MULTIPLICATION.getSignOfOperation()) == 1
						|| !textBox.contains(Operations.DIVISION.getSignOfOperation())))
			multiple(text);
	}

	private void divideConditions(Text text) {
		if (textBox.contains(Operations.DIVISION.getSignOfOperation())
				&& (textBox.indexOf(Operations.DIVISION.getSignOfOperation()) <= textBox
						.indexOf(Operations.MULTIPLICATION.getSignOfOperation())
						|| textBox.indexOf(Operations.DIVISION.getSignOfOperation()) == 1
						|| !textBox.contains(Operations.MULTIPLICATION.getSignOfOperation())))
			divide(text);
	}

	private void plusConditions(Text text) {
		if (textBox.contains(Operations.ADDITON.getSignOfOperation())
				&& (textBox.indexOf(Operations.ADDITON.getSignOfOperation()) <= textBox
						.indexOf(Operations.SUBSTRACTION.getSignOfOperation())
						|| textBox.indexOf(Operations.ADDITON.getSignOfOperation()) == 1
						|| !textBox.contains(Operations.SUBSTRACTION.getSignOfOperation())))
			plus(text);
	}

	private void minusConditions(Text text) {
		if (textBox.contains(Operations.SUBSTRACTION.getSignOfOperation())
				&& (textBox.indexOf(Operations.SUBSTRACTION.getSignOfOperation()) <= textBox
						.indexOf(Operations.ADDITON.getSignOfOperation())
						|| textBox.indexOf(Operations.SUBSTRACTION.getSignOfOperation()) == 1
						|| !textBox.contains(Operations.ADDITON.getSignOfOperation())))
			minus(text);
	}

	private String checkDouble(double number) {
		String lastResult;
		int secondNumber = (int) number;
		double result = number - secondNumber;

		if (result == (double) 0) {
			lastResult = Integer.toString(secondNumber);
			return lastResult;
		} else {
			lastResult = Double.toString(number);
			return lastResult;
		}
	}

	void decideOperations(Text text) {
		while (textBox.contains(Operations.MULTIPLICATION.getSignOfOperation())
				|| textBox.contains(Operations.DIVISION.getSignOfOperation())) {
			multipleConditions(text);
			divideConditions(text);
		}
		while (textBox.contains(Operations.ADDITON.getSignOfOperation())
				|| textBox.contains(Operations.SUBSTRACTION.getSignOfOperation())) {
			plusConditions(text);
			minusConditions(text);
		}
	}
}