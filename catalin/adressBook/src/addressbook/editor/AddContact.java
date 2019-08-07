package addressbook.editor;

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
import addressbook.editor.AddressBookEditor.TitlesOfColumns;
import addressbook.persons.Address;
import addressbook.persons.Contact;
import addressbook.persons.Contact.ContactElements;
import addressbook.view.AddressBookView;

public class AddContact extends EditorPart {
	public static final String ID = "addressbook.editor.addcontact";
	private Contact person;

	private void createLabel(Composite parent, String element) {
		Label label = new Label(parent, SWT.NONE);
		label.setText(element);
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));
	}

	public AddContact() {

	}

	@Override
	public void doSave(IProgressMonitor monitor) {

	}

	@Override
	public void doSaveAs() {

	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		if (!(input instanceof AddContactInput)) {
			throw new PartInitException("Invalid Input: Must be " + AddContactInput.class.getName());
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
		ContactElements persons = ContactElements.INSTANCE;
		AddressBookView object = new AddressBookView();
		String checkNumbers = "[0-9]+";

		parent.setLayout(new FillLayout());
		Composite body = new Composite(parent, SWT.NONE);
		body.setLayout(new GridLayout(4, true));

		createLabel(body, TitlesOfColumns.ID.getSignOfElements());
		Text textId = new Text(body, SWT.BORDER);
		textId.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));

		createLabel(body, TitlesOfColumns.FIRSTNAME.getSignOfElements());
		Text textFirstName = new Text(body, SWT.BORDER);
		textFirstName.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));

		createLabel(body, TitlesOfColumns.LASTNAME.getSignOfElements());
		Text textLastName = new Text(body, SWT.BORDER);
		textLastName.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));

		createLabel(body, TitlesOfColumns.COUNTRY.getSignOfElements());
		Text textCountry = new Text(body, SWT.BORDER);
		textCountry.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));

		createLabel(body, TitlesOfColumns.CITY.getSignOfElements());
		Text textCity = new Text(body, SWT.BORDER);
		textCity.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));

		createLabel(body, TitlesOfColumns.STREET.getSignOfElements());
		Text textStreet = new Text(body, SWT.BORDER);
		textStreet.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));

		createLabel(body, TitlesOfColumns.POSTALCODE.getSignOfElements());
		Text textPostalCode = new Text(body, SWT.BORDER);
		textPostalCode.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));

		createLabel(body, TitlesOfColumns.PHONE.getSignOfElements());
		Text textPhoneNumber = new Text(body, SWT.BORDER);
		textPhoneNumber.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));

		createLabel(body, TitlesOfColumns.EMAIL.getSignOfElements());
		Text textEmail = new Text(body, SWT.BORDER);
		textEmail.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));

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
					person = new Contact(Integer.parseInt(textId.getText()), textFirstName.getText(),
							textLastName.getText(),
							new Address(textCountry.getText(), textCity.getText(), textStreet.getText(),
									textPostalCode.getText()),
							Integer.parseInt(textPhoneNumber.getText()), textEmail.getText());
					persons.getContacts().add(person);
					object.refresh();
				}
			}
		});
	}

	@Override
	public void setFocus() {

	}
}
