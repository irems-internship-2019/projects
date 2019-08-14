package rcpbook.view;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;

import rcpbook.cmd.Delete;
import rcpbook.contacts.ContactsManager;
//import rcpbook.contacts.DetailesModel;
import rcpbook.detailes.MyDetailesComparator;
import rcpbook.enums.DetailesEnum;
import rcpbook.tableLabelProvider.DetailesLabelProvider;

public class DetailesView extends ViewPart
{
    public static final String ID = "RCPBook.Detailes";
    
    private TableViewer detailesViewer;

    private MyDetailesComparator comparator;

    private List<TableViewerColumn> tableCollums = new ArrayList<TableViewerColumn>();
    
    public DetailesView()
    {
	// TODO Auto-generated constructor stub
    }

    @Override
    public void createPartControl(Composite parent)
    {

	parent.setLayout(new GridLayout(2, false));
	detailesUI(parent);
    }

    public void setInput(ContactsManager contact)
    {
	List<ContactsManager> addresses = new ArrayList<ContactsManager>();

	addresses.add(contact);
	detailesViewer.setInput(addresses);
	Delete delete = new Delete();
	delete.earlySelection(contact);

	detailesViewer.refresh();
    }

    public void detailesUI(Composite parent)
    {

	detailesViewer = new TableViewer(parent,
		SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);

	// vTools.assignDetailesViewer(detailesViewer);

	detailesViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, SWT.FILL));

	comparator = new MyDetailesComparator();
	detailesViewer.setComparator(comparator);

	detailesViewer.setContentProvider(ArrayContentProvider.getInstance());
	// detailesViewer.setInput(tableImput.getAddresses());

	createDetailesColums(parent);
	detailesViewer.setLabelProvider(new DetailesLabelProvider(tableCollums));
	
	final Table table = detailesViewer.getTable();
	table.setHeaderVisible(true);
	table.setLinesVisible(true);
    }

    private void createDetailesColums(final Composite parent)
    {
	int bounds = 100, i = 0;

	for (DetailesEnum title : DetailesEnum.values())
	{
	    TableViewerColumn createTableViewerColumn = createDetailesViewerColumn(title.getColumn(), bounds, i);

	    tableCollums.add(createTableViewerColumn);

	    i++;
	}
    }

    private TableViewerColumn createDetailesViewerColumn(String title, int bound, final int colNumber)
    {
	final TableViewerColumn viewerColumn = new TableViewerColumn(detailesViewer, SWT.NONE);
	final TableColumn column = viewerColumn.getColumn();
	column.setText(title);
	column.setWidth(bound);
	column.setResizable(true);
	column.setMoveable(true);
	column.addSelectionListener(getSelectionAdapter(column, colNumber));

	return viewerColumn;
    }

    private SelectionAdapter getSelectionAdapter(final TableColumn column, final int index)
    {
	SelectionAdapter selectionAdapter = new SelectionAdapter()
	{
	    @Override
	    public void widgetSelected(SelectionEvent e)
	    {
		comparator.setColumn(index);
		int dir = comparator.getDirection();
		detailesViewer.getTable().setSortDirection(dir);

		detailesViewer.refresh();
	    }
	};
	return selectionAdapter;
    }

    @Override
    public void setFocus()
    {
	// TODO Auto-generated method stub

    }

}
