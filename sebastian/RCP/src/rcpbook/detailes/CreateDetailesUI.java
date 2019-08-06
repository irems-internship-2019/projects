package rcpbook.detailes;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import rcpbook.contacts.AddressModel;
import rcpbook.contacts.ContactsManager;
import rcpbook.detailes.DetailesManager;
import rcpbook.view.ViewerTools;
import rcpbook.contacts.ContactsModel;

public class CreateDetailesUI {
	
	private TableViewer detailesViewer;
	private ViewerTools vTools = new ViewerTools();
	
//	private String[] labelNames = { "First Name: ", "Last Name:", "Country: ", "City: ", "Street: ", "Postal Code",
//			"Phone Number: ", "Email address: " };

	public void detailesUI(Composite parent) {

		detailesViewer = new TableViewer(parent,
				SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);

		vTools.assignDetailesViewer(detailesViewer);
		
		createDetailesColums(parent);
		
		detailesViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, SWT.FILL));
		detailesViewer.setContentProvider(ArrayContentProvider.getInstance());
		
//		//ContactsModel data = new ContactsModel(10);
//		DisplaySelectedItem item = new DisplaySelectedItem();
//		detailesViewer.setInput(item.setDetailesArray());
////		createLabels(parent);
		
		AddressModel tableImput = new AddressModel();
		detailesViewer.setInput(tableImput.getAddresses());
		
//		ContactsModel tableImput = new ContactsModel();
//		detailesViewer.setInput(tableImput.getContacts());
		
		final Table table = detailesViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
	}
	
	private void createDetailesColums(final Composite parent) {
		String[] titles = { "ID", "First name", "Last name", "Country", "City", "Street","Postal Code" };
		int bounds = 100;

		// List<Elements> persons = new ArrayList<Elements>();
		// for(int i=0; i<10; i++)
		// persons.add(Elements.createRandomPerson());

		TableViewerColumn column = createDetailesViewerColumn(titles[0], bounds, 0);
		column.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				if (element instanceof ContactsManager)
					return ((ContactsManager) element).getID();
				return super.getText(element);
			}
		});

		column = createDetailesViewerColumn(titles[1], bounds, 1);
		// column.setEditingSupport(new FirstNameEditingSupport(tableViewer));//u need
		// more of these
		column.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				if (element instanceof ContactsManager)
					return ((ContactsManager) element).getFirst();
				return super.getText(element);
			}

		});

		column = createDetailesViewerColumn(titles[2], bounds, 2);
		column.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				if (element instanceof ContactsManager)
					return ((ContactsManager) element).getSecond();
				return super.getText(element);
			}
		});

		column = createDetailesViewerColumn(titles[3], bounds, 3);
		column.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				if (element instanceof ContactsManager)
					return "" + ((ContactsManager) element).getAddress().getCountry();
				return super.getText(element);
			}

		});

		column = createDetailesViewerColumn(titles[4], bounds, 4);
		column.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				if (element instanceof ContactsManager)
					return ((ContactsManager) element).getAddress().getCity();
				return super.getText(element);
			}
		});

		column = createDetailesViewerColumn(titles[5], bounds, 5);
		// column.setEditingSupport(new OptionEditingSupport(tableViewer));
		column.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				if (element instanceof ContactsManager)
					return ((ContactsManager) element).getAddress().getStreet();
				return super.getText(element);
			}
		});
		
		column = createDetailesViewerColumn(titles[6], bounds, 6);
		// column.setEditingSupport(new OptionEditingSupport(tableViewer));
		column.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				if (element instanceof ContactsManager)
					return ((ContactsManager) element).getAddress().getPostalCode();
				return super.getText(element);
			}
		});
	}
	
	private TableViewerColumn createDetailesViewerColumn(String title, int bound, final int colNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(detailesViewer, SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		//column.addSelectionListener(getSelectionAdapter(column, colNumber));
		return viewerColumn;
	}
}
