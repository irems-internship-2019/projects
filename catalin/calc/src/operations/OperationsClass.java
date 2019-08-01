package operations;

import org.eclipse.swt.widgets.Text;

import ui.UserInterface;

public class OperationsClass extends UserInterface {
	private int numberIndex;
	private double firstNumber;
	private double secondNumber;
	private double result;

	public enum Operations {
		ADDITON("+"),
		SUBSTRACTION("-"),
		DIVISION("/"),
		MULTIPLICATION("x");

		private final String signOfOperation;

		Operations(String signOfOperation) {
			this.signOfOperation = signOfOperation;
		}

		public String getSignOfOperation() {
			return signOfOperation;
		}

		public Character getCharacterOfOperation() {
			return signOfOperation.charAt(0);
		}
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

	private void findNumbers(int numberOfIndex) {
		firstNumber = Double.parseDouble(allTexts.get(numberIndex - 1));
		secondNumber = Double.parseDouble(allTexts.get(numberIndex + 1));
	}

	private void setNewIndex(int numberOfIndex) {
		allTexts.remove(numberIndex + 1);
		allTexts.remove(numberIndex);
		allTexts.set(numberIndex - 1, checkDouble(result));
	}

	protected void plus(Text text) {
		numberIndex = allTexts.indexOf(Operations.ADDITON.getSignOfOperation());
		findNumbers(numberIndex);
		result = firstNumber + secondNumber;
		setNewIndex(numberIndex);
	}

	protected void minus(Text text) {
		numberIndex = allTexts.indexOf(Operations.SUBSTRACTION.getSignOfOperation());
		findNumbers(numberIndex);
		result = firstNumber - secondNumber;
		setNewIndex(numberIndex);
	}

	protected void multiple(Text text) {
		numberIndex = allTexts.indexOf(Operations.MULTIPLICATION.getSignOfOperation());
		findNumbers(numberIndex);
		result = firstNumber * secondNumber;
		setNewIndex(numberIndex);
	}

	protected void divide(Text text) {
		numberIndex = allTexts.indexOf(Operations.DIVISION.getSignOfOperation());
		findNumbers(numberIndex);
		result = firstNumber / secondNumber;
		setNewIndex(numberIndex);
	}
}