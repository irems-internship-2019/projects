package calc.pckg;
//STILL NEED TO BE DONE ########################################################################
//brackets counter aka. "(5+4))))" or "(((1+2)" not good - DONE
//after a calculation is done, reset Display IF, a "number" or "(" is pressed
//calculation is done IF you press "=" program dies - FIXED return ERROR...still need proper Verification
//remove decimals if number doesn't need it aka. 12.0000000000 not good
//if cases where 1.1. is inputed, display ERROR message - FIXED...still need proper Verification
//##############################################################################################

//no package... NOT GOOD

import java.util.Stack;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

class Calculator 
{

	static Stack<String> inputStack = new Stack<>();
	static Stack<String> multipleDigitStack = new Stack<>();
	static int numberOfBrackets = 0;

	public static void main(String[] args) {

		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Calculator");
		shell.setSize(300, 400);
		Font font = new Font(shell.getDisplay(), new FontData("Arial", 20, SWT.NONE));

		// Seteaza dimensiunea gridului, nr linii si coloane
		GridLayout gridLayout = new GridLayout(4, false);
		// gridLayout.verticalSpacing = 5;
		shell.setLayout(gridLayout);

		// GRID DATA##############################################################
		// GridData pentru results
		GridData span_colums = new GridData(SWT.FILL, SWT.FILL, true, true);
		span_colums.horizontalSpan = 4;

		// GridDAta pentru butoane
		GridData col_1 = new GridData(SWT.FILL, SWT.FILL, true, true);
		col_1.horizontalSpan = 1;

		// GridData pentru history
		GridData rowHistory = new GridData(SWT.FILL, SWT.FILL, true, true);
		rowHistory.horizontalSpan = 3;

		// first String in the stack
		inputStack.push("dorel");

		Text theText = new Text(shell, SWT.LEFT_TO_RIGHT);
		theText.setLayoutData(span_colums);
		theText.setFont(font);

		Button btn_openBrackets = new Button(shell, SWT.PUSH);
		btn_openBrackets.setText("(");
		btn_openBrackets.setLayoutData(col_1);
		btn_openBrackets.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				inputStack.push("(");
				if (!ConditionsAndVerification.Verify(inputStack.peek(), inputStack.get(inputStack.size() - 2))) {
					inputStack.pop();
				} else {
					theText.setText(theText.getText() + "(");
				}
			}
		});

		Button btn_closedBrackets = new Button(shell, SWT.PUSH);
		btn_closedBrackets.setText(")");
		btn_closedBrackets.setLayoutData(col_1);
		btn_closedBrackets.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				inputStack.push(")");
				if (!ConditionsAndVerification.Verify(inputStack.peek(), inputStack.get(inputStack.size() - 2))) {
					inputStack.pop();
				} else {
					theText.setText(theText.getText() + ")");
				}
			}
		});

		Button btnClear = new Button(shell, SWT.PUSH);
		btnClear.setText("C");
		btnClear.setLayoutData(col_1);
		btnClear.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				inputStack.clear();
				// the stack can't be empty
				inputStack.push("dorel");
				theText.setText("");
			}
		});

		Button btnBackspace = new Button(shell, SWT.PUSH);
		btnBackspace.setText("DEL");
		btnBackspace.setLayoutData(col_1);
		btnBackspace.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				inputStack.pop();
				if (inputStack.isEmpty())
					inputStack.push("dorel");
				if (!theText.getText().isEmpty())
					theText.setText(theText.getText().substring(0, theText.getText().length() - 1));
			}
		});

		Button btn_7 = new Button(shell, SWT.PUSH);
		btn_7.setText("7");
		btn_7.setLayoutData(col_1);
		btn_7.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				inputStack.push("7");
				if (!ConditionsAndVerification.Verify(inputStack.peek(), inputStack.get(inputStack.size() - 2))) {
					inputStack.pop();
				} else {
					theText.setText(theText.getText() + "7");
				}
			}
		});

		Button btn_8 = new Button(shell, SWT.PUSH);
		btn_8.setText("8");
		btn_8.setLayoutData(col_1);
		btn_8.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				inputStack.push("8");
				if (!ConditionsAndVerification.Verify(inputStack.peek(), inputStack.get(inputStack.size() - 2))) {
					inputStack.pop();
				} else {
					theText.setText(theText.getText() + "8");
				}
			}
		});

		Button btn_9 = new Button(shell, SWT.PUSH);
		btn_9.setText("9");
		btn_9.setLayoutData(col_1);
		btn_9.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				inputStack.push("9");
				if (!ConditionsAndVerification.Verify(inputStack.peek(), inputStack.get(inputStack.size() - 2))) {
					inputStack.pop();
				} else {
					theText.setText(theText.getText() + "9");
				}
			}
		});

		Button btn_divide = new Button(shell, SWT.PUSH);
		btn_divide.setText("/");
		btn_divide.setLayoutData(col_1);
		btn_divide.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				inputStack.push("/");
				if (!ConditionsAndVerification.Verify(inputStack.peek(), inputStack.get(inputStack.size() - 2))) {
					inputStack.pop();
				} else {
					theText.setText(theText.getText() + "/");
				}
			}
		});

		Button btn_4 = new Button(shell, SWT.PUSH);
		btn_4.setText("4");
		btn_4.setLayoutData(col_1);
		btn_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				inputStack.push("4");
				if (!ConditionsAndVerification.Verify(inputStack.peek(), inputStack.get(inputStack.size() - 2))) {
					inputStack.pop();
				} else {
					theText.setText(theText.getText() + "4");
				}
			}
		});

		Button btn_5 = new Button(shell, SWT.PUSH);
		btn_5.setText("5");
		btn_5.setLayoutData(col_1);
		btn_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				inputStack.push("5");
				if (!ConditionsAndVerification.Verify(inputStack.peek(), inputStack.get(inputStack.size() - 2))) {
					inputStack.pop();
				} else {
					theText.setText(theText.getText() + "5");
				}
			}
		});

		Button btn_6 = new Button(shell, SWT.PUSH);
		btn_6.setText("6");
		btn_6.setLayoutData(col_1);
		btn_6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				inputStack.push("6");
				if (!ConditionsAndVerification.Verify(inputStack.peek(), inputStack.get(inputStack.size() - 2))) {
					inputStack.pop();
				} else {
					theText.setText(theText.getText() + "6");
				}
			}
		});

		Button btn_multiply = new Button(shell, SWT.PUSH);
		btn_multiply.setText("*");
		btn_multiply.setLayoutData(col_1);
		btn_multiply.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				inputStack.push("*");
				if (!ConditionsAndVerification.Verify(inputStack.peek(), inputStack.get(inputStack.size() - 2))) {
					inputStack.pop();
				} else {
					theText.setText(theText.getText() + "*");
				}
			}
		});

		Button btn_1 = new Button(shell, SWT.PUSH);
		btn_1.setText("1");
		btn_1.setLayoutData(col_1);
		btn_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				inputStack.push("1");
				if (!ConditionsAndVerification.Verify(inputStack.peek(), inputStack.get(inputStack.size() - 2))) {
					inputStack.pop();
				} else {
					theText.setText(theText.getText() + "1");
				}
			}
		});

		Button btn_2 = new Button(shell, SWT.PUSH);
		btn_2.setText("2");
		btn_2.setLayoutData(col_1);
		btn_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				inputStack.push("2");
				if (!ConditionsAndVerification.Verify(inputStack.peek(), inputStack.get(inputStack.size() - 2))) {
					inputStack.pop();
				} else {
					theText.setText(theText.getText() + "2");
				}
			}
		});

		Button btn_3 = new Button(shell, SWT.PUSH);
		btn_3.setText("3");
		btn_3.setLayoutData(col_1);
		btn_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				inputStack.push("3");
				if (!ConditionsAndVerification.Verify(inputStack.peek(), inputStack.get(inputStack.size() - 2))) {
					inputStack.pop();
				} else {
					theText.setText(theText.getText() + "3");
				}
			}
		});

		Button btn_minus = new Button(shell, SWT.PUSH);
		btn_minus.setText("-");
		btn_minus.setLayoutData(col_1);
		btn_minus.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				inputStack.push("-");
				if (!ConditionsAndVerification.Verify(inputStack.peek(), inputStack.get(inputStack.size() - 2))) {
					inputStack.pop();
				} else {
					theText.setText(theText.getText() + "-");
				}
			}
		});

		Button btn_0 = new Button(shell, SWT.PUSH);
		btn_0.setText("0");
		btn_0.setLayoutData(col_1);
		btn_0.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				inputStack.push("0");
				if (!ConditionsAndVerification.Verify(inputStack.peek(), inputStack.get(inputStack.size() - 2))) {
					inputStack.pop();
				} else {
					theText.setText(theText.getText() + "0");
				}
			}
		});

		Button btn_dot = new Button(shell, SWT.PUSH);
		btn_dot.setText(".");
		btn_dot.setLayoutData(col_1);
		btn_dot.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				inputStack.push(".");
				if (!ConditionsAndVerification.Verify(inputStack.peek(), inputStack.get(inputStack.size() - 2))) {
					inputStack.pop();
				} else {
					theText.setText(theText.getText() + ".");
				}
			}
		});

		Button btn_equals = new Button(shell, SWT.PUSH);
		btn_equals.setText("=");
		btn_equals.setLayoutData(col_1);
		btn_equals.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				try {
					numberOfBrackets = ConditionsAndVerification.checkNumberOfBrackets(inputStack);
					if (numberOfBrackets != 0 && numberOfBrackets > 0) {
						for (int i = 0; i < numberOfBrackets; i++) {
							inputStack.push(")");
							theText.setText(theText.getText() + ")");
						}
					} else if (numberOfBrackets < 0) {
						numberOfBrackets *= (-1);
						for (int i = 1; i <= numberOfBrackets; i++) {
							inputStack.add(i, "(");
							theText.setText("(" + theText.getText());
						}
					}

					// firstly ComplexNumbers unites multiple digit numbrs eg [1,1,1] will werge as
					// [111]
					// secondly CheckifDecimal unites numbers like [1, ., 234] => [1.234]
					String[] infixDecimalDouble = AfterEqualsOperations.CheckifDecimal(
							AfterEqualsOperations.ComplexNumbers(inputStack, inputStack.size()), inputStack.size());

					// thirdly RemoveNull removes null's from the String and outputs a clean string
					String[] infixFinal = AfterEqualsOperations.RemoveNull(infixDecimalDouble, inputStack.size());

					// lastly InfixToPostfixConverter convers infix xpresion to postfix expresion,
					// and then
					// PostFixCalculator takes the postfixExpresion and calculates it
					int infixCount = infixFinal.length;
					theText.setText(theText.getText() + "=" + AfterEqualsOperations
							.PostFixCalculator(AfterEqualsOperations.InfixToPostfixConverter(infixFinal, infixCount)));
					inputStack.clear();
					// the stack can't be empty
					inputStack.push("dorel");

				} catch (Exception exception) {
					inputStack.clear();
					// the stack can't be empty
					inputStack.push("dorel");
					theText.setText("- ERROR -");
					// delete this line for final
					System.out.println(exception);
				}
			}
		});

		Button btn_plus = new Button(shell, SWT.PUSH);
		btn_plus.setText("+");
		btn_plus.setLayoutData(col_1);
		btn_plus.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				inputStack.push("+");
				if (!ConditionsAndVerification.Verify(inputStack.peek(), inputStack.get(inputStack.size() - 2))) {
					inputStack.pop();
				} else {
					theText.setText(theText.getText() + "+");
				}
			}
		});

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}
