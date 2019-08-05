package addressbook.view;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.part.ViewPart;

import addressbook.persons.ContactClass.Contact;
import addressbook.table.TableForAddressBook;

public class AddressBookView extends ViewPart {
	@Inject IWorkbench workbench;
	private static TableViewer viewer;
	TableForAddressBook tableCreater = new TableForAddressBook();
	
	private void createViewer(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		tableCreater.createColumns(parent, viewer);
		final Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		viewer.setContentProvider(new ArrayContentProvider());
		
		viewer.setInput(Contact.INSTANCE.getContacts());

		getSite().setSelectionProvider(viewer);

		tableCreater.viewerLayout(viewer);

	}
	
	public TableViewer getViewer() {
		return viewer;
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(2, false));
		Label searchLabel = new Label(parent, SWT.NONE);
		searchLabel.setText("Search: ");
		Text searchText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		searchText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));

		createViewer(parent);
		
		getSite().setSelectionProvider(viewer);
	}
	

	public void refresh() {
	    viewer.refresh();
	}

	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}
}
