package calc;

import org.eclipse.swt.widgets.Text;

import calc.OperationsClass.Operations;

public class RestrictionConditions extends UserInterface {

	private static void checkOperators(Text text, Character element) {
		if (!numbers.isEmpty() && !numbers.get(numbers.size() - 1).equals('.')) {
			StringBuilder string = new StringBuilder();
			for (Character newCharacters : numbers) {
				string.append(newCharacters);
			}

			String result = string.toString();
			textBox.add(result);

			if (!textBox.isEmpty()
					&& !textBox.get(textBox.size() - 1).equals(Operations.MULTIPLICATION.getSignOfOperation())
					&& !textBox.get(textBox.size() - 1).equals(Operations.DIVISION.getSignOfOperation())
					&& !textBox.get(textBox.size() - 1).equals(Operations.ADDITON.getSignOfOperation())
					&& !textBox.get(textBox.size() - 1).equals(Operations.SUBSTRACTION.getSignOfOperation())
					&& !textBox.get(textBox.size() - 1).equals("=")) {
				textBox.add(element.toString());

				if (textBox.get(textBox.size() - 1).equals("=")) {
					equalChecker = true;
					textBox.remove(textBox.size() - 1);
				}
			}
			numbers.clear();
			string = null;
			updateText(text);
		}

	}

	boolean checkPoint() {
		boolean pointChecker = true;

		if (!numbers.isEmpty() && !numbers.get(numbers.size() - 1).equals('.') || (!textBox.isEmpty()
				&& !textBox.get(textBox.size() - 1).equals(Operations.MULTIPLICATION.getSignOfOperation())
				&& !textBox.get(textBox.size() - 1).equals(Operations.DIVISION.getSignOfOperation())
				&& !textBox.get(textBox.size() - 1).equals(Operations.ADDITON.getSignOfOperation())
				&& !textBox.get(textBox.size() - 1).equals(Operations.SUBSTRACTION.getSignOfOperation()))) {
			pointChecker = false;
		}
		return pointChecker;
	}

	void checkConditions(Text text, Character elementString) {
		equalChecker = false;

		if (!elementString.equals('x') && !elementString.equals('/') && !elementString.equals('+')
				&& !elementString.equals('-') && !elementString.equals('=')) {
			numbers.add(elementString);
			text.append(elementString.toString());
		} else
			checkOperators(text, elementString);
	}
}