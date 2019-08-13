package rcpbook.view;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import rcpbook.contacts.ContactsManager;
import rcpbook.contacts.ContactsModel;
import rcpbook.enums.ContactEnum;
import rcpbook.table.ContactsFilter;
import rcpbook.table.MyViewerComparator;

public class ContactsView extends ViewPart
{
    public static final String ID = "RCPBook.view";

    private TableViewer tableViewer;
    private ContactsModel model = new ContactsModel();

    private ContactsFilter contactFilter = new ContactsFilter();
    private MyViewerComparator comparator;
    private ContactEnum temporary = ContactEnum.ID;
    private static List<TableViewerColumn> tableCollums = new ArrayList<TableViewerColumn>();
    int i = 0;

    @Override
    public void createPartControl(Composite parent)
    {

	parent.setLayout(new GridLayout(2, false));
	createTableUI(parent);

	getSite().setSelectionProvider(tableViewer);
    }

    public void createTableUI(Composite parent)
    {

	tableViewer = new TableViewer(parent,
		SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
	
	
	createFilter(parent);

	addDoubleClickListner();

	// vTools.assignContactsViewer(tableViewer);

	tableViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, SWT.FILL));
	tableViewer.setContentProvider(ArrayContentProvider.getInstance());
	tableViewer.setInput(model.getElements());

	tableViewer.setLabelProvider(new InnerLabelProvider());// (new ColumnLabelProvider());
	createTableColums(parent);

	
	comparator = new MyViewerComparator();
	tableViewer.setComparator(comparator);

	final Table table = tableViewer.getTable();
	table.setHeaderVisible(true);
	table.setLinesVisible(true);
    }

    public class InnerLabelProvider implements ITableLabelProvider
    {

	@Override
	public void addListener(ILabelProviderListener listener)
	{
	    // TODO Auto-generated method stub

	}

	@Override
	public void dispose()
	{
	    // TODO Auto-generated method stub

	}

	@Override
	public boolean isLabelProperty(Object element, String property)
	{
	    // TODO Auto-generated method stub
	    return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener)
	{
	    // TODO Auto-generated method stub

	}

	@Override
	public Image getColumnImage(Object element, int columnIndex)
	{
	    // TODO Auto-generated method stub
	    return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex)
	{
//	    0 pentru test macar...
//	    tableCollums.get(0).setLabelProvider(new ColumnLabelProvider()
//	    {
//
//	    });

	    if (element instanceof ContactsManager)
	    {
		// ContactEnum tableContent = temporary;

		ContactsManager contactMgr = (ContactsManager) element;

		switch (columnIndex)
		    {
		    case 0:
			return contactMgr.getID();
		    case 1:
			return contactMgr.getFirstName();
		    case 2:
			return contactMgr.getLastName();
		    case 3:
			return contactMgr.getAddress().getStreet();
		    case 4:
			return contactMgr.getPhone();
		    case 5:
			return contactMgr.getEmail();
		    default:
			// System.out.println("Unimplemented thing");
			break;
		    }
	    }

	    // TODO Auto-generated method stub
	    return null;
	}

    }

    private void createFilter(Composite parent)
    {
	tableViewer.addFilter(contactFilter);

	Label searchLabel = new Label(parent, SWT.NONE);
	searchLabel.setText("Search");

	final Text searchText = new Text(parent, SWT.BORDER | SWT.SEARCH);
	searchText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));

	searchText.addKeyListener(new KeyAdapter()
	{
	    public void keyReleased(KeyEvent ke)
	    {
		contactFilter.setSearchText(searchText.getText());

		tableViewer.refresh();
	    }
	});
    }

    private void createTableColums(final Composite parent)
    {
	int bounds = 100;
	InnerLabelProvider inner = new InnerLabelProvider();
	// TableViewerColumn column;

	for (ContactEnum title : ContactEnum.values())
	{
	    // column = createTableViewerColumn(title.getColumn(), bounds, i);
	    tableCollums.add(createTableViewerColumn(title.getColumn(), bounds, i));

//	    tableCollums.get(0).setLabelProvider(new ColumnLabelProvider()
//		    {
//	
//		    });
	    inner.getColumnText(model.getElements(), i);

	    
	    
	    
	    
	    
	    
	    
	    // ContactEnum tableContent = ContactEnum.ID;

//	    column.setLabelProvider(new ColumnLabelProvider()
//	    {
//
//		public String getText(Object element)
//		{
//		    if (element instanceof ContactsManager)
//		    {
//			ContactEnum tableContent = temporary;
//
//			ContactsManager contactMgr = (ContactsManager) element;
//
//			switch (temporary)
//			    {
//			    case ID:
//				temporary = tableContent.next();
//				return contactMgr.getID();
//			    case FIRSTNAME:
//				temporary = tableContent.next();
//				return contactMgr.getFirstName();
//			    case LASTNAME:
//				temporary = tableContent.next();
//				return contactMgr.getLastName();
//			    case STREET:
//				temporary = tableContent.next();
//				return contactMgr.getAddress().getStreet();
//			    case PHONENUMBER:
//				temporary = tableContent.next();
//				return contactMgr.getPhone();
//			    case EMAIL:
//				temporary = ContactEnum.ID;
//				return contactMgr.getEmail();
//			    default:
//				// System.out.println("Unimplemented thing");
//				break;
//			    }
//		    }
//		    return super.getText(element);
//		}
//	    });
	    i++;
	}
    }

    private TableViewerColumn createTableViewerColumn(String title, int bound, final int colNumber)
    {
	final TableViewerColumn viewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
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
		tableViewer.getTable().setSortDirection(dir);

		tableViewer.refresh();
	    }
	};
	return selectionAdapter;
    }

    private void addDoubleClickListner()
    {
	tableViewer.addDoubleClickListener(new IDoubleClickListener()
	{

	    @Override
	    public void doubleClick(DoubleClickEvent event)
	    {
		IStructuredSelection selection = tableViewer.getStructuredSelection();
		ContactsManager firstElement = (ContactsManager) selection.getFirstElement();

//		DetailesModel address = new DetailesModel();
//		address.addNewEntry(firstElement);

		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

		try
		{
		    if (activePage.findView(DetailesView.ID) == null)
		    {
			DetailesView showView = (DetailesView) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getActivePage().showView("RCPBook.Detailes");
			showView.setInput(firstElement);
		    } else
		    {

			((DetailesView) activePage.findView(DetailesView.ID)).setInput(firstElement);
		    }

		} catch (PartInitException e)
		{
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	    }
	});
    }

    @Override
    public void setFocus()
    {
    }

    public ContactsManager getSelectedItem()
    {
	final StructuredSelection selection = (StructuredSelection) tableViewer.getSelection();
	final ContactsManager selectedContact = (ContactsManager) selection.getFirstElement();

	return selectedContact;
    }

    public void refreshView()
    {
	tableViewer.refresh();
    }
}