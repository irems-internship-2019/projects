package ui.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
//import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartConstants;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;

import ejb_Handler.DatabaseHandler;
//import model.contacts.AddressManager;
//import model.contacts.ContactsManager;
//import model.contacts.ContactsModel;
import model.enums.EditorLabelEnum;
import model.enums.ErrorsEnum;
import services.database.BookAddresses;
import services.database.BookContacts;
//import services.database.DatabaseServices;
import services.exceptions.MyCustomException;
import services.exceptions.MyUncheckedCustomExceptions;
//import rcpbook.editor.CreateEditorUI;
import ui.views.ContactsView;

public class EditorView extends EditorPart
{

    public static final String ID = "RCPBook.Create";

    private boolean dirty = false;

    // private ContactsModel newContact = new ContactsModel();
    public BookContacts contact;
    private Text[] textNames = new Text[8];
    private RegexValidation regex = new RegexValidation();

   private DatabaseHandler bean = new DatabaseHandler();
    //private DatabaseServices dbs = new  DatabaseServices();
    /*
     * //Not Working
     * properly################################################################# //
     * private Text[] textNames;
     * 
     * private Text firstName; private Text lastName; private Text country; private
     * Text city; private Text street; private Text postalCode; private Text
     * phoneNumber; private Text email;
     * 
     * private void addTextToArray() { textNames = new Text[] { firstName, lastName,
     * country, city, street, postalCode, phoneNumber, email }; }
     * 
     * @Override public void createPartControl(Composite parent) {
     * parent.setLayout(new GridLayout(2, false)); EditorUI(parent); }
     * 
     * public void EditorUI(Composite parent) {
     * 
     * createEditorFields(parent);
     * 
     * }
     * 
     * private void createEditorFields(Composite parent) { firstName = new
     * Text(parent, SWT.BORDER | SWT.SEARCH); lastName = new Text(parent, SWT.BORDER
     * | SWT.SEARCH); country = new Text(parent, SWT.BORDER | SWT.SEARCH); city =
     * new Text(parent, SWT.BORDER | SWT.SEARCH); street = new Text(parent,
     * SWT.BORDER | SWT.SEARCH); postalCode = new Text(parent, SWT.BORDER |
     * SWT.SEARCH); phoneNumber = new Text(parent, SWT.BORDER | SWT.SEARCH); email =
     * new Text(parent, SWT.BORDER | SWT.SEARCH);
     * 
     * 
     * addTextToArray();
     * 
     * int i = 0;
     * 
     * for (EditorLabelEnum text : EditorLabelEnum.values()) { new Label(parent,
     * SWT.NONE).setText(text.getText());
     * 
     * textNames[i].setLayoutData(new GridData(GridData.GRAB_HORIZONTAL |
     * GridData.HORIZONTAL_ALIGN_FILL)); textNames[i].addModifyListener(new
     * ModifyListener() {
     * 
     * @Override public void modifyText(ModifyEvent evt) { setDirty(true); } });
     * i++; } }
     * 
     * private void createText() {
     * 
     * }
     * 
     * private void editContact() {
     * 
     * // this is better if you delete the contact and insert it back at ID - 1
     * 
     * ContactsManager contact =
     * newContact.getElements().get(this.contact.getIntId() - 1);
     * 
     * contact.setFirstName(firstName.getText());
     * contact.setLastName(lastName.getText());
     * contact.getAddress().setCountry(country.getText());
     * contact.getAddress().setCity(city.getText());
     * contact.getAddress().setStreet(street.getText());
     * contact.getAddress().setPostalCode(postalCode.getText());
     * contact.setPhoneNumber(phoneNumber.getText());
     * contact.setEmail(email.getText());
     * 
     * // #########################################################################
     * 
     * setDirty(false); contact = null; refreshContactView(); }
     * 
     * private void newContact() { newContact.addNewContact(new ContactsManager(
     * firstName.getText(), lastName.getText(), new
     * AddressManager(country.getText(), city.getText(), street.getText(),
     * postalCode.getText()), phoneNumber.getText(), email.getText()));
     * 
     * setDirty(false);
     * 
     * refreshContactView(); }
     * 
     * public void updateWidgets() {
     * 
     * if (contact != null) { firstName.setText(contact.getFirstName());
     * lastName.setText(contact.getLastName());
     * country.setText(contact.getAddress().getCountry());
     * city.setText(contact.getAddress().getCity());
     * street.setText(contact.getAddress().getStreet());
     * postalCode.setText(contact.getAddress().getPostalCode());
     * phoneNumber.setText(contact.getPhone()); email.setText(contact.getEmail()); }
     * else { for (Text text : textNames) { text.setText(""); } } }
     * //*##########################################################################
     * ###########
     */

    @Override
    public void createPartControl(Composite parent)
    {
	parent.setLayout(new GridLayout(2, false));
	EditorUI(parent);
    }

    public void EditorUI(Composite parent)
    {

	createEditorFields(parent);

    }

    private void createEditorFields(Composite parent)
    {

	int i = 0;

	for (EditorLabelEnum text : EditorLabelEnum.values())
	{
	    new Label(parent, SWT.NONE).setText(text.getText());

	    textNames[i] = new Text(parent, SWT.BORDER | SWT.SEARCH);
	    textNames[i].setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
	    textNames[i].addModifyListener(new ModifyListener()
	    {
		@Override
		public void modifyText(ModifyEvent evt)
		{
		    setDirty(true);
		}
	    });
	    i++;
	}
    }

    private void editContact() throws MyCustomException
    {

	contact.setFirst_name(textNames[0].getText());
	contact.setLast_name(textNames[1].getText());
	contact.getAddress().setCountry(textNames[2].getText());
	contact.getAddress().setCity(textNames[3].getText());
	contact.getAddress().setStreet(textNames[4].getText());
	contact.getAddress().setPostal_code(textNames[5].getText());

	/* #########################-REGEX-################################# */
	if (regex.validatePhoneNumber(textNames[6].getText()))
	    contact.setPhone_number(textNames[6].getText());
	else
	    return;

	if (regex.validateEmail(textNames[7].getText()))
	    contact.setEmail(textNames[7].getText());
	else
	    return;
	/* #########################-REGEX-################################# */

	bean.GetBean().updateDatabaseContact(contact);

	setDirty(false);
	contact = null;

	refreshContactView();
    }

    private void newContact() throws MyCustomException
    {
	BookContacts newCont;
	newCont = new BookContacts( /* this dont matter */0l, textNames[0].getText(), textNames[1].getText(),
		new BookAddresses(textNames[2].getText(), textNames[3].getText(), textNames[4].getText(),
			textNames[5].getText()),
		textNames[6].getText(), textNames[7].getText());

	bean.GetBean().addNewContactToDatabase(newCont);

	setDirty(false);

	refreshContactView();
    }

    public void updateWidgets()
    {

	if (contact != null)
	{
	    textNames[0].setText(contact.getFirst_name());
	    textNames[1].setText(contact.getLast_name());
	    textNames[2].setText(contact.getAddress().getCountry());
	    textNames[3].setText(contact.getAddress().getCity());
	    textNames[4].setText(contact.getAddress().getStreet());
	    textNames[5].setText(contact.getAddress().getPostal_code());
	    textNames[6].setText(contact.getPhone_number());
	    textNames[7].setText(contact.getEmail());
	} else
	{
	    for (Text text : textNames)
	    {
		text.setText("");
	    }
	}
    }

    public void setModel(BookContacts model)
    {
	this.contact = model;
    }

    public static void openEditor(BookContacts model)
    {
	AddressBookNewContactImput input = new AddressBookNewContactImput();

	IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

	try
	{
	    EditorView addressBookEditor = (EditorView) activePage.getActiveEditor();

	    if (addressBookEditor != null)
	    {
		addressBookEditor.setModelAndWidget(model);
	    } else
	    {
		EditorView newAddress = (EditorView) activePage.openEditor(input, ID);
		newAddress.setModelAndWidget(model);
	    }

	} catch (PartInitException e)
	{
	    e.printStackTrace();
	}
    }

    public void setModelAndWidget(BookContacts model)
    {
	setModel(model);
	updateWidgets();

    }

    @Override
    public void setFocus()
    {
	// TODO Auto-generated method stub

    }

    @Override
    public void doSave(IProgressMonitor monitor)
    {

	try
	{
	    if (contact != null)
		editContact();
	    else
	    {
		newContact();
	    }
	} catch (Exception e)
	{
	    throw new MyUncheckedCustomExceptions(e, ErrorsEnum.BAD);
	}

    }

    @Override
    public void doSaveAs()
    {
	// TODO Auto-generated method stub

    }

    @Override
    public void init(IEditorSite site, IEditorInput input) throws PartInitException
    {
	if (!(input instanceof AddressBookNewContactImput))
	{
	    throw new PartInitException("Invalid Input: Must be " + AddressBookNewContactImput.class.getName());
	}
	setSite(site);
	setInput(input);
    }

    @Override
    public boolean isDirty()
    {

	if (dirty)
	    return true;
	else
	    return false;
    }

    public final void setDirty(boolean dirty)
    {

	if (this.isDirty() == dirty)
	    return;

	this.dirty = dirty;

//	Notify PROP_DIRTY changes to Workbench.
	firePropertyChange(IWorkbenchPartConstants.PROP_DIRTY);
    }

    @Override
    public boolean isSaveAsAllowed()
    {
	// TODO Auto-generated method stub
	return false;
    }

    public void refreshContactView()
    {
	IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
	ContactsView contactView = (ContactsView) activePage.findView(ContactsView.ID);

	contactView.refreshView();

    }
}
