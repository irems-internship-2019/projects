package adress;

import java.util.*;

import javax.inject.Inject;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.part.ViewPart;

import Model.Contact;


public class View extends ViewPart {
	public static final String ID = "Adress.view";

	@Inject IWorkbench workbench;
	
	private TableViewer viewer;
	
	@Override
	public void createPartControl(Composite parent) {
		GridLayout layout = new GridLayout(2, false);
		parent.setLayout(layout);
		createViewer(parent);
	}
	
	private void createViewer(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		createColumns(parent, viewer);
		final Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		viewer.setContentProvider(new ArrayContentProvider());
		// Get the content for the viewer, setInput will call getElements in the
		// contentProvider
		//!!!!---   viewer.setInput(ModelProvider.INSTANCE.getPersons());----  !!!!!!
		// make the selection available to other views
		getSite().setSelectionProvider(viewer);
	//	viewer.setInput();
		// Set the sorter for the table

		// Layout the viewer
		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalSpan = 2;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		viewer.getControl().setLayoutData(gridData);
	}

	
	
	private void createColumns(final Composite parent, final TableViewer viewer) {
		String[] titles = { "id","First name", "Last name", "adress", "phone Number", "email Adress" };
		int[] bounds = { 50, 100, 100, 100, 100,100 };
		
		TableViewerColumn idColumn = createTableViewerColumn(titles[0], bounds[0],0);
		idColumn.setLabelProvider(new ColumnLabelProvider(){
	        public String getText(Object element) {
                Contact p = (Contact) element;
                return p.getId();
            }
        });

		TableViewerColumn firstNameColumn = createTableViewerColumn(titles[1], bounds[1], 1);
		firstNameColumn.setLabelProvider(new ColumnLabelProvider() {
		    public String getText(Object element) {
                Contact p = (Contact) element;
                return p.getFirstName();
            }
        });

		
		TableViewerColumn lastNameColumn  = createTableViewerColumn(titles[2], bounds[2], 2);
		lastNameColumn.setLabelProvider(new ColumnLabelProvider()); 
		
		TableViewerColumn adressNameColumn  = createTableViewerColumn(titles[3], bounds[3], 3);
		adressNameColumn.setLabelProvider(new ColumnLabelProvider()); 
		
		TableViewerColumn phoneNumberColumn  = createTableViewerColumn(titles[4], bounds[4], 4);
		phoneNumberColumn.setLabelProvider(new ColumnLabelProvider()); 
		
		TableViewerColumn emailAdress  = createTableViewerColumn(titles[5], bounds[5], 5);
		emailAdress.setLabelProvider(new ColumnLabelProvider()); 
		
		 
	}
	 
	 
	
	private TableViewerColumn createTableViewerColumn(String title, int bound, final int colNumber) {
	    final TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
	    final TableColumn column = viewerColumn.getColumn();
	    column.setText(title);
	    column.setWidth(bound);
	    column.setResizable(true);
	    column.setMoveable(true);
	    return viewerColumn;
	}
	
	

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	
	public  void refresh() {
		
        viewer.refresh();		
	}  
	
}
