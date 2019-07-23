package calc;

import org.eclipse.swt.widgets.Text;

public class Operation extends ButtonClass {

	public static void plus(Text text) {
		int numberIndex = textBox.indexOf("+");
		double firstNumber = Double.parseDouble(textBox.get(numberIndex - 1));
		double secondNumber = Double.parseDouble(textBox.get(numberIndex + 1));
		double result = firstNumber + secondNumber;
		textBox.remove(numberIndex + 1);
		textBox.remove(numberIndex);
		textBox.set(numberIndex - 1, checkDouble(result));
		updateText(text);

	}

	public static void minus(Text text) {
		int numberIndex = textBox.indexOf("-");
		double firstNumber = Double.parseDouble(textBox.get(numberIndex - 1));
		double secondNumber = Double.parseDouble(textBox.get(numberIndex + 1));
		double result = firstNumber - secondNumber;
		textBox.remove(numberIndex + 1);
		textBox.remove(numberIndex);
		textBox.set(numberIndex - 1, checkDouble(result));
		updateText(text);
	}

	public static void multiple(Text text) {
		int numberIndex = textBox.indexOf("x");
		double firstNumber = Double.parseDouble(textBox.get(numberIndex - 1));
		double secondNumber = Double.parseDouble(textBox.get(numberIndex + 1));
		double result = firstNumber * secondNumber;
		textBox.remove(numberIndex + 1);
		textBox.remove(numberIndex);
		textBox.set(numberIndex - 1, checkDouble(result));
		updateText(text);

	}

	public static void divide(Text text) {
		int numberIndex = textBox.indexOf("/");
		double firstNumber = Double.parseDouble(textBox.get(numberIndex - 1));
		double secondNumber = Double.parseDouble(textBox.get(numberIndex + 1));
		double result = firstNumber / secondNumber;
		textBox.remove(numberIndex + 1);
		textBox.remove(numberIndex);
		textBox.set(numberIndex - 1, checkDouble(result));
		updateText(text);
	}

	public static String checkDouble(double number) {
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

}
