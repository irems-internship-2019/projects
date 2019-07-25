package calc.pckg;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Label;

public class Calculator {

	private static final String EMPTY_STRING = "";

	private Shell shell;
	private Text calculatorInput;
	private float lastOperationResult = 0;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		try {
			Calculator window = new Calculator();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(355, 316);
		shell.setText("SWT Calculator");
		Color operationsColor = new Color(shell.getDisplay(), new RGB(200, 20, 200));

		shell.setLayout(new GridLayout(4, false));

		calculatorInput = new Text(shell, SWT.BORDER);
		calculatorInput.setEditable(false);
		calculatorInput.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1));

		Button clearButton = new Button(shell, SWT.NONE);
		clearButton.setText("C");
		clearButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		Color clearColor = new Color(shell.getDisplay(), new RGB(0, 0, 255));
		clearButton.setBackground(clearColor);

		Button deleteButton = new Button(shell, SWT.NONE);
		deleteButton.setText("DEL");
		deleteButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		Color deleteColor = new Color(shell.getDisplay(), new RGB(0, 0, 255));
		deleteButton.setBackground(deleteColor);

		Button divisionButton = new Button(shell, SWT.NONE);
		divisionButton.setText("/");
		divisionButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		divisionButton.setBackground(operationsColor);

		Button multiplyButton = new Button(shell, SWT.NONE);
		multiplyButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		multiplyButton.setText("X");
		multiplyButton.setBackground(operationsColor);

		Button buttonSeven = new Button(shell, SWT.NONE);
		buttonSeven.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		buttonSeven.setText("7");

		Button buttonEight = new Button(shell, SWT.NONE);
		buttonEight.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		buttonEight.setText("8");

		Button buttonNine = new Button(shell, SWT.NONE);
		buttonNine.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		buttonNine.setText("9");

		Button minusButton = new Button(shell, SWT.NONE);
		minusButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		minusButton.setText("-");
		minusButton.setBackground(operationsColor);

		Button buttonFour = new Button(shell, SWT.NONE);
		buttonFour.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		buttonFour.setText("4");

		Button buttonFive = new Button(shell, SWT.NONE);
		buttonFive.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		buttonFive.setText("5");

		Button buttonSix = new Button(shell, SWT.NONE);
		buttonSix.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		buttonSix.setText("6");

		Button adittionButton = new Button(shell, SWT.NONE);
		adittionButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		adittionButton.setText("+");
		adittionButton.setBackground(operationsColor);

		Button buttonOne = new Button(shell, SWT.NONE);
		buttonOne.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		buttonOne.setText("1");

		Button buttonTwo = new Button(shell, SWT.NONE);
		buttonTwo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		buttonTwo.setText("2");

		Button buttonThree = new Button(shell, SWT.NONE);
		buttonThree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		buttonThree.setText("3");

		Button equalButton = new Button(shell, SWT.NONE);
		equalButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		equalButton.setText("=");
		Color equalColor = new Color(shell.getDisplay(), new RGB(0, 255, 0));
		equalButton.setBackground(equalColor);

		Button buttonZero = new Button(shell, SWT.NONE);
		buttonZero.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		buttonZero.setText("0");

		Button commaButton = new Button(shell, SWT.NONE);
		commaButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		commaButton.setText(".");

		Label label = new Label(shell, SWT.NONE);
		label.setLayoutData((new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1)));
		label.setText(EMPTY_STRING);

		deleteButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				if (calculatorInput.getText().length() != 0)
					calculatorInput
							.setText(calculatorInput.getText().substring(0, calculatorInput.getText().length() - 1));
			}
		});

		clearButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lastOperationResult = 0;

				calculatorInput.setText(EMPTY_STRING);
			}
		});

		divisionButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				float secondOperand = 0;

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
			}
		});

		multiplyButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				float secondOperand = 0;

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
			}
		});

		minusButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				float secondOperand = 0;

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
			}
		});

		adittionButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				float secondOperand = 0;

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
			}
		});

		buttonSeven.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				calculatorInput.setText(calculatorInput.getText() + 7);
			}
		});

		buttonEight.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				calculatorInput.setText(calculatorInput.getText() + 8);
			}
		});

		buttonNine.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				calculatorInput.setText(calculatorInput.getText() + 9);
			}
		});

		buttonFour.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				calculatorInput.setText(calculatorInput.getText() + 4);
			}
		});

		buttonFive.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				calculatorInput.setText(calculatorInput.getText() + 5);
			}
		});

		buttonSix.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				calculatorInput.setText(calculatorInput.getText() + 6);
			}
		});

		buttonOne.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				calculatorInput.setText(calculatorInput.getText() + 1);
			}
		});

		buttonTwo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				calculatorInput.setText(calculatorInput.getText() + 2);
			}
		});

		buttonThree.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				calculatorInput.setText(calculatorInput.getText() + 3);
			}
		});

		buttonZero.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				calculatorInput.setText(calculatorInput.getText() + 0);
			}
		});

		commaButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				   long multipleCommaChecker = calculatorInput.getText().chars().filter(ch -> ch == '.').count();
                if(multipleCommaChecker <1 )
				calculatorInput.setText(calculatorInput.getText() + ".");
			}
		});

		equalButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				float secondOperand = 0;

				if (Operations.IsNumber(calculatorInput.getText()) == true)

					secondOperand = Float.parseFloat(calculatorInput.getText());

				calculatorInput.setText(
						String.valueOf(Operations.allOperations(label.getText(), lastOperationResult, secondOperand)));

				lastOperationResult = 0;
				secondOperand = 0;
			}
		});
	}
}