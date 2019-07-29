package calc.pckg;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
//import org.eclipse.swt.graphics.Color;
//import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;



class CreateCalculatorUi extends StackOperations {

	CalculatorRestrictions calculatorRestrictions = new CalculatorRestrictions();

	private final static String NULL= "null";

	private Shell shell;

	void createNewWindow() 
	{
		Display display = new Display();

		shell = new Shell(display);
		shell.setText("Calculator");
		shell.setSize(300, 400);
		shell.setLayout(new GridLayout(4, false));


		createContent();

		shell.open();
		while (!shell.isDisposed()) 
		{
			if (!display.readAndDispatch())
				display.sleep();
		}

		display.dispose();
	}

	private void createContent() 
	{
		TextWidget newDisplay = new TextWidget(); 
		newDisplay.createTextWidget(shell);
		createButtons();

		pushStack(NULL);
	}

	private void createButtons() 
	{
		buttonData("(");
		buttonData(")");
		buttonData("C");
		buttonData("DEL");

		buttonData("7");
		buttonData("8");
		buttonData("9");
		buttonData("/");

		buttonData("4");
		buttonData("5");
		buttonData("6");
		buttonData("*");

		buttonData("1");
		buttonData("2");
		buttonData("3");
		buttonData("-");

		buttonData("0");
		buttonData(".");
		buttonData("=");
		buttonData("+");
	}

	private void buttonData(String strNum)
	{
		Button btn = new Button(shell, SWT.PUSH);
		btn.setText(strNum);
		btn.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		btn.addSelectionListener(makeButtonSelectionListner(strNum));
		//		Color color = new Color(shell.getDisplay(), 230, 232, 235);
		//      btn.setBackground(color);
	}

	private SelectionListener makeButtonSelectionListner(String strNum) 
	{
		return new SelectionAdapter() 
		{
			CalculatorOperations calcOperations = new CalculatorOperations();
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				switch (strNum) 
				{
				case "C":
					clearTheStack();
					break;

				case "DEL":
					deleteOneElement();
					break;

				case "=":
					calcOperations.calculateFinalValue();
					break;

				default:
					pushStack(strNum);

					if (!calculatorRestrictions.inputRestrictions(stackPeek(),getStack(stackSize() - 2))) 
						popStack();
					else 
						TextWidget.setDisplayAfter(strNum);
					break;
				}

			}
		};
	}

	private void clearTheStack()
	{
		clearStack();
		// the stack can't be empty
		pushStack(NULL);
		TextWidget.clearDisplay();
	}

	private void deleteOneElement()
	{
		popStack();
		if (isEmpty())
			pushStack(NULL);

		TextWidget.removeOneElement();

	}
}
