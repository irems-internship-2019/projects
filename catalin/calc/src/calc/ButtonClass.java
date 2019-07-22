package calc;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class ButtonClass {

	int maxTextBox = -1;

	static public ArrayList<String> textBox = new ArrayList<>();
	public ArrayList<Character> numbers = new ArrayList<>();

	public static void updateText(Text text) {
		text.setText("");
		for (String text1 : textBox)
			text.append(text1);
	}

	public void buttons(Shell shell) {

		Composite composite = new Composite(shell, SWT.NONE);

		GridLayout layout = new GridLayout(4, true);
		composite.setLayout(layout);

		GridData dataFirst = new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1);
		GridData dataSecond = new GridData(SWT.FILL, SWT.FILL, true, true);
		GridData dataThird = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);

		Text text = new Text(composite, SWT.BORDER | SWT.RIGHT_TO_LEFT);
		text.setLayoutData(dataFirst);

		Button buttonClear = new Button(composite, SWT.PUSH);
		buttonClear.setLayoutData(dataSecond);
		buttonClear.setText("Clear");

		buttonClear.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				textBox.clear();
				maxTextBox = -1;
				updateText(text);

			}
		});

		Button buttonBack = new Button(composite, SWT.PUSH);
		buttonBack.setLayoutData(dataSecond);
		buttonBack.setText("Back");

		buttonBack.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				textBox.remove(maxTextBox);
				maxTextBox--;
				updateText(text);

			}
		});

		Button buttonDivide = new Button(composite, SWT.PUSH);
		buttonDivide.setLayoutData(dataThird);
		buttonDivide.setText("÷");

		buttonDivide.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text.append(buttonDivide.getText());
				numbersCondition(text, '÷');

			}
		});

		Button button7 = new Button(composite, SWT.PUSH);
		button7.setLayoutData(dataSecond);
		button7.setText("7");

		button7.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text.append(button7.getText());
				numbersCondition(text, '7');
			}
		});

		Button button8 = new Button(composite, SWT.PUSH);
		button8.setLayoutData(dataSecond);
		button8.setText("8");

		button8.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text.append(button8.getText());
				numbersCondition(text, '8');
			}
		});

		Button button9 = new Button(composite, SWT.PUSH);
		button9.setLayoutData(dataSecond);
		button9.setText("9");

		button9.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text.append(button9.getText());
				numbersCondition(text, '9');
			}
		});

		Button buttonMultiple = new Button(composite, SWT.PUSH);
		buttonMultiple.setLayoutData(dataSecond);
		buttonMultiple.setText(" x ");

		buttonMultiple.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text.append(buttonMultiple.getText());
				numbersCondition(text, 'x');
			}
		});

		Button button4 = new Button(composite, SWT.PUSH);
		button4.setLayoutData(dataSecond);
		button4.setText("4");

		button4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text.append(button4.getText());
				numbersCondition(text, '4');
			}
		});

		Button button5 = new Button(composite, SWT.PUSH);
		button5.setLayoutData(dataSecond);
		button5.setText("5");

		button5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text.append(button5.getText());
				numbersCondition(text, '5');
			}
		});

		Button button6 = new Button(composite, SWT.PUSH);
		button6.setLayoutData(dataSecond);
		button6.setText("6");

		button6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text.append(button6.getText());
				numbersCondition(text, '6');
			}
		});

		Button buttonMinus = new Button(composite, SWT.PUSH);
		buttonMinus.setLayoutData(dataSecond);
		buttonMinus.setText(" - ");

		buttonMinus.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text.append(buttonMinus.getText());
				numbersCondition(text, '-');
			}
		});

		Button button1 = new Button(composite, SWT.PUSH);
		button1.setLayoutData(dataSecond);
		button1.setText("1");

		button1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text.append(button1.getText());
				numbersCondition(text, '1');
			}
		});

		Button button2 = new Button(composite, SWT.PUSH);
		button2.setLayoutData(dataSecond);
		button2.setText("2");

		button2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text.append(button2.getText());
				numbersCondition(text, '2');
			}
		});

		Button button3 = new Button(composite, SWT.PUSH);
		button3.setLayoutData(dataSecond);
		button3.setText("3");

		button3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text.append(button3.getText());
				numbersCondition(text, '3');
			}
		});

		Button buttonPlus = new Button(composite, SWT.PUSH);
		buttonPlus.setLayoutData(dataSecond);
		buttonPlus.setText(" + ");

		buttonPlus.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text.append(buttonPlus.getText());
				numbersCondition(text, '+');
			}
		});

		Button buttonPunct = new Button(composite, SWT.PUSH);
		buttonPunct.setLayoutData(dataSecond);
		buttonPunct.setText(".");

		buttonPunct.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text.append(buttonPunct.getText());
				numbersCondition(text, '.');
			}
		});

		Button button0 = new Button(composite, SWT.PUSH);
		button0.setLayoutData(dataSecond);
		button0.setText("0");

		button0.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text.append(button0.getText());
				numbersCondition(text, '0');
			}
		});

		Button buttonEqual = new Button(composite, SWT.PUSH);
		buttonEqual.setLayoutData(dataThird);
		buttonEqual.setText(" = ");

		buttonEqual.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				numbersCondition(text, '=');
				decide(text);

			}
		});

	}

	public void numbersCondition(Text text, Character elementString) {

		StringBuilder string = new StringBuilder();

		if (!elementString.equals('x') && !elementString.equals('÷') && !elementString.equals('+')
				&& !elementString.equals('-') && !elementString.equals('='))
			numbers.add(elementString);
		else {
			for (Character newCharacters : numbers) {
				string.append(newCharacters);
			}
			String result = string.toString();
			textBox.add(result);
			maxTextBox++;
			textBox.add(elementString.toString());
			maxTextBox++;
			numbers.clear();
			string = null;
			updateText(text);
		}
	}

	public void restrictionAdd(Text text) {

		if (textBox.get(maxTextBox - 1).equals("x") || textBox.get(maxTextBox - 1).equals("÷")
				|| textBox.get(maxTextBox - 1).equals("+")
				|| textBox.get(maxTextBox - 1).equals("-") && textBox.get(maxTextBox - 1).equals(".")) {
			textBox.remove(maxTextBox);
			updateText(text);
			maxTextBox--;
		}

	}

	public void decide(Text text) {

		while (textBox.contains("x") || textBox.contains("÷") || textBox.contains("+") || textBox.contains("-")) {

			if (textBox.contains("x"))
				Operation.multiple(text);
			{
				if (textBox.contains("÷"))
					Operation.divide(text);
				{
					if (textBox.contains("+"))
						Operation.plus(text);
					{
						if (textBox.contains("-"))
							Operation.minus(text);
					}
				}
			}
		}

	}

}
