package ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import operations.OperationsOrder;
import restrictions.RestrictionConditions;

public class ResetPointEqualButtons extends UserInterface {
	public enum ResetPointEqual {
		CLEAR("Clear"),
		BACK("Back"),
		POINT("."),
		EQUAL("=");

		private final String signOfResetPointEqual;

		private ResetPointEqual(String signOfResetPointEqual) {
			this.signOfResetPointEqual = signOfResetPointEqual;
		}

		public String getSignOfResetPointEqual() {
			return signOfResetPointEqual;
		}

		public Character getCharacterOfResetPointEqual() {
			return signOfResetPointEqual.charAt(0);
		}
	}

	void createClearAndBack(Text text, Composite composite) {
		final Button buttonClear = new Button(composite, SWT.PUSH);
		buttonClear.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		buttonClear.setText(ResetPointEqual.CLEAR.getSignOfResetPointEqual());

		buttonClear.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				allTexts.clear();
				numbers.clear();
				updateText(text);
			}
		});
		final Button buttonBack = new Button(composite, SWT.PUSH);
		buttonBack.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		buttonBack.setText(ResetPointEqual.BACK.getSignOfResetPointEqual());

		buttonBack.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!numbers.isEmpty()) {
					numbers.remove(numbers.size() - 1);
					updateText(text);
				} else if (!allTexts.isEmpty()) {
					allTexts.remove(allTexts.size() - 1);
					updateText(text);
				}
			}
		});
	}

	void createPointAndEqual(Text text, Composite composite) {
		RestrictionConditions conditions = new RestrictionConditions();

		final Button buttonPunct = new Button(composite, SWT.PUSH);
		buttonPunct.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		buttonPunct.setText(ResetPointEqual.POINT.getSignOfResetPointEqual());

		buttonPunct.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (conditions.checkPoint() == false)
					conditions.insertElements(text, ResetPointEqual.POINT.getCharacterOfResetPointEqual());
			}
		});

		final Button buttonEqual = new Button(composite, SWT.PUSH);
		buttonEqual.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		buttonEqual.setText(ResetPointEqual.EQUAL.getSignOfResetPointEqual());

		buttonEqual.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				RestrictionConditions operatorsChecker = new RestrictionConditions();
				if (operatorsChecker.checkForOperators() == true || !numbers.isEmpty() && !numbers
						.get(numbers.size() - 1).equals(ResetPointEqual.POINT.getCharacterOfResetPointEqual())) {
					conditions.insertElements(text, ResetPointEqual.EQUAL.getCharacterOfResetPointEqual());
					OperationsOrder operation = new OperationsOrder();
					operation.decideOperations(text);
				}
			}
		});
	}
}