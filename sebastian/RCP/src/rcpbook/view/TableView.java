package rcpbook.view;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import rcpbook.table.CreateContactUi;

public class TableView extends ViewPart {
	public static final String ID = "RCPBook.view";
	private CreateContactUi createNewTable = new CreateContactUi();
	
//	@Inject IWorkbench workbench;
	

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(2, false));
		createNewTable.CreateTableUI(parent);
	}


	@Override
	public void setFocus() {
	}
}