package adress.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
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
import adress.AdressDetailsView;
import adress.View;
import checker.SelectChecker;

public class AdressBookEditor extends EditorPart {

	private Text firstNameText;
	private Text lastNameText;
	private Text phoneNumberText;
	private Text emailAdressText;
	private Text adressText;
	private Text countryText;
	private Text cityText;
	private Text postalCodeText;
	private boolean dirtyCheck;
	SelectChecker check = new SelectChecker();

	private Contact contact;
	
	ContactProvider contacts = ContactProvider.INSTANCE;

	public static final String ID = "adress.book.editor";
	
	
	public static void openEditor(Contact model)
	{
		AdressbookEditorInput input = new AdressbookEditorInput();
		
		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		
		try 
		{
			AdressBookEditor addressBookEditor = (AdressBookEditor) activePage.openEditor(input, ID);
			addressBookEditor.setModel(model);
		addressBookEditor.updateWidgets();
		} 
		catch (PartInitException e) 
		{
			e.printStackTrace();
		}
	}

	private void setModel(Contact model) {
		// TODO Auto-generated method stub
		this.contact = model;
	}

	public AdressBookEditor() {
		// TODO Auto-generated constructor stub
	}



	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		// TODO Auto-generated method stub
		if (!(input instanceof AdressbookEditorInput)) {
			throw new PartInitException("Invalid Input: Must be " + AdressbookEditorInput.class.getName());
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
	
	
	private void updateWidgets()
	{
		
		firstNameText.setText(contact.getFirstName());
		lastNameText.setText(contact.getLastName());
		countryText.setText(contact.getAddress().getCountry());
		cityText.setText(contact.getAddress().getCity());
		adressText.setText(contact.getAddress().getStreet());
		postalCodeText.setText(contact.getAddress().getPostal_code());
		phoneNumberText.setText(contact.getphoneNumber());
		emailAdressText.setText(contact.getEmailAdress());

	}

	@Override
	public void createPartControl(Composite parent) {
		// Add Code.
		// If you want to design with WindowBuilder Designer
		// Change code like: (Important!!!)

		parent.setLayout(new GridLayout(2, false));
		Label firstNameLabel = new Label(parent, SWT.NONE);
		firstNameLabel.setText("First Name");
		firstNameText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		firstNameText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
		firstNameText.setText("first");

		parent.setLayout(new GridLayout(2, false));
		Label lastNameLabel = new Label(parent, SWT.NONE);
		lastNameLabel.setText("Last Name");
		lastNameText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		lastNameText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
		lastNameText.setText("last");

		parent.setLayout(new GridLayout(2, false));
		Label phoneNumberLabel = new Label(parent, SWT.NONE);
		phoneNumberLabel.setText("Phone Number");
		phoneNumberText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		phoneNumberText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
		phoneNumberText.setText("phone");

		parent.setLayout(new GridLayout(2, false));
		Label adressLabel = new Label(parent, SWT.NONE);
		adressLabel.setText("Adress");
		adressText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		adressText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
		adressText.setText("adress");

		parent.setLayout(new GridLayout(2, false));
		Label emailAdressLabel = new Label(parent, SWT.NONE);
		emailAdressLabel.setText("E-mail");
		emailAdressText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		emailAdressText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
		emailAdressText.setText("email");

		parent.setLayout(new GridLayout(2, false));
		Label countryLabel = new Label(parent, SWT.NONE);
		countryLabel.setText("Country");
		countryText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		countryText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
		countryText.setText("country");

		parent.setLayout(new GridLayout(2, false));
		Label cityLabel = new Label(parent, SWT.NONE);
		cityLabel.setText("City");
		cityText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		cityText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
		cityText.setText("city");

		parent.setLayout(new GridLayout(2, false));
		Label postalCodeLabel = new Label(parent, SWT.NONE);
		postalCodeLabel.setText("Postal Code");
		postalCodeText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		postalCodeText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
		postalCodeText.setText("postal");

		if (check.isSelected() == true)
			editState(parent);
		else
			InsertState(parent);

	}
	
	
	private void listenerForText(Text text) 
	{
		text.addKeyListener(new KeyListener() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
			}
	
			@Override
			public void keyPressed(KeyEvent e) 
			{
				setDirty(true);
			}
		});
	}
	
	private void editState(Composite parent) {

		View part = (View) getSite().getPage().findView(View.ID);
		AdressDetailsView detailsPart = (AdressDetailsView) getSite().getPage().findView(AdressDetailsView.ID);

		firstNameText.setText(part.getSelectedItem().getFirstName());
		listenerForText(firstNameText);

		lastNameText.setText(part.getSelectedItem().getLastName());
		listenerForText(lastNameText);

		phoneNumberText.setText(part.getSelectedItem().getphoneNumber());
		listenerForText(phoneNumberText);

		adressText.setText(part.getSelectedItem().getStreet());
		listenerForText(adressText);

		emailAdressText.setText(part.getSelectedItem().getEmailAdress());
		listenerForText(emailAdressText);

		countryText.setText(part.getSelectedItem().getCountry());
		listenerForText(countryText);

		cityText.setText("edit");
		postalCodeText.setText("edit");

		Button insertButton = new Button(parent, SWT.PUSH);
		insertButton.setText("Edit Contact");
		insertButton.setFont(JFaceResources.getDialogFont());
		insertButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {

				if (part.getSelectedItem().getFirstName().equals(firstNameText.getText()) == false
						|| part.getSelectedItem().getLastName().equals(lastNameText.getText()) == false
						|| part.getSelectedItem().getphoneNumber().equals(phoneNumberText.getText()) == false
						|| part.getSelectedItem().getStreet().equals(adressText.getText()) == false
						|| part.getSelectedItem().getEmailAdress().equals(emailAdressText.getText()) == false
						|| part.getSelectedItem().getCountry().equals(countryText.getText()) == false

				) {

					contacts.getContacts().get(contacts.getContacts().indexOf(part.getSelectedItem()))
							.setFirstName(firstNameText.getText());
					contacts.getContacts().get(contacts.getContacts().indexOf(part.getSelectedItem()))
							.setLastName(lastNameText.getText());
					contacts.getContacts().get(contacts.getContacts().indexOf(part.getSelectedItem()))
							.setAdress(adressText.getText());
					contacts.getContacts().get(contacts.getContacts().indexOf(part.getSelectedItem()))
							.setEmailAdress(emailAdressText.getText());
					contacts.getContacts().get(contacts.getContacts().indexOf(part.getSelectedItem()))
							.setCountry(countryText.getText());
				}
				setDirty(false);
				part.refresh();
				detailsPart.refresh();

				setDirty(false);
			}
		});


	}

	private void InsertState(Composite parent) {

		Button insertButton = new Button(parent, SWT.PUSH);
		insertButton.setText("New Contact");
		insertButton.setFont(JFaceResources.getDialogFont());
		insertButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {

				contacts.addContacts(new Contact(firstNameText.getText(),
						new Address(countryText.getText(), cityText.getText(), adressText.getText(),
								postalCodeText.getText()),
						lastNameText.getText(), phoneNumberText.getText(), emailAdressText.getText()));

				View part = (View) getSite().getPage().findView(View.ID);
				part.refresh();
 
			}
		});



	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
