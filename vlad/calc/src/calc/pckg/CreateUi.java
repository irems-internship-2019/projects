package calc.pckg;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class CreateUi {
	private static final String EMPTY_STRING = "";
	private static final String COMMA = ".";

	private Label label;
	private float lastOperationResult = 0;
	private Text calculatorInput;
	private Shell shell;

	private Button createButton(String buttonCharacter) {
		Button button = new Button(shell, SWT.PUSH);
		button.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				switch (buttonCharacter) {

				case ".":
					if (buttonCharacter == COMMA) {
						final char COMMA_CHAR = '.';

						long multipleCommaChecker = calculatorInput.getText().chars().filter(ch -> ch == COMMA_CHAR)
								.count();
						if (multipleCommaChecker < 1)
							calculatorInput.setText(calculatorInput.getText() + COMMA);
					}
					break;

				case "DEL":
					if (calculatorInput.getText().length() != 0)
						calculatorInput.setText(
								calculatorInput.getText().substring(0, calculatorInput.getText().length() - 1));

					break;
				case "C":
					if (calculatorInput.getText().length() != 0) {
						lastOperationResult = 0;
						Color clearColor = new Color(shell.getDisplay(), new RGB(0, 0, 255));
						button.setBackground(clearColor);
						calculatorInput.setText(EMPTY_STRING);

					}
					break;

				default:
					Color color = new Color(shell.getDisplay(), new RGB(0, 255, 0));
					if (button.getBackground() != color)
						button.setBackground(new Color(shell.getDisplay(), new RGB(0, 255, 0)));
					else if (button.getBackground() == color)
						button.setBackground(new Color(shell.getDisplay(), new RGB(255, 255, 255)));

					calculatorInput.setText(calculatorInput.getText() + buttonCharacter);
					break;
				}
			}
		});
		button.setText(buttonCharacter);

		return button;
	}

	private Button createOperationsButton(String buttonCharacter) {
		Button button = new Button(shell, SWT.PUSH);
		button.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		button.setText(buttonCharacter);
		button.setBackground(new Color(shell.getDisplay(), new RGB(200, 20, 200)));
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				float secondOperand = 0;
				switch (buttonCharacter) {
				case "*":
					secondOperand = 0;

					if ((lastOperationResult != 0) && Operations.IsNumber(calculatorInput.getText()) == true) {
						secondOperand = Float.parseFloat(calculatorInput.getText());

						Float result = lastOperationResult * secondOperand;
						calculatorInput.setText(String.valueOf(result));
						lastOperationResult = 0;
					} else if (Operations.IsNumber(calculatorInput.getText()) == true) {
						lastOperationResult = Float.parseFloat(calculatorInput.getText());
						calculatorInput.setText(EMPTY_STRING);
					} else if (Operations.IsNumber(calculatorInput.getText()) == false)
						calculatorInput.setText(EMPTY_STRING);

					label.setText(OperationsTypes.MULTIPLICATION.getName());
					break;
				case "/":
					secondOperand = 0;

					if ((lastOperationResult != 0) && Operations.IsNumber(calculatorInput.getText()) == true) {
						secondOperand = Float.parseFloat(calculatorInput.getText());

						Float result = lastOperationResult / secondOperand;
						calculatorInput.setText(String.valueOf(result));
						lastOperationResult = 0;
					} else if (Operations.IsNumber(calculatorInput.getText()) == true) {
						lastOperationResult = Float.parseFloat(calculatorInput.getText());
						calculatorInput.setText(EMPTY_STRING);
					} else if (Operations.IsNumber(calculatorInput.getText()) == false)
						calculatorInput.setText(EMPTY_STRING);

					label.setText(OperationsTypes.DIVISION.getName());
					break;
				case "+":
					secondOperand = 0;

					if ((lastOperationResult != 0) && Operations.IsNumber(calculatorInput.getText()) == true) {
						secondOperand = Float.parseFloat(calculatorInput.getText());

						Float result = lastOperationResult + secondOperand;
						calculatorInput.setText(String.valueOf(result));
						lastOperationResult = 0;
					} else if (Operations.IsNumber(calculatorInput.getText()) == true) {
						lastOperationResult = Float.parseFloat(calculatorInput.getText());
						calculatorInput.setText(EMPTY_STRING);
					} else if (Operations.IsNumber(calculatorInput.getText()) == false)
						calculatorInput.setText(EMPTY_STRING);

					label.setText(OperationsTypes.ADDITION.getName());
					break;
				case "-":
					secondOperand = 0;

					if ((lastOperationResult != 0) && Operations.IsNumber(calculatorInput.getText()) == true) {
						secondOperand = Float.parseFloat(calculatorInput.getText());

						Float result = lastOperationResult - secondOperand;
						calculatorInput.setText(String.valueOf(result));
						lastOperationResult = 0;
					} else if (Operations.IsNumber(calculatorInput.getText()) == true) {
						lastOperationResult = Float.parseFloat(calculatorInput.getText());
						calculatorInput.setText(EMPTY_STRING);
					} else if (Operations.IsNumber(calculatorInput.getText()) == false)
						calculatorInput.setText(EMPTY_STRING);

					label.setText(OperationsTypes.SUBSTRACTION.getName());
					break;
				case "=":
					secondOperand = 0;

					if (Operations.IsNumber(calculatorInput.getText()) == true)

						secondOperand = Float.parseFloat(calculatorInput.getText());

					calculatorInput.setText(String
							.valueOf(Operations.allOperations(label.getText(), lastOperationResult, secondOperand)));

					lastOperationResult = 0;
					secondOperand = 0;

					Color deleteColor = new Color(shell.getDisplay(), new RGB(0, 0, 255));
					button.setBackground(deleteColor);
					break;

				}

			}
		});

		return button;
	}

	private void createLabel() {
		label = new Label(shell, SWT.NONE);
		label.setLayoutData((new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1)));
		label.setText(EMPTY_STRING);

	}

	private void createText() {
		calculatorInput = new Text(shell, SWT.BORDER);
		calculatorInput.setEditable(false);
		calculatorInput.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1));

	}

	protected void open() {
		Display display = Display.getDefault();
		shell = new Shell();
		shell.setSize(355, 316);
		shell.setText("SWT Calculator");
		shell.setLayout(new GridLayout(4, false));

		initiateUi();

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	private void initiateUi() {

		createText();

		createButton("C");

		createButton("DEL");

		createOperationsButton("/");

		createOperationsButton("*");

		createButton("7");

		createButton("8");

		createButton("9");

		createOperationsButton("-");

		createButton("4");

		createButton("5");

		createButton("6");

		createOperationsButton("+");

		createButton("1");

		createButton("2");

		createButton("3");

		createOperationsButton("=");

		Button button0 = createButton("0");
		button0.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));

		createButton(".");

		createLabel();

	}

}
