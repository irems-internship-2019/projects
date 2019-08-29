package ui.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

//import _EJB_TEST.Database_bean;
import ejb_Handler.DatabaseHandler;
import services.exceptions.MyCustomException;
import services.exceptions.MyUncheckedCustomExceptions;
import model.contacts.ContactsManager;
//import model.contacts.ContactsModel;
import model.enums.ContactEnum;
import model.enums.ErrorsEnum;
//import services.database.DatabaseServices;
//import ui.exceptions.MyCustomException;
//import ui.exceptions.MyUncheckedCustomExceptions;
import ui.lableProviders.ContactsLabelProvider;
import ui.utilities.ContactsFilter;
import ui.utilities.MyViewerComparator;

public class ContactsView extends ViewPart
{
    public static final String ID = "RCPBook.view";

    private TableViewer tableViewer;
    // private ContactsModel model = new ContactsModel();

    private ContactsFilter contactFilter = new ContactsFilter();
    private MyViewerComparator comparator;

    // private ContactEnum temporary = ContactEnum.ID;
    private List<TableViewerColumn> tableCollums = new ArrayList<TableViewerColumn>();
    // private CreateDatabaseHashMap map = new CreateDatabaseHashMap();
   private DatabaseHandler bean = new DatabaseHandler();
    //private DatabaseServices dbs = new DatabaseServices();

    @Override
    public void createPartControl(Composite parent)
    {

	parent.setLayout(new GridLayout(2, false));

//	try
//	{
//	   bean.GetBean().loadFromDatabase();
//	} catch (MyCustomException e)
//	{
//	    // TODO Auto-generated catch block
//	    e.printStackTrace();
//	}

	try
	{
	    createTableUI(parent);
	} catch (MyCustomException e)
	{
	    throw new MyUncheckedCustomExceptions(e, ErrorsEnum.BAD);

	}
	getSite().setSelectionProvider(tableViewer);
    }

    public void createTableUI(Composite parent) throws MyCustomException
    {

	tableViewer = new TableViewer(parent,
		SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);

	createFilter(parent);

	addDoubleClickListner();

	createButtons(parent);

	// dbs.doSomethingJPA();

	// vTools.assignContactsViewer(tableViewer);

	tableViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, SWT.FILL));
	tableViewer.setContentProvider(ArrayContentProvider.getInstance());
	try
	{
	    tableViewer.setInput(bean.GetBean().loadFromDatabase());
	} catch (MyCustomException e)
	{
	    throw new MyCustomException(e, ErrorsEnum.BAD);
	}

	createTableColums(parent);
	tableViewer.setLabelProvider(new ContactsLabelProvider(tableCollums));

	comparator = new MyViewerComparator();
	tableViewer.setComparator(comparator);

	final Table table = tableViewer.getTable();
	table.setHeaderVisible(true);
	table.setLinesVisible(true);
    }

    private void createTableColums(final Composite parent)
    {
	int bounds = 100, i = 0;

	for (ContactEnum title : ContactEnum.values())
	{
	    TableViewerColumn createTableViewerColumn = createTableViewerColumn(title.getColumn(), bounds, i);

	    tableCollums.add(createTableViewerColumn);

	    i++;
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

    private void createButtons(Composite parent)
    {
	buttonData("<<", parent);
	buttonData(">>", parent);
    }

    private void buttonData(String strNum, Composite parent)
    {
	Button btn = new Button(parent, SWT.PUSH);
	btn.setText(strNum);
	btn.setLayoutData(new GridData(1, 1, false, false));
	btn.addSelectionListener(makeButtonSelectionListner(strNum));
    }

    private SelectionListener makeButtonSelectionListner(String strNum)
    {
	return new SelectionAdapter()
	{
	    @Override
	    public void widgetSelected(SelectionEvent e)
	    {

		switch (strNum)
		    {
		    case ">>":
			// database.loadNextSetOfContacts();
			// model.clearContactsList();
			tableViewer.refresh();
			break;

		    case "<<":
			// database.loadPreviousSetOfContacts();
			// model.clearContactsList();
			tableViewer.refresh();
			break;

		    default:
			break;
		    }
	    }
	};
    }

    public void refreshView()
    {
	try
	{
	    tableViewer.setInput(bean.GetBean().loadFromDatabase());
	} catch (MyCustomException e)
	{

	    e.printStackTrace();
	}
	tableViewer.refresh();
    }
}