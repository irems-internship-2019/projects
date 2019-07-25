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

	private static Stack<String> inputStack = new Stack<>();
	//static Stack<String> multipleDigitStack = new Stack<>();
	//static int numberOfBrackets = 0;

	public static void main(String[] args) 
	{
		Display display = new Display();

		Shell shell = new Shell(display);
		shell.setText("Calculator");
		shell.setSize(300, 400);

		// Seteaza dimensiunea gridului, nr linii si coloane
		GridLayout gridLayout = new GridLayout(4, false);
		// gridLayout.verticalSpacing = 5;
		shell.setLayout(gridLayout);

		Font font = new Font(shell.getDisplay(), new FontData("Arial", 20, SWT.NONE));

		// GridDAta pentru butoane
		GridData col_1 = new GridData(SWT.FILL, SWT.FILL, true, true);
		col_1.horizontalSpan = 1;

		// first String in the stack
		inputStack.push("null");

		Text calculatorDisplay = new Text(shell, SWT.LEFT_TO_RIGHT);
		GridData span_colums = new GridData(SWT.FILL, SWT.FILL, true, true);
		span_colums.horizontalSpan = 4;

		calculatorDisplay.setLayoutData(span_colums);
		calculatorDisplay.setFont(font);

		Button btnOpenBrackets = CreateUi.makeButton(shell, "(", col_1);
		btnOpenBrackets.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				inputStack.push("(");

				if (!CalculatorRestrictions.restrictions(inputStack.peek(), inputStack.get(inputStack.size() - 2))) 
					inputStack.pop();
				else 
					calculatorDisplay.setText(calculatorDisplay.getText() + "(");
			}
		});
		
		Button btnClosedBrackets = CreateUi.makeButton(shell, ")", col_1);
		btnClosedBrackets.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				inputStack.push(")");
				
				if (!CalculatorRestrictions.restrictions(inputStack.peek(), inputStack.get(inputStack.size() - 2))) 
					inputStack.pop();
				else 
					calculatorDisplay.setText(calculatorDisplay.getText() + ")");
			}
		});

		Button btnClear = CreateUi.makeButton(shell, "C", col_1);
		btnClear.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				inputStack.clear();
				// the stack can't be empty
				inputStack.push("null");
				calculatorDisplay.setText("");
			}
		});
		Button btnBackspace = CreateUi.makeButton(shell, "DEL", col_1);
		btnBackspace.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				inputStack.pop();
				if (inputStack.isEmpty())
					inputStack.push("null");
				
				if (!calculatorDisplay.getText().isEmpty())
					calculatorDisplay.setText(calculatorDisplay.getText().substring(0, calculatorDisplay.getText().length() - 1));
			}
		});
		
		Button btn_7 = CreateUi.makeButton(shell, "7", col_1);
		btn_7.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				inputStack.push("7");
				
				if (!CalculatorRestrictions.restrictions(inputStack.peek(), inputStack.get(inputStack.size() - 2))) 
					inputStack.pop();
				else 
					calculatorDisplay.setText(calculatorDisplay.getText() + "7");
			}
		});
		
		Button btn_8 = CreateUi.makeButton(shell, "8", col_1);
		btn_8.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				inputStack.push("8");
				
				if (!CalculatorRestrictions.restrictions(inputStack.peek(), inputStack.get(inputStack.size() - 2))) 
					inputStack.pop();
				else 
					calculatorDisplay.setText(calculatorDisplay.getText() + "8");
			}
		});
		
		Button btn_9 = CreateUi.makeButton(shell, "9", col_1);
		btn_9.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				inputStack.push("9");
				
				if (!CalculatorRestrictions.restrictions(inputStack.peek(), inputStack.get(inputStack.size() - 2))) 
					inputStack.pop();
				else 
					calculatorDisplay.setText(calculatorDisplay.getText() + "9");
			}
		});

		Button btnDivide = CreateUi.makeButton(shell, "/", col_1);
		btnDivide.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				inputStack.push("/");
				
				if (!CalculatorRestrictions.restrictions(inputStack.peek(), inputStack.get(inputStack.size() - 2))) 
					inputStack.pop();
				else 
					calculatorDisplay.setText(calculatorDisplay.getText() + "/");
			}
		});

		Button btn_4 = CreateUi.makeButton(shell, "4", col_1);
		btn_4.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				inputStack.push("4");
				
				if (!CalculatorRestrictions.restrictions(inputStack.peek(), inputStack.get(inputStack.size() - 2))) 
					inputStack.pop();
				else 
					calculatorDisplay.setText(calculatorDisplay.getText() + "4");
			}
		});

		Button btn_5 = CreateUi.makeButton(shell, "5", col_1);
		btn_5.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				inputStack.push("5");
				
				if (!CalculatorRestrictions.restrictions(inputStack.peek(), inputStack.get(inputStack.size() - 2))) 
					inputStack.pop();
				else 
					calculatorDisplay.setText(calculatorDisplay.getText() + "5");
			}
		});

		Button btn_6 = CreateUi.makeButton(shell, "6", col_1);
		btn_6.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				inputStack.push("6");
				
				if (!CalculatorRestrictions.restrictions(inputStack.peek(), inputStack.get(inputStack.size() - 2))) 
					inputStack.pop();
				else 
					calculatorDisplay.setText(calculatorDisplay.getText() + "6");

			}
		});

		Button btnMultiply = CreateUi.makeButton(shell, "*", col_1);
		btnMultiply.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				inputStack.push("*");
				
				if (!CalculatorRestrictions.restrictions(inputStack.peek(), inputStack.get(inputStack.size() - 2))) 
					inputStack.pop();
				else 
					calculatorDisplay.setText(calculatorDisplay.getText() + "*");
			}
		});

		Button btn_1 = CreateUi.makeButton(shell, "1", col_1);
		btn_1.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				inputStack.push("1");
				
				if (!CalculatorRestrictions.restrictions(inputStack.peek(), inputStack.get(inputStack.size() - 2)))
					inputStack.pop();
				else 
					calculatorDisplay.setText(calculatorDisplay.getText() + "1");
			}
		});

		Button btn_2 = CreateUi.makeButton(shell, "2", col_1);
		btn_2.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				inputStack.push("2");
				
				if (!CalculatorRestrictions.restrictions(inputStack.peek(), inputStack.get(inputStack.size() - 2))) 
					inputStack.pop();
				else 
					calculatorDisplay.setText(calculatorDisplay.getText() + "2");
			}
		});

		Button btn_3 = CreateUi.makeButton(shell, "3", col_1);
		btn_3.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				inputStack.push("3");
				
				if (!CalculatorRestrictions.restrictions(inputStack.peek(), inputStack.get(inputStack.size() - 2))) 
					inputStack.pop();
				else 
					calculatorDisplay.setText(calculatorDisplay.getText() + "3");
			}
		});
		
		Button btnMinus = CreateUi.makeButton(shell, "-", col_1);
		btnMinus.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				inputStack.push("-");
				
				if (!CalculatorRestrictions.restrictions(inputStack.peek(), inputStack.get(inputStack.size() - 2))) 
					inputStack.pop();
				else 
					calculatorDisplay.setText(calculatorDisplay.getText() + "-");
			}
		});

		Button btn_0 = CreateUi.makeButton(shell, "0", col_1);
		btn_0.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				inputStack.push("0");
				
				if (!CalculatorRestrictions.restrictions(inputStack.peek(), inputStack.get(inputStack.size() - 2))) 
					inputStack.pop();
				else 
					calculatorDisplay.setText(calculatorDisplay.getText() + "0");
			}
		});

		Button btnDot = CreateUi.makeButton(shell, ".", col_1);
		btnDot.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				inputStack.push(".");
				
				if (!CalculatorRestrictions.restrictions(inputStack.peek(), inputStack.get(inputStack.size() - 2))) 
					inputStack.pop();
				else 
					calculatorDisplay.setText(calculatorDisplay.getText() + ".");
			}
		});

		Button btnEquals = CreateUi.makeButton(shell, "=", col_1);
		btnEquals.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{

				try {
					int numberOfBrackets = 0;
					numberOfBrackets = CalculatorRestrictions.checkNumberOfBrackets(inputStack);
					if (numberOfBrackets != 0 && numberOfBrackets > 0) 
					{
						for (int i = 0; i < numberOfBrackets; i++) 
						{
							inputStack.push(")");
							calculatorDisplay.setText(calculatorDisplay.getText() + ")");
						}
					} else if (numberOfBrackets < 0) {
						numberOfBrackets *= (-1);
						for (int i = 1; i <= numberOfBrackets; i++) 
						{
							inputStack.add(i, "(");
							calculatorDisplay.setText("(" + calculatorDisplay.getText());
						}
					}

					String[] infixDecimalDouble = Operations.checkIfDecimal(
							Operations.complexNumbers(inputStack, inputStack.size()), inputStack.size());
					String[] infixFinal = Operations.removeNull(infixDecimalDouble, inputStack.size());
					int infixCount = infixFinal.length;
					calculatorDisplay.setText(calculatorDisplay.getText() + "=" + Operations
							.postFixCalculator(Operations.infixToPostfixConverter(infixFinal, infixCount)));

					inputStack.clear();
					inputStack.push("null");

				} catch (Exception exception) 
				{
					inputStack.clear();
					// the stack can't be empty
					inputStack.push("null");
					calculatorDisplay.setText("- ERROR -");
					// delete this line for final
					System.out.println(exception);
				}
			}
		});
		
		Button btnPlus = CreateUi.makeButton(shell, "+", col_1);
		btnPlus.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				inputStack.push("+");
				
				if (!CalculatorRestrictions.restrictions(inputStack.peek(), inputStack.get(inputStack.size() - 2))) 
					inputStack.pop();
				 else 
					calculatorDisplay.setText(calculatorDisplay.getText() + "+");
			}
		});

		shell.open();
		while (!shell.isDisposed()) 
		{
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}
