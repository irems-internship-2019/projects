package addressbook_nonsql.view;

import java.util.ArrayList;

import javax.inject.Inject;

import org.eclipse.jface.viewers.ArrayContentProvider;
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
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.part.ViewPart;
import addressbook_nonsql.comparator.ContactDetailsComparator;
import addressbook_nonsql.enums.AddressBookDetailsEnum;
import addressbook_nonsql.filter.ContactDetailsFilter;
import addressbook_nonsql.labels.InnerDetailsLabelProvider;
import addressbook_nonsql.persons.Contact;

public class AddressBookDetailsView extends ViewPart
{
    public static final String ID = "addressbook_nonsql.view.addressbookdetailsview";
    @Inject
    IWorkbench workbench;
    private ContactDetailsComparator comparator;
    private TableViewer viewer;
    private ArrayList<TableViewerColumn> tableColumns = new ArrayList<TableViewerColumn>();
    private ArrayList<Contact> contactElement;

    private void createViewer(Composite parent)
    {
	viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
	final Table table = viewer.getTable();
	table.setHeaderVisible(true);
	table.setLinesVisible(true);

	viewer.setContentProvider(new ArrayContentProvider());
	viewerLayout();
    }

    @Override
    public void createPartControl(Composite parent)
    {
	ContactDetailsFilter filter = new ContactDetailsFilter();
	parent.setLayout(new GridLayout(2, false));
	Label searchLabel = new Label(parent, SWT.NONE);
	searchLabel.setText("Search: ");
	Text searchText = new Text(parent, SWT.BORDER | SWT.SEARCH);
	searchText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));

	createViewer(parent);
	createTableColumns();
	viewer.setLabelProvider(new InnerDetailsLabelProvider(tableColumns));

	comparator = new ContactDetailsComparator();
	viewer.setComparator(comparator);

	searchText.addKeyListener(new KeyAdapter()
	{
	    public void keyReleased(KeyEvent ke)
	    {
		filter.setSearchText(searchText.getText());
		viewer.refresh();
	    }
	});
	viewer.addFilter(filter);
    }

    private TableViewerColumn createTableViewerColumn(String title, int bound, final int colNumber)
    {
	TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
	TableColumn column = viewerColumn.getColumn();
	column.setText(title);
	column.setWidth(bound);
	column.setResizable(true);
	column.setMoveable(true);
	column.addSelectionListener(getSelectionAdapter(column, colNumber));
	return viewerColumn;
    }

    private SelectionAdapter getSelectionAdapter(TableColumn column, int index)
    {
	SelectionAdapter selectionAdapter = new SelectionAdapter()
	{
	    @Override
	    public void widgetSelected(SelectionEvent e)
	    {
		ContactDetailsComparator comparator = (ContactDetailsComparator) viewer.getComparator();
		comparator.setColumn(index);

		int direction = comparator.getDirection();
		viewer.getTable().setSortDirection(direction);
		viewer.getTable().setSortColumn(column);
		viewer.refresh();
	    }
	};
	return selectionAdapter;
    }

    private void createTableColumns()
    {
	int count = 0;
	int bounds = 100;

	for (AddressBookDetailsEnum title : AddressBookDetailsEnum.values())
	{
	    TableViewerColumn createTableViewerColumn = createTableViewerColumn(title.getColumn(), bounds, count);

	    tableColumns.add(createTableViewerColumn);
	    count++;
	}
    }

    private void viewerLayout()
    {
	GridData gridData = new GridData();
	gridData.verticalAlignment = GridData.FILL;
	gridData.horizontalSpan = 2;
	gridData.grabExcessHorizontalSpace = true;
	gridData.grabExcessVerticalSpace = true;
	gridData.horizontalAlignment = GridData.FILL;
	viewer.getControl().setLayoutData(gridData);
    }

    public void refresh()
    {
	viewer.refresh();
    }

    public void setDetailsViewInput(Contact contact)
    {
	contactElement = new ArrayList<Contact>();
	contactElement.add(contact);

	viewer.setInput(contactElement);
    }

    public ArrayList<Contact> getDetailsViewContact()
    {
	return contactElement;
    }

    @Override
    public void setFocus()
    {
	viewer.getControl().setFocus();
    }
}