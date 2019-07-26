package calc.pckg;
//STILL NEED TO BE DONE ########################################################################
//after a calculation is done, reset Display IF, a "number" or "(" is pressed
//calculation is done IF you press "=" program dies - FIXED return ERROR...still need proper Verification
//if cases where 1.1. is inputed, display ERROR message - FIXED...still need proper Verification
//##############################################################################################

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
	static Text calculatorDisplay;

	public static void main(String[] args) 
	{
		Display display = new Display();

		Shell shell = new Shell(display);
		shell.setText("Calculator");
		shell.setSize(300, 400);

		GridLayout gridLayout = new GridLayout(4, false);
		shell.setLayout(gridLayout);

		Font font = new Font(shell.getDisplay(), new FontData("Arial", 20, SWT.NONE));

		GridData oneColumn = new GridData(SWT.FILL, SWT.FILL, true, true);
		oneColumn.horizontalSpan = 1;
		
		calculatorDisplay = new Text(shell, SWT.LEFT_TO_RIGHT);
		
		GridData span_colums = new GridData(SWT.FILL, SWT.FILL, true, true);
		span_colums.horizontalSpan = 4;

		calculatorDisplay.setLayoutData(span_colums);
		calculatorDisplay.setFont(font);

		//the stack can't be empty
		inputStack.push("null");

		Button btnOpenBrackets = CreateUi.makeButton(shell, "(", oneColumn);
		btnOpenBrackets.addSelectionListener(CreateUi.getButtonSelectionListner("("));

		Button btnClosedBrackets = CreateUi.makeButton(shell, ")", oneColumn);
		btnClosedBrackets.addSelectionListener(CreateUi.getButtonSelectionListner(")"));

		Button btnClear = CreateUi.makeButton(shell, "C", oneColumn);
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

		Button btnBackspace = CreateUi.makeButton(shell, "DEL", oneColumn);
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

		Button btn_7 = CreateUi.makeButton(shell, "7", oneColumn);
		btn_7.addSelectionListener(CreateUi.getButtonSelectionListner("7"));

		Button btn_8 = CreateUi.makeButton(shell, "8", oneColumn);
		btn_8.addSelectionListener(CreateUi.getButtonSelectionListner("8"));

		Button btn_9 = CreateUi.makeButton(shell, "9", oneColumn);
		btn_9.addSelectionListener(CreateUi.getButtonSelectionListner("9"));

		Button btnDivide = CreateUi.makeButton(shell, "/", oneColumn);
		btnDivide.addSelectionListener(CreateUi.getButtonSelectionListner("/"));

		Button btn_4 = CreateUi.makeButton(shell, "4", oneColumn);
		btn_4.addSelectionListener(CreateUi.getButtonSelectionListner("4"));

		Button btn_5 = CreateUi.makeButton(shell, "5", oneColumn);
		btn_5.addSelectionListener(CreateUi.getButtonSelectionListner("5"));

		Button btn_6 = CreateUi.makeButton(shell, "6", oneColumn);
		btn_6.addSelectionListener(CreateUi.getButtonSelectionListner("6"));

		Button btnMultiply = CreateUi.makeButton(shell, "*", oneColumn);
		btnMultiply.addSelectionListener(CreateUi.getButtonSelectionListner("*"));

		Button btn_1 = CreateUi.makeButton(shell, "1", oneColumn);
		btn_1.addSelectionListener(CreateUi.getButtonSelectionListner("1"));

		Button btn_2 = CreateUi.makeButton(shell, "2", oneColumn);
		btn_2.addSelectionListener(CreateUi.getButtonSelectionListner("2"));

		Button btn_3 = CreateUi.makeButton(shell, "3", oneColumn);
		btn_3.addSelectionListener(CreateUi.getButtonSelectionListner("3"));

		Button btnMinus = CreateUi.makeButton(shell, "-", oneColumn);
		btnMinus.addSelectionListener(CreateUi.getButtonSelectionListner("-"));

		Button btn_0 = CreateUi.makeButton(shell, "0", oneColumn);
		btn_0.addSelectionListener(CreateUi.getButtonSelectionListner("0"));

		Button btnDot = CreateUi.makeButton(shell, ".", oneColumn);
		btnDot.addSelectionListener(CreateUi.getButtonSelectionListner("."));

		Button btnEquals = CreateUi.makeButton(shell, "=", oneColumn);
		btnEquals.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
            CalculatorOperations.calculateFinalValue();
			}
		});

		Button btnPlus = CreateUi.makeButton(shell, "+", oneColumn);
		btnPlus.addSelectionListener(CreateUi.getButtonSelectionListner("+"));

		shell.open();
		while (!shell.isDisposed()) 
		{
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}
