package operations;

import org.eclipse.swt.widgets.Text;

public class OperationsOrder extends OperationsClass {
	private void multipleConditions(Text text) {
		if (allTexts.contains(Operations.MULTIPLICATION.getSignOfOperation()))
			multiple(text);
	}

	private void divideConditions(Text text) {
		if (allTexts.contains(Operations.DIVISION.getSignOfOperation()))
			divide(text);
	}

	private void plusConditions(Text text) {
		if (allTexts.contains(Operations.ADDITON.getSignOfOperation()))
			plus(text);
	}

	private void minusConditions(Text text) {
		if (allTexts.contains(Operations.SUBSTRACTION.getSignOfOperation()))
			minus(text);
	}

	public void decideOperations(Text text) {
		while (allTexts.contains(Operations.MULTIPLICATION.getSignOfOperation())
				|| allTexts.contains(Operations.DIVISION.getSignOfOperation())) {
			multipleConditions(text);
			divideConditions(text);
		}
		while (allTexts.contains(Operations.ADDITON.getSignOfOperation())
				|| allTexts.contains(Operations.SUBSTRACTION.getSignOfOperation())) {
			plusConditions(text);
			minusConditions(text);
		}
		updateText(text);
	}
}