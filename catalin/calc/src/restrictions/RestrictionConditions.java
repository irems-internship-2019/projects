package restrictions;

import org.eclipse.swt.widgets.Text;

import operations.OperationsClass.Operations;
import ui.UserInterface;
import ui.ResetPointEqualButtons.ResetPointEqual;

public class RestrictionConditions extends UserInterface {
	private boolean checkForEqual(Character element) {
		boolean equalChecker = false;

		if (element.equals(ResetPointEqual.EQUAL.getCharacterOfResetPointEqual()) == true || checkForOperators() == true
				|| (!numbers.isEmpty() && !numbers.get(numbers.size() - 1)
						.equals(ResetPointEqual.POINT.getCharacterOfResetPointEqual())))
			equalChecker = true;

		return equalChecker;
	}

	private void insertOperators(Text text, Character element) {
		if (checkForEqual(element) == true) {
			StringBuilder string = new StringBuilder();
			for (Character newCharacters : numbers) {
				string.append(newCharacters);
			}

			if (!numbers.isEmpty()) {
				String result = string.toString();
				allTexts.add(result);
			}
			allTexts.add(element.toString());

			if (allTexts.get(allTexts.size() - 1).equals(ResetPointEqual.EQUAL.getSignOfResetPointEqual())) {
				allTexts.remove(allTexts.size() - 1);
			}
			numbers.clear();
			string = null;
			updateText(text);
		}
	}

	public boolean checkForOperators() {
		boolean conditionChecker = false;

		if (!allTexts.isEmpty()
				&& !allTexts.get(allTexts.size() - 1).equals(Operations.MULTIPLICATION.getSignOfOperation())
				&& !allTexts.get(allTexts.size() - 1).equals(Operations.DIVISION.getSignOfOperation())
				&& !allTexts.get(allTexts.size() - 1).equals(Operations.ADDITON.getSignOfOperation())
				&& !allTexts.get(allTexts.size() - 1).equals(Operations.SUBSTRACTION.getSignOfOperation()))
			conditionChecker = true;

		return conditionChecker;
	}

	public boolean checkPoint() {
		boolean pointChecker = true;

		if (!numbers.isEmpty()
				&& !numbers.get(numbers.size() - 1).equals(ResetPointEqual.POINT.getCharacterOfResetPointEqual())
				|| (!allTexts.isEmpty()
						&& !allTexts.get(allTexts.size() - 1).equals(Operations.MULTIPLICATION.getSignOfOperation())
						&& !allTexts.get(allTexts.size() - 1).equals(Operations.DIVISION.getSignOfOperation())
						&& !allTexts.get(allTexts.size() - 1).equals(Operations.ADDITON.getSignOfOperation())
						&& !allTexts.get(allTexts.size() - 1).equals(Operations.SUBSTRACTION.getSignOfOperation()))) {
			pointChecker = false;
		}
		return pointChecker;
	}

	public void insertElements(Text text, Character elementString) {
		if (!elementString.equals(Operations.MULTIPLICATION.getCharacterOfOperation())
				&& !elementString.equals(Operations.DIVISION.getCharacterOfOperation())
				&& !elementString.equals(Operations.ADDITON.getCharacterOfOperation())
				&& !elementString.equals(Operations.SUBSTRACTION.getCharacterOfOperation())
				&& !elementString.equals(ResetPointEqual.EQUAL.getCharacterOfResetPointEqual())) {
			numbers.add(elementString);
			text.append(elementString.toString());
		} else
			insertOperators(text, elementString);
	}
}