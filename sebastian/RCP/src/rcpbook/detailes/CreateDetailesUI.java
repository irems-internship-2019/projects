package rcpbook.detailes;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import rcpbook.contacts.DetailesModel;
import rcpbook.contacts.ContactsManager;
import rcpbook.view.ViewerTools;

public class CreateDetailesUI {

	private TableViewer detailesViewer;
	private ViewerTools vTools = new ViewerTools();
	private int i = 0, iterator = 0;

	public void detailesUI(Composite parent) {

		detailesViewer = new TableViewer(parent,
				SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);

		vTools.assignDetailesViewer(detailesViewer);

		createDetailesColums(parent);

		detailesViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, SWT.FILL));
		detailesViewer.setContentProvider(ArrayContentProvider.getInstance());

		DetailesModel tableImput = new DetailesModel();
		detailesViewer.setInput(tableImput.getAddresses());

		final Table table = detailesViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
	}

	private void createDetailesColums(final Composite parent) {
		String[] titles = { "ID", "First name", "Last name", "Country", "City", "Street", "Postal Code" };
		int bounds = 100;

		TableViewerColumn column;

		for (String title : titles) {
			column = createDetailesViewerColumn(title, bounds, i);
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
							return ((ContactsManager) element).getAddress().getCountry();
						case 4:
							iterator++;
							return ((ContactsManager) element).getAddress().getCity();
						case 5:
							iterator++;
							return ((ContactsManager) element).getAddress().getStreet();
						case 6:
							iterator = 0;
							return ((ContactsManager) element).getAddress().getPostalCode();
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
	}

	private TableViewerColumn createDetailesViewerColumn(String title, int bound, final int colNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(detailesViewer, SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		// column.addSelectionListener(getSelectionAdapter(column, colNumber));
		return viewerColumn;
	}
}
