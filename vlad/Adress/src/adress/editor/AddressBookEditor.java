package adress.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;

import Model.Address;
import Model.Contact;
import Model.ContactProvider;
import address.AddressContactsView;
import address.AddressDetailsView;

public class AddressBookEditor extends EditorPart {

	private Text firstNameText;
	private Text lastNameText;
	private Text phoneNumberText;
	private Text emailAdressText;
	private Text adressText;
	private Text countryText;
	private Text cityText;
	private Text postalCodeText;
	private boolean dirtyCheck;

	private Contact contact;

	ContactProvider contacts = ContactProvider.INSTANCE;

	public static final String ID = "adress.book.editor";

	public static void openEditor(Contact model) {
		AddressbookEditorInput input = new AddressbookEditorInput();

		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

		try {
			AddressBookEditor editor = (AddressBookEditor) activePage.getActiveEditor();

			if (editor != null) {

				editor.setModel(model);
				editor.updateWidgets();
			} else {
				AddressBookEditor createEditor = (AddressBookEditor) activePage.openEditor(input, AddressBookEditor.ID);
				createEditor.setModel(model);
				createEditor.updateWidgets();
			}

		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

	private void setModel(Contact model) {
		// TODO Auto-generated method stub
		this.contact = model;

	}

	public Contact getModel() {
		
		return this.contact;

	}
	 

	public AddressBookEditor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		if (contact != null)
			editState();
		else
			InsertState();
		setDirty(false);
	}

	@Override
	public void doSaveAs() {

	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		// TODO Auto-generated method stub
		if (!(input instanceof AddressbookEditorInput)) {
			throw new PartInitException("Invalid Input: Must be " + AddressbookEditorInput.class.getName());
		}
		setSite(site);
		setInput(input);

	}

	@Override
	public boolean isDirty() {
		if (dirtyCheck == true)
			return true;
		else
			return false;
	}

	public final void setDirty(boolean isDirty) {
		if (this.isDirty() == isDirty)
			return;
		this.dirtyCheck = isDirty;
		firePropertyChange(IWorkbenchPartConstants.PROP_DIRTY);
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	private void updateWidgets() {

		if (contact != null) {
			firstNameText.setText(contact.getFirstName());
			lastNameText.setText(contact.getLastName());
			countryText.setText(contact.getAddress().getCountry());
			cityText.setText(contact.getAddress().getCity());
			adressText.setText(contact.getAddress().getStreet());
			postalCodeText.setText(contact.getAddress().getPostalCode());
			phoneNumberText.setText(contact.getphoneNumber());
			emailAdressText.setText(contact.getEmailAdress());
		} else {
			firstNameText.setText("");
			lastNameText.setText("");
			countryText.setText("");
			cityText.setText("");
			adressText.setText("");
			postalCodeText.setText("");
			phoneNumberText.setText("");
			emailAdressText.setText("");
		}
		listenerForText(firstNameText);
		listenerForText(lastNameText);
		listenerForText(countryText);
		listenerForText(cityText);
		listenerForText(adressText);
		listenerForText(postalCodeText);
		listenerForText(phoneNumberText);
		listenerForText(emailAdressText);

	}

	private Text newText(Composite parent) {
		Text text = new Text(parent, SWT.BORDER | SWT.SEARCH);
		text.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
		return text;
	}

	private Label newLabel(Composite parent, String text) {
		Label label = new Label(parent, SWT.NONE);
		label.setText(text);
		return label;
	}

	private void createDetailsInput(Composite parent) {
		parent.setLayout(new GridLayout(2, false));

		newLabel(parent, "First Name");

		firstNameText = newText(parent);

		newLabel(parent, "Last Name");

		lastNameText = newText(parent);

		newLabel(parent, "Phone Number");

		phoneNumberText = newText(parent);

		newLabel(parent, "Adress");

		adressText = newText(parent);

		newLabel(parent, "E-mail");

		emailAdressText = newText(parent);

		newLabel(parent, "Country");

		countryText = newText(parent);

		newLabel(parent, "City");

		cityText = newText(parent);

		newLabel(parent, "Postal Code");

		postalCodeText = newText(parent);
	}

	@Override
	public void createPartControl(Composite parent) {
		// Add Code.
		// If you want to design with WindowBuilder Designer
		// Change code like: (Important!!!)

		createDetailsInput(parent);

		if (contact != null)
			editState();
		else
			InsertState();
		setDirty(false);
	}

	private void listenerForText(Text text) {

		text.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				// TODO Auto-generated method stub
				setDirty(true);
			}
		});
	}

	private void editState() {

		if (contact.getFirstName().equals(firstNameText.getText()) == false
				|| contact.getLastName().equals(lastNameText.getText()) == false
				|| contact.getphoneNumber().equals(phoneNumberText.getText()) == false
				|| contact.getEmailAdress().equals(emailAdressText.getText()) == false
				|| contact.getAddress().getCity().equals(cityText.getText()) == false
				|| contact.getAddress().getCountry().equals(countryText.getText()) == false
				|| contact.getAddress().getStreet().equals(adressText.getText()) == false
				|| contact.getAddress().getPostalCode().equals(postalCodeText.getText()) == false

		) {

			contacts.getContacts().get(contacts.getContacts().indexOf(contact)).setFirstName(firstNameText.getText());

			contacts.getContacts().get(contacts.getContacts().indexOf(contact)).setLastName(lastNameText.getText());

			contacts.getContacts().get(contacts.getContacts().indexOf(contact))
					.setPhoneNumber(phoneNumberText.getText());

			contacts.getContacts().get(contacts.getContacts().indexOf(contact))
					.setEmailAddress(emailAdressText.getText());

			contacts.getContacts().get(contacts.getContacts().indexOf(contact)).getAddress()
					.setCity(cityText.getText());

			contacts.getContacts().get(contacts.getContacts().indexOf(contact)).getAddress()
					.setCountry(countryText.getText());

			contacts.getContacts().get(contacts.getContacts().indexOf(contact)).getAddress()
					.setStreet(adressText.getText());

			contacts.getContacts().get(contacts.getContacts().indexOf(contact)).getAddress()
					.setPostalCode(postalCodeText.getText());

			refreshContactView();
		}

	}

	private void InsertState() {

		if (isDirty()) {
			contacts.addContacts(new Contact(firstNameText.getText(),
					new Address(countryText.getText(), cityText.getText(), adressText.getText(),
							postalCodeText.getText()),
					lastNameText.getText(), phoneNumberText.getText(), emailAdressText.getText()));
			refreshContactView();
		}

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	private void refreshContactView() {

		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		AddressContactsView view = (AddressContactsView) activePage.findView(AddressContactsView.ID);

		view.refresh();
		
	}

	public void closePage() {

		IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage page = workbenchWindow.getActivePage();
		page.closeEditor(this, true);

	}
	
	

}
