package addressbook.table;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import addressbook.comparator.ContactComparator;
import addressbook.persons.Contact;

public class TableForAddressBook
{
    private TableViewerColumn createTableViewerColumn(TableViewer viewer, String title, int bound, final int colNumber)
    {
	TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
	TableColumn column = viewerColumn.getColumn();
	column.setText(title);
	column.setWidth(bound);
	column.setResizable(true);
	column.setMoveable(true);
	column.addSelectionListener(getSelectionAdapter(viewer, column, colNumber));
	return viewerColumn;
    }

    private SelectionAdapter getSelectionAdapter(TableViewer viewer, TableColumn column, int index)
    {
	SelectionAdapter selectionAdapter = new SelectionAdapter()
	{
	    @Override
	    public void widgetSelected(SelectionEvent e)
	    {
		ContactComparator comparator = (ContactComparator) viewer.getComparator();
		comparator.setColumn(index);

		int direction = comparator.getDirection();
		viewer.getTable().setSortDirection(direction);
		viewer.getTable().setSortColumn(column);
		viewer.refresh();
	    }
	};
	return selectionAdapter;
    }

    public void viewerLayout(TableViewer viewer)
    {
	GridData gridData = new GridData();
	gridData.verticalAlignment = GridData.FILL;
	gridData.horizontalSpan = 2;
	gridData.grabExcessHorizontalSpace = true;
	gridData.grabExcessVerticalSpace = true;
	gridData.horizontalAlignment = GridData.FILL;
	viewer.getControl().setLayoutData(gridData);
    }

    public void createColumns(Composite parent, TableViewer viewer)
    {
	String[] titles =
	{ "Id", "First name", "Last name", "Address", "Phone number", "Email Address" };
	int[] bounds =
	{ 50, 100 };

	TableViewerColumn column = createTableViewerColumn(viewer, titles[0], bounds[0], 0);
	column.setLabelProvider(new ColumnLabelProvider()
	{
	    @Override
	    public String getText(Object element)
	    {
		Contact select = (Contact) element;
		return Integer.toString(select.getId());
	    }
	});

	column = createTableViewerColumn(viewer, titles[1], bounds[1], 1);
	column.setLabelProvider(new ColumnLabelProvider()
	{
	    @Override
	    public String getText(Object element)
	    {
		Contact select = (Contact) element;
		return select.getFirstName();
	    }
	});

	column = createTableViewerColumn(viewer, titles[2], bounds[1], 2);
	column.setLabelProvider(new ColumnLabelProvider()
	{
	    @Override
	    public String getText(Object element)
	    {
		Contact select = (Contact) element;
		return select.getLastName();
	    }
	});

	column = createTableViewerColumn(viewer, titles[3], bounds[1], 3);
	column.setLabelProvider(new ColumnLabelProvider()
	{
	    @Override
	    public String getText(Object element)
	    {
		Contact select = (Contact) element;
		return select.getAddress().getStreet();
	    }
	});

	column = createTableViewerColumn(viewer, titles[4], bounds[1], 4);
	column.setLabelProvider(new ColumnLabelProvider()
	{
	    @Override
	    public String getText(Object element)
	    {
		Contact select = (Contact) element;
		return select.getPhoneNumber();
	    }
	});

	column = createTableViewerColumn(viewer, titles[5], bounds[1], 5);
	column.setLabelProvider(new ColumnLabelProvider()
	{
	    @Override
	    public String getText(Object element)
	    {
		Contact select = (Contact) element;
		return select.getEmailAddress();
	    }
	});
    }
}