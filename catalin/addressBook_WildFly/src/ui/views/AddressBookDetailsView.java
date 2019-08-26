package ui.views;

import java.util.ArrayList;

import javax.inject.Inject;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.part.ViewPart;

import models.enums.AddressBookDetailsEnum;
import models.persons.Contact;
import ui.labels.InnerDetailsLabelProvider;

public class AddressBookDetailsView extends ViewPart
{
    public static final String ID = "addressbook.view.addressbookdetailsview";
    @Inject
    IWorkbench workbench;
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
	createViewer(parent);
	createTableColumns();
	viewer.setLabelProvider(new InnerDetailsLabelProvider(tableColumns));
    }

    private TableViewerColumn createTableViewerColumn(String title, int bound, final int colNumber)
    {
	TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
	TableColumn column = viewerColumn.getColumn();
	column.setText(title);
	column.setWidth(bound);
	column.setResizable(true);
	column.setMoveable(true);
	return viewerColumn;
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