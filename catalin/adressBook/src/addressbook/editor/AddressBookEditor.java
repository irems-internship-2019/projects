package addressbook.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
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
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartConstants;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;
import addressbook.persons.Address;
import addressbook.persons.Contact;
import addressbook.persons.Contact.ContactElements;
import addressbook.view.AddressBookDetailsView;
import addressbook.view.AddressBookView;

public class AddressBookEditor extends EditorPart 
{
	public static final String ID = "addressbook.editor.addressbookeditor";
	
	private ContactElements persons = ContactElements.INSTANCE;
	
	private boolean isDirty;
	
	private Text textId;
	private Text textFirstName;
	private Text textLastName;
	private Text textCountry;
	private Text textCity;
	private Text textStreet;
	private Text textPostalCode;
	private Text textPhoneNumber;
	private Text textEmail;

	private Contact contact;

	public static void openEditor(Contact model)
	{
		AddressBookEditorInput input = new AddressBookEditorInput();
		
		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		
		try 
		{
			AddressBookEditor addressBookEditor = (AddressBookEditor) activePage.openEditor(input, ID);
			addressBookEditor.setModel(model);
			addressBookEditor.updateWidgets();
		} 
		catch (PartInitException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void createPartControl(Composite parent) 
	{
		parent.setLayout(new FillLayout());
		Composite body = new Composite(parent, SWT.NONE);
		body.setLayout(new GridLayout(4, true));
	
		createLabel(body, "Id");
		textId = new Text(body, SWT.BORDER);
		textId.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
		listenerForText(textId);
	
		createLabel(body, "First Name");
		textFirstName = new Text(body, SWT.BORDER);
		textFirstName.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
		listenerForText(textFirstName);
	
		createLabel(body, "Last Name");
		textLastName = new Text(body, SWT.BORDER);
		textLastName.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
		listenerForText(textLastName);
	
		createLabel(body, "Country");
		textCountry = new Text(body, SWT.BORDER);
		textCountry.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
		listenerForText(textCountry);
	
		createLabel(body, "City");
		textCity = new Text(body, SWT.BORDER);
		textCity.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
		listenerForText(textCity);
	
		createLabel(body, "Street");
		textStreet = new Text(body, SWT.BORDER);
		textStreet.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
		listenerForText(textStreet);
	
		createLabel(body, "Postal Code");
		textPostalCode = new Text(body, SWT.BORDER);
		textPostalCode.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
		listenerForText(textPostalCode);
	
		createLabel(body, "Phone Number");
		textPhoneNumber = new Text(body, SWT.BORDER);
		textPhoneNumber.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
		listenerForText(textPhoneNumber);
	
		createLabel(body, "E-mail");
		textEmail = new Text(body, SWT.BORDER);
		textEmail.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
		listenerForText(textEmail);
	}

	private void createLabel(Composite parent, String element) 
	{
		Label label = new Label(parent, SWT.NONE);
		label.setText(element);
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));
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
	
	private void updateWidgets()
	{
		textId.setText(Integer.toString(contact.getId()));
		textFirstName.setText(contact.getFirstName());
		textLastName.setText(contact.getLastName());
		textCountry.setText(contact.getAddress().getCountry());
		textCity.setText(contact.getAddress().getCity());
		textStreet.setText(contact.getAddress().getStreet());
		textPostalCode.setText(contact.getAddress().getPostal_code());
		textPhoneNumber.setText(Integer.toString(contact.getPhoneNumber()));
		textEmail.setText(contact.getEmailAddress());
	}

	
//	private boolean principalCondition() 
//	{
//		String checkNumbers = "[0-9]+";
//		if (textId.getText().matches(checkNumbers) && textId.getText().length() != 0
//				&& textFirstName.getText().length() != 0 && textLastName.getText().length() != 0
//				&& textCountry.getText().length() != 0 && textCity.getText().length() != 0
//				&& textStreet.getText().length() != 0 && textPostalCode.getText().length() != 0
//				&& textPhoneNumber.getText().matches(checkNumbers) && textPhoneNumber.getText().length() != 0
//				&& textEmail.getText().length() != 0)
//			return true;
//		else
//			return false;
//	}

//	private void conditionForAdd() {
//		Contact person;
//		if (principalCondition() == true) {
//			person = new Contact(Integer.parseInt(textId.getText()), textFirstName.getText(), textLastName.getText(),
//					new Address(textCountry.getText(), textCity.getText(), textStreet.getText(),
//							textPostalCode.getText()),
//					Integer.parseInt(textPhoneNumber.getText()), textEmail.getText());
//			persons.getContacts().add(person);
//			object.refresh();
//			setDirty(false);
//		}
//	}

	private void conditionForEdit() 
	{
//		AddressBookDetailsView secondObject = new AddressBookDetailsView();

		contact = new Contact(id, firstName, lastName, address, phoneNumber, emailAddress)
		
		if (principalCondition() == true) 
		{
			if (!textId.getText().equals(Integer.toString(recentlyElement.getId()))) {
				persons.getContacts().get(persons.getContacts().indexOf(recentlyElement))
						.setId(Integer.parseInt(textId.getText()));
				object.refresh();
			}
			if (!textFirstName.getText().equals(recentlyElement.getFirstName())) {
				persons.getContacts().get(persons.getContacts().indexOf(recentlyElement))
						.setFirstName(textFirstName.getText());
				object.refresh();
			}
			if (!textLastName.getText().equals(recentlyElement.getLastName())) {
				persons.getContacts().get(persons.getContacts().indexOf(recentlyElement))
						.setLastName(textLastName.getText());
				object.refresh();
			}
			if (!textCountry.getText().equals(recentlyElement.getAddress().getCountry())) {
				persons.getContacts().get(persons.getContacts().indexOf(recentlyElement)).getAddress()
						.setCountry(textCountry.getText());
				object.refresh();
			}
			if (!textCity.getText().equals(recentlyElement.getAddress().getCity())) {
				persons.getContacts().get(persons.getContacts().indexOf(recentlyElement)).getAddress()
						.setCity(textCity.getText());
				object.refresh();
			}
			if (!textStreet.getText().equals(recentlyElement.getAddress().getStreet())) {
				persons.getContacts().get(persons.getContacts().indexOf(recentlyElement)).getAddress()
						.setStreet(textStreet.getText());
				object.refresh();
			}
			if (!textPostalCode.getText().equals(recentlyElement.getAddress().getPostal_code())) {
				persons.getContacts().get(persons.getContacts().indexOf(recentlyElement)).getAddress()
						.setPostal_code(textPostalCode.getText());
				object.refresh();
			}
			if (!textPhoneNumber.getText().equals(Integer.toString(recentlyElement.getPhoneNumber()))) {
				persons.getContacts().get(persons.getContacts().indexOf(recentlyElement))
						.setPhoneNumber(Integer.parseInt(textPhoneNumber.getText()));
				object.refresh();
			}
			if (!textEmail.getText().equals(recentlyElement.getEmailAddress())) {
				persons.getContacts().get(persons.getContacts().indexOf(recentlyElement))
						.setEmailAddress(textEmail.getText());
				object.refresh();
			}
			secondObject.refresh();
			setDirty(false);
		}
	}

	private void setModel(Contact model) 
	{
		this.contact = model;
	}

	@Override
	public void doSave(IProgressMonitor monitor) 
	{

	}

	@Override
	public void doSaveAs() 
	{

	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException 
	{
		if (!(input instanceof AddressBookEditorInput)) 
		{
			throw new PartInitException("Invalid Input: Must be " + AddressBookEditorInput.class.getName());
		}
		
		setSite(site);
		setInput(input);
	}

	@Override
	public boolean isDirty() 
	{
		if (isDirty == true)
			return true;
		else
			return false;

	}

	public final void setDirty(boolean isDirty) 
	{
		if (this.isDirty() == isDirty)
			return;
		
		this.isDirty = isDirty;
		firePropertyChange(IWorkbenchPartConstants.PROP_DIRTY);
	}

	@Override
	public boolean isSaveAsAllowed() 
	{
		return false;
	}

	@Override
	public void setFocus() 
	{
	}
}