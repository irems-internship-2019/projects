package services.editor;

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
//import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartConstants;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;

import model.contacts.AddressManager;
import model.contacts.ContactsManager;
import model.contacts.ContactsModel;
import model.enums.EditorLabelEnum;
import services.database.DatabaseServices;
//import rcpbook.editor.CreateEditorUI;
import ui.views.ContactsView;

public class EditorView extends EditorPart
{

    public static final String ID = "RCPBook.Create";

    private boolean dirty = false;
    // private ViewerTools vTools = new ViewerTools();
    private ContactsModel newContact = new ContactsModel();
    public ContactsManager contact;
    private Text[] textNames = new Text[8];
    // private CreateDatabaseHashMap map = new CreateDatabaseHashMap();


    private DatabaseServices dbs = new DatabaseServices();

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

    private void editContact()
    {

	ContactsManager contact = newContact.getElements().get(newContact.getElements().indexOf(this.contact)
	/* map.getArrayToDatabaseMap().get(this.contact.getIntId()) */);

	contact.setFirstName(textNames[0].getText());
	contact.setLastName(textNames[1].getText());
	contact.getAddress().setCountry(textNames[2].getText());
	contact.getAddress().setCity(textNames[3].getText());
	contact.getAddress().setStreet(textNames[4].getText());
	contact.getAddress().setPostalCode(textNames[5].getText());
	contact.setPhoneNumber(textNames[6].getText());
	contact.setEmail(textNames[7].getText());

	dbs.updateDatabaseContact(newContact.getElements().get(newContact.getElements().indexOf(this.contact)
	/* map.getArrayToDatabaseMap().get(this.contact.getIntId()) */));

	setDirty(false);
	contact = null;

	refreshContactView();
    }

    private void newContact()
    {
	ContactsManager newCont;
	newCont = new ContactsManager(
		textNames[0].getText(), textNames[1].getText(), new AddressManager(textNames[2].getText(),
			textNames[3].getText(), textNames[4].getText(), textNames[5].getText()),
		textNames[6].getText(), textNames[7].getText());

	//newCont.setPrimaryKeyID(dbs.checkContactsMaxId() + 1);
	dbs.addNewContactToDatabase(newCont);
	ContactsModel newContact = new ContactsModel();
	newContact.clearContactsList();
	dbs.loadFromDatabase();
	//newContact.addNewContact(newCont);

	// map.createHashMap();

	setDirty(false);

	refreshContactView();
    }

    public void updateWidgets()
    {

	if (contact != null)
	{
	    textNames[0].setText(contact.getFirstName());
	    textNames[1].setText(contact.getLastName());
	    textNames[2].setText(contact.getAddress().getCountry());
	    textNames[3].setText(contact.getAddress().getCity());
	    textNames[4].setText(contact.getAddress().getStreet());
	    textNames[5].setText(contact.getAddress().getPostalCode());
	    textNames[6].setText(contact.getPhone());
	    textNames[7].setText(contact.getEmail());
	} else
	{
	    for (Text text : textNames)
	    {
		text.setText("");
	    }
	}
    }

    public void setModel(ContactsManager model)
    {
	this.contact = model;
    }

    public static void openEditor(ContactsManager model)
    {
	AddressBookNewContactImput input = new AddressBookNewContactImput();

	IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

	try
	{
	    EditorView addressBookEditor = (EditorView) activePage.getActiveEditor();

	    if (addressBookEditor != null)
	    {
		// activePage.openEditor(input, ID);
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

    public void setModelAndWidget(ContactsManager model)
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
//		final EditorPart editor = (EditorPart) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
//	
//
//		    editor.doSave(monitor);

	if (contact != null)
	    editContact();
	else
	{
	    newContact();

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

//			 //Notify PROP_DIRTY changes to Workbench.
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
