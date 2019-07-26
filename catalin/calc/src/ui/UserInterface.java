package ui;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class UserInterface {

	protected static ArrayList<Character> numbers = new ArrayList<>();
	protected static ArrayList<String> allTexts = new ArrayList<>();

	private void createButtonsUI(Text text, Composite composite) {
		ResetPointEqualButtons resetPointEqual = new ResetPointEqualButtons();
		resetPointEqual.createClearAndBack(text, composite);

		NumbersAndOperatorsButtons numberAndOperator = new NumbersAndOperatorsButtons();
		numberAndOperator.createNumbersAndOperators(text, composite);

		resetPointEqual.createPointAndEqual(text, composite);
	}

	protected void updateText(Text text) {
		text.setText("");
		for (String text1 : allTexts) {
			text.append(text1);
		}
		for (Character text2 : numbers) {
			text.append(text2.toString());
		}
	}

	public void createUI() {
		Display display = new Display();

		Shell shell = new Shell(display);
		shell.setText("Calculator");
		shell.setSize(300, 400);
		shell.setLayout(new FillLayout());

		Composite composite = new Composite(shell, SWT.NONE);
		GridLayout layout = new GridLayout(4, true);

		Text text = new Text(composite, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1));

		composite.setLayout(layout);

		createButtonsUI(text, composite);

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
}