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
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartConstants;
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
			
			  if(editor!=null)
			  {
				
			  editor.setModel(model);
			  editor.updateWidgets();
			  }
			  else 
			  {
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
		}
		else
		{
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

	@Override
	public void createPartControl(Composite parent) {
		// Add Code.
		// If you want to design with WindowBuilder Designer
		// Change code like: (Important!!!)

		parent.setLayout(new GridLayout(2, false));
		
		Label firstNameLabel = new Label(parent, SWT.NONE);
		firstNameLabel.setText("Last Name");
		firstNameText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		firstNameText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
		
		Label lastNameLabel = new Label(parent, SWT.NONE);
		lastNameLabel.setText("Last Name");
		lastNameText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		lastNameText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));

		Label phoneNumberLabel = new Label(parent, SWT.NONE);
		phoneNumberLabel.setText("Phone Number");
		phoneNumberText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		phoneNumberText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));

		Label adressLabel = new Label(parent, SWT.NONE);
		adressLabel.setText("Adress");
		adressText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		adressText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));

		Label emailAdressLabel = new Label(parent, SWT.NONE);
		emailAdressLabel.setText("E-mail");
		emailAdressText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		emailAdressText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));

		Label countryLabel = new Label(parent, SWT.NONE);
		countryLabel.setText("Country");
		countryText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		countryText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));

		Label cityLabel = new Label(parent, SWT.NONE);
		cityLabel.setText("City");
		cityText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		cityText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));

		Label postalCodeLabel = new Label(parent, SWT.NONE);
		postalCodeLabel.setText("Postal Code");
		postalCodeText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		postalCodeText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));

		if (contact != null)
			editState();
		else
			InsertState();
		setDirty(false);
	}

	private void createFirstNameLabel(Composite parent) {
		Label firstNameLabel = new Label(parent, SWT.NONE);
		firstNameLabel.setText("Last Name");
		firstNameText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		firstNameText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
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

		contacts.addContacts(new Contact(firstNameText.getText(),
				new Address(countryText.getText(), cityText.getText(), adressText.getText(), postalCodeText.getText()),
				lastNameText.getText(), phoneNumberText.getText(), emailAdressText.getText()));
		refreshContactView();

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
		

		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		 
		

	}
	

}
