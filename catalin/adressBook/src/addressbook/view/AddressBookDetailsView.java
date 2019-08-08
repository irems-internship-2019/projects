package addressbook.view;

import javax.inject.Inject;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.part.ViewPart;
import addressbook.comparator.ContactDetailsComparator;
import addressbook.filter.ContactDetailsFilter;
import addressbook.table.TableForAddressBookDetails;

public class AddressBookDetailsView extends ViewPart {
	public static final String ID = "addressbook.view.addressbookdetailsview";
	@Inject
	IWorkbench workbench;
	private ContactDetailsComparator comparator;
	private static TableViewer viewer;
	TableForAddressBookDetails tableCreater = new TableForAddressBookDetails();

	private void createViewer(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		tableCreater.createColumns(parent, viewer);
		final Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		viewer.setContentProvider(new ArrayContentProvider());

		viewer.setInput(TableForAddressBookDetails.contactDetails);

		getSite().setSelectionProvider(viewer);

		tableCreater.viewerLayout(viewer);

	}

	public TableViewer getViewer() {
		return viewer;
	}

	@Override
	public void createPartControl(Composite parent) {
		ContactDetailsFilter filter = new ContactDetailsFilter();
		parent.setLayout(new GridLayout(2, false));
		Label searchLabel = new Label(parent, SWT.NONE);
		searchLabel.setText("Search: ");
		Text searchText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		searchText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));

		createViewer(parent);

		comparator = new ContactDetailsComparator();
		viewer.setComparator(comparator);

		searchText.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				filter.setSearchText(searchText.getText());
				viewer.refresh();
			}
		});
		viewer.addFilter(filter);

		getSite().setSelectionProvider(viewer);
	}

	public void refresh() {
		viewer.refresh();
	}

	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}
}
