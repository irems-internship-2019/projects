package addressbook.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FillLayout;
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

import addressbook.persons.Address;
import addressbook.persons.Contact;
import addressbook.persons.Contact.ContactElements;
import addressbook.view.AddressBookView;

public class AddressBookEditor extends EditorPart 
{
	public static final String ID = "addressbook.editor.addressbookeditor";
	private ContactElements persons = ContactElements.INSTANCE;
	private boolean isDirty;

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
		} catch (PartInitException e) 
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

		createLabel(body, "First Name");
		textFirstName = new Text(body, SWT.BORDER);
		textFirstName.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));

		createLabel(body, "Last Name");
		textLastName = new Text(body, SWT.BORDER);
		textLastName.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));

		createLabel(body, "Country");
		textCountry = new Text(body, SWT.BORDER);
		textCountry.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));

		createLabel(body, "City");
		textCity = new Text(body, SWT.BORDER);
		textCity.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));

		createLabel(body, "Street");
		textStreet = new Text(body, SWT.BORDER);
		textStreet.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));

		createLabel(body, "Postal Code");
		textPostalCode = new Text(body, SWT.BORDER);
		textPostalCode.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));

		createLabel(body, "Phone Number");
		textPhoneNumber = new Text(body, SWT.BORDER);
		textPhoneNumber.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));

		createLabel(body, "E-mail");
		textEmail = new Text(body, SWT.BORDER);
		textEmail.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));

		listenerForAllText();
	}

	private void createLabel(Composite parent, String element) 
	{
		Label label = new Label(parent, SWT.NONE);
		label.setText(element);
		label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));
	}

	private void listenerForText(Text text) 
	{
		text.addModifyListener(new ModifyListener() 
		{
			@Override
			public void modifyText(ModifyEvent e) 
			{
				setDirty(true);
			}
		});
	}

	private void listenerForAllText() 
	{
		listenerForText(textFirstName);
		listenerForText(textLastName);
		listenerForText(textCountry);
		listenerForText(textCity);
		listenerForText(textStreet);
		listenerForText(textPostalCode);
		listenerForText(textPhoneNumber);
		listenerForText(textEmail);
	}

	private void updateWidgets() 
	{
		if (contact != null) 
		{
			textFirstName.setText(contact.getFirstName());
			textLastName.setText(contact.getLastName());
			textCountry.setText(contact.getAddress().getCountry());
			textCity.setText(contact.getAddress().getCity());
			textStreet.setText(contact.getAddress().getStreet());
			textPostalCode.setText(contact.getAddress().getPostal_code());
			textPhoneNumber.setText(contact.getPhoneNumber());
			textEmail.setText(contact.getEmailAddress());
			setDirty(false);
		}
	}

	private void updateContentOfView() 
	{
		if (isDirty == true) 
		{
			if (contact == null) 
			{
				conditionForAdd();
			} else
				conditionForEdit();
		}
	}

	private void conditionForAdd() 
	{
		Contact person;
		person = new Contact(textFirstName.getText(), textLastName.getText(),
				new Address(textCountry.getText(), textCity.getText(), textStreet.getText(), textPostalCode.getText()),
				textPhoneNumber.getText(), textEmail.getText());
		persons.getContacts().add(person);
		refreshView();
		setDirty(false);
	}

	private void conditionForEdit() 
	{
		Contact element = new Contact(textFirstName.getText(), textLastName.getText(),
				new Address(textCountry.getText(), textCity.getText(), textStreet.getText(), textPostalCode.getText()),
				textPhoneNumber.getText(), textEmail.getText());
		int idNumber = contact.getId();
		persons.getContacts().remove(persons.getContacts().indexOf(contact));
		persons.getContacts().add(element);
		persons.getContacts().get(persons.getContacts().indexOf(element)).setId(idNumber);
		contact = element;
		Contact.index--;
		refreshView();
		setDirty(false);
	}

	private void setModel(Contact model) 
	{
		this.contact = model;
	}

	@Override
	public void doSave(IProgressMonitor monitor) 
	{
		updateContentOfView();
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

	private void refreshView() 
	{
		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		AddressBookView addressBookView = (AddressBookView) activePage.findView(AddressBookView.ID);

		addressBookView.refresh();
	}
}