package calc.pckg;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

class TextWidget {

	private static Text calculatorDisplay;

	void createTextWidget(Shell shell) 
	{
		calculatorDisplay = new Text(shell, SWT.LEFT_TO_RIGHT);
		calculatorDisplay.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1));

		Font font = new Font(shell.getDisplay(), new FontData("Arial", 16, SWT.NONE));
		calculatorDisplay.setFont(font);
	}

	static void setDisplayAfter(String strNum) 
	{
		calculatorDisplay.setText(calculatorDisplay.getText() + strNum);
	}

	static void setDisplayBefore(String strNum) 
	{
		calculatorDisplay.setText(strNum + calculatorDisplay.getText());
	}

	static void clearDisplay () 
	{
		calculatorDisplay.setText("");
	}

	static void removeOneElement () 
	{
		if (!calculatorDisplay.getText().isEmpty())
			calculatorDisplay.setText(calculatorDisplay.getText().substring(0, calculatorDisplay.getText().length() - 1));
	}

	static void setDisplayToError () 
	{
		calculatorDisplay.setText("- ERROR -");
	}

	static void setDisplayFinalValue(Double finalValue) 
	{
		calculatorDisplay.setText(calculatorDisplay.getText() + "=" + finalValue);
	}


}
