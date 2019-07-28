package calc.pckg;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

class CreateCalculatorUi {

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

		StackOperations.pushStack(NULL);
	}

	//	private void createTextWidget() 
	//	{
	//		calculatorDisplay = new Text(shell, SWT.LEFT_TO_RIGHT);
	//		calculatorDisplay.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1));
	//		
	//		Font font = new Font(shell.getDisplay(), new FontData("Arial", 16, SWT.NONE));
	//		calculatorDisplay.setFont(font);
	//	}

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
	}

	private SelectionListener makeButtonSelectionListner(String strNum) 
	{
		return new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				switch (strNum) 
				{
				case "C":
					clearStack();
					break;

				case "DEL":
					deleteOneElement();
					break;

				case "=":
					CalculatorOperations.calculateFinalValue();
					break;

				default:
					StackOperations.pushStack(strNum);

					if (!CalculatorRestrictions.inputRestrictions(StackOperations.stackPeek(), StackOperations.getStack(StackOperations.stackSize() - 2))) 
						StackOperations.popStack();
					else 
						TextWidget.setDisplayAfter(strNum);
					break;
				}

			}
		};
	}

	private void clearStack()
	{
		StackOperations.clearStack();
		// the stack can't be empty
		StackOperations.pushStack(NULL);
		TextWidget.clearDisplay();
	}

	private void deleteOneElement()
	{
		StackOperations.popStack();
		if (StackOperations.isEmpty())
			StackOperations.pushStack(NULL);

		TextWidget.removeOneElement();

	}
}
