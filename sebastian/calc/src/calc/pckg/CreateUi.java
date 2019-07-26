package calc.pckg;


import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;

class CreateUi {

	static Button makeButton(Shell shell, String strNum, GridData grid)
	{
		Button btn = new Button(shell, SWT.PUSH);
		btn.setText(strNum);
		btn.setLayoutData(grid);

		return btn;
	}

	static SelectionListener getButtonSelectionListner(String strNum) 
	{
		return new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				Calculator.inputStack.push(strNum);

				if (!CalculatorRestrictions.inputRestrictions(Calculator.inputStack.peek(), Calculator.inputStack.get(Calculator.inputStack.size() - 2))) 
					Calculator.inputStack.pop();
				else 
					Calculator.calculatorDisplay.setText(Calculator.calculatorDisplay.getText() + strNum);
			}
		};
	}

}
