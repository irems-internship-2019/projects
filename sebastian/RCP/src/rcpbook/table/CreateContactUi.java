package rcpbook.table;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import rcpbook.contacts.AddressManager;
import rcpbook.contacts.AddressModel;
import rcpbook.contacts.ContactsManager;
import rcpbook.contacts.ContactsModel;

public class CreateContactUi {

	private TableViewer tableViewer;

	public void CreateTableUI(Composite parent) {

		tableViewer = new TableViewer(parent,
				SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);

		createTableColums(parent);

		tableViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, SWT.FILL));
		tableViewer.setContentProvider(ArrayContentProvider.getInstance());

		// new AddressModel(10);
		
		
		// test
		AddressModel address = new AddressModel();
		address.setNumber(10);
		address.getData();
		// test
		
		
		ContactsModel data = new ContactsModel(10);
		tableViewer.setInput(data.getElements());

		final Table table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
	}

	private void createTableColums(final Composite parent) {
		String[] titles = { "ID", "First name", "Last name", "Address", "Phone Number", "Email" };
		int bounds = 100;

		// List<Elements> persons = new ArrayList<Elements>();
		// for(int i=0; i<10; i++)
		// persons.add(Elements.createRandomPerson());

		TableViewerColumn column = createTableViewerColumn(titles[0], bounds, 0);
		column.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				if (element instanceof ContactsManager)
					return ((ContactsManager) element).getID();
				return super.getText(element);
			}
		});

		column = createTableViewerColumn(titles[1], bounds, 1);
		// column.setEditingSupport(new FirstNameEditingSupport(tableViewer));//u need
		// more of these
		column.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				if (element instanceof ContactsManager)
					return ((ContactsManager) element).getFirst();
				return super.getText(element);
			}

		});

		column = createTableViewerColumn(titles[2], bounds, 2);
		column.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				if (element instanceof ContactsManager)
					return ((ContactsManager) element).getSecond();
				return super.getText(element);
			}
		});

		column = createTableViewerColumn(titles[3], bounds, 3);
		column.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				if (element instanceof ContactsManager)
					return "" + ((ContactsManager) element).getAddress();
				return super.getText(element);
			}

		});

		column = createTableViewerColumn(titles[4], bounds, 4);
		column.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				if (element instanceof ContactsManager)
					return ((ContactsManager) element).getPhone();
				return super.getText(element);
			}
		});

		column = createTableViewerColumn(titles[5], bounds, 5);
		// column.setEditingSupport(new OptionEditingSupport(tableViewer));
		column.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				if (element instanceof ContactsManager)
					return ((ContactsManager) element).getEmail();
				return super.getText(element);
			}
		});
	}

	private TableViewerColumn createTableViewerColumn(String title, int bound, final int colNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		// column.addSelectionListener(getSelectionAdapter(column, colNumber));
		return viewerColumn;
	}

}
