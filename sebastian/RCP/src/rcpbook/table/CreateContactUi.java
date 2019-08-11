package rcpbook.table;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import rcpbook.contacts.DetailesModel;
import rcpbook.contacts.ContactsManager;
import rcpbook.contacts.ContactsModel;
import rcpbook.view.DetailesView;
import rcpbook.view.ViewerTools;

public class CreateContactUi {

	private TableViewer tableViewer;
	private ContactsModel model = new ContactsModel();
	private ViewerTools vTools = new ViewerTools();
	private ContactsFilter contactFilter = new ContactsFilter();
	private MyViewerComparator comparator;
	private int iterator = 0;
	int i = 0;

	public void CreateTableUI(Composite parent) {

		tableViewer = new TableViewer(parent,
				SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);

		createTableColums(parent);

		createFilter();

		// addSingleClickListner();

		addDoubleClickListner();

		vTools.assignContactsViewer(tableViewer);

		tableViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, SWT.FILL));
		tableViewer.setContentProvider(ArrayContentProvider.getInstance());

		comparator = new MyViewerComparator();
		tableViewer.setComparator(comparator);

		Label searchLabel = new Label(parent, SWT.NONE);
		searchLabel.setText("Search");

		final Text searchText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		searchText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));

		searchText.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				contactFilter.setSearchText(searchText.getText());

				vTools.refreshContactsViewer();
			}
		});

		tableViewer.setInput(model.getElements());

		final Table table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
	}

	private void createFilter() {
		tableViewer.addFilter(contactFilter);
	}

	private void createTableColums(final Composite parent) {
		String[] columnTitles = { "ID", "First name", "Last name", "Street", "Phone Number", "Email" };

		int bounds = 100;

		TableViewerColumn column;

		for (String title : columnTitles) {
			column = createTableViewerColumn(title, bounds, i);
			column.setLabelProvider(new ColumnLabelProvider() {
				public String getText(Object element) {
					if (element instanceof ContactsManager) {

						switch (iterator) {
						case 0:
							iterator++;
							return ((ContactsManager) element).getID();
						case 1:
							iterator++;
							return ((ContactsManager) element).getFirst();
						case 2:
							iterator++;
							return ((ContactsManager) element).getSecond();
						case 3:
							iterator++;
							return ((ContactsManager) element).getAddress().getStreet();
						case 4:
							iterator++;
							return ((ContactsManager) element).getPhone();
						case 5:
							iterator = 0;
							return ((ContactsManager) element).getEmail();
						default:
							// code doesn't reach this
							iterator = 0;
							break;
						}
					}
					return super.getText(element);
				}
			});
			i++;
		}

		// int bounds = 100,i = 0;
//		TableViewerColumn colum;
//		
//		for(String title: columnTitles) 
//		{
//			
//		}
//		
//		
//		TableViewerColumn column = createTableViewerColumn(columnTitles[0], bounds, 0);
//		column.setLabelProvider(new ColumnLabelProvider() {
//			public String getText(Object element) {
//				if (element instanceof ContactsManager)
//					return ((ContactsManager) element).getID();
//				return super.getText(element);
//			}
//		});
//
//		column = createTableViewerColumn(columnTitles[1], bounds, 1);
//		column.setLabelProvider(new ColumnLabelProvider() {
//			public String getText(Object element) {
//				if (element instanceof ContactsManager)
//					return ((ContactsManager) element).getFirst();
//				return super.getText(element);
//			}
//
//		});
//
//		column = createTableViewerColumn(columnTitles[2], bounds, 2);
//		column.setLabelProvider(new ColumnLabelProvider() {
//			public String getText(Object element) {
//				if (element instanceof ContactsManager)
//					return ((ContactsManager) element).getSecond();
//				return super.getText(element);
//			}
//		});
//
//		column = createTableViewerColumn(columnTitles[3], bounds, 3);
//		column.setLabelProvider(new ColumnLabelProvider() {
//			public String getText(Object element) {
//				if (element instanceof ContactsManager)
//					return ((ContactsManager) element).getAddress().getStreet();
//				return super.getText(element);
//			}
//
//		});
//
//		column = createTableViewerColumn(columnTitles[4], bounds, 4);
//		column.setLabelProvider(new ColumnLabelProvider() {
//			public String getText(Object element) {
//				if (element instanceof ContactsManager)
//					return ((ContactsManager) element).getPhone();
//				return super.getText(element);
//			}
//		});
//
//		column = createTableViewerColumn(columnTitles[5], bounds, 5);
//		// column.setEditingSupport(new OptionEditingSupport(tableViewer));
//		column.setLabelProvider(new ColumnLabelProvider() {
//			public String getText(Object element) {
//				if (element instanceof ContactsManager)
//					return ((ContactsManager) element).getEmail();
//				return super.getText(element);
//			}
//		});
	}

	private TableViewerColumn createTableViewerColumn(String title, int bound, final int colNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
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
				tableViewer.getTable().setSortDirection(dir);

				vTools.refreshContactsViewer();
			}
		};
		return selectionAdapter;
	}

	private void addDoubleClickListner() {
		tableViewer.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {
				IStructuredSelection selection = tableViewer.getStructuredSelection();
				ContactsManager firstElement = (ContactsManager) selection.getFirstElement();

				DetailesModel address = new DetailesModel();
				address.addNewEntry(firstElement);

				IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

				try {
					if (activePage.findView(DetailesView.ID) == null)
						PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
								.showView("RCPBook.Detailes");
				} catch (PartInitException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				vTools.refreshDetailesView();
			}
		});
	}
}
