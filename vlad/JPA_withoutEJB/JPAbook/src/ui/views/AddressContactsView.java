package ui.views;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.eclipse.jface.viewers.ArrayContentProvider;
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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.IWorkbenchPage;
import dataBaseManager.DataBaseServices;
import model.Contact;
import ui.comparator.ContactComparator;
import ui.contactsilter.filter.ContactFilter;
import ui.filter.enums.ContactsEnums;
import ui.labelsprovider.ContactsLabelProvider;

import org.eclipse.jface.viewers.DoubleClickEvent;

public class AddressContactsView extends ViewPart {
	public static final String ID = "Adress.view";

	@Inject
	IWorkbench workbench;
	ContactFilter contactFilter = new ContactFilter();
	DataBaseServices dataManager = new DataBaseServices();

	private TableViewer viewer;
	private ContactComparator comparator;
	private List<TableViewerColumn> tableCollums = new ArrayList<TableViewerColumn>();

	private void createDoubleSelector() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {
				IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

				try {

					AddressDetailsView showView = (AddressDetailsView) activePage.showView(AddressDetailsView.ID);

					showView.setInputForViewer(getSelectedItem());
					showView.refresh();

				} catch (PartInitException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void createPartControl(Composite parent) {
		GridLayout layout = new GridLayout(2, false);
		parent.setLayout(layout);
		Label searchLabel = new Label(parent, SWT.NONE);
		searchLabel.setText("Search: ");
		final Text searchText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		searchText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
		createViewer(parent);
		createDoubleSelector();
		comparator = new ContactComparator();
		viewer.setComparator(comparator);
		
		dataManager.loadContacts();

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
		viewer.setLabelProvider(new ContactsLabelProvider(tableCollums));

		final Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		viewer.setContentProvider(new ArrayContentProvider());
		getSite().setSelectionProvider(viewer);

		viewer.setInput(dataManager.loadContacts());
		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalSpan = 2;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		viewer.getControl().setLayoutData(gridData);
	}

	private void createTableColums(final Composite parent) {
		int bounds = 100, i = 0;

		for (ContactsEnums title : ContactsEnums.values()) {
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

	public Contact getSelectedItem() {
		final StructuredSelection selection = (StructuredSelection) viewer.getSelection();
		final Contact selectedContact = (Contact) selection.getFirstElement();

		return selectedContact;
	}

	public TableViewer getViewer() {
		return viewer;
	}

	public void refresh() {
		viewer.setInput(dataManager.loadContacts());
		//viewer.refresh();

	}

}
