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

import rcpbook.contacts.AddressModel;
import rcpbook.contacts.ContactsManager;
import rcpbook.view.ViewerTools;

public class CreateDetailesUI {

	private TableViewer detailesViewer;
	private ViewerTools vTools = new ViewerTools();
	private int i = 0, temporary = 0, checkthisS = 0;

	public void detailesUI(Composite parent) {

		detailesViewer = new TableViewer(parent,
				SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);

		vTools.assignDetailesViewer(detailesViewer);

		createDetailesColums(parent);

		detailesViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, SWT.FILL));
		detailesViewer.setContentProvider(ArrayContentProvider.getInstance());

		AddressModel tableImput = new AddressModel();
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
					System.out.println(checkthisS++);
					if (checkthisS > 7) {

						int iterator = temporary;
						if (element instanceof ContactsManager) {

							switch (iterator) {
							case 0:
								setIndex(0);
								return ((ContactsManager) element).getID();
							case 1:
								setIndex(1);
								return ((ContactsManager) element).getFirst();
							case 2:
								setIndex(2);
								return ((ContactsManager) element).getSecond();
							case 3:
								setIndex(3);
								return ((ContactsManager) element).getAddress().getCountry();
							case 4:
								setIndex(4);
								return ((ContactsManager) element).getAddress().getCity();
							case 5:
								setIndex(5);
								return ((ContactsManager) element).getAddress().getStreet();
							case 6:
								setIndex(6);
								return ((ContactsManager) element).getAddress().getPostalCode();
							default:
								setIndex(0);

								break;
							}
						}
					}
					return super.getText(element);
				}
			});

			i++;
		}

//		
//		
//					column.setLabelProvider(new ColumnLabelProvider() {
//		public String getText(Object element) {
//			
//			if (element instanceof ContactsManager) {
//              int iterator = 0;
//              for (@SuppressWarnings("unused") String title : titles) {
//				switch (iterator) {
//				case 0:
//					iterator++;
//					return ((ContactsManager) element).getID();
//				case 1:
//					iterator++;
//					return ((ContactsManager) element).getFirst();
//				case 2:
//					iterator++;
//					return ((ContactsManager) element).getSecond();
//				case 3:
//					iterator++;
//					return ((ContactsManager) element).getAddress().getCountry();
//				case 4:
//					iterator++;
//					return ((ContactsManager) element).getAddress().getCity();
//				case 5:
//					iterator++;
//					return ((ContactsManager) element).getAddress().getStreet();
//				case 6:
//					iterator++;
//					return ((ContactsManager) element).getAddress().getPostalCode();
//				default:
//					break;
//				}
//              }
//			}
//
//			return super.getText(element);
//		}
//	});

//		column = createDetailesViewerColumn(titles[0], bounds, 0);
//		column.setLabelProvider(new ColumnLabelProvider() {
//			public String getText(Object element) {
//				if (element instanceof ContactsManager)
//					return ((ContactsManager) element).getID();
//				return super.getText(element);
//			}
//		});
//
//		column = createDetailesViewerColumn(titles[1], bounds, 1);
//		// column.setEditingSupport(new FirstNameEditingSupport(tableViewer));//u need
//		// more of these
//		column.setLabelProvider(new ColumnLabelProvider() {
//			public String getText(Object element) {
//				if (element instanceof ContactsManager)
//					return ((ContactsManager) element).getFirst();
//				return super.getText(element);
//			}
//
//		});
//
//		column = createDetailesViewerColumn(titles[2], bounds, 2);
//		column.setLabelProvider(new ColumnLabelProvider() {
//			public String getText(Object element) {
//				if (element instanceof ContactsManager)
//					return ((ContactsManager) element).getSecond();
//				return super.getText(element);
//			}
//		});
//
//		column = createDetailesViewerColumn(titles[3], bounds, 3);
//		column.setLabelProvider(new ColumnLabelProvider() {
//			public String getText(Object element) {
//				if (element instanceof ContactsManager)
//					return "" + ((ContactsManager) element).getAddress().getCountry();
//				return super.getText(element);
//			}
//
//		});
//
//		column = createDetailesViewerColumn(titles[4], bounds, 4);
//		column.setLabelProvider(new ColumnLabelProvider() {
//			public String getText(Object element) {
//				if (element instanceof ContactsManager)
//					return ((ContactsManager) element).getAddress().getCity();
//				return super.getText(element);
//			}
//		});
//
//		column = createDetailesViewerColumn(titles[5], bounds, 5);
//		// column.setEditingSupport(new OptionEditingSupport(tableViewer));
//		column.setLabelProvider(new ColumnLabelProvider() {
//			public String getText(Object element) {
//				if (element instanceof ContactsManager)
//					return ((ContactsManager) element).getAddress().getStreet();
//				return super.getText(element);
//			}
//		});
//
//		column = createDetailesViewerColumn(titles[6], bounds, 6);
//		// column.setEditingSupport(new OptionEditingSupport(tableViewer));
//		column.setLabelProvider(new ColumnLabelProvider() {
//			public String getText(Object element) {
//				if (element instanceof ContactsManager)
//					return ((ContactsManager) element).getAddress().getPostalCode();
//				return super.getText(element);
//			}
//		});
	}

//	private ColumnLabelProvider setColumnLabelProvider(TableViewerColumn column) 
//	{
//		public String getText(Object element) {
//			if (element instanceof ContactsManager)
//				return ((ContactsManager) element).getID();
//			return super.getText(element);
//		}
//	}

	private void setIndex(int index) {

		index++;
		temporary = index;
//		if (checkthisS == 14)
//			checkthisS = 0;

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
