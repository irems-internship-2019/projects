package address;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import Filter.ContactFilter;
import Model.Contact;
import Model.ContactProvider;
import comparator.ContactComparator;
import enums.AdressEnums;
import enums.ContactsEnums;
import labelsprovider.AdressLabelProvider;
import labelsprovider.ContactsLabelProvider;

public class AddressDetailsView extends ViewPart {
	public static final String ID = "Adress.view2";
	ContactFilter contactFilter = new ContactFilter();

	private TableViewer viewer;
	private ContactComparator comparator;
	private ArrayList<Contact> listForDetailsView = new ArrayList<Contact>();
	private List<TableViewerColumn> tableCollums = new ArrayList<TableViewerColumn>();


	

	public AddressDetailsView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		GridLayout layout = new GridLayout(2, false);
		parent.setLayout(layout);
		Label searchLabel = new Label(parent, SWT.NONE);
		searchLabel.setText("Search: ");
		final Text searchText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		searchText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
		createViewer(parent);
		comparator = new ContactComparator();
		viewer.setComparator(comparator);

		searchText.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				contactFilter.setSearchText(searchText.getText());
				viewer.refresh();
			}

		});
		viewer.addFilter(contactFilter);
	}

	private void createViewer(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		createTableColums(parent);
		viewer.setLabelProvider(new AdressLabelProvider(tableCollums));
		final Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		viewer.setContentProvider(new ArrayContentProvider());
		getSite().setSelectionProvider(viewer);
		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalSpan = 2;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		viewer.getControl().setLayoutData(gridData);
	}

	public void setInputForViewer(Contact contact) {
		listForDetailsView.removeAll(listForDetailsView);
		listForDetailsView.add(contact);

		viewer.setInput(listForDetailsView);
	}

	 private void createTableColums(final Composite parent)
	    {
		int bounds = 100,i=0 ;
		 

		for (AdressEnums title : AdressEnums.values())
		{
		    TableViewerColumn createTableViewerColumn = createTableViewerColumn(title.getColumn(), bounds, i);

		    tableCollums.add(createTableViewerColumn);

		    i++;
		}
	    }
	

	private TableViewerColumn createTableViewerColumn(String title, int bound, final int colNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		column.addSelectionListener(getSelectionAdapter(column, colNumber));
		return viewerColumn;
	}

	private SelectionAdapter getSelectionAdapter(final TableColumn column, final int index) {
		SelectionAdapter selectionAdapter = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				comparator.setColumn(index);
				int dir = comparator.getDirection();
				viewer.getTable().setSortDirection(dir);
				viewer.getTable().setSortColumn(column);
				viewer.refresh();
			}
		};
		return selectionAdapter;
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	public void refresh() {
		// TODO Auto-generated method stub
		viewer.refresh();
	}

	public boolean equalityCheck(int id) {
		boolean check = false;

		for (Contact cnt : listForDetailsView) {

			if (cnt.getIdInt() == id)
				check = true;
		}

		if (check == true)
			return check;
		else
			return false;

	}

	public TableViewer getViewer() {
		// TODO Auto-generated method stub
		return viewer;
	}

	public void closeView() {
		IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage page = workbenchWindow.getActivePage();

		page.hideView(this);
	}

}
