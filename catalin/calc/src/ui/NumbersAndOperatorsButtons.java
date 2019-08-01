package ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import operations.OperationsClass.Operations;
import restrictions.RestrictionConditions;

class NumbersAndOperatorsButtons {
	private enum Numbers {
		ZERO("0"),
		ONE("1"),
		TWO("2"),
		THREE("3"),
		FOUR("4"),
		FIVE("5"),
		SIX("6"),
		SEVEN("7"),
		EIGHT("8"),
		NINE("9");

		private final String signOfNumbers;

		private Numbers(String signOfNumbers) {
			this.signOfNumbers = signOfNumbers;
		}

		public String getSignOfNumbers() {
			return signOfNumbers;
		}

		public Character getCharacterOfNumbers() {
			return signOfNumbers.charAt(0);
		}
	}

	private Button createButton(Composite composite, Text text, String string, Character character) {
		final Button button = new Button(composite, SWT.PUSH);
		RestrictionConditions conditions = new RestrictionConditions();

		button.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		button.setText(string);

		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				conditions.insertElements(text, character);
			}
		});

		return button;
	}

	void createNumbersAndOperators(Text text, Composite composite) {
		// button divide
		final Button buttonDivide = createButton(composite, text, Operations.DIVISION.getSignOfOperation(),
				Operations.DIVISION.getCharacterOfOperation());
		buttonDivide.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));

		// button 7
		createButton(composite, text, Numbers.SEVEN.getSignOfNumbers(), Numbers.SEVEN.getCharacterOfNumbers());

		// button 8
		createButton(composite, text, Numbers.EIGHT.getSignOfNumbers(), Numbers.EIGHT.getCharacterOfNumbers());

		// button 9
		createButton(composite, text, Numbers.NINE.getSignOfNumbers(), Numbers.NINE.getCharacterOfNumbers());

		// button multiplication
		createButton(composite, text, Operations.MULTIPLICATION.getSignOfOperation(),
				Operations.MULTIPLICATION.getCharacterOfOperation());

		// button 4
		createButton(composite, text, Numbers.FOUR.getSignOfNumbers(), Numbers.FOUR.getCharacterOfNumbers());

		// button 5
		createButton(composite, text, Numbers.FIVE.getSignOfNumbers(), Numbers.FIVE.getCharacterOfNumbers());

		// button 6
		createButton(composite, text, Numbers.SIX.getSignOfNumbers(), Numbers.SIX.getCharacterOfNumbers());

		// button subtraction
		createButton(composite, text, Operations.SUBSTRACTION.getSignOfOperation(),
				Operations.SUBSTRACTION.getCharacterOfOperation());

		// button 1
		createButton(composite, text, Numbers.ONE.getSignOfNumbers(), Numbers.ONE.getCharacterOfNumbers());

		// button 2
		createButton(composite, text, Numbers.TWO.getSignOfNumbers(), Numbers.TWO.getCharacterOfNumbers());

		// button 3
		createButton(composite, text, Numbers.THREE.getSignOfNumbers(), Numbers.THREE.getCharacterOfNumbers());

		// button addition
		createButton(composite, text, Operations.ADDITON.getSignOfOperation(),
				Operations.ADDITON.getCharacterOfOperation());

		// button 0
		createButton(composite, text, Numbers.ZERO.getSignOfNumbers(), Numbers.ZERO.getCharacterOfNumbers());
	}
}