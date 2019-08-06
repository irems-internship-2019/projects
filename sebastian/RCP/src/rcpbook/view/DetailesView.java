package rcpbook.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import rcpbook.detailes.CreateDetailesUI;
import rcpbook.table.CreateContactUi;

public class DetailesView extends ViewPart {
	public static final String ID = "RCPBook.Detailes";
	private CreateDetailesUI newDetailes = new CreateDetailesUI();

	public DetailesView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {

		parent.setLayout(new GridLayout(2, false));
		newDetailes.detailesUI(parent); 
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
