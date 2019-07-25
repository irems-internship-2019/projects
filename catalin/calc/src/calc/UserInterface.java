package calc;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class UserInterface {
	static boolean equalChecker;

	static ArrayList<Character> numbers = new ArrayList<>();
	static ArrayList<String> textBox = new ArrayList<>();

	private void createButtonsUI(Composite composite) {
		RestrictionConditions conditions = new RestrictionConditions();

		GridData dataFirst = new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1);
		GridData dataSecond = new GridData(SWT.FILL, SWT.FILL, true, true);
		GridData dataThird = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);

		Text text = new Text(composite, SWT.BORDER);
		text.setLayoutData(dataFirst);

		final Button buttonClear = new Button(composite, SWT.PUSH);
		buttonClear.setLayoutData(dataSecond);
		buttonClear.setText("Clear");

		buttonClear.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				textBox.clear();
				numbers.clear();
				updateText(text);
			}
		});
		final Button buttonBack = new Button(composite, SWT.PUSH);
		buttonBack.setLayoutData(dataSecond);
		buttonBack.setText("Back");

		buttonBack.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!numbers.isEmpty()) {
					numbers.remove(numbers.size() - 1);
					updateText(text);
				} else if (!textBox.isEmpty()) {
					textBox.remove(textBox.size() - 1);
					updateText(text);
				}
			}
		});
		final Button buttonDivide = new Button(composite, SWT.PUSH);
		buttonDivide.setLayoutData(dataThird);
		buttonDivide.setText("/");

		buttonDivide.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				conditions.checkConditions(text, '/');
			}
		});
		final Button button7 = new Button(composite, SWT.PUSH);
		button7.setLayoutData(dataSecond);
		button7.setText("7");

		button7.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				conditions.checkConditions(text, '7');
			}
		});
		final Button button8 = new Button(composite, SWT.PUSH);
		button8.setLayoutData(dataSecond);
		button8.setText("8");

		button8.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				conditions.checkConditions(text, '8');
			}
		});
		final Button button9 = new Button(composite, SWT.PUSH);
		button9.setLayoutData(dataSecond);
		button9.setText("9");

		button9.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				conditions.checkConditions(text, '9');
			}
		});
		final Button buttonMultiple = new Button(composite, SWT.PUSH);
		buttonMultiple.setLayoutData(dataSecond);
		buttonMultiple.setText("x");

		buttonMultiple.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				conditions.checkConditions(text, 'x');
			}
		});
		final Button button4 = new Button(composite, SWT.PUSH);
		button4.setLayoutData(dataSecond);
		button4.setText("4");

		button4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				conditions.checkConditions(text, '4');
			}
		});
		final Button button5 = new Button(composite, SWT.PUSH);
		button5.setLayoutData(dataSecond);
		button5.setText("5");

		button5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				conditions.checkConditions(text, '5');
			}
		});
		final Button button6 = new Button(composite, SWT.PUSH);
		button6.setLayoutData(dataSecond);
		button6.setText("6");

		button6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				conditions.checkConditions(text, '6');
			}
		});
		final Button buttonMinus = new Button(composite, SWT.PUSH);
		buttonMinus.setLayoutData(dataSecond);
		buttonMinus.setText("-");

		buttonMinus.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				conditions.checkConditions(text, '-');
			}
		});
		final Button button1 = new Button(composite, SWT.PUSH);
		button1.setLayoutData(dataSecond);
		button1.setText("1");

		button1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				conditions.checkConditions(text, '1');
			}
		});
		final Button button2 = new Button(composite, SWT.PUSH);
		button2.setLayoutData(dataSecond);
		button2.setText("2");

		button2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				conditions.checkConditions(text, '2');
			}
		});
		final Button button3 = new Button(composite, SWT.PUSH);
		button3.setLayoutData(dataSecond);
		button3.setText("3");

		button3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				conditions.checkConditions(text, '3');
			}
		});
		final Button buttonPlus = new Button(composite, SWT.PUSH);
		buttonPlus.setLayoutData(dataSecond);
		buttonPlus.setText("+");

		buttonPlus.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				conditions.checkConditions(text, '+');
			}
		});
		final Button buttonPunct = new Button(composite, SWT.PUSH);
		buttonPunct.setLayoutData(dataSecond);
		buttonPunct.setText(".");

		buttonPunct.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (conditions.checkPoint() == false)
					conditions.checkConditions(text, '.');
			}
		});
		final Button button0 = new Button(composite, SWT.PUSH);
		button0.setLayoutData(dataSecond);
		button0.setText("0");

		button0.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				conditions.checkConditions(text, '0');
			}
		});
		final Button buttonEqual = new Button(composite, SWT.PUSH);
		buttonEqual.setLayoutData(dataThird);
		buttonEqual.setText("=");

		buttonEqual.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				conditions.checkConditions(text, '=');
				if (equalChecker == true) {
					OperationsClass operation = new OperationsClass();
					operation.decideOperations(text);
				}
			}
		});
	}

	static void updateText(Text text) {
		text.setText("");
		for (String text1 : textBox) {
			text.append(text1);
		}
		for (Character text2 : numbers) {
			text.append(text2.toString());
		}
	}

	void createUI() {
		Display display = new Display();

		Shell shell = new Shell(display);
		shell.setText("Calculator");
		shell.setSize(300, 400);
		shell.setLayout(new FillLayout());

		Composite composite = new Composite(shell, SWT.NONE);
		GridLayout layout = new GridLayout(4, true);
		composite.setLayout(layout);

		createButtonsUI(composite);

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
}