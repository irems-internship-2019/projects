package rcpbook.view;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import rcpbook.detailes.CreateDetailesUI;

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
