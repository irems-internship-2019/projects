package ui.views;

import java.util.ArrayList;
import java.util.Properties;

import javax.inject.Inject;
import javax.naming.InitialContext;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.StructuredSelection;
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
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import addressejb.Services;
import addressejb.ServicesRemote;
import models.enums.AddressBookEnum;
import models.persons.Contact;
import ui.comparator.ContactComparator;
import ui.filter.ContactFilter;
import ui.labels.InnerLabelProvider;

public class AddressBookView extends ViewPart
{
    public static final String ID = "addressbook.view.addressbookview";
    @Inject
    IWorkbench workbench; 
    private ContactComparator comparator;
    private TableViewer viewer;
    private ArrayList<TableViewerColumn> tableColumns = new ArrayList<TableViewerColumn>();
    
    private ServicesRemote createBean()
    {
	ServicesRemote bean = null;
	try{   
	Properties props = new Properties();
        props.put("java.naming.factory.url.pkgs","org.jboss.ejb.client.naming");
        InitialContext context = new InitialContext(props);

        String appName = "";        	 
        String moduleName = "EJBAddressBookMaven"; 
        String distinctName = "";        	 
        String beanName = Services.class.getSimpleName();        	 
        String interfaceName = ServicesRemote.class.getName();
        String name = "ejb:" + appName + "/" + moduleName + "/" +  distinctName    + "/" + beanName + "!" + interfaceName;
        bean = (ServicesRemote)context.lookup(name);
	}catch(Exception e){
		e.printStackTrace();
	}
	return bean;
    }
    
    private void createDoubleSelector()
    {
	viewer.addDoubleClickListener(new IDoubleClickListener()
	{
	    @Override
	    public void doubleClick(DoubleClickEvent event)
	    {
		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		AddressBookDetailsView addressBookDetailsView = (AddressBookDetailsView) activePage
			.findView(AddressBookDetailsView.ID);

		try
		{
		    if (addressBookDetailsView == null)
		    {
			AddressBookDetailsView openedAddressBookDetailsViewer = (AddressBookDetailsView) activePage
				.showView(AddressBookDetailsView.ID);
			openedAddressBookDetailsViewer.setDetailsViewInput(getSelectedItem());
			openedAddressBookDetailsViewer.refresh();
		    } else
		    {
			addressBookDetailsView.setDetailsViewInput(getSelectedItem());
			addressBookDetailsView.refresh();
		    }
		} catch (PartInitException e)
		{
		    MessageDialog.openError(Display.getDefault().getActiveShell(), "Error", "Can't open the view");
		}
	    }
	});
    }

    private void createViewer(Composite parent)
    {
	ServicesRemote beanElement = createBean();
	
	viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
	final Table table = viewer.getTable();
	table.setHeaderVisible(true);
	table.setLinesVisible(true);

	viewer.setContentProvider(new ArrayContentProvider());

	viewer.setInput(beanElement.getServerContacts());

	getSite().setSelectionProvider(viewer);

	viewerLayout();
    }

    public Contact getSelectedItem()
    {
	final StructuredSelection selection = (StructuredSelection) viewer.getSelection();
	final Contact selectedContact = (Contact) selection.getFirstElement();

	return selectedContact;
    }

    @Override
    public void createPartControl(Composite parent)
    {
	ContactFilter filter = new ContactFilter();
	parent.setLayout(new GridLayout(2, false));
	Label searchLabel = new Label(parent, SWT.NONE);
	searchLabel.setText("Search: ");
	Text searchText = new Text(parent, SWT.BORDER | SWT.SEARCH);
	searchText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));

	createViewer(parent);
	createDoubleSelector();
	createTableColumns();
	viewer.setLabelProvider(new InnerLabelProvider(tableColumns));

	comparator = new ContactComparator();
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

    private void createTableColumns()
    {
	int count = 0;
	int bounds = 100;

	for (AddressBookEnum title : AddressBookEnum.values())
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
	ServicesRemote beanElement = createBean();
	viewer.setInput(beanElement.getServerContacts());
	viewer.refresh();
    }

    @Override
    public void setFocus()
    {
	viewer.getControl().setFocus();
    }
}