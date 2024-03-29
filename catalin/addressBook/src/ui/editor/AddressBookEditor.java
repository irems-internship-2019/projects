package ui.editor;

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

import models.persons.Address;
import models.persons.Contact;
import services.regex.Regex;
import services.server.ServerServices;
import ui.exceptions.ExceptionsDialogs;
import ui.views.AddressBookView;

public class AddressBookEditor extends EditorPart
{
    public static final String ID = "addressbook.editor.addressbookeditor";
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
    private Contact person;
    private ServerServices manager = new ServerServices();

    public static void openEditor(Contact model)
    {
	AddressBookEditorInput input = new AddressBookEditorInput();

	IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
	activePage.setEditorAreaVisible(true);

	try
	{
	    AddressBookEditor editor = (AddressBookEditor) activePage.getActiveEditor();

	    if (editor != null)
	    {
		editor.setModel(model);
		editor.updateWidgets();
	    } else
	    {
		AddressBookEditor addressBookEditor = (AddressBookEditor) activePage.openEditor(input, ID);
		addressBookEditor.setModel(model);
		addressBookEditor.updateWidgets();
	    }
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
	textFirstName = createText(body);

	createLabel(body, "Last Name");
	textLastName = createText(body);

	createLabel(body, "Country");
	textCountry = createText(body);

	createLabel(body, "City");
	textCity = createText(body);

	createLabel(body, "Street");
	textStreet = createText(body);

	createLabel(body, "Postal Code");
	textPostalCode = createText(body);

	createLabel(body, "Phone Number");
	textPhoneNumber = createText(body);

	createLabel(body, "E-mail");
	textEmail = createText(body);

	listenerForAllText();
    }

    private void createLabel(Composite parent, String element)
    {
	Label label = new Label(parent, SWT.NONE);
	label.setText(element);
	label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));
    }

    private Text createText(Composite parent)
    {
	Text text = new Text(parent, SWT.BORDER);
	text.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
	return text;
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
	} else
	{
	    textFirstName.setText("");
	    textLastName.setText("");
	    textCountry.setText("");
	    textCity.setText("");
	    textStreet.setText("");
	    textPostalCode.setText("");
	    textPhoneNumber.setText("");
	    textEmail.setText("");
	    setDirty(false);
	}
    }

    private void updateContentOfView()
    {
	if (isDirty == true)
	{
	    if (contact == null)
		addContact();
	    else
		editContact();
	}
    }

    private void checkRegex()
    {
	Regex regex = new Regex();
	textEmail.setText(regex.isValidEmail(textEmail.getText()));
	textPhoneNumber.setText(regex.isValidPhone(textPhoneNumber.getText()));
    }

    private Contact setContact()
    {
	return new Contact(textFirstName.getText(), textLastName.getText(),
		new Address(textCountry.getText(), textCity.getText(), textStreet.getText(), textPostalCode.getText()),
		textPhoneNumber.getText(), textEmail.getText());
    }

    private void addContact()
    {
	checkRegex();

	person = setContact();

	try
	{
	    manager.addServerContact(person);
	    refreshView();
	} catch (ExceptionsDialogs e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	setDirty(false);
    }

    private void editContact()
    {
	checkRegex();

	person = setContact();
	person.setId(contact.getId());

	try
	{
	    manager.editServerContact(person);
	    refreshView();
	} catch (ExceptionsDialogs e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
	setDirty(false);
    }

    private void setModel(Contact contact)
    {
	this.contact = contact;
    }

    public Contact getModel()
    {
	return contact;
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