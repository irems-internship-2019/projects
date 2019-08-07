package addressbook.editor;

import java.util.ArrayList;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import addressbook.persons.Contact;
import addressbook.persons.Contact.ContactElements;
import addressbook.view.AddressBookDetailsView;
import addressbook.view.AddressBookView;

public class AddressBookEditor extends EditorPart {
	public static final String ID = "addressbook.editor.addressbookeditor";
	private ContactElements persons = ContactElements.INSTANCE;
	private Contact recentlyElement = contactSelected.get(contactSelected.size() - 1);
	public static ArrayList<Contact> contactSelected = new ArrayList<Contact>();

	public enum TitlesOfColumns {
		ID("Id"),
		FIRSTNAME("First Name"),
		LASTNAME("Last Name"),
		COUNTRY("Country"),
		CITY("City"),
		STREET("Street"),
		POSTALCODE("Postal Code"),
		PHONE("Phone Number"),
		EMAIL("Email Address");

		private final String signOfElements;

		private TitlesOfColumns(String signOfElements) {
			this.signOfElements = signOfElements;
		}

		public String getSignOfElements() {
			return signOfElements;
		}
	}

	private void createLabel(Composite parent, String element) {
		Label label = new Label(parent, SWT.NONE);
		label.setText(element);
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));
	}

	public AddressBookEditor() {

	}

	public void clearContactSelected() {
		contactSelected.clear();
	}

	@Override
	public void doSave(IProgressMonitor monitor) {

	}

	@Override
	public void doSaveAs() {

	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		if (!(input instanceof AddressBookEditorInput)) {
			throw new PartInitException("Invalid Input: Must be " + AddressBookEditorInput.class.getName());
		}
		setSite(site);
		setInput(input);
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {
		AddressBookView object = new AddressBookView();
		AddressBookDetailsView secondObject = new AddressBookDetailsView();
		String checkNumbers = "[0-9]+";

		parent.setLayout(new FillLayout());
		Composite body = new Composite(parent, SWT.NONE);
		body.setLayout(new GridLayout(4, true));

		createLabel(body, TitlesOfColumns.ID.getSignOfElements());
		Text textId = new Text(body, SWT.BORDER);
		textId.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
		textId.setText(Integer.toString(contactSelected.get(contactSelected.size() - 1).getId()));

		createLabel(body, TitlesOfColumns.FIRSTNAME.getSignOfElements());
		Text textFirstName = new Text(body, SWT.BORDER);
		textFirstName.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
		textFirstName.setText(contactSelected.get(contactSelected.size() - 1).getFirstName());

		createLabel(body, TitlesOfColumns.LASTNAME.getSignOfElements());
		Text textLastName = new Text(body, SWT.BORDER);
		textLastName.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
		textLastName.setText(contactSelected.get(contactSelected.size() - 1).getLastName());

		createLabel(body, TitlesOfColumns.COUNTRY.getSignOfElements());
		Text textCountry = new Text(body, SWT.BORDER);
		textCountry.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
		textCountry.setText(contactSelected.get(contactSelected.size() - 1).getAddress().getCountry());

		createLabel(body, TitlesOfColumns.CITY.getSignOfElements());
		Text textCity = new Text(body, SWT.BORDER);
		textCity.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
		textCity.setText(contactSelected.get(contactSelected.size() - 1).getAddress().getCity());

		createLabel(body, TitlesOfColumns.STREET.getSignOfElements());
		Text textStreet = new Text(body, SWT.BORDER);
		textStreet.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
		textStreet.setText(contactSelected.get(contactSelected.size() - 1).getAddress().getStreet());

		createLabel(body, TitlesOfColumns.POSTALCODE.getSignOfElements());
		Text textPostalCode = new Text(body, SWT.BORDER);
		textPostalCode.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
		textPostalCode.setText(contactSelected.get(contactSelected.size() - 1).getAddress().getPostal_code());

		createLabel(body, TitlesOfColumns.PHONE.getSignOfElements());
		Text textPhoneNumber = new Text(body, SWT.BORDER);
		textPhoneNumber.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
		textPhoneNumber.setText(Integer.toString(contactSelected.get(contactSelected.size() - 1).getPhoneNumber()));

		createLabel(body, TitlesOfColumns.EMAIL.getSignOfElements());
		Text textEmail = new Text(body, SWT.BORDER);
		textEmail.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
		textEmail.setText(contactSelected.get(contactSelected.size() - 1).getEmailAddress());

		Button button = new Button(body, SWT.PUSH);
		button.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, true, true, 1, 1));
		button.setText("OK");
		button.setFont(JFaceResources.getDialogFont());
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (textId.getText().matches(checkNumbers) && textId.getText().length() != 0
						&& textFirstName.getText().length() != 0 && textLastName.getText().length() != 0
						&& textCountry.getText().length() != 0 && textCity.getText().length() != 0
						&& textStreet.getText().length() != 0 && textPostalCode.getText().length() != 0
						&& textPhoneNumber.getText().matches(checkNumbers) && textPhoneNumber.getText().length() != 0
						&& textEmail.getText().length() != 0) {
					if (!textId.getText()
							.equals(Integer.toString(contactSelected.get(contactSelected.size() - 1).getId()))) {
						persons.getContacts().get(persons.getContacts().indexOf(recentlyElement))
								.setId(Integer.parseInt(textId.getText()));
						object.refresh();
					}
					if (!textFirstName.getText()
							.equals(contactSelected.get(contactSelected.size() - 1).getFirstName())) {
						persons.getContacts().get(persons.getContacts().indexOf(recentlyElement))
								.setFirstName(textFirstName.getText());
						object.refresh();
					}
					if (!textLastName.getText().equals(contactSelected.get(contactSelected.size() - 1).getLastName())) {
						persons.getContacts().get(persons.getContacts().indexOf(recentlyElement))
								.setLastName(textLastName.getText());
						object.refresh();
					}
					if (!textCountry.getText()
							.equals(contactSelected.get(contactSelected.size() - 1).getAddress().getCountry())) {
						persons.getContacts().get(persons.getContacts().indexOf(recentlyElement)).getAddress()
								.setCountry(textCountry.getText());
						object.refresh();
					}
					if (!textCity.getText()
							.equals(contactSelected.get(contactSelected.size() - 1).getAddress().getCity())) {
						persons.getContacts().get(persons.getContacts().indexOf(recentlyElement)).getAddress()
								.setCity(textCity.getText());
						object.refresh();
					}
					if (!textStreet.getText()
							.equals(contactSelected.get(contactSelected.size() - 1).getAddress().getStreet())) {
						persons.getContacts().get(persons.getContacts().indexOf(recentlyElement)).getAddress()
								.setStreet(textStreet.getText());
						object.refresh();
					}
					if (!textPostalCode.getText()
							.equals(contactSelected.get(contactSelected.size() - 1).getAddress().getPostal_code())) {
						persons.getContacts().get(persons.getContacts().indexOf(recentlyElement)).getAddress()
								.setPostal_code(textPostalCode.getText());
						object.refresh();
					}
					if (!textPhoneNumber.getText().equals(
							Integer.toString(contactSelected.get(contactSelected.size() - 1).getPhoneNumber()))) {
						persons.getContacts().get(persons.getContacts().indexOf(recentlyElement))
								.setPhoneNumber(Integer.parseInt(textPhoneNumber.getText()));
						object.refresh();
					}
					if (!textEmail.getText()
							.equals(contactSelected.get(contactSelected.size() - 1).getEmailAddress())) {
						persons.getContacts().get(persons.getContacts().indexOf(recentlyElement))
								.setEmailAddress(textEmail.getText());
						object.refresh();
					}
					secondObject.refresh();
				}
			}
		});
	}

	@Override
	public void setFocus() {

	}
}