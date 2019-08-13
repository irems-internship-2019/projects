package rcpbook.view;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
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
import rcpbook.enums.ContactEnum;
import rcpbook.enums.DetailesEnum;

public class DetailesView extends ViewPart
{
    public static final String ID = "RCPBook.Detailes";
    // private CreateDetailesUI newDetailes = new CreateDetailesUI();

    private TableViewer detailesViewer;
  //  private DetailesModel tableImput = new DetailesModel();
    // private ViewerTools vTools = new ViewerTools();
    private int i = 0;
    private MyDetailesComparator comparator;
    private DetailesEnum temporary = DetailesEnum.ID;
  
    
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

	createDetailesColums(parent);

	detailesViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, SWT.FILL));

	comparator = new MyDetailesComparator();
	detailesViewer.setComparator(comparator);

	detailesViewer.setContentProvider(ArrayContentProvider.getInstance());
	//detailesViewer.setInput(tableImput.getAddresses());

	final Table table = detailesViewer.getTable();
	table.setHeaderVisible(true);
	table.setLinesVisible(true);
    }

    private void createDetailesColums(final Composite parent)
    {
	int bounds = 100;

	TableViewerColumn column;

	for (DetailesEnum title : DetailesEnum.values())
	{
	    column = createDetailesViewerColumn(title.getColumn(), bounds, i);

	    column.setLabelProvider(new ColumnLabelProvider()
	    {
		public String getText(Object element)
		{
		    if (element instanceof ContactsManager)
		    {
			DetailesEnum tableContent = temporary;

			ContactsManager contactMgr = (ContactsManager) element;

			switch (temporary)
			    {
			    case ID:
				temporary = tableContent.next();
				return contactMgr.getID();
			    case FIRSTNAME:
				temporary = tableContent.next();
				return contactMgr.getFirstName();
			    case LASTNAME:
				temporary = tableContent.next();
				return contactMgr.getLastName();
			    case COUNTRY:
				temporary = tableContent.next();
				return contactMgr.getAddress().getStreet();
			    case CITY:
				temporary = tableContent.next();
				return contactMgr.getPhone();
			    case STREET:
				temporary = tableContent.next();
				return contactMgr.getEmail();
			    case POSTALCODE:
				temporary = DetailesEnum.ID;
				return contactMgr.getEmail();
			    default:
				// System.out.println("Unimplemented thing");
				break;
			    }
		    }
		    return super.getText(element);
		}
	    });
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
