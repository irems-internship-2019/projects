package addressbook.view;

import javax.inject.Inject;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
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
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import addressbook.comparator.ContactComparator;
import addressbook.editor.AddressBookEditor;
import addressbook.filter.ContactFilter;
import addressbook.persons.Contact;
import addressbook.persons.Contact.ContactElements;
import addressbook.table.TableForAddressBook;
import addressbook.table.TableForAddressBookDetails;

public class AddressBookView extends ViewPart {
	public static final String ID = "addressbook.view.addressbookview";
	@Inject
	IWorkbench workbench;
	private ContactComparator comparator;
	private TableViewer viewer;
	TableForAddressBook tableCreater = new TableForAddressBook();

	private void createDoubleSelector() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) 
			{
				IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				
				try {
					activePage.showView(AddressBookDetailsView.ID);
				} catch (PartInitException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
//				IStructuredSelection selection = viewer.getStructuredSelection();
//				Contact firstElement = (Contact) selection.getFirstElement();
//				if (!TableForAddressBookDetails.contactDetails.contains(firstElement))
//					TableForAddressBookDetails.contactDetails.add(firstElement);
//
//				AddressBookDetailsView object = new AddressBookDetailsView();
//				object.refresh();

				
			}
		});
	}

	private void createSelector() {
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = viewer.getStructuredSelection();
				Contact firstElement = (Contact) selection.getFirstElement();

//				AddressBookEditor.recentlyElement = firstElement;

			}
		});
	}

	private void createViewer(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		tableCreater.createColumns(parent, viewer);
		final Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		viewer.setContentProvider(new ArrayContentProvider());

		viewer.setInput(ContactElements.INSTANCE.getContacts());

		getSite().setSelectionProvider(viewer);

		tableCreater.viewerLayout(viewer);

	}

	public TableViewer getViewer() {
		return viewer;
	}
	
	public Contact getSelectedItem()
	{
		final StructuredSelection selection = (StructuredSelection) viewer.getSelection();
		final Contact selectedContact = (Contact) selection.getFirstElement();
		
		return selectedContact;
	}

	@Override
	public void createPartControl(Composite parent) {
		ContactFilter filter = new ContactFilter();
		parent.setLayout(new GridLayout(2, false));
		Label searchLabel = new Label(parent, SWT.NONE);
		searchLabel.setText("Search: ");
		Text searchText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		searchText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));

		createViewer(parent);
		createDoubleSelector();
		createSelector();

		comparator = new ContactComparator();
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
